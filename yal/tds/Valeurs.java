/*
 *  Ce singleton sert à stocker toutes les positions utiles
 */

package yal.tds;

public class Valeurs {

    private static Valeurs instance = new Valeurs();

    public static Valeurs getInstance() {
        return instance;
    }

    private int taillePile; // Taille actuelle de la pile

    private Valeurs() {
        this.taillePile = 0;
    }

    /**
     * Retourne la taille de la pile
     * @return la taille de la pile
     */
    public int getTaillePile() {
        return this.taillePile;
    }

    /**
     * Incrémente la taille de la pile
     */
    public void empiler() {
        this.taillePile -= 4;
    }

    /**
     * Décrémente la taille de la pile
     */
    public void depiler() {
        this.taillePile += 4;
    }

}
