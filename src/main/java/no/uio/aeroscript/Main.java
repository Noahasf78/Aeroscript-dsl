package no.uio.aeroscript;

import no.uio.aeroscript.antlr.AeroScriptLexer;
import no.uio.aeroscript.antlr.AeroScriptParser;
import no.uio.aeroscript.ast.stmt.Statement;
import no.uio.aeroscript.runtime.TypeChecker;
import no.uio.aeroscript.type.Point;
import no.uio.aeroscript.error.ThrowingErrorListener;
import no.uio.aeroscript.error.TypeError;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.setProperty("org.jline.terminal.dumb", "true");
        Stack<Statement> stack = new Stack<>();
        float batteryLevel;
        Point initialPosition;

        String path = args[0];

        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(path)));

            AeroScriptLexer lexer = new AeroScriptLexer(CharStreams.fromString(content));
            lexer.removeErrorListeners();
            lexer.addErrorListener(ThrowingErrorListener.INSTANCE);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            AeroScriptParser parser = new AeroScriptParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(ThrowingErrorListener.INSTANCE);
            AeroScriptParser.ProgramContext programContext = parser.program();

            TypeChecker typeChecker = new TypeChecker(programContext);
            typeChecker.check();

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (ParseCancellationException e) {
            System.err.println("Parser error: " + e.getMessage());
        } catch (TypeError e) {
            System.err.println("TypeError:\n  " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
