package apps.sportapp;

// Die Klasse Sportaktivität repräsentiert eine allgemeine sportliche Aktivität
public class Sportaktivität {
    private float time; // Attribut zur Speicherung der Dauer der Aktivität
    private User user; // Attribut zur Speicherung des Benutzers, der die Aktivität ausführt

    // Konstruktor zur Initialisierung der Sportaktivität-Objekte
    public Sportaktivität(User user, int time) {
        this.user = user;
    	this.time = time;
    }

    // Getter-Methode zur Rückgabe der Dauer der Aktivität
    public float getTime() {
        return time;
    }

    // Methode zur Rückgabe der verbrannten Kalorien (Standardwert)
    public float getKcal() {
    	return 150; // Standardwert für die verbrannten Kalorien
    }

    // Getter-Methode zur Rückgabe des Benutzers
    public User getUser() {
    	return user;
    }

    // Setter-Methode zum Setzen der Dauer der Aktivität
    public void setTime(int time) {
        this.time = time;
    }

    // Setter-Methode zum Setzen des Benutzers
    public void setUser(User user) {
    	this.user = user;
    }
    
 

 
}


