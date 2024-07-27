package apps.sportapp;

public class Joggen extends Ausdauersportarten {
    private float kcal;
    private float pace; // km / h 

    public Joggen(User user, int time, float dist) {
        super(user, time, dist);
        this.pace = dist / time * 60;
        setKcal(user, time, dist);
    }

    @Override
    public float getKcal() {
        return kcal;
    }
 
    @Override
    public float getPace() {
        return pace;
    }
    
    public void setKcal(User user, float time, float dist) {
    	this.kcal = (float) (0.75 * (dist / time * 60) * user.getWeight() * (time / 60));
    }
}
