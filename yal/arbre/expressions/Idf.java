package yal.arbre.expressions;

import yal.tds.TDS;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

public class Idf extends ExpressionBinaire {

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
        this.dep = 52525235;
    }

    /**
     * Getter sur le deplacement de la variable dans la pile
     * @return deplacement dans la pile
     */
    public int getDeplacement() {
        return this.dep;
    }

    public String getNom() {
        return this.nom;
    }

    @Override
    public void verifier() {
        Symbole s = TDS.getInstance().getTableLocaleCourante().identifier(new EntreeVariable(this.nom, this.getNoLigne()));
        this.dep = s.getDeplacement();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder();
        string.append("lw $v0, " + this.dep + "($s7)\n");
        string.append("sw $v0, 0($sp)\n");
        string.append("addi $sp, $sp, -4\n");
        return string.toString();
    }
}
