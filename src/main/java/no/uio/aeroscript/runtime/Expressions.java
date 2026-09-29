package no.uio.aeroscript.runtime;

import no.uio.aeroscript.antlr.AeroScriptParser.ExpressionContext;
import no.uio.aeroscript.ast.expr.Node;
import no.uio.aeroscript.ast.expr.NumberNode;
import no.uio.aeroscript.ast.expr.OperationNode;
import no.uio.aeroscript.error.TypeError;

/** Builds and type-checks expression ASTs without evaluating random values. */
final class Expressions {
    enum Type { NUM, POINT, RANGE }
    record Expression(Type type, Node node) {}

    static Expression compile(ExpressionContext ctx) {
        if (ctx.NUMBER() != null) {
            float value = Float.parseFloat(ctx.NUMBER().getText());
            if (!Float.isFinite(value)) throw new TypeError("Number must be finite.");
            return new Expression(Type.NUM, new NumberNode(value));
        }
        if (ctx.point() != null || ctx.range() != null) {
            var parts = ctx.point() != null ? ctx.point().expression() : ctx.range().expression();
            Expression left = compile(parts.get(0)), right = compile(parts.get(1));
            require(left, Type.NUM, "Point/range components must be NUM.");
            require(right, Type.NUM, "Point/range components must be NUM.");
            // Ranges are consumed by RANDOM below; POINT is the same two-component AST.
            return new Expression(ctx.point() != null ? Type.POINT : Type.RANGE,
                    new OperationNode("POINT", left.node(), right.node()));
        }
        if (ctx.RANDOM() != null) {
            if (ctx.rrange == null)
                return new Expression(Type.NUM, new OperationNode("RANDOM", new NumberNode(0f), new NumberNode(1f)));
            Expression range = compile(ctx.rrange);
            require(range, Type.RANGE, "random requires a numeric range.");
            return new Expression(Type.NUM, new Node() {
                public Object evaluate() {
                    var bounds = (no.uio.aeroscript.type.Point) range.node().evaluate();
                    float low = bounds.getX(), high = bounds.getY();
                    if (!Float.isFinite(low) || !Float.isFinite(high) || low >= high)
                        throw new IllegalArgumentException("Random range must have finite, increasing bounds.");
                    return (float) java.util.concurrent.ThreadLocalRandom.current().nextDouble(low, high);
                }
            });
        }

        Expression left = compile(ctx.expression(0));
        if (ctx.POINT() != null) {
            require(left, Type.POINT, "point requires two numeric coordinates.");
            return left;
        }
        if (ctx.NEG() != null) {
            require(left, Type.NUM, "Negation requires NUM.");
            return new Expression(Type.NUM, new OperationNode("NEG", left.node(), null));
        }
        if (ctx.expression().size() == 1) return left; // Parentheses.
        Expression right = compile(ctx.expression(1));
        String op = ctx.TIMES() != null ? "TIMES" : ctx.PLUS() != null ? "PLUS" : "MINUS";
        Type result;
        if (op.equals("TIMES")) {
            if (left.type() == Type.NUM && right.type() == Type.NUM) result = Type.NUM;
            else if ((left.type() == Type.POINT && right.type() == Type.NUM)
                    || (left.type() == Type.NUM && right.type() == Type.POINT)) result = Type.POINT;
            else throw new TypeError("Multiplication requires NUM operands or a POINT and NUM.");
        } else {
            if (left.type() != right.type() || left.type() == Type.RANGE)
                throw new TypeError("Addition/subtraction requires matching NUM or POINT operands.");
            result = left.type();
        }
        return new Expression(result, new OperationNode(op, left.node(), right.node()));
    }

    static void require(Expression expression, Type expected, String message) {
        if (expression.type() != expected) throw new TypeError(message);
    }
}
