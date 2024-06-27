package Fitnessapp;

import java.time.LocalDate;

public class Tag {
	
	LocalDate datum = LocalDate.now();
	int schritte = 0;
	double[] kalPlus = new double[0];
	double[] kalMinus = new double[0];
	double kalStand = 0;
	//Aktivitaet[] akt = new Akt[]; -> Klasse noch nicht implementiert
	
	/*
	 * Konstruktore
	 */
	
	//leerer default-Konstruktor
	public Tag() {
		
	}
	
	//Konstruktor mit allen Attributen bis auf akt (Klasse fehlt noch)
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
	public boolean isTag(Tag[] tage){
		for(int i=0; i<tage.length; i++) {
			if ( tage[i].datum.equals(LocalDate.now())) {
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

	public void setSchritte(int schritte) {
		this.schritte = schritte;
	}

	public double[] getKalPlus() {
		return kalPlus;
	}

	/*
	 * setKalPlus
	 * Ersatzarray wird erstellt, der um 1 länger ist als this
	 * Array wird ins Ersatzarray kopiert, neuer Eintrag am Ende eingetragen
	 */
	public void setKalPlus(double kalPlus) {
		double[] ersatz = new double[this.kalPlus.length+1];
		for(int i = 0; i < ersatz.length; i++) {
			if(i==ersatz.length-1) {
				ersatz[i]=kalPlus;
			} else {
				ersatz[i]=this.kalPlus[i];
			}
		}
		this.kalPlus=ersatz;
	}

	public double[] getKalMinus() {
		return kalMinus;
	}

	/*
	 * setKalMinus
	 * Ersatzarray wird erstellt, der um 1 länger ist als this
	 * Array wird ins Ersatzarray kopiert, neuer Eintrag am Ende eingetragen
	 */
	public void setKalMinus(double kalMinus) {
		double[] ersatz = new double[this.kalMinus.length+1];
		for(int i = 0; i < ersatz.length; i++) {
			if(i==ersatz.length-1) {
				ersatz[i]=kalMinus;
			} else {
				ersatz[i]=this.kalMinus[i];
			}
		}
		this.kalMinus=ersatz;
	}

	public double getKalStand() {
		return kalStand;
	}

	/*
	 * setKalStand
	 * Summe der Kalorieneinahme und Summe des Kalorienverbrauchs des Tages wird
	 * berrechnet und setzt den Stand als Attribut des Tages
	 */
	public void setKalStand(double[] kalPlus, double[] kalMinus) {
		double kalPlusSum = 0;
		double kalMinusSum = 0;
		
		for(int i = 0; i < kalPlus.length; i++) {
			kalPlusSum=kalPlusSum+kalPlus[i];
		}
		
		for(int i = 0; i < kalMinus.length; i++) {
			kalMinusSum= kalMinusSum+kalMinus[i];
		}
		
		this.kalStand = kalPlusSum-kalMinusSum;
	}
}
