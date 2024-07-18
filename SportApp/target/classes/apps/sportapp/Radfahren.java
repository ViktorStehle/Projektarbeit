package apps.sportapp;

public class Radfahren extends Ausdauersportarten {
	
	private int kcal;
	private float geschwindigkeit; //km pro h
	
	
	public Radfahren(int dauer, float distanz) {
		super(dauer, distanz);
		this.geschwindigkeit = dauer / distanz;
		this.kcal =  (int) (((28 * 0.2017) - (70 * 0.09036) + (130 * 0.4472) - 20.4022) * dauer/4.184); //anpasbar machen und Unterschiede für Geschlechter hinzufügen
		// TODO Auto-generated constructor stub
	}

	public int getKcal() {
		return kcal;
	}

	public float getGeschwindigkeit() {
		return geschwindigkeit;
	}
	
	

}
