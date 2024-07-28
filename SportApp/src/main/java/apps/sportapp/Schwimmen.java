package apps.sportapp;

// Die Klasse Schwimmen erweitert die Klasse Ausdauersportarten
public class Schwimmen extends Ausdauersportarten {
    private float kcal;
    private float pace; // m / min

    // Konstruktor zur Initialisierung der Schwimmen-Objekte
    public Schwimmen(User user, int time, float dist) {
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
    	this.kcal = (2 * (dist / time) * user.getWeight() * time);
    }
    
}
