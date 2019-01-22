package yal.arbre.instructions;

import yal.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    protected Expression exp ;

    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    @Override
    public void verifier() {
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("");
        string.append("li $v0, " + this.exp.toMIPS() + "\n");
        string.append("move $a0, $v0\n");
        string.append("li $v0, 1\n");
        string.append("syscall\n");
        string.append("li $v0, 4\n");   // Code du print pour le retour à la ligne
        string.append("la $a0, ln\n");  // Saut à la ligne
        string.append("syscall\n");     // Appel du saut de ligne
        return string.toString();
    }

}
