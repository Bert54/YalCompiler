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

    private int compteurBoucle;

    private int compteurComparaisonLogique;

    private Valeurs() {
        this.taillePile = 0;
        this.compteurBoucle = 1;
        this.compteurComparaisonLogique = 1;
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

    public void incrementerCompteurBoucle() {
        this.compteurBoucle++;
    }

    public int getCompteurBoucle() {
        return this.compteurBoucle;
    }

    public void incrementerCompteurComparaisonLogique() {
        this.compteurComparaisonLogique++;
    }

    public int getCompteurComparaisonLogique() {
        return this.compteurComparaisonLogique;
    }


}
