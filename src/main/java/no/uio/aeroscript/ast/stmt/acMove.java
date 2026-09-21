package no.uio.aeroscript.ast.stmt;
import java.io.PrintStream;
import java.util.HashMap;
import no.uio.aeroscript.ast.Utilities;
import no.uio.aeroscript.type.Memory;
import no.uio.aeroscript.type.Point;


public class acMove extends Statement {
    private final HashMap<Memory, Object> heap; // En heap som lagrer programtilstand, variabler, osv.
    private final HashMap<String, Float> modifiers; // HashMap for å lagre modifikatorer som tid og hastighet.
    private final String mode; // Lagre bevegelsesmodus ("to" eller "by").
    private final Object value; // Verdi som representerer enten et punkt eller en distanse (avhengig av modus).

    public acMove(HashMap<Memory, Object> heap, HashMap<String, Float> modifiers, String mode, Object value) {
        this.heap = heap; 
        this.modifiers = modifiers; 
        this.mode = mode; 
        this.value = value;
   }

   public void execute() {  
        assert this.heap.get(Memory.VARIABLES) instanceof HashMap; // Sjekker at MEMORY.VARIABLES er en HashMap.

        HashMap<String, Object> variabler = (HashMap)this.heap.get(Memory.VARIABLES); // Henter variabler fra heap.
        float batteryConsumption;

        switch (this.mode) { // Sjekker bevegelsesmodus ("to" eller "by").
            case "to": // Hvis modus er "to" (flytte til et punkt):
                if (!(this.value instanceof Point)) { // Sjekker om verdien er et punkt.
                    throw new IllegalArgumentException("Invalid value for move statement: " + 
                    String.valueOf(this.value)); // Kaster feil hvis verdien ikke er et punkt.
                }
                Point point = (Point)this.value; // Caster verdien til et punkt.
                System.out.println("Move to (" + point.getX() + ", " + point.getY() + ")"); // Caster value til et Point og skriver ut X- og Y-koordinatene

                float travelledDistance = Utilities.computeDistance((Point)variabler.get("current position"), point); // Beregn tilbakelagt distanse fra nåværende posisjon til målet.
                batteryConsumption = travelledDistance * 0.7F + 0.1F * (Float)this.modifiers.get("time") + 1.0F * (Float)this.modifiers.get("speed"); // Beregn batteriforbruk basert på distanse, tid og hastighet.

                variabler.put("distance travelled", (Float)variabler.get("distance travelled") + travelledDistance); // Oppdater variabelen for tilbakelagt distanse.
                variabler.put("current position", point); // Oppdater nåværende posisjon til det nye punktet.
                variabler.put("battery level", (Float)variabler.get("battery level") - batteryConsumption); // Oppdater batterinivået med beregnet forbruk.
                break;

            case "by": // Hvis modus er "by" (flytte med en viss distanse):
                if (!(this.value instanceof Float)) { // Sjekker om verdien er en flyttall (distanse).
                   throw new IllegalArgumentException("Invalid value for move statement: " + String.valueOf(this.value)); // Kaster feil hvis verdien ikke er et flyttall.
                }
                
                System.out.println("Move by " + String.valueOf(this.value) + " meters"); // Skriv ut bevegelsen med gitt distanse.
                
                batteryConsumption= (Float)this.value * 0.5F + 0.1F * (Float)this.modifiers.get("time") + 1.0F * (Float)this.modifiers.get("speed"); // Beregn batteriforbruk basert på distansen, tid og hastighet.
                
                Point currentPosition = (Point)variabler.get("current position"); // Hent nåværende posisjon fra variablene.
                Point newPosition = new Point(currentPosition.getX() + (Float)this.value, currentPosition.getY()); // Beregn ny posisjon ved å legge til distansen til X-koordinaten.@

                variabler.put("distance travelled", (Float)variabler.get("distance travelled") + (Float)this.value); // Oppdater variabelen for tilbakelagt distanse.
                variabler.put("current position", newPosition); // Oppdater nåværende posisjon til den nye posisjonen.
                variabler.put("battery level", (Float)variabler.get("battery level") - batteryConsumption); // Oppdater batterinivået med beregnet forbruk.
        }
        variabler.put("battery low", Utilities.checkBattery((Float)variabler.get("battery level"), (Float)variabler.get("initial battery level"), this.heap)); // Sjekker om batteriet er lavt og oppdaterer "battery low"-status.

   }
    
}
