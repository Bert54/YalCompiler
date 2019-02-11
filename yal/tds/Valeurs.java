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

    private int compteurCondition;

    private int compteurExpresionLogiqueNot;

    /**
     * Constructeur
     */
    private Valeurs() {
        this.taillePile = 0;
        this.compteurBoucle = 1;
        this.compteurComparaisonLogique = 1;
        this.compteurExpresionLogiqueNot = 1;
    }

    /**
     * Getter sur la taille de la pile
     * @return la taille de la pile
     */
    public int getTaillePile() {
        return this.taillePile;
    }

    /**
     * Incremente la taille de la pile
     */
    public void empiler() {
        this.taillePile -= 4;
    }

    /**
     * Decremente la taille de la pile
     */
    public void depiler() {
        this.taillePile += 4;
    }

    /**
     * Incremente le compteur du nombre de boucles utilises
     */
    public void incrementerCompteurBoucle() {
        this.compteurBoucle++;
    }

    /**
     * Getter sur le nombre de boucles utilises
     * @return nombre de boucles
     */
    public int getCompteurBoucle() {
        return this.compteurBoucle;
    }

    /**
     * Incremente le compteur du nombre de comparaisons effectuees
     */
    public void incrementerCompteurComparaisonLogique() {
        this.compteurComparaisonLogique++;
    }

    /**
     * Getter sur le nombre de comparaisons effectuees
     * @return nombre de comparaisons
     */
    public int getCompteurComparaisonLogique() {
        return this.compteurComparaisonLogique;
    }

    public void incrementerCompteurCondition(){
        this.compteurCondition++;
    }

    public int getCompteurCondition() {
        return this.compteurCondition;
    }

    public void incrementerCompteurExpressionLogiqueNot() {
        this.compteurExpresionLogiqueNot++;
    }

    public int getCompteurExpresionLogiqueNot() {
        return compteurExpresionLogiqueNot;
    }
}
