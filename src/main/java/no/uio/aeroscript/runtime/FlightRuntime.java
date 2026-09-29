package no.uio.aeroscript.runtime;

import java.io.PrintStream;
import java.util.Locale;
import no.uio.aeroscript.antlr.AeroScriptParser;
import no.uio.aeroscript.type.Point;

/** Deterministic, sequential flight simulation. No hardware or external event input. */
public final class FlightRuntime {
    // Illustrative energy model: percentage points per metre of travel.
    private static final double ENERGY_PER_METRE = 0.1;
    private final PrintStream out;
    private double x, y, altitude, heading, distance;
    private double battery = 100;
    private int actions;

    public FlightRuntime(PrintStream out) { this.out = out; }

    public void run(AeroScriptParser.ProgramContext program) {
        // Reject unsupported control flow before executing any action.
        if (program.execution().size() != 1)
            throw new IllegalArgumentException("Simulation supports one sequential mission block.");
        var mission = program.execution(0);
        if (mission.declare != null)
            throw new IllegalArgumentException("Execution transitions are not supported by this simulator.");
        for (var statement : mission.statement()) {
            if (statement.action() == null || statement.action().expression() != null)
                throw new IllegalArgumentException("Simulation supports actions without reactions, nested blocks, or speed/duration modifiers.");
        }
        log("[MISSION] %s (simulation)%n", mission.define.getText());
        for (var statement : mission.statement()) execute(statement.action());
        log("[SUMMARY] Mission complete | actions=%d | position=(%.1f, %.1f) | altitude=%.1f m | distance=%.2f m | battery=%.2f%%%n",
                actions, x, y, altitude, distance, battery);
    }

    private void execute(AeroScriptParser.ActionContext action) {
        String name;
        double nextX = x, nextY = y, nextAltitude = altitude;
        if (action.acAscend() != null) {
            nextAltitude += nonnegative(action.acAscend().expression());
            name = "ASCEND";
        } else if (action.acMove() != null) {
            var move = action.acMove();
            if (move.TO() != null) {
                Point point = (Point) Expressions.compile(move.expression()).node().evaluate();
                nextX = point.getX(); nextY = point.getY();
            } else {
                double length = nonnegative(move.expression());
                nextX += length * Math.cos(Math.toRadians(heading));
                nextY += length * Math.sin(Math.toRadians(heading));
            }
            name = "MOVE";
        } else if (action.acTurn() != null) {
            var turn = action.acTurn();
            double angle = nonnegative(turn.expression());
            heading = ((heading + (turn.LEFT() != null ? angle : -angle)) % 360 + 360) % 360;
            name = "TURN";
        } else if (action.acDescend() != null) {
            var descend = action.acDescend();
            nextAltitude = descend.GROUND() != null ? 0 : altitude - nonnegative(descend.expression());
            if (nextAltitude < 0) throw new IllegalArgumentException("Cannot descend below ground.");
            name = "DESCEND";
        } else {
            nextX = 0; nextY = 0; nextAltitude = 0;
            name = "DOCK";
        }
        if (!Double.isFinite(nextX) || !Double.isFinite(nextY) || !Double.isFinite(nextAltitude))
            throw new IllegalArgumentException("Flight coordinates must be finite.");
        // Dock travels horizontally to base, then descends to ground.
        double travel = Math.hypot(nextX - x, nextY - y) + Math.abs(nextAltitude - altitude);
        double remaining = battery - travel * ENERGY_PER_METRE;
        if (remaining < 20)
            throw new IllegalArgumentException("Battery failsafe: action would leave less than 20%; simulation stopped.");
        x = nextX; y = nextY; altitude = nextAltitude;
        distance += travel; battery = remaining; actions++;
        log("[%s] position=(%.1f, %.1f) | altitude=%.1f m | battery=%.2f%%%n", name, x, y, altitude, battery);
    }

    private double nonnegative(AeroScriptParser.ExpressionContext expression) {
        double value = ((Number) Expressions.compile(expression).node().evaluate()).doubleValue();
        if (!Double.isFinite(value) || value < 0)
            throw new IllegalArgumentException("Action argument must be finite and nonnegative.");
        return value;
    }

    private void log(String format, Object... arguments) { out.printf(Locale.ROOT, format, arguments); }
}
