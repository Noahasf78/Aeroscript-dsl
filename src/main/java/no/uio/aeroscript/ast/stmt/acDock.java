package no.uio.aeroscript.ast.stmt;
import java.util.HashMap;
import no.uio.aeroscript.ast.Utilities;
import no.uio.aeroscript.type.Memory;
import no.uio.aeroscript.type.Point;

// denne klasse representerer en handling av at en drone returnerer til basen 
public class acDock extends Statement{
    private final HashMap<Memory, Object> heap; // heap som inneholder variabler og annen informasjon 
    private final HashMap<String, Float> modifiers; // Modifikatorer for tid og hastighet

    public acDock(HashMap<Memory, Object> heap, HashMap<String, Float> modifiers) {
        this.heap = heap;
        this.modifiers = modifiers;
    }

    public void execute() {
        System.out.println("tilbake til basen");

        // Sjekk at MEMORY.VARIABLES er en HashMap
        assert this.heap.get(Memory.VARIABLES) instanceof HashMap;

        // Hent variablene fra heap
        HashMap<String, Object> variabler = (HashMap)this.heap.get(Memory.VARIABLES);

        // Beregn batteriforbruk basert på høyde, tid og hastighet
        float batteryUsed = (Float)variabler.get("altitude") * 0.1F 
        + 0.1F * (Float)this.modifiers.get("time") 
        + 0.6F * (Float)this.modifiers.get("speed");

        // Beregn nåværende avstand reist fra dronens posisjon til startposisjonen
        float currentDistanceTravelled = Utilities.computeDistance(
            (Point)variabler.get("current position"), 
            (Point)variabler.get("initial position")
        );

        // Oppdater variabelen 'distance travelled' med nåværende høyde og reiseavstand tilbake til basen
        variabler.put("distance travelled", 
        (Float)variabler.get("distance travelled") 
        + (Float)variabler.get("altitude") 
        + currentDistanceTravelled
        );

        // Sett dronens høyde til 0 (fordi den lander på basen)
        variabler.put("altitude", 0.0F);

        // Sett dronens posisjon tilbake til startposisjonen
        variabler.put("current position", variabler.get("initial position"));

        // Oppdater batterinivået etter docking
        variabler.put("battery level", 
                 (Float)variabler.get("battery level") - batteryUsed
        );

        // Sjekk om batteriet er lavt etter oppdateringen
        variabler.put("battery low", 
                 Utilities.checkBattery(
                     (Float)variabler.get("battery level"), 
                     (Float)variabler.get("initial battery level"), 
                     this.heap)
        );


    }

}





