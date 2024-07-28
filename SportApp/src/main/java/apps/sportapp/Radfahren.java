package apps.sportapp;

// Die Klasse Radfahren erweitert die Klasse Ausdauersportarten
public class Radfahren extends Ausdauersportarten {
    private float kcal;
    private float pace; // km pro h

    // Konstruktor zur Initialisierung der Radfahren-Objekte
    public Radfahren(User user, int time, float dist) {
        super(user, time, dist);
        this.pace = dist / time * 60;
        setKcal(user, time, dist);
    }

    // Überschreibt die getKcal-Methode aus der Oberklasse
    @Override
    public float getKcal() {
        return kcal;
    }

    // Überschreibt die getPace-Methode aus der Oberklasse
    @Override
    public float getPace() {
        return pace;
    }

    // Methode zur Berechnung und Setzung der verbrannten Kalorien
    public void setKcal(User user, float time, float dist) {
        // Berechnung der Kalorien basierend auf dem Gewicht des Benutzers, der Distanz und der Zeit
    	this.kcal = (float) (0.049 * user.getWeight() * (dist / time * 60) * time) / 60;
    }
    
}
