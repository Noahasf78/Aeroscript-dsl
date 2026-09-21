package no.uio.aeroscript;

import no.uio.aeroscript.antlr.AeroScriptLexer;
import no.uio.aeroscript.antlr.AeroScriptParser;
import no.uio.aeroscript.runtime.Interpreter;
import no.uio.aeroscript.runtime.REPL;
import no.uio.aeroscript.type.Memory;
import no.uio.aeroscript.type.Point;
import no.uio.aeroscript.ast.stmt.Statement;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        System.setProperty("org.jline.terminal.dumb", "true");

        HashMap<Memory, Object> heap = new HashMap<>();
        Stack<Statement> stack = new Stack<>();
        float batteryLevel;
        Point initialPosition;

        // Argument validation
        if (args.length < 1) {
            System.err.println("Usage: java -jar aeroscript.jar <path to file> [-b <battery level>] [-p <x> <y>]");
            System.exit(1);
        }

        String path = args[0];

        // Parsing battery level from args
        if (args.length > 1 && args[1].equals("-b")) {
            batteryLevel = Float.parseFloat(args[2]);
        } else if (args.length > 3 && args[4].equals("-b")) {
            batteryLevel = Float.parseFloat(args[5]);
        } else {
            batteryLevel = 100;
        }

        // Parsing initial position from args
        if (args.length > 1 && args[1].equals("-p")) {
            initialPosition = new Point(Float.parseFloat(args[2]), Float.parseFloat(args[3]));
        } else if (args.length > 3 && args[3].equals("-p")) {
            initialPosition = new Point(Float.parseFloat(args[4]), Float.parseFloat(args[5]));
        } else {
            initialPosition = new Point(0, 0);
        }

        float initialX = initialPosition.getX();
        float initialY = initialPosition.getY();
        float initialZ = 0;

        // Setting up variables in the heap
        HashMap<Memory, HashMap<String, Object>> variables = new HashMap<>();
        variables.put(Memory.VARIABLES, new HashMap<>());
        HashMap<String, Object> vars = variables.get(Memory.VARIABLES);

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

        try {
            // Parsing the AeroScript file
            String content = new String(Files.readAllBytes(Paths.get(path)));
            AeroScriptLexer lexer = new AeroScriptLexer(CharStreams.fromString(content));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            AeroScriptParser parser = new AeroScriptParser(tokens);
            AeroScriptParser.ProgramContext programContext = parser.program();

            Interpreter interpreter = new Interpreter(heap, stack);
            interpreter.visitProgram(programContext);

            REPL repl = new REPL(heap, interpreter.getListeners(), interpreter.getFirstExecution());
            System.out.println("Welcome to the AeroScript REPL!");

            while (!repl.isTerminating()) {
                System.setProperty("org.jline.terminal", "jline.UnsupportedTerminal");
                LineReader reader = LineReaderBuilder.builder().build();

                String next;
                String left;
                String[] splits;

                do {
                    next = reader.readLine("MO> ");
                    if (next == null) break;

                    splits = next.trim().split(" ", 2);
                    left = splits.length == 1 ? "" : splits[1].trim();
                } while (!repl.command(splits[0], left));
            }

            Point finalPosition = interpreter.getPosition();
            float altitude = interpreter.getAltitude();

            // Printing final states
            System.out.println("Initial position: " + initialX + ", " + initialY + ", " + initialZ);
            System.out.println("Initial battery capacity: " + batteryLevel);
            System.out.println("Final position: " + finalPosition.getX() + ", " + finalPosition.getY() + ", " + altitude);
            System.out.println("Final battery level (%): " + interpreter.getBatteryLevel() + "%");
            System.out.println("Distance travelled: " + interpreter.getDistanceTravelled() + " meters");
            System.out.println("Execution complete!");

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
