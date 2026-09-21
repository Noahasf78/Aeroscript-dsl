package no.uio.aeroscript.runtime;

import no.uio.aeroscript.antlr.AeroScriptBaseVisitor;
import no.uio.aeroscript.antlr.AeroScriptParser;
import no.uio.aeroscript.ast.expr.*;

import no.uio.aeroscript.type.Point;

// Interpreter-klassen som besøker hvert uttrykksnode og evaluerer det
public class Interpreter extends AeroScriptBaseVisitor<Node> {

    @Override
    public Node visitExpression(AeroScriptParser.ExpressionContext ctx) {
        // Hvis uttrykket er et heltall, opprett en NumberNode
        if (ctx.NUMBER() != null) {
            return new NumberNode(Float.parseFloat(ctx.NUMBER().getText()));
        }
        // Håndterer addisjon
        if (ctx.PLUS() != null) {
            Node venstre = (Node) visit(ctx.expression(0));
            Node høyre = (Node) visit(ctx.expression(1));
            return new OperationNode("PLUS", venstre, høyre);
        }
        // Håndterer substraksjon
        if (ctx.MINUS() != null) {
            Node venstre = (Node) visit(ctx.expression(0));
            Node høyre = (Node) visit(ctx.expression(1));
            return new OperationNode("MINUS", venstre, høyre);
        }

        // Håndterer multiplikasjon
        if (ctx.TIMES() != null) {
            Node venstre = (Node) visit(ctx.expression(0));
            Node høyre = (Node) visit(ctx.expression(1));
            return new OperationNode("TIMES", venstre, høyre);
        }
        
        
        // Håndterer enkelt sub-uttrykk inni parenteser
        else if (ctx.expression().size() == 1) {
            return (Node) visit(ctx.expression(0));
        }
        // Håndterer negasjon
        if (ctx.NEG() != null) {
            Node venstre = (Node) visit(ctx.expression(0));
            return new OperationNode("NEG", venstre,null);
        }
        
        
        // Håndterer random-funksjonen
        if (ctx.RANDOM() != null) {
            if (ctx.range() != null){
                var min = visitExpression(ctx.range().min);
                var max = visitExpression(ctx.range().max);
                return new OperationNode("RANDOM", min, max);
            } else {
                return new OperationNode("RANDOM", null,null);
            }
        }

        // Håndterer point-funksjonen
        if (ctx.POINT()!=null) {
            Node x = visitExpression(ctx.expression(0));
            Node y = visitExpression(ctx.expression(1));
            return new OperationNode("POINT", x, y);
        }

        if (ctx.LPAREN() != null) {
            return visitExpression(ctx.expression(0));
        }

        // Tilbakefall til standard oppførsel hvis ingen av betingelsene matchet
        return super.visitExpression(ctx);
    }

  
}
