package apps.sportapp;

import java.time.LocalDate;

public class Day {

    LocalDate date = LocalDate.now();
    int schritte = 0;
    double[] kalPlus = new double[0];
    double[] kalMinus = new double[0];
    double kalStand = 0;
    //Aktivity[] akt = new Akt[]; -> Klasse noch nicht implementiert

    /*
     * Konstruktore
     */

    //leerer default-Konstruktor
    public Day() {
    }

    //Konstruktor mit allen Attributen bis auf akt (Klasse fehlt noch)
    public Day(LocalDate date, int schritte, double[] kalPlus, double[] kalMinus,
               double kalStand) {

        this.date = date;
        this.schritte = schritte;
        this.kalPlus = kalPlus;
        this.kalMinus = kalMinus;
        this.kalStand = kalStand;
    }

    /*
     * Klasseneigene Methoden
     */

    /*
     * isDay: überprüft, ob ein Tag-Objekt für den aktuellen Tag vorhanden ist
     * im Tag[]-Array des Users
     */
    public static boolean isDay(Day[] days, LocalDate date){
        for (Day day : days) {
            if (day.date.equals(date)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Getter- und Settermethoden
     */
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
        setKalStand(this.kalPlus, this.kalMinus);
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
        setKalStand(this.kalPlus, this.kalMinus);
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

        for (double plus : kalPlus) {
            kalPlusSum = kalPlusSum + plus;
        }

        for (double minus : kalMinus) {
            kalMinusSum = kalMinusSum + minus;
        }

        this.kalStand = kalPlusSum-kalMinusSum;
    }
}
