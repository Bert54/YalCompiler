package yal.arbre.instructions;

import yal.arbre.expressions.ExpressionBinaire;
import yal.arbre.expressions.Idf;

public class Affectation extends Instruction {

    private Idf idf;
    private ExpressionBinaire exp;

    /**
     * Constructeur d'une affectation
     * @param n numero de ligne
     * @param idf identificateur
     * @param exp expression
     */
    public Affectation(int n, Idf idf, ExpressionBinaire exp) {
        super(n);
        this.idf = idf;
        this.exp = exp;
    }

    @Override
    public void verifier() {
        this.idf.verifier();
        this.exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("#Affectation\n");
        string.append(this.exp.toMIPS());
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $v0, 0($sp)\n");    // on charge la variable
        string.append("sw $v0, " + this.idf.getDeplacement() + "($s7)\n");
        return string.toString();
    }
}
