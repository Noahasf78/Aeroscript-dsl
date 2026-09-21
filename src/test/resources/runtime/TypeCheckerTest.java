package runtime;

import no.uio.aeroscript.antlr.AeroScriptLexer;
import no.uio.aeroscript.antlr.AeroScriptParser;
import no.uio.aeroscript.error.TypeError;
import no.uio.aeroscript.runtime.TypeChecker;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TypeCheckerTest {

    private void runTypeChecker(String script) {
        AeroScriptLexer lexer = new AeroScriptLexer(CharStreams.fromString(script));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AeroScriptParser parser = new AeroScriptParser(tokens);
        AeroScriptParser.ProgramContext programContext = parser.program();

        TypeChecker typeChecker = new TypeChecker(programContext);
        typeChecker.check();
    }

    @Test
    public void testValidScript_MoveToPoint() {
        String script = """
            TakeOff {
                move to point (10, 20)
            }
        """;

        assertDoesNotThrow(() -> runTypeChecker(script));
    }

    @Test
    public void testInvalidScript_MoveToNum() {
        String script = """
            TakeOff {
                move to 30
            }
        """;

        assertThrows(TypeError.class, () -> runTypeChecker(script), "move to requires a POINT type.");
    }

    @Test
    public void testValidScript_AscendByNum() {
        String script = """
            TakeOff {
                ascend by 50
            }
        """;

        assertDoesNotThrow(() -> runTypeChecker(script));
    }

    @Test
    public void testInvalidScript_AscendByPoint() {
        String script = """
            TakeOff {
                ascend by point (10, 20)
            }
        """;

        assertThrows(TypeError.class, () -> runTypeChecker(script), "ascend by requires a NUM type.");
    }

    @Test
    public void testValidScript_NestedPoints() {
        String script = """
            ScanArea {
                move to point (random[0, 10], point (5, 5))
            }
        """;

        assertThrows(TypeError.class, () -> runTypeChecker(script), "Components of a point must be of type NUM.");
    }

    @Test
    public void testValidScript_TurnByNum() {
        String script = """
            Explore {
                turn right by 45
            }
        """;

        assertDoesNotThrow(() -> runTypeChecker(script));
    }

    @Test
    public void testInvalidScript_RandomRangeWithPoint() {
        String script = """
            Explore {
                move to point (random[0, point(5, 10)], 15)
            }
        """;

        assertThrows(TypeError.class, () -> runTypeChecker(script), "Random range must have bounds of type NUM.");
    }

    @Test
    public void testValidScript_RandomWithoutRange() {
        String script = """
            TakeOff {
                move to point (random, random)
            }
        """;

        assertDoesNotThrow(() -> runTypeChecker(script));
    }

    @Test
    public void testValidScript_Reactions() {
        String script = """
            TakeOff {
                on low battery -> EmergencyLanding
            }
        """;

        assertDoesNotThrow(() -> runTypeChecker(script));
    }

    @Test
    public void testInvalidScript_MessageEventWithoutID() {
        String script = """
            TakeOff {
                on message [] -> ReturnToBase
            }
        """;

        assertThrows(TypeError.class, () -> runTypeChecker(script), "on message requires a valid ID.");
    }
}
