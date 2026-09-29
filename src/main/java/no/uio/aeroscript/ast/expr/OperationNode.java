package no.uio.aeroscript.ast.expr;

import no.uio.aeroscript.type.Point;
import java.util.Random;

public class OperationNode extends Node {
    private final String operation;
    private final Node left;
    private final Node right;

    public OperationNode(String operation, Node left, Node right) {
        this.operation = operation;
        this.left = left;
        this.right = right;
    }

    @Override
    public Object evaluate() {
        return switch (operation) {
            case "PLUS" -> {
                Object leftValue = left.evaluate();
                Object rightValue = right.evaluate();
                if (leftValue instanceof Point point && rightValue instanceof Point otherPoint) {
                    yield new Point((Float) point.getX() + (Float) otherPoint.getX(), (Float) point.getY() + (Float) otherPoint.getY());
                } else if (leftValue instanceof Float && rightValue instanceof Float) {
                    yield (Float) leftValue + (Float) rightValue;
                } else {
                    throw new IllegalArgumentException("Invalid operation: " + operation);
                }
            }
            case "MINUS" -> {
                Object leftValue = left.evaluate();
                Object rightValue = right.evaluate();
                if (leftValue instanceof Point point && rightValue instanceof Point otherPoint) {
                    yield new Point((Float) point.getX() - (Float) otherPoint.getX(), (Float) point.getY() - (Float) otherPoint.getY());
                } else if (leftValue instanceof Float && rightValue instanceof Float) {
                    yield (Float) leftValue - (Float) rightValue;
                } else {
                    throw new IllegalArgumentException("Invalid operation: " + operation);
                }
            }
            case "TIMES" -> {
                Object leftValue = left.evaluate();
                Object rightValue = right.evaluate();
                if (leftValue instanceof Point point && rightValue instanceof Float) {
                    yield new Point((Float) point.getX() * (Float) rightValue, (Float) point.getY() * (Float) rightValue);
                } else if (leftValue instanceof Float && rightValue instanceof Point point) {
                    yield new Point((Float) leftValue * (Float) point.getX(), (Float) leftValue * (Float) point.getY());
                } else if (leftValue instanceof Float && rightValue instanceof Float) {
                    yield (Float) leftValue * (Float) rightValue;
                } else {
                    throw new IllegalArgumentException("Invalid operation: " + operation);
                }
            }
            case "NEG" -> (Float) left.evaluate() * (-1);
            // For RANDOM return a random number between left and right
            case "RANDOM" -> {
                Random r = new Random();
                float leftValue = (Float) left.evaluate();
                float rightValue = (Float) right.evaluate();
                yield r.nextFloat((rightValue-leftValue)) + leftValue;
            }
            case "POINT" -> new Point((Float) left.evaluate(), (Float) right.evaluate());
            default -> throw new IllegalArgumentException("Invalid operation: " + operation);
        };
    }
}
