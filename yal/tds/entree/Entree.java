package yal.tds.entree;

public abstract class Entree {

    protected String nom;
    protected int ligne;

    /**
     * Constructeur d'une entrée
     * @param n Nom de l'entrée
     * @param l Numéro de ligne de l'entrée
     */
    public Entree(String n, int l) {
        this.nom = n;
        this.ligne = l;
    }

    /**
     * Retourne le numéro de ligne de cette entrée
     * @return La ligne de l'entrée
     */
    public int getLigne() {
        return this.ligne;
    }

    /**
     * Retourne le nom de cette entrée
     * @return le nom de l'entrée
     */
    public String getNom() {
        return this.nom;
    }

}
