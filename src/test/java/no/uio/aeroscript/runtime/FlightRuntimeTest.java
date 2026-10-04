package no.uio.aeroscript.runtime;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import no.uio.aeroscript.antlr.AeroScriptLexer;
import no.uio.aeroscript.antlr.AeroScriptParser;
import no.uio.aeroscript.error.ThrowingErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightRuntimeTest {
    private static AeroScriptParser.ProgramContext mission(String actions) {
        var lexer = new AeroScriptLexer(CharStreams.fromString("Mission { " + actions + " }"));
        lexer.removeErrorListeners();
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);
        var parser = new AeroScriptParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(ThrowingErrorListener.INSTANCE);
        var program = parser.program();
        new TypeChecker(program).check();
        return program;
    }

    private static String run(String actions) {
        var output = new ByteArrayOutputStream();
        new FlightRuntime(new PrintStream(output)).run(mission(actions));
        return output.toString(StandardCharsets.UTF_8);
    }

    private static String finalState(String output) {
        String summary = output.lines().filter(line -> line.startsWith("[SUMMARY]")).findFirst().orElseThrow();
        return summary.substring(summary.indexOf("position="));
    }

    @Test void splitAscentsMatchOneContinuousAscentAtBatteryBoundary() {
        String direct = run("ascend by 800");
        String split = run("ascend by 799 ascend by 1");
        assertEquals(finalState(direct), finalState(split));
        assertTrue(split.contains("distance=800.00 m | battery=20.00%"));
    }

    @Test void splitRelativeMovesMatchOneContinuousMove() {
        assertEquals(finalState(run("move by 800")),
                finalState(run("move by 799 move by 1")));
    }

    @Test void intermediateWaypointDoesNotChangeBatteryForSameStraightRoute() {
        String direct = run("move to point (800, 0)");
        String split = run("move to point (799, 0) move to point (800, 0)");
        assertEquals(finalState(direct), finalState(split));
        assertTrue(split.contains("battery=20.00%"));
    }

    @Test void dockingIncludesBothHorizontalReturnAndDescent() {
        String direct = run("ascend by 100 move by 300 return to base");
        String split = run("ascend by 99 ascend by 1 move by 299 move by 1 return to base");
        assertEquals(finalState(direct), finalState(split));
        assertTrue(direct.contains("[DOCK] position=(0.0, 0.0) | altitude=0.0 m | battery=20.00%"));
        assertTrue(direct.contains("distance=800.00 m"));
    }

    @Test void zeroDistanceActionsConsumeNoEnergyAtTwentyPercent() {
        String direct = run("move to point (800, 0)");
        String stationary = run("move to point (800, 0) move to point (800, 0) move by 0 ascend by 0 descend to ground");
        assertEquals(finalState(direct), finalState(stationary));
        assertTrue(stationary.contains("actions=5"));
    }

    @Test void belowThresholdActionIsRejectedBeforeAnyMovement() {
        var output = new ByteArrayOutputStream();
        var runtime = new FlightRuntime(new PrintStream(output));
        var error = assertThrows(IllegalArgumentException.class,
                () -> runtime.run(mission("move by 800.0001")));
        assertTrue(error.getMessage().contains("Battery failsafe"));
        assertFalse(output.toString(StandardCharsets.UTF_8).contains("[MOVE]"));
        assertFalse(output.toString(StandardCharsets.UTF_8).contains("[SUMMARY]"));
    }

    @Test void rejectedMovementLeavesDistanceBatteryAndPositionUnchanged() {
        var output = new ByteArrayOutputStream();
        var runtime = new FlightRuntime(new PrintStream(output));
        assertThrows(IllegalArgumentException.class,
                () -> runtime.run(mission("move by 800 move by 0.0001")));
        output.reset();
        runtime.run(mission("move by 0"));
        String state = output.toString(StandardCharsets.UTF_8);
        assertTrue(state.contains("actions=2"));
        assertEquals("position=(800.0, 0.0) | altitude=0.0 m | distance=800.00 m | battery=20.00%",
                finalState(state));
    }
}
