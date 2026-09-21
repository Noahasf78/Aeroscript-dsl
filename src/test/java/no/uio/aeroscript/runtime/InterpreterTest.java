package no.uio.aeroscript.runtime;

import no.uio.aeroscript.antlr.AeroScriptLexer;
import no.uio.aeroscript.antlr.AeroScriptParser;
import no.uio.aeroscript.ast.stmt.Execution;
import no.uio.aeroscript.ast.stmt.Statement;
import no.uio.aeroscript.type.Memory;
import no.uio.aeroscript.type.Point;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class InterpreterTest {

    private HashMap<Memory, Object> heap;
    private Stack<Statement> stack;

    private void initInterpreter() {
        this.heap = new HashMap<>();
        this.stack = new Stack<>();

        HashMap<Memory, HashMap<String, Object>> variables = new HashMap<>();
        variables.put(Memory.VARIABLES, new HashMap<>());
        HashMap<String, Object> vars = variables.get(Memory.VARIABLES);

        float batteryLevel = 100;
        int initialZ = 0;
        Point initialPosition = new Point(0, 0);

        vars.put("initial position", initialPosition);
        vars.put("current position", initialPosition);
        vars.put("altitude", initialZ);
        vars.put("initial battery level", batteryLevel);
        vars.put("battery level", batteryLevel);
        vars.put("battery low", false);
        vars.put("distance travelled", 0.0f);
        vars.put("initial execution", null);

        heap.put(Memory.EXECUTION_TABLE, new HashMap<>());
        heap.put(Memory.REACTIONS, new HashMap<>());
        heap.put(Memory.MESSAGES, new HashMap<>());
        heap.put(Memory.VARIABLES, vars);
    }

    private AeroScriptParser.ExpressionContext parseExpression(String expression) {
        AeroScriptLexer lexer = new AeroScriptLexer(CharStreams.fromString(expression));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AeroScriptParser parser = new AeroScriptParser(tokens);
        return parser.expression();
    }

    @Test
    void getFirstExecution() {
        initInterpreter();
        Interpreter interpreter = new Interpreter(this.heap, this.stack);

        Execution firstExecution = new Execution("first", new Stack<>(), new HashMap<>(), this.heap, this.stack, null, true);
        interpreter.setFirstExecution(firstExecution);

        assertEquals(firstExecution, interpreter.getFirstExecution(), "First execution should be correctly set and retrieved");
    }

    @Test
    void getPosition() {
        initInterpreter();
        Interpreter interpreter = new Interpreter(this.heap, this.stack);

        Point position = interpreter.getPosition();

        assertNotNull(position, "Position should not be null");
        assertEquals(new Point(0, 0), position, "Initial position should be (0, 0)");
    }

    @Test
    void getDistanceTravelled() {
        initInterpreter();
        Interpreter interpreter = new Interpreter(this.heap, this.stack);

        assertEquals(0.0f, interpreter.getDistanceTravelled(), "Distance travelled should be 0 initially");
    }

    @Test
    void getBatteryLevel() {
        initInterpreter();
        Interpreter interpreter = new Interpreter(this.heap, this.stack);

        assertEquals(100.0f, interpreter.getBatteryLevel(), "Battery level should be 100% initially");
    }

    @Test
    void visitProgram() throws Exception {
        initInterpreter();
        Interpreter interpreter = new Interpreter(this.heap, this.stack);

        // Parsing a sample program file (ensure program.aero exists and is correctly formatted)
        String programContent = Files.readString(Paths.get("path/to/your/program.aero"));
        AeroScriptLexer lexer = new AeroScriptLexer(CharStreams.fromString(programContent));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AeroScriptParser parser = new AeroScriptParser(tokens);

        AeroScriptParser.ProgramContext programContext = parser.program();
        interpreter.visitProgram(programContext);

        // Assert that the program has 9 executions, as specified in the requirements
        Map<String, Execution> executionTable = (Map<String, Execution>) heap.get(Memory.EXECUTION_TABLE);
        assertEquals(9, executionTable.size(), "Program should contain exactly 9 executions");
        assertNotNull(interpreter.getFirstExecution(), "First execution should not be null after running the program");
    }

    @Test
    void visitExpression() {
        initInterpreter();
        Interpreter interpreter = new Interpreter(this.heap, this.stack);

        assertEquals(5.0f, Float.parseFloat(interpreter.visitExpression(parseExpression("2 + 3")).evaluate().toString()), "2 + 3 should equal 5");
        assertEquals(-1.0f, Float.parseFloat(interpreter.visitExpression(parseExpression("2 - 3")).evaluate().toString()), "2 - 3 should equal -1");
        assertEquals(6.0f, Float.parseFloat(interpreter.visitExpression(parseExpression("2 * 3")).evaluate().toString()), "2 * 3 should equal 6");
        assertEquals(-1.0f, Float.parseFloat(interpreter.visitExpression(parseExpression("--1")).evaluate().toString()), "--1 should equal -1");
    }
}
