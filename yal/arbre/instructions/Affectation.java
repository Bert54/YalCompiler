package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;

public class Affectation extends Instruction {

    private Idf idf;
    private Expression exp;

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
        string.append("lw $v0, " + idf.toMIPS() + "\n");
        string.append("li $v0, " + exp.toMIPS() + "\n");
        string.append("sw $v0, " + idf.toMIPS() + "\n");
        return string.toString();
    }
}
