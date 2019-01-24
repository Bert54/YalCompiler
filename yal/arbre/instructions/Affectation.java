package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;

public class Affectation extends Instruction {

    private Idf idf;
    private Expression exp;

    /**
     * Constructeur d'une affectation
     * @param n numero de ligne
     * @param idf identificateur
     * @param exp expression
     */
    public Affectation(int n, Idf idf, Expression exp) {
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
        StringBuilder string = new StringBuilder("");
        string.append("lw $v0, " + idf.toMIPS() + "\n");    // on charge la variable
        string.append("li $v0, " + exp.toMIPS() + "\n");    // code du print
        string.append("sw $v0, " + idf.toMIPS() + "\n");    // on empile la variable
        return string.toString();
    }
}
