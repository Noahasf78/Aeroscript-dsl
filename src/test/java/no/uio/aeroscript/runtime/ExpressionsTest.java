package no.uio.aeroscript.runtime;

import java.util.Random;
import no.uio.aeroscript.antlr.AeroScriptLexer;
import no.uio.aeroscript.antlr.AeroScriptParser;
import no.uio.aeroscript.error.ThrowingErrorListener;
import no.uio.aeroscript.error.TypeError;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionsTest {
    /** Supplies a known legal double sample so rounding tests cannot pass by chance. */
    private static Random sample(double value) {
        return new Random() {
            @Override public double nextDouble(double origin, double bound) {
                assertTrue(value >= origin && value < bound, "Test sample must be inside the requested range");
                return value;
            }
        };
    }

    private static Expressions.Expression compile(String source) {
        var lexer = new AeroScriptLexer(CharStreams.fromString(source));
        lexer.removeErrorListeners();
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);
        var parser = new AeroScriptParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(ThrowingErrorListener.INSTANCE);
        var expression = parser.expression();
        assertEquals(org.antlr.v4.runtime.Token.EOF, parser.getCurrentToken().getType());
        return Expressions.compile(expression);
    }

    @Test void adjacentPositiveBoundsCannotRoundUpToExcludedUpperBound() {
        float low = 1f, high = Math.nextUp(low);
        double nearHigh = Math.nextDown((double) high);
        assertEquals(high, (float) nearHigh); // The narrowing conversion reproduces the bug.
        assertEquals(low, Expressions.randomInRange(low, high, sample(nearHigh)));
    }

    @Test void adjacentNegativeBoundsCannotRoundUpToExcludedUpperBound() {
        float low = -1f, high = Math.nextUp(low);
        double nearHigh = Math.nextDown((double) high);
        assertEquals(high, (float) nearHigh);
        assertEquals(low, Expressions.randomInRange(low, high, sample(nearHigh)));
    }

    @Test void zeroUpperBoundRemainsExclusive() {
        float low = -Float.MIN_VALUE;
        double nearZero = Math.nextDown(0.0);
        assertTrue((float) nearZero == 0f);
        assertEquals(low, Expressions.randomInRange(low, 0f, sample(nearZero)));
    }

    @Test void inclusiveLowerBoundAndInteriorValuesArePreserved() {
        assertEquals(-5f, Expressions.randomInRange(-5f, 3f, sample(-5.0)));
        assertEquals(-2.5f, Expressions.randomInRange(-5f, 3f, sample(-2.5)));
        assertEquals(0f, Expressions.randomInRange(-5f, 3f, sample(0.0)));
        assertEquals(2f, Expressions.randomInRange(-5f, 3f, sample(2.0)));
    }

    @Test void wideFiniteRangesRemainSupported() {
        float low = -Float.MAX_VALUE, high = Float.MAX_VALUE;
        assertEquals(0f, Expressions.randomInRange(low, high, sample(0.0)));
        assertEquals(Math.nextDown(high),
                Expressions.randomInRange(low, high, sample(Math.nextDown((double) high))));
    }

    @Test void invalidBoundsFailBeforeSampling() {
        Random unused = new Random() {
            @Override public double nextDouble(double origin, double bound) {
                fail("Invalid bounds must be rejected before sampling");
                return 0;
            }
        };
        for (float[] bounds : new float[][]{
                {1f, 1f}, {2f, 1f}, {-0f, 0f}, {Float.NaN, 1f}, {0f, Float.NaN},
                {Float.NEGATIVE_INFINITY, 1f}, {0f, Float.POSITIVE_INFINITY}}) {
            var error = assertThrows(IllegalArgumentException.class,
                    () -> Expressions.randomInRange(bounds[0], bounds[1], unused));
            assertEquals("Random range must have finite, increasing bounds.", error.getMessage());
        }
    }

    @Test void compiledAdjacentRangeHasOnlyOneRepresentableResult() {
        var expression = compile("random[1, 1.0000001192092896]");
        assertEquals(Expressions.Type.NUM, expression.type());
        assertEquals(1f, (Float) expression.node().evaluate());
        var negative = compile("random[--1, --0.9999999403953552]");
        assertEquals(-1f, (Float) negative.node().evaluate());
    }

    @Test void compiledInvalidRangesRetainValidation() {
        for (String source : new String[]{"random[1, 1]", "random[2, 1]"}) {
            var expression = compile(source);
            assertThrows(IllegalArgumentException.class, () -> expression.node().evaluate());
        }
        assertThrows(TypeError.class, () -> compile("random[0, point(1, 2)]"));
    }
}
