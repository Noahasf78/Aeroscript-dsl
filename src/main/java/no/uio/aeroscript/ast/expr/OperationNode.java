package no.uio.aeroscript.ast.expr;
import java.util.Random;
import no.uio.aeroscript.type.Point;

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
        switch (operation) {
            case "PLUS": 
                return (Float) left.evaluate() + (Float) right.evaluate();
            
            case "MINUS": 
                return (Float) left.evaluate() - (Float) right.evaluate();
            
            case "TIMES": 
                return (Float) left.evaluate() * (Float) right.evaluate();
            
            case "NEG":    
                return -1*(Float) left.evaluate(); 
            
            case "RANDOM": 
                float min = (Float) left.evaluate();
                float max = (Float) right.evaluate();
                Random random = new Random();       
                return (float) (min + (max - min) * random.nextFloat());
            
            case "POINT": 
                    float x = (Float) left.evaluate();
                    float y = (Float) right.evaluate();
                    return new Point(x, y);
                
                default:
                    throw new UnsupportedOperationException("Operation not supported: " + operation);
                
        }

    }
}
