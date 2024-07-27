package apps.sportapp;

public class Ausdauersportarten extends Sportaktivität {
    private float distance;

    public Ausdauersportarten(User user, int time, float dist) {
        super(user, time);
        this.distance = dist;
    }

    public float getDistance() {
        return distance;
    }


    public float getPace() {
        return 0; // Default implementation
    }
    
    public void setDistanz(float dist) {
        this.distance = dist;
    }
    

}
