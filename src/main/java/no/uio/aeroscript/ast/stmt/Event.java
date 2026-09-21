
package no.uio.aeroscript.ast.stmt;

public enum Event {
    OBSTACLE {
        @Override
        public String getName() {
            return "obstacle";
        }
    },
    BATTERY {
        @Override
        public String getName() {
            return "low battery";
        }
    },
    MESSAGE {
        @Override
        public String getName() {
            return "message";
        }
    };

    public abstract String getName();
}
