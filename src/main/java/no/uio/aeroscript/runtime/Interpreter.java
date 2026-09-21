package no.uio.aeroscript.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import no.uio.aeroscript.antlr.AeroScriptBaseVisitor;
import no.uio.aeroscript.antlr.AeroScriptParser;
import no.uio.aeroscript.ast.expr.Node;
import no.uio.aeroscript.ast.expr.NumberNode;
import no.uio.aeroscript.ast.expr.OperationNode;
import no.uio.aeroscript.ast.stmt.acAscend;
import no.uio.aeroscript.ast.stmt.acDescend;
import no.uio.aeroscript.ast.stmt.acDock;
import no.uio.aeroscript.ast.stmt.Event;
import no.uio.aeroscript.ast.stmt.Execution;
import no.uio.aeroscript.ast.stmt.acMove;
import no.uio.aeroscript.ast.stmt.Reaction;
import no.uio.aeroscript.ast.stmt.Statement;
import no.uio.aeroscript.ast.stmt.acMove;
import no.uio.aeroscript.type.Memory;
import no.uio.aeroscript.type.Point;
import no.uio.aeroscript.type.Range;

public class Interpreter extends AeroScriptBaseVisitor<Object> {
    // Instanser av viktige datastrukturer som brukes av interpreteren
    private final HashMap<Memory, Object> heap; // Lager for variabler og annen delt data
    private final Stack<Statement> stack; // Stack for å håndtere statements under kjøring
    private final HashMap<String, Runnable> listeners = new HashMap<>(); // Lyttere for ulike hendelser
    private Execution firstExecution; // Første Execution i programmet, for å starte programmet
    private boolean toStopBattery = false; // Flag for å sjekke lavt batteri
    private List<Execution> executionableList = new ArrayList<>(); // Liste over kjørbare Executions
    private final HashMap<String, Float> modifiers = new HashMap<>(); // Modifikatorer (som hastighet og tid) for handlinger

    // Konstruktor initialiserer heap og stack
    public Interpreter(HashMap<Memory, Object> heap, Stack<Statement> stack) {
        this.heap = heap;
        this.stack = stack;
    }

    // Setter opp den første Execution og legger til i variabelregisteret
    public void setFirstExecution(Execution firstExecution) {
        HashMap<String, Object> vars = (HashMap)this.heap.get(Memory.VARIABLES);
        vars.put("initial execution", firstExecution);
        this.firstExecution = firstExecution;
    }

    // Getter for hendelseslyttere
    public Map<String, Runnable> getListeners() {
        return this.listeners;
    }

    // Returnerer første Execution
    public Execution getFirstExecution() {
        return this.firstExecution;
    }

    // Henter gjeldende posisjon til objektet
    public Point getPosition() {
        assert this.heap.get(Memory.VARIABLES) instanceof HashMap;

        HashMap<String, Object> vars = (HashMap)this.heap.get(Memory.VARIABLES);
        return (Point)vars.get("current position");
    }

    // Beregner tilbakelagt distanse
    public float getDistanceTravelled() {
        assert this.heap.get(Memory.VARIABLES) instanceof HashMap;

        HashMap<String, Object> vars = (HashMap)this.heap.get(Memory.VARIABLES);
        return (Float)vars.get("distance travelled");
    }

    // Henter gjeldende batterinivå i prosent
    public float getBatteryLevel() {
        assert this.heap.get(Memory.VARIABLES) instanceof HashMap;

        HashMap<String, Object> vars = (HashMap)this.heap.get(Memory.VARIABLES);
        return (Float)vars.get("battery level") / (Float)vars.get("initial battery level") * 100.0F;
    }

    // Henter gjeldende høyde (altitude)
    public float getAltitude() {
        assert this.heap.get(Memory.VARIABLES) instanceof HashMap;

        HashMap<String, Object> vars = (HashMap)this.heap.get(Memory.VARIABLES);
        return (Float)vars.get("altitude");
    }

    // Sjekker batterinivå for å se om det er lavt
    private void checkBattery() {
        assert this.heap.get(Memory.VARIABLES) instanceof HashMap;

        HashMap<String, Object> vars = (HashMap)this.heap.get(Memory.VARIABLES);
        this.toStopBattery = (Boolean)vars.get("battery low");
    }

