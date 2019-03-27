/*
 *  Ce singleton sert à stocker toutes les positions utiles
 */

package yal.tds;

import yal.arbre.expressions.ConstanteEntiere;
import yal.arbre.expressions.ExpressionBinaire;

import java.util.HashMap;

public class Valeurs {

    private static Valeurs instance = new Valeurs();

    public static Valeurs getInstance() {
        return instance;
    }

    private HashMap<Integer, Integer> taillePiles;

    private int taillePile; // Taille actuelle de la pile

    private int compteurBoucle;

    private int compteurComparaisonLogique;

    private int compteurCondition;

    private int compteurExpresionLogiqueNot;

    private int compteurBooleen;

    private int nbRetourner;

    private int nbFonctionPasse;

    /**
     * Constructeur
     */
    private Valeurs() {
        this.taillePile = 0;
        this.taillePiles = new HashMap<>();
        this.ajouterCompteurPile(0);
        this.compteurBoucle = 1;
        this.compteurComparaisonLogique = 1;
        this.compteurCondition = 1;
        this.compteurExpresionLogiqueNot = 1;
        this.compteurBooleen = 1;
        this.nbRetourner = 0;
        this.nbFonctionPasse = 0;
    }


    /**
     * Ajoute un nouveau compteur de pile pour une nouvelle table locale
     * @param numBloc numéro de bloc de la nouvelle table locale
     */
    public void ajouterCompteurPile(int numBloc) {
        this.taillePiles.put(numBloc, 0);
    }

    /**
     * Getter sur la taille de la pile
     * @return la taille de la pile
     */
    public int getTaillePile(int numBloc) {
        return this.taillePiles.get(numBloc);
    }

    /**
     * Incremente la taille de la pile de la table locale courante (variable)
     */
    public void empiler(int numBloc) {
        int taille = this.taillePiles.get(numBloc);
        taille -= 4;
        this.taillePiles.remove(numBloc);
        this.taillePiles.put(numBloc, taille);
    }

    /**
     * Incremente la taille de la pile de la table locale courante (tableau)
     */
    public void empilerTableau(int numBloc, ExpressionBinaire tab) {
        int taille = this.taillePiles.get(numBloc);
        if (tab instanceof ConstanteEntiere) {
            taille -= Integer.parseInt(tab.toString()) * 4; // Tableau statique : on connait sa taille, on incrémente donc la pile en conséquence
        }
        else {
            taille -= 4; // Tableau dynamique : sa taille sera déterminé lors de son exécution, on incrémente donc la pile que pour l'espace qui contiendra son adresse d'implémentation
        }
        this.taillePiles.remove(numBloc);
        this.taillePiles.put(numBloc, taille);
    }

    /**
     * Decremente la taille de la pile
     */
    public void depiler(int numBloc) {
        this.taillePiles.remove(numBloc);
        this.taillePiles.put(numBloc, 0);
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

    /**
     * Incrémente le compteur de conditions
     */
    public void incrementerCompteurCondition(){
        this.compteurCondition++;
    }

    /**
     * Récupère le compteur de conditions
     * @return compteur de conditions
     */
    public int getCompteurCondition() {
        return this.compteurCondition;
    }

    /**
     * Incrémente le compteur d'inversion d'expression logique
     */
    public void incrementerCompteurExpressionLogiqueNot() {
        this.compteurExpresionLogiqueNot++;
    }

    /**
     * Récupère le compteur de conditions
     * @return compteur de conditions
     */
    public int getCompteurExpresionLogiqueNot() {
        return this.compteurExpresionLogiqueNot;
    }

    /**
     * Recupère le compteur d'inversion d'expression logique
     * @return compteur d'inversion d'expression logique
     */
    public int getCompteurBooleen() {
        return compteurBooleen;
    }

    /**
     * Incrémente le compteur d'inversion d'expression logique
     */
    public void incrementerCompteurBoleen() {
        this.compteurBooleen++;
    }

    /**
     * Incrémente le compteur du passeur de fonctions
     */
    public void incrementerNbFontionPasse() {
        this.nbFonctionPasse++;
    }

    /**
     * Récupère le compeur du passeur de fonctions
     * @return compteur du passeur de fonctions
     */
    public int getNbFonctionPasse() {
        return this.nbFonctionPasse;
    }

}
