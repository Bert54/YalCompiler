package yal.arbre.expressions;

import yal.tds.TDS;
import yal.tds.entree.EntreeFonction;

import java.util.ArrayList;

public class AppelFonction extends ExpressionBinaire {

    private String nom;
    private ArrayList<ExpressionBinaire> parametres;

    public AppelFonction(int n, String nom, ArrayList<ExpressionBinaire> params) {
        super(n);
        this.nom = nom;
        this.parametres = params;
    }

    public void ajouter(ExpressionBinaire exp) {
        this.parametres.add(exp);
    }

    @Override
    public void verifier() {
        TDS.getInstance().identifier(new EntreeFonction(this.nom, this.getNoLigne(), this.parametres.size()));
        for (ExpressionBinaire exp: this.parametres) {
            exp.verifier();
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder();
        // Pour notre cher Dylan : une étiquette de fonction est représenté par : nomFonction+nbparamètres
        string.append("addi $sp, $sp, -4\n");
        string.append("jal " + this.nom + this.parametres.size() + "\n");
        return string.toString();
    }
}
