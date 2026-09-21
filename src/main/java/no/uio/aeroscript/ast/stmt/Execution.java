package no.uio.aeroscript.ast.stmt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import no.uio.aeroscript.type.Memory;

public class Execution extends Statement {
   private final String name;
   private final List<Statement> statements; // Liste av statements som denne Execution skal utføre
   private final Map<String, Execution> nestedExecutions; // map der nøkkelen er navnet til en Execution
   private final HashMap<String, Runnable> listeners;   // knytte meldinger til handlinger (Runnable objekter)
   private final HashMap<Memory, Object> heap;          // Heap som lagrer programtilstanden, variabler osv.
   private final Stack<Statement> stack;                // Hovedstakken med statements som skal utføres
   private final Stack<Statement> emergencyStack;       // En nødstack som brukes i tilfelle kritiske situasjoner (f.eks. lavt batteri)
   private final List<String> declare = new ArrayList(); // Liste over deklarerte variabler for Execution-instansen
   private final boolean toExec;                        // Indikerer om Execution er klar for å utføres
   private boolean messageReceivingEnabled = true;      // Variabel for å styre om meldingmottak er aktivert

   public Execution(String name, List<Statement> statements, Map<String, Execution> nestedExecutions, HashMap<Memory, Object> heap, Stack<Statement> stack, HashMap<String, Runnable> listeners, boolean toExec) {
      this.name = name;
      this.statements = statements;
      this.nestedExecutions = nestedExecutions;
      this.heap = heap;
      this.stack = stack;
      this.listeners = listeners;
      this.emergencyStack = new Stack();
      this.toExec = toExec;
   }

   // Henter navnet på Execution
   public String getName() {
      return this.name;
   }

   // Henter listen av statements i denne Execution
   public List<Statement> getStatements() {
      return this.statements;
   }

   // Henter kartet over nested Execution-instanser
   public Map<String, Execution> getNestedExecutions() {
      return this.nestedExecutions;
   }

   // Legger til en listener for en spesifikk melding
   public void addListener(String message, Runnable listener) {
      this.listeners.put(message, listener);
   }

   // Henter listen over deklarerte variabler
   public List<String> getDeclare() {
      return this.declare;
   }

   // Legger til en variabel i deklarasjonslisten
   public void addDeclare(String variable) {
      this.declare.add(variable);
   }

   // Sjekker om Execution er klar for å kjøres
   public boolean getExec() {
    return this.toExec;
   }

   // Metode for å motta en melding og trigge en reaksjon
   public void receiveMessage(String message) {
    if (this.messageReceivingEnabled) { // Sjekker om meldingmottak er aktivert
        // Henter meldingskartet fra heap
        Map<String, String> messages = (Map)this.heap.get(Memory.MESSAGES);

        // Henter reaksjonstriggere fra heap
        Map<String, String> triggers = (Map)this.heap.get(Memory.REACTIONS);
        Map executions;

        if (messages != null && messages.containsKey(message)) { // Sjekker om meldingen finnes
            executions = (Map)this.heap.get(Memory.EXECUTION_TABLE);
            if (executions != null) {
                System.out.println("Reacting to " + message); // Loggfører reaksjon på meldingen
                Execution execution = (Execution)executions.get(messages.get(message));
                this.stack.addAll(execution.getStatements()); // Legger statements til stacken
                if (!execution.getDeclare().isEmpty()) { // Sjekker deklarasjoner
                    Iterator var = execution.getDeclare().iterator();

                    while(var.hasNext()) {
                        String variable = (String)var.next();
                        this.stack.addAll(((Execution)executions.get(variable)).getStatements()); // Legger nested statements til stack
                    }
                }
            }

        }
        else if (triggers != null && triggers.containsKey(message)) { //sjekekr triggers
            executions = (Map)this.heap.get(Memory.EXECUTION_TABLE);
            if (executions != null) {
                assert this.heap.get(Memory.VARIABLES) instanceof HashMap;

                HashMap<String, Object> vars = (HashMap)this.heap.get(Memory.VARIABLES);
                System.out.println("Reacting to " + message); // Loggfører reaksjon
                Execution execution = (Execution)executions.get(triggers.get(message));

                if (message.equals("low battery")) { // Spesialtilfelle: Lavt batteri
                    this.messageReceivingEnabled = false; // Deaktiverer meldinger
                    this.emergencyStack.addAll(execution.getStatements()); // Legger til nødstack
                }

                this.stack.addAll(0, execution.getStatements()); // Legger til statements på toppen av stack
                if (!execution.getDeclare().isEmpty()) {
                    Iterator var2 = execution.getDeclare().iterator();

                    while(var2.hasNext()) {
                        String variable = (String)var2.next();
                        this.stack.addAll(0, ((Execution)executions.get(variable)).getStatements()); // Legger til nested statements
                    }
                }
            }
        }
        this.execute(); // Utfører statements

    }
   }

   // Metode for å legge til flere statements til hovedstacken
   public void addStatements(List<Statement> statements) {
    this.stack.addAll(statements);
   }

   // Hovedmetoden for å utføre statements fra stack
   public void execute() {
    assert this.heap.get(Memory.VARIABLES) instanceof HashMap;

    HashMap<String, Object> vars = (HashMap)this.heap.get(Memory.VARIABLES);
    Iterator iterator;
    Statement statement;

    if (!this.emergencyStack.isEmpty()) { // Sjekker nødstack
        iterator = this.emergencyStack.iterator();

        while(iterator.hasNext()) {
            statement = (Statement)iterator.next();
            statement.execute(); // Utfører nødstatement
            iterator.remove();
        }

        this.emergencyStack.clear(); // Tømmer nødstack
        System.out.println("Battery level too low, closing the execution"); // Loggfører lavt batteri
        System.exit(1); // Avslutter programmet
    }

    iterator = this.stack.iterator();

    while(true) {
        while(iterator.hasNext()) { //itererer gjennom stacken
            statement = (Statement)iterator.next();
            if (statement instanceof Execution) { // Sjekker om statement er en Execution
                Execution execStatement = (Execution)statement;
                if (!execStatement.toExec) { // Sjekker om statement er klar til å kjøres
                    iterator.remove();
                    continue;
                }
                if (!execStatement.getDeclare().isEmpty()) { // Sjekker deklarasjoner
                    Iterator var3 = execStatement.getDeclare().iterator();

                    while(var3.hasNext()) {
                        String variable = (String)var3.next();
                        this.stack.addAll(((Execution)execStatement.getNestedExecutions().get(variable)).getStatements()); // Legger nested statements til stack
                }
            }
        }
        statement.execute(); // Utfører statement
        iterator.remove(); // Fjerner statement fra stack
    }
    return; // Avslutter metode

   }
}
}




 

   



