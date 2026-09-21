package no.uio.aeroscript.ast;
import java.util.HashMap;
import java.util.Map;
import no.uio.aeroscript.ast.stmt.Event;
import no.uio.aeroscript.ast.stmt.Execution;
import no.uio.aeroscript.type.Memory;
import no.uio.aeroscript.type.Point;

public class Utilities {
    public Utilities() {}

    // Beregner avstanden mellom to punkter.
    public static float computeDistance(Point point1, Point point2) { 
        // Beregner avstanden mellom point1 og point2.
        return (float)Math.sqrt(Math.pow((double)(point1.getX() - 
        point2.getX()), 2.0) + 
        Math.pow((double)(point1.getY() - 
        point2.getY()), 2.0)); 
    }

    // Privat metode som beregner prosentandelen av batteriet som er igjen.
    private static float getPercentageLevel(float currentLevel, float initialLevel) { 
        return currentLevel / initialLevel * 100.0F; // Dividerer nåværende batterinivå med opprinnelig nivå og ganger med 100 for å få prosentandel.
    }

    // Sjekker om batterinivået er under 20%, og utfører handlinger basert på resultatet.
    public static boolean checkBattery(float currentLevel, float initialLevel, HashMap<Memory, Object> heap) {
        float percentage = getPercentageLevel(currentLevel, initialLevel); // Finner batteriprosent ved å bruke getPercentageLevel-metoden.

        // Hvis batteriprosenten er mindre enn eller lik 20, utfør handlinger.
        if (percentage <= 20.0F) { 
             // Sørger for at de nødvendige datastrukturene i heap er instanser av HashMap.
            assert heap.get(Memory.REACTIONS) instanceof HashMap; 
            assert heap.get(Memory.EXECUTION_TABLE) instanceof HashMap; 
            assert heap.get(Memory.VARIABLES) instanceof HashMap;

            // Hent "REACTIONS" og "VARIABLES" fra heapen og kast dem til riktig type.
            Map<String, String> triggers = (Map)heap.get(Memory.REACTIONS); 
            Map<String, Object> variabler = (Map)heap.get(Memory.VARIABLES);

            // Sjekk om "low battery"-triggeren finnes i reaksjonene (triggers).
            if (triggers.containsKey("low battery")) {
                // Hvis "low battery"-triggeren finnes, hent initial execution fra variablene.
                Execution initialExecution = (Execution)variabler.get("initial execution");
                
                // Send en batterimelding (BATTERY) til initialExecution.
                initialExecution.receiveMessage(Event.BATTERY.getName()); 
            }
            else {
                // Hvis ingen "low battery"-trigger er definert, avslutt programmet med feilkode 1.
                System.out.println("Battery level below 20%, exiting");
                System.exit(1);
            }
            // Returner true hvis batterinivået er lavt.
            return true;
        }
        else {
            // Returner false hvis batterinivået ikke er lavt.
            return false;
        }
    }   
}
