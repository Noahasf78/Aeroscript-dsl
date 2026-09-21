package no.uio.aeroscript.ast.stmt;

import java.util.HashMap;
import no.uio.aeroscript.ast.Utilities;
import no.uio.aeroscript.type.Memory;

// AscendStatement representerer en handling for å stige en spesifisert høyde
public class acAscend extends Statement {
    private final HashMap<Memory, Object> heap; // Referanse til heap som inneholder variabler og annen informasjon
    private final HashMap<String, Float> modifiers; // Modifikatorer for tid og hastighet
    private final float distance; // Hvor mye å stige

    public acAscend(HashMap<Memory, Object> heap, HashMap<String, Float> modifiers, float distance) {
        this.heap = heap;
        this.modifiers = modifiers;
        this.distance = distance;
     }

    
    // Utfører stigningshandlingen
    public void execute() {
        System.out.println("stiger med " + this.distance + " meter");
    
        
        // Sjekker at MEMORY.VARIABLES er en HashMap
        assert this.heap.get(Memory.VARIABLES) instanceof HashMap;
        
        // henter variabler fra heap
        HashMap<String, Object> variabler = (HashMap)this.heap.get(Memory.VARIABLES);

        // Beregn batteriforbruk basert på stigming, tid
        float batteryConsumption = this.distance * 0.6F 
        + 0.1F * (Float) this.modifiers.get("time") 
        + 1.0F * (Float) this.modifiers.get("speed");

        // Oppdater variabler
        variabler.put("altitude", this.distance); // Oppdaterer nåværende høyde
        variabler.put("distance travelled", (Float) variabler.get("distance travelled") + this.distance); // Oppdaterer total avstand
        variabler.put("battery level", (Float) variabler.get("battery level") - batteryConsumption); // Oppdaterer batterinivå
        variabler.put("battery low", Utilities.checkBattery((Float) variabler.get("battery level"), (Float) variabler.get("initial battery level"), this.heap)); // Sjekker om batterinivået er lavt

    }
}