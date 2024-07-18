package apps.sportapp;

public class Joggen extends Ausdauersportarten {
	
	private int kcal;
	private float geschwindigkeit; //min pro km

	public Joggen(int dauer, float distanz) {
		super(dauer, distanz);
		this.kcal = (int) (1/dauer * 70 * distanz); //Gewicht anpassbar machen
		this.geschwindigkeit = dauer/distanz;
		// TODO Auto-generated constructor stub
	}

	public int getKcal() {
		return kcal;
	}


	public float getGeschwindigkeit() {
		return geschwindigkeit;
	}

	

}