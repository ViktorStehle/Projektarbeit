package apps.sportapp;

public class Schwimmen extends Ausdauersportarten {
    private int kcal;
    private float geschwindigkeit; // min pro 100m

    public Schwimmen(int dauer, float distanz) {
        super(dauer, distanz);
        this.geschwindigkeit = dauer / distanz * 100;
        this.kcal = (int) ((0.9 * 70 * 24) * 10.3 / 24 * dauer / 60); // anpassbar für Geschlecht & Gewicht machen
    }

    @Override
    public int getKcal() {
        return this.kcal;
    }

    @Override
    public float getGeschwindigkeit() {
        return geschwindigkeit;
    }
}
