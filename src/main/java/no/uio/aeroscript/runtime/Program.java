package no.uio.aeroscript.runtime;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import no.uio.aeroscript.ast.stmt.Execution;
import no.uio.aeroscript.ast.stmt.Statement;
import no.uio.aeroscript.type.Memory;

public class Program {

    // Lagrer tilstanden og variablene til programmet under kjøring
    private final HashMap<Memory, Object> heap;

    // Lagrer prosedyrene som kan kjøres, identifisert ved navn
    private final Map<String, Execution> stack;

    // Konstruktør som initialiserer heap (programtilstand) og stack (lagring av prosedyrer)
    public Program(HashMap<Memory, Object> heap) {
        this.heap = heap;
        this.stack = new HashMap<>(); // Oppretter en tom stack for prosedyrer
    }

    // Legger til en prosedyre i stacken slik at den kan hentes og kjøres senere
    public void addProcedure(Execution execution) {
        this.stack.put(execution.getName(), execution); // Bruker navnet på prosedyren som nøkkel
    }

    // Kjører en spesifikk prosedyre basert på ID
    public void run(String id) {
        // Henter prosedyren med det spesifiserte ID fra stacken
        Execution execution = this.stack.get(id);

        if (execution != null) { // Sjekker om prosedyren eksisterer i stacken
            // Henter kartet over eksisterende prosedyrer fra heap
            Map<String, Execution> executionsMap = (Map<String, Execution>) this.heap.get(Memory.EXECUTION_TABLE);

            // Henter listen over instruksjoner for denne spesifikke prosedyren
            List<Statement> statements = executionsMap.get(id).getStatements();

            // Legger instruksjonene til prosedyren som skal kjøre
            execution.addStatements(statements);

            // Starter kjøringen av prosedyren
            execution.execute();
        } else {
            // Kaster en feilmelding hvis prosedyren med gitt ID ikke finnes i stacken
            throw new IllegalArgumentException("Execution with id " + id + " not found.");
        }
    }
}
