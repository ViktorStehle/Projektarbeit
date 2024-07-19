package apps.sportapp;

public class Ausdauersportarten extends Sportaktivität {
    private float distanz;

    public Ausdauersportarten(int dauer, float distanz) {
        super(dauer);
        this.distanz = distanz;
    }

    public float getDistanz() {
        return distanz;
    }

    public void setDistanz(float distanz) {
        this.distanz = distanz;
    }

    public float getGeschwindigkeit() {
        return 0; // Default implementation
    }
}
