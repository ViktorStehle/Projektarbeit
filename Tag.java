package Fitnessapp;

import java.time.LocalDate;

public class Tag {
	
	LocalDate datum;
	int schritte;
	double[] kalPlus;
	double[] kalMinus;
	double kalStand;
	//Aktivitaet[] akt; -> Klasse noch nicht implementiert
	
	/*
	 * Konstruktore
	 */
	
	//leerer default-Konstruktor
	public Tag() {
		
	}
	
	//Konstruktor mit allen Attributen bis auf akt (Klasse fehtl noch)
	public Tag (LocalDate datum, int schritte, double[] kalPlus, double[] kalMinus,
	double kalStand) {
		this.datum = datum;
		this.schritte = schritte;
		this.kalPlus = kalPlus;
		this.kalMinus = kalMinus;
		this.kalStand = kalStand;
	}
	
	/*
	 * Klasseneigene Methoden
	 */
	
	
	/*
	 * isTag: überprüft, ob ein Tag-Objekt für den aktuellen Tag vorhanden ist
	 * im Tag[]-Array des Users 
	 */
	public boolean isTag(LocalDate datum, Tag[] tage){
		for(int i=0; i<tage.length; i++) {
			if ( tage[i].datum == datum) {
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 * Getter- und Settermethoden
	 */
	
	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public int getSchritte() {
		return schritte;
	}

	//Set-Schritte (Task am Kanban-Board)
	public void setSchritte(int schritte) {
		this.schritte = schritte;
	}

	public double[] getKalPlus() {
		return kalPlus;
	}

	public void setKalPlus(double[] kalPlus) {
		this.kalPlus = kalPlus;
	}

	public double[] getKalMinus() {
		return kalMinus;
	}

	public void setKalMinus(double[] kalMinus) {
		this.kalMinus = kalMinus;
	}

	public double getKalStand() {
		return kalStand;
	}

	public void setKalStand(double kalStand) {
		this.kalStand = kalStand;
	}
}
