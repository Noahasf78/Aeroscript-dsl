package no.uio.aeroscript.ast.stmt;


import java.util.HashMap;
import no.uio.aeroscript.ast.Utilities;
import no.uio.aeroscript.type.Memory;


public class acTurn extends Statement {
    private final HashMap<Memory, Object> heap; // Referanse til heap som inneholder variabler og annen informasjon
    private final HashMap<String, Float> modifiers; // Modifikatorer for tid og hastighet
    private final String direction; // Retning å svinge (venstre eller høyre)
    private final float angle; // Vinkel å svinge med (i grader)

    public acTurn(HashMap<Memory, Object> heap, HashMap<String, Float> modifiers, String direction, float angle) {
        this.heap = heap; 
        this.modifiers = modifiers;
        this.direction = direction;
        this.angle = angle;
        }

    public void execute() {
        // Sjekker retningen og skriver ut hvilken retning dronen svinger
        switch(this.direction) {
            case "left":
                System.out.println("svinger til " + this.angle + "grader");
                break;
            case "right":
                System.out.println("svinger til " + this.angle + " grader");
        }

        // sjekk at Memory.variables er en hashmap 
        assert this.heap.get(Memory.VARIABLES) instanceof HashMap;

        // hent variables fra heapen 
        HashMap<String,Object> variabler = (HashMap)this.heap.get(Memory.VARIABLES);

        // Beregn batteriforbruk basert på vinkelen, tid og hastighet
        float batteriforbruk = this.angle * 0.3F 
        + 0.1F * (Float)this.modifiers.get("time") 
        + 1.0F * (Float)this.modifiers.get("speed");

        // Oppdaterer variabler 
        variabler.put ("distance travelled", (Float)variabler.get("distance travelled")+ this.angle); // øker distanse dekket basert på vinkelen 
        variabler.put("battery level", (Float)variabler.get("battery level") - batteriforbruk); // reduserer baterinivået på forbruket 

        // Sjekk om batterinivået er lavt og oppdater "battery low" status
        
        variabler.put("battery low", Utilities.checkBattery((Float)variabler.get("battery level "), (Float)variabler.get("initial battery level"), this.heap));

    }   
}
