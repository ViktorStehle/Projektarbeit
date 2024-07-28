package apps.sportapp;

public class Joggen extends Ausdauersportarten {
    private float kcal;
    private float pace; // km / h 

    //Konstruktor für Klasse Joggen
    public Joggen(User user, int time, float dist) {
        super(user, time, dist);
        this.pace = dist / time * 60;
        setKcal(user, time, dist);
    }

    @Override
    // Getter-Methode für Kilokalorien
    public float getKcal() {
        return kcal;
    }
 
    @Override
    // Getter-Methode für Geschwindigkeit
    public float getPace() {
        return pace;
    }
    
    // Setter-Methode für Kilokalorien
    public void setKcal(User user, float time, float dist) {
    	this.kcal = (float) (0.75 * (dist / time * 60) * user.getWeight() * (time / 60));
    }
}
