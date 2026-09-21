package no.uio.aeroscript;

import no.uio.aeroscript.antlr.AeroScriptLexer;
import no.uio.aeroscript.antlr.AeroScriptParser;
import no.uio.aeroscript.ast.expr.Node;
import no.uio.aeroscript.runtime.Interpreter;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide exactly one filename as an argument.");
            return;
        }

        String filePath = args[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // For each line in the file, parse and evaluate the expression
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) { // Skip empty lines
                    // 1. Create a lexer with the input line
                    AeroScriptLexer lexer = new AeroScriptLexer(CharStreams.fromString(line));

                    // 2. Tokenize the input
                    CommonTokenStream tokens = new CommonTokenStream(lexer);

                    // 3. Parse the tokenized input
                    AeroScriptParser parser = new AeroScriptParser(tokens);
                    AeroScriptParser.ExpressionContext expressionContext = parser.expression();

                    // 4. Interpret the parsed expression
                    Interpreter interpreter = new Interpreter();
                    Node resultNode = interpreter.visit(expressionContext);

                    // 5. Evaluate the expression and print the result
                    Object result = resultNode.evaluate();
                    System.out.println("Result: " + result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
