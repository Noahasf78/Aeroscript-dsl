package no.uio.aeroscript.ast.expr;

import no.uio.aeroscript.type.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OperationNodeTest {
    /** Numeric values change on each call, making accidental resampling observable. */
    private static final class CountingNode extends Node {
        private final Object firstValue;
        private int evaluations;

        CountingNode(Object firstValue) { this.firstValue = firstValue; }

        @Override
        public Object evaluate() {
            evaluations++;
            return firstValue instanceof Float number ? number + evaluations - 1 : firstValue;
        }
    }

    @Test void numericArithmeticEvaluatesEachOperandOnce() {
        String[] operations = {"PLUS", "MINUS", "TIMES"};
        float[] expected = {9f, 3f, 18f};
        for (int i = 0; i < operations.length; i++) {
            var left = new CountingNode(6f);
            var right = new CountingNode(3f);
            assertEquals(expected[i], (Float) new OperationNode(operations[i], left, right).evaluate());
            assertEquals(1, left.evaluations, operations[i]);
            assertEquals(1, right.evaluations, operations[i]);
        }
    }

    @Test void pointArithmeticEvaluatesEachOperandOnce() {
        for (String operation : new String[]{"PLUS", "MINUS"}) {
            var left = new CountingNode(new Point(6f, 9f));
            var right = new CountingNode(new Point(2f, 3f));
            Point result = (Point) new OperationNode(operation, left, right).evaluate();
            assertEquals(operation.equals("PLUS") ? 8f : 4f, result.getX());
            assertEquals(operation.equals("PLUS") ? 12f : 6f, result.getY());
            assertEquals(1, left.evaluations);
            assertEquals(1, right.evaluations);
        }
    }

    @Test void pointScalingUsesTheSameScalarForBothCoordinates() {
        for (boolean scalarFirst : new boolean[]{false, true}) {
            var point = new CountingNode(new Point(3f, 5f));
            var scalar = new CountingNode(2f);
            Point result = (Point) new OperationNode("TIMES",
                    scalarFirst ? scalar : point, scalarFirst ? point : scalar).evaluate();
            assertEquals(6f, result.getX());
            assertEquals(10f, result.getY());
            assertEquals(1, point.evaluations);
            assertEquals(1, scalar.evaluations);
        }
    }

    @Test void eachEvaluationTakesFreshOperandValues() {
        var left = new CountingNode(2f);
        var right = new CountingNode(3f);
        var operation = new OperationNode("PLUS", left, right);
        assertEquals(5f, (Float) operation.evaluate());
        assertEquals(7f, (Float) operation.evaluate());
        assertEquals(2, left.evaluations);
        assertEquals(2, right.evaluations);
    }

    @Test void negationEvaluatesOnlyItsLeftOperand() {
        var left = new CountingNode(4f);
        var unused = new CountingNode(99f);
        assertEquals(-4f, (Float) new OperationNode("NEG", left, unused).evaluate());
        assertEquals(1, left.evaluations);
        assertEquals(0, unused.evaluations);
        assertEquals(-2f, (Float) new OperationNode("NEG", new NumberNode(2f), null).evaluate());
    }

    @Test void pointConstructionEvaluatesEachCoordinateOnce() {
        var left = new CountingNode(3f);
        var right = new CountingNode(5f);
        Point result = (Point) new OperationNode("POINT", left, right).evaluate();
        assertEquals(3f, result.getX());
        assertEquals(5f, result.getY());
        assertEquals(1, left.evaluations);
        assertEquals(1, right.evaluations);
    }

    @Test void randomEvaluatesEachBoundOnce() {
        var left = new CountingNode(0f);
        var right = new CountingNode(1f);
        float result = (Float) new OperationNode("RANDOM", left, right).evaluate();
        assertTrue(result >= 0f && result < 1f);
        assertEquals(1, left.evaluations);
        assertEquals(1, right.evaluations);
    }

    @Test void incompatibleArithmeticOperandsStillFailWithoutResampling() {
        for (String operation : new String[]{"PLUS", "MINUS", "TIMES"}) {
            var left = new CountingNode(new Point(1f, 2f));
            var right = new CountingNode(operation.equals("TIMES") ? new Point(3f, 4f) : 3f);
            var error = assertThrows(IllegalArgumentException.class,
                    () -> new OperationNode(operation, left, right).evaluate());
            assertEquals("Invalid operation: " + operation, error.getMessage());
            assertEquals(1, left.evaluations);
            assertEquals(1, right.evaluations);
        }
    }

    @Test void unknownOperationDoesNotEvaluateOperands() {
        var left = new CountingNode(1f);
        var right = new CountingNode(2f);
        assertThrows(IllegalArgumentException.class,
                () -> new OperationNode("UNKNOWN", left, right).evaluate());
        assertEquals(0, left.evaluations);
        assertEquals(0, right.evaluations);
    }
}
