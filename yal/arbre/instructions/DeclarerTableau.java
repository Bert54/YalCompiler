package yal.arbre.instructions;

import yal.arbre.expressions.ExpressionBinaire;

public class DeclarerTableau extends Instruction {

    private String nom;
    private int origine;
    private ExpressionBinaire taille;

    public DeclarerTableau(int n, String nom, ExpressionBinaire exp) {
        super(n);
        this.nom = nom;
        this.taille = exp;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder();

        return string.toString();
    }
}
