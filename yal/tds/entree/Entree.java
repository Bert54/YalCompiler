package yal.tds.entree;

public abstract class Entree {

    protected String nom;
    protected int ligne;

    public Entree(String n, int l) {
        this.nom = n;
        this.ligne = l;
    }

    public int getLigne() {
        return this.ligne;
    }

    public String getNom() {
        return this.nom;
    }

}
