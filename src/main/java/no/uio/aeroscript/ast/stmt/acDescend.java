package no.uio.aeroscript.ast.stmt;

import java.util.HashMap;
import no.uio.aeroscript.ast.Utilities;
import no.uio.aeroscript.type.Memory;

// DescendStatement representerer en handling der systemet går ned til bakken.
public class acDescend extends Statement {
    private final HashMap<Memory, Object> heap;  // Heap som inneholder variabler og tilstander i programmet
    private final HashMap<String, Float> modifiers; // Modifiers som kan brukes til å justere oppførselen til handlingen

    public acDescend(HashMap<Memory, Object> heap, HashMap<String, Float> modifiers) {
        this.heap = heap;
        this.modifiers = modifiers;
    }

    // utfører nedstigninghandlingen
    public void execute() {
        System.out.println("synker ned til bakken");

        // Sjekker at MEMORY.VARIABLES er av typen HashMap
        assert this.heap.get(Memory.VARIABLES) instanceof HashMap;

        // Henter variabler fra heap
        HashMap<String, Object> variabler = (HashMap)this.heap.get(Memory.VARIABLES);

        // Beregner batteriforbruket basert på nåværende høyde, tid og hastighet
        float batteryConsumption = (Float)variabler.get("altitude") * 0.2F + 
                                   0.1F * (Float)this.modifiers.get("time") + 
                                   1.0F * (Float)this.modifiers.get("speed");
        
        
         // Oppdaterer avstand reist med nåværende høyde
         variabler.put("distance travelled", (Float)variabler.get("distance travelled") + (Float)variabler.get("altitude"));
        
         // Setter høyden til 0, ettersom vi synker til bakken
         variabler.put("altitude", 0.0F);
         
         // Oppdaterer batterinivået basert på forbruket
         variabler.put("battery level", (Float)variabler.get("battery level") - batteryConsumption);
         
         // Sjekker om batterinivået er lavt og oppdaterer status
         variabler.put("battery low", Utilities.checkBattery((Float)variabler.get("battery level"), 
                                                     (Float)variabler.get("initial battery level"), 
                                                     this.heap));


    }
    
}
