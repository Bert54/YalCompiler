package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;

public class Ecrire extends Instruction {

    protected Expression exp ;

    /**
     * Constructeur d'une ecriture
     * @param e expression a ecrire
     * @param n numero de ligne
     */
    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    @Override
    public void verifier() {
        exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("#Ecriture\n");
        string.append(this.exp.toMIPS());
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $v0, 0($sp)\n");
        string.append("move $a0, $v0\n");   // placement de $v0 dans $a0
        string.append("li $v0, 1\n");       // code du print d'un entier
        string.append("syscall\n");         // appel du système pour l'affichage de l'entier
        string.append(this.sautDeLigne());
        return string.toString();
    }

}
