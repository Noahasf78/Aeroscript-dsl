package no.uio.aeroscript.ast.expr;
// Hva skjer her???

    // Node som representerer et tall
    public class NumberNode extends Node {
        private final Float value;

        public NumberNode(Float value) {
            this.value = value;
        }

        @Override
        public Float evaluate() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }


