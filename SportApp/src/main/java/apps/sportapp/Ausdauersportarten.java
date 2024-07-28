package apps.sportapp;

public class Ausdauersportarten extends Sportaktivität {
    private float distance;

// Konstruktor zur Initialisierung der Ausdauersportarten-Objekte
    public Ausdauersportarten(User user, int time, float dist) {
        super(user, time);
        this.distance = dist;
    }

// Methode zur Rückgabe der Distanz
    public float getDistance() {
        return distance;
    }

// Methode zur Berechnung der Geschwindigkeit (wird in Unterklasse überschrieben)
    public float getPace() {
        return 0; // Default implementation
    }

// Methode zum Setzen der Distanz
    public void setDistanz(float dist) {
        this.distance = dist;
    }
    

}
