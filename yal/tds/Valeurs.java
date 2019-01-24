package yal.tds;

public class Valeurs {

    private static Valeurs instance = new Valeurs();

    public static Valeurs getInstance() {
        return instance;
    }

    private int taillePile;

    private Valeurs() {
        this.taillePile = 0;
    }

    public int getTaillePile() {
        return this.taillePile;
    }

    public void empiler() {
        this.taillePile -= 4;
    }

    public void depiler() {
        this.taillePile += 4;
    }

}
