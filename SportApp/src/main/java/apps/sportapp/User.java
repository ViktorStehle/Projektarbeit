package apps.sportapp;

import java.time.LocalDate;

public class User {

    //static int counter = 0;
    //int id;
    String name;
    String passwort;
    String geschlecht;
    LocalDate gbdatum;
    double gewicht;
    double groesse;
    Day[] days;

    //default-Konstruktor
    User(){
    }

    //Konstruktor mit relevanten Attributen
    User(String name, String passwort, String geschlecht, LocalDate gbdatum, double gewicht, double groesse){
        //User.counter = counter+1;
        //this.id = User.counter;
        this.name = name;
        this.passwort = passwort;
        this.geschlecht = geschlecht;
        this.gbdatum = gbdatum;
        this.gewicht = gewicht;
        this.groesse = groesse;
        this.days = new Day[]{};
    }

    /*
    setter- und getter-Methoden
     */

    //prüft, ob etwas an vorhandenem Tag-Objekt oder ein neues Tag-Objekt hinzugefügt werden muss
    public void setDays(LocalDate date, int schritte, double kalPlus, double kalMinus) {
        if (Day.isDay(this.days, date)){
            System.out.println("isDays ist true!");
            for (Day day : this.days) {
                if (day.date.equals(date)) {

                    //Schritte summieren
                    day.setSchritte(day.schritte + schritte);

                    //ans KalorienPlus-Array anhängen
                    if(kalPlus!=0){
                        day.setKalPlus(kalPlus);
                    }

                    //ans KalorienMinus-Array anhängen
                    if(kalMinus!=0){
                        day.setKalMinus(kalMinus);
                    }
                }
            }
        } else {
            System.out.println("appendDay wird aufgerufen");
            Day day = new Day ();
            day.setSchritte(schritte);
            day.setDate(date);
            day.setKalPlus(kalPlus);
            day.setKalMinus(kalMinus);
            appendDay(day);
        }
    }

    //wenn Eintrag gelöscht wird, müssen gelöschte Werte auch vom Tag-Objekt gelöscht werden
    public void deleteFromDays(LocalDate date, int schritte, double kalPlus, double kalMinus){
        for (Day day : this.days) {
            if (day.date.equals(date)) {

                //Schritte subtrahieren
                day.setSchritte(day.schritte - schritte);

                //aus KalorienPlus-Array löschen
                if(kalPlus!=0){
                    day.kalPlus = deleteFromArray(day.kalPlus, kalPlus);
                    day.setKalStand(day.kalPlus, day.kalMinus);
                }

                //aus KalorienMinus-Array löschen
                if(kalMinus!=0){
                    day.kalMinus = deleteFromArray(day.kalMinus, kalMinus);
                    day.setKalStand(day.kalPlus, day.kalMinus);
                }
            }
        }
    }

    /*
    Hilfsmethoden
     */

    //hängt neues Tag-Objekt an das Array des Users an
    public void appendDay(Day day) {
        Day[] ersatz = new Day[this.days.length+1];
        for(int i = 0; i < ersatz.length; i++) {
            if(i==ersatz.length-1) {
                ersatz[i]=day;
            } else {
                ersatz[i]=this.days[i];
            }
        }
        this.days=ersatz;
    }

    //hängt neues User-Objekt an das User-Array bei der Registrierung an (siehe RegisterController, Zeile 68 und LoginController)
    public static User[] appendUser(User[] users, User user) {
        User[] ersatz = new User[users.length+1];
        for(int i = 0; i < ersatz.length; i++) {
            if(i==ersatz.length-1) {
                ersatz[i]=user;
            } else {
                ersatz[i]=users[i];
            }
        }
        return ersatz;
    }

    //findet Eintrag im Array und löscht es
    public double[] deleteFromArray(double[] array, double wert){
        double[] ersatz = new double[array.length-1];
        boolean gefunden = false;
        for (int i = 0; i < ersatz.length; i++){
            if (array[i]==wert&&!gefunden){
                gefunden = true;
                ersatz[i] = array[i+1];
            } else if (!(array[i]==wert)&&gefunden){
                ersatz[i] = array[i+1];
            } else {
                ersatz[i] = array[i];
            }
        }
        return ersatz;
    }


}
