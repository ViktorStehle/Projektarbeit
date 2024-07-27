package apps.sportapp;

public class Radfahren extends Ausdauersportarten {
    private float kcal;
    private float pace; // km pro h

    public Radfahren(User user, int time, float dist) {
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
    	this.kcal = (float) (0.049 * user.getWeight() * (dist / time * 60) * time) / 60;
    }
    
}
