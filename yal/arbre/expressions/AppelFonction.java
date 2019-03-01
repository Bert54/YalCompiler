package yal.arbre.expressions;

import yal.arbre.instructions.Declarer;
import yal.exceptions.RetournerManquantException;
import yal.tds.TDS;
import yal.tds.entree.EntreeFonction;
import yal.tds.symbole.Symbole;

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
        return null;
    }
}
