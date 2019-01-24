package yal.arbre.expressions;

import yal.tds.TDS;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

public class Idf extends Expression {

    private String nom;
    private int dep;

    /**
     * Constructeur d'un identificateur
     * @param n numero de ligne
     * @param nom nom de l'identificateur
     */
    public Idf(int n, String nom) {
        super(n);
        this.nom = nom;
    }

    @Override
    public void verifier() {
        Symbole s = TDS.getInstance().identifier(new EntreeVariable(this.nom, this.getNoLigne()));
        this.dep = s.getDeplacement();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("");
        string.append(this.dep + "($s7)");  // $s7 étant la pile
        return string.toString();
    }
}
