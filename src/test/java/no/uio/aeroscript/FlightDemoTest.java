package no.uio.aeroscript;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

class FlightDemoTest {
    @TempDir Path directory;
    record Result(int status, String out, String err) {}

    private Result run(String... args) {
        var out = new ByteArrayOutputStream();
        var err = new ByteArrayOutputStream();
        int status = Main.run(args, new PrintStream(out), new PrintStream(err));
        return new Result(status, out.toString(StandardCharsets.UTF_8), err.toString(StandardCharsets.UTF_8));
    }

    private Result script(String source) throws Exception {
        Path path = directory.resolve("mission.aero");
        Files.writeString(path, source);
        return run(path.toString());
    }

    @Test void demoCompletesAtBaseWithCalculatedEnergyUse() {
        Result result = run("examples/demo_flight.aero");
        assertEquals(0, result.status(), result.err());
        assertEquals("", result.err());
        assertTrue(result.out().contains("[ASCEND] position=(0.0, 0.0) | altitude=20.0 m"));
        assertTrue(result.out().contains("[MOVE] position=(10.0, 20.0) | altitude=20.0 m"));
        assertTrue(result.out().contains("actions=3 | position=(0.0, 0.0) | altitude=0.0 m | distance=84.72 m | battery=91.53%"));
    }

    @Test void wrongTypesFailBeforeFlight() throws Exception {
        for (String action : new String[]{"ascend by point (1, 2)", "move to 10", "move by point (1, 2)",
                "turn by point (1, 2)", "move to point (1, point (2, 3))", "move to point (random[0, point(1, 2)], 2)"}) {
            Result result = script("Demo { " + action + " }");
            assertEquals(1, result.status(), action);
            assertTrue(result.err().contains("TypeError:"), result.err());
            assertEquals("", result.out());
        }
    }

    @Test void expressionsAndGroundDescentAreAccepted() throws Exception {
        Result result = script("Demo { ascend by (10 + 10) move to point (5 * 2, 30 - 10) descend to ground return to base }");
        assertEquals(0, result.status(), result.err());
        assertTrue(result.out().contains("[MOVE] position=(10.0, 20.0)"));
    }

    @Test void turnsAffectRelativeMovement() throws Exception {
        Result result = script("Demo { ascend by 5 turn left by 90 move by 10 return to base }");
        assertEquals(0, result.status(), result.err());
        assertTrue(result.out().contains("[MOVE] position=(0.0, 10.0)"));
    }

    @Test void randomPointStaysInRange() throws Exception {
        Result result = script("Demo { move to point (random[1, 2], random) return to base }");
        assertEquals(0, result.status(), result.err());
    }

    @Test void batteryGuardStopsBeforeUnsafeAction() throws Exception {
        Result result = script("Demo { ascend by 801 return to base }");
        assertEquals(1, result.status());
        assertTrue(result.err().contains("Battery failsafe"));
        assertFalse(result.out().contains("[ASCEND]"));
        assertFalse(result.out().contains("Mission complete"));
        assertEquals(0, script("Demo { ascend by 800 }").status());
    }

    @Test void invalidSyntaxAndArgumentsReturnFailure() throws Exception {
        assertEquals(1, script("Demo { on message [] -> Dock }").status());
        assertEquals(1, script("Demo { ascend by --1 }").status());
        assertEquals(1, script("Demo { descend by 1 }").status());
        assertEquals(1, script("Demo { ascend by random[2, 1] }").status());
        assertEquals(1, run().status());
        assertEquals(1, run(directory.resolve("missing.aero").toString()).status());
    }

    @Test void unsupportedControlFlowFailsBeforeActions() throws Exception {
        for (String source : new String[]{"Demo { ascend by 20 on low battery -> Dock }",
                "Demo { ascend by 20 on message [go] -> Dock }",
                "Demo { ascend by 20 Nested { return to base } }",
                "Demo { ascend by 20 } -> Dock", "Demo { ascend by 20 at speed 2 }"}) {
            Result result = script(source);
            assertEquals(1, result.status());
            assertTrue(result.out().contains("[TYPECHECK] Passed"));
            assertFalse(result.out().contains("[ASCEND]"));
        }
    }
}
