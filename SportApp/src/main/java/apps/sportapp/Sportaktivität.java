package apps.sportapp;

public class Sportaktivität {
    private float time;
    private User user;


    public Sportaktivität(User user, int time) {
        this.user = user;
    	this.time = time;
    }

    public float getTime() {
        return time;
    }
    
    public float getKcal() {
    	return 150;
    }
    
    public User getUser() {
    	return user;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
    public void setUser(User user) {
    	this.user = user;
    }
    
 

 
}


