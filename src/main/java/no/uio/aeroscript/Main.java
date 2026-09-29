package no.uio.aeroscript;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import no.uio.aeroscript.antlr.AeroScriptLexer;
import no.uio.aeroscript.antlr.AeroScriptParser;
import no.uio.aeroscript.error.ThrowingErrorListener;
import no.uio.aeroscript.error.TypeError;
import no.uio.aeroscript.runtime.FlightRuntime;
import no.uio.aeroscript.runtime.TypeChecker;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class Main {
    public static void main(String[] args) {
        int status = run(args, System.out, System.err);
        if (status != 0) System.exit(status);
    }

    public static int run(String[] args, PrintStream out, PrintStream err) {
        if (args.length != 1) {
            err.println("Usage: java -jar aeroscript-1.0-all.jar <mission.aero>");
            return 1;
        }
        try {
            var lexer = new AeroScriptLexer(CharStreams.fromString(Files.readString(Path.of(args[0]))));
            lexer.removeErrorListeners();
            lexer.addErrorListener(ThrowingErrorListener.INSTANCE);
            var parser = new AeroScriptParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(ThrowingErrorListener.INSTANCE);
            var program = parser.program();
            new TypeChecker(program).check();
            out.println("[TYPECHECK] Passed");
            new FlightRuntime(out).run(program);
            return 0;
        } catch (IOException e) {
            err.println("File error: " + e.getMessage());
        } catch (ParseCancellationException e) {
            err.println("Parser error: " + e.getMessage());
        } catch (TypeError e) {
            err.println("TypeError: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            err.println("Execution error: " + e.getMessage());
        }
        return 1;
    }
}
