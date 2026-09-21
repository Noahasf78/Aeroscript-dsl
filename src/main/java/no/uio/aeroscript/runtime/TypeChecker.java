package no.uio.aeroscript.runtime;

import no.uio.aeroscript.antlr.AeroScriptBaseVisitor;
import no.uio.aeroscript.antlr.AeroScriptParser;
import no.uio.aeroscript.ast.expr.Node;
import no.uio.aeroscript.ast.expr.NumberNode;
import no.uio.aeroscript.ast.expr.OperationNode;
import no.uio.aeroscript.error.TypeError;


public class TypeChecker extends AeroScriptBaseVisitor<Object> {
    private final AeroScriptParser.ProgramContext ctx;
    
    enum Type {
        NUM,
        POINT
    }

    public TypeChecker(AeroScriptParser.ProgramContext ctx) {
        this.ctx = ctx;
    }

    public void check() {
        visitProgram(ctx);
    }

    @Override
    public Object visitProgram(AeroScriptParser.ProgramContext ctx) {
        if (ctx == null) return null;

        for (AeroScriptParser.ExecutionContext execution : ctx.execution()) {
            visit(execution); // Visit each execution block
        }
        return null;
    }

    @Override
    public Object visitExecution(AeroScriptParser.ExecutionContext ctx) {
        if (ctx == null) return null;

        for (AeroScriptParser.StatementContext statement : ctx.statement()) {
            visit(statement); // Visit each statement in the execution block
        }
        return null;
    }

    @Override
    public Object visitStatement(AeroScriptParser.StatementContext ctx) {
        if (ctx.action() != null) {
            // Sjekk alle actions i grammatikken
            if (ctx.action().acMove() != null) {
                if (ctx.action().acMove().TO() != null) {
                    throw new TypeError("move to requires a POINT type.");
                }
                if (ctx.action().acMove().BY() != null) {
                    throw new TypeError("move by requires a NUM type.");
                }
            }

            if (ctx.action().acAscend() != null) {
                throw new TypeError("ascend by requires a NUM type.");
            }

            if (ctx.action().acDescend() != null) {
                if (ctx.action().acDescend() != null) {
                    throw new TypeError("descend by requires a NUM type.");
                }
            }

            if (ctx.action().acTurn() != null) {
                throw new TypeError("turn requires a NUM type.");
            }

            if (ctx.action().acDock() != null) {
                // Ingen typefeil for acDock
            }

        } else if (ctx.reaction() != null) {
            // Sjekk alle reactions i grammatikken
            if (ctx.reaction().event().OBSTACLE() != null) {
                // Ingen typefeil for on obstacle
            }
            if (ctx.reaction().event().BATTERY() != null) {
                // Ingen typefeil for on low battery
            }
            if (ctx.reaction().event().MESSAGE() != null) {
                throw new TypeError("on message requires a valid ID.");
            }

        } else if (ctx.execution() != null) {
            visit(ctx.execution()); // Nested execution
        }

        return null;
    }
}
