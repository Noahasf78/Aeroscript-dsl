package no.uio.aeroscript.ast.stmt;

import java.util.HashMap;
import java.util.Map;
// import no.uio.aeroscript.ast.stmt.Reaction.1;
import no.uio.aeroscript.type.Memory;

public class Reaction extends Statement {
    public final Event event; // (OBSTACLE, BATTERY eller MESSAGE).
    private final String id; // En unik id for denne reaksjonen.
    private final HashMap<Memory, Object> heap; // Heap som lagrer minne og tilstand for programmet.
    private final HashMap<String, Runnable> listeners; // reagerer på visse hendelser.

    public Reaction(Event event, String id, HashMap<Memory, Object> heap, HashMap<String, Runnable> listeners) {
        this.event = event;
        this.id = id;
        this.heap = heap;
        this.listeners = listeners;
    }

    public void execute() {
        // Henter Execution-mapping fra heap for å finne ut hvilke Execution-objekter som er assosiert med en ID.
        Map<String, Execution> executionMap = (Map<String, Execution>) this.heap.get(Memory.EXECUTION_TABLE);

        // Switch som bestemmer hva som skal gjøres basert på hvilken event som utløses
        switch (this.event) {
            // Hvis event er BATTERY:
            case BATTERY:
                System.out.println("Klar til å reagere på " + this.event.getName());
                
                assert this.id != null; // sørger for at ID ikke er null 

                // Oppdaterer heapen med en ny reaksjon knyttet til hindringen.
                this.heap.put(Memory.REACTIONS, Map.of(Event.OBSTACLE.getName(), this.id));

                // Legger til en lytter for OBSTACLE-hendelsen som skal utføre en spesifikk handling når hendelsen oppstår.
                this.listeners.put(Event.OBSTACLE.getName(), () -> {
                    Execution execution = executionMap.get(this.id); // Henter Execution-objektet assosiert med denne ID-en.
                    // Hvis en Execution er funnet, sendes en melding om at OBSTACLE-hendelsen har skjedd.
                    if (execution != null) {
                        execution.receiveMessage(Event.OBSTACLE.getName());
                    }
                });
                break;
            
            // Hvis hendelsen er MESSAGE:
            case MESSAGE:
                System.out.println("Klar til å reagere på " + this.event.getName());

                assert this.id != null;

                // Oppdaterer heapen med en ny reaksjon knyttet til meldingen.
                this.heap.put(Memory.REACTIONS, Map.of(Event.MESSAGE.getName(), this.id));

                // Legger til en lytter for MESSAGE-hendelsen som utfører en handling når meldingen mottas.
                this.listeners.put(Event.MESSAGE.getName(), () -> {
                    Execution execution = executionMap.get(this.id); // Henter Execution-objektet assosiert med denne ID-en.
                    // Hvis en Execution er funnet, sendes en melding om at meldingen har blitt mottatt.
                    if (execution != null) {
                        execution.receiveMessage(Event.MESSAGE.getName());
                    }
                });
                break;
            
            // Hvis hendelsen er OBSTACLE
            case OBSTACLE:
                System.out.println("Klar til å reagere på " + this.event.getName());

                assert this.id != null;

                // Oppdaterer heapen med en ny reaksjon knyttet til hindringen.
                this.heap.put(Memory.REACTIONS, Map.of(Event.OBSTACLE.getName(), this.id));

                // Legger til en lytter for OBSTACLE-hendelsen som skal utføre en spesifikk handling når hendelsen oppstår.
                this.listeners.put(Event.OBSTACLE.getName(), () -> {
                    Execution execution = executionMap.get(this.id);    // Henter Execution-objektet assosiert med denne ID-en.
                    // Hvis en Execution er funnet, sendes en melding om at OBSTACLE-hendelsen har skjedd.
                    if (execution != null) {
                        execution.receiveMessage(Event.OBSTACLE.getName());
                    }
                });
                break;
            
            // Default tilfelle hvis ingen av de spesifikke hendelsene gjenkjennes.
            default:
                // Hvis hendelsen ikke er kjent, kaster vi en feil.
                throw new IllegalArgumentException("Ukjent hendelse: " + this.event);     
        }
    }
}

    