    // Besøker programmet, oppretter og utfører execution-sekvenser
    public Object visitProgram(AeroScriptParser.ProgramContext ctx) {
        Program program = new Program(this.heap);
        this.firstExecution = null;
        List<Execution> executions = new ArrayList<>();
        
        // Behandler hver Execution-blokk i programmet
        for (AeroScriptParser.ExecutionContext executionContext : ctx.execution()) {
            Execution execution = (Execution)this.visit(executionContext);
            program.addProcedure(execution);
            executions.add(execution);
        }

        // Kjører kjørbare (exec) Execution-blokker
        for (Execution execution : executions) {
            if (execution.getExec()) {
                program.run(execution.getName());
            }
        }

        return null;
    }

    // Håndterer og lagrer informasjon om Execution-blokken
    public Object visitExecution(AeroScriptParser.ExecutionContext ctx) {
        String define = ctx.define != null ? ctx.define.getText() : null;
        String declare = ctx.declare != null ? ctx.declare.getText() : null;
        List<Statement> statements = new ArrayList<>();
        Map<String, Execution> nestedExecutions = new HashMap<>();
        boolean executable = ctx.exec != null;
        
        // Oppretter ny Execution og legger til i exec-liste om den er kjørbar
        Execution execution = new Execution(define, statements, nestedExecutions, this.heap, this.stack, this.listeners, executable);
        if (executable) this.executionableList.add(execution);

        // Setter første Execution om den ikke allerede er satt og kjørbar
        if (this.firstExecution == null && executable) {
            this.setFirstExecution(execution);
            if (this.toStopBattery) {
                this.firstExecution.receiveMessage(Event.BATTERY.getName());
            }
        }

        // Behandler statements i Execution-blokken
        for (AeroScriptParser.StatementContext statementContext : ctx.statement()) {
            Statement statement = (Statement)this.visit(statementContext);
            execution.getStatements().add(statement);

            if (statement instanceof Execution nestedExecution) {
                execution.getNestedExecutions().put(nestedExecution.getName(), nestedExecution);
            }
        }

        // Legger til 'declare' om det er definert
        if (declare != null) {
            execution.addDeclare(declare);
        }

        // Lagre Execution i heap etter at den er definert
        Map<String, Execution> executionsMap = new HashMap<>((Map)this.heap.get(Memory.EXECUTION_TABLE));
        executionsMap.put(define, execution);
        this.heap.put(Memory.EXECUTION_TABLE, executionsMap);
        return execution;
    }

    // Implementasjon av de forskjellige besøkene for statements og handlinger (eksempel: visitAcAscend, visitAcMove, etc.)
    public Object visitAcAscend(AeroScriptParser.AcAscendContext ctx) {
        Node value = (Node)this.visit(ctx.expression());
        float newAltitude = Float.parseFloat(value.evaluate().toString());
        HashMap<String, Float> mods = new HashMap<>(this.modifiers);
        acAscend stmt = new acAscend(this.heap, mods, newAltitude);
        this.checkBattery();
        this.modifiers.remove("time");
        this.modifiers.remove("speed");
        return stmt;
    }

    // Eksempel på implementasjon for move-handling
    public Object visitAcMove(AeroScriptParser.AcMoveContext ctx) {
        String direction = ctx.TO() != null ? "to" : "by";
        Node value = (Node)this.visit(ctx.expression());
        acMove stmt = new acMove(this.heap, new HashMap<>(this.modifiers), direction, value.evaluate());
        this.checkBattery();
        this.modifiers.remove("time");
        this.modifiers.remove("speed");
        return stmt;
    }

    // Gjenværende handlinger som visitAcTurn, visitAcDock, etc., følger tilsvarende struktur

    // Besøk uttrykk og prosesser som punkter og rekkevidde, samt aritmetiske operasjoner (pluss, minus, ganger)
    public Node visitExpression(AeroScriptParser.ExpressionContext ctx) {
        if (ctx.PLUS() != null || ctx.MINUS() != null || ctx.TIMES() != null) {
            Node expr = (Node)this.visit(ctx.expression(0));
            Node x = (Node)this.visit(ctx.expression(1));
            String operator = ctx.PLUS() != null ? "PLUS" : (ctx.MINUS() != null ? "MINUS" : "TIMES");
            return new OperationNode(operator, expr, x);
        } else if (ctx.RANDOM() != null) {
            // Behandle RANDOM-operasjon
            Node start = ctx.range() != null ? (Node)this.visit(ctx.range().expression(0)) : new NumberNode(0.0F);
            Node end = ctx.range() != null ? (Node)this.visit(ctx.range().expression(1)) : new NumberNode(100.0F);
            return new OperationNode("RANDOM", start, end);
        } else if (ctx.NUMBER() != null) {
            return new NumberNode(Float.parseFloat(ctx.NUMBER().getText()));
        }
        return null;
    }
}
