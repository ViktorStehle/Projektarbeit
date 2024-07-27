package apps.sportapp;

public class Schwimmen extends Ausdauersportarten {
    private float kcal;
    private float pace; // m / min

    public Schwimmen(User user, int time, float dist) {
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
    	this.kcal = (2 * (dist / time) * user.getWeight() * time);
    }
    
}
