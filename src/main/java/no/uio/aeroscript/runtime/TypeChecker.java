package no.uio.aeroscript.runtime;

import no.uio.aeroscript.antlr.AeroScriptBaseVisitor;
import no.uio.aeroscript.antlr.AeroScriptParser;
import static no.uio.aeroscript.runtime.Expressions.Type.*;

/** Static argument checking over the ANTLR parse tree. */
public class TypeChecker extends AeroScriptBaseVisitor<Void> {
    private final AeroScriptParser.ProgramContext program;

    public TypeChecker(AeroScriptParser.ProgramContext program) { this.program = program; }
    public void check() { visit(program); }

    @Override
    public Void visitAction(AeroScriptParser.ActionContext ctx) {
        if (ctx.acAscend() != null)
            check(ctx.acAscend().expression(), NUM, "ascend by requires a NUM type.");
        if (ctx.acMove() != null) {
            boolean to = ctx.acMove().TO() != null;
            check(ctx.acMove().expression(), to ? POINT : NUM,
                    to ? "move to requires a POINT type." : "move by requires a NUM type.");
        }
        if (ctx.acTurn() != null)
            check(ctx.acTurn().expression(), NUM, "turn requires a NUM type.");
        if (ctx.acDescend() != null && ctx.acDescend().expression() != null)
            check(ctx.acDescend().expression(), NUM, "descend by requires a NUM type.");
        if (ctx.expression() != null)
            check(ctx.expression(), NUM, "Speed/duration requires a NUM type.");
        return null;
    }

    private void check(AeroScriptParser.ExpressionContext ctx, Expressions.Type expected, String message) {
        Expressions.require(Expressions.compile(ctx), expected, message);
    }
}
