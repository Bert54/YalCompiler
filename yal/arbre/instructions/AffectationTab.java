package yal.arbre.instructions;

import yal.arbre.expressions.ExpressionBinaire;
import yal.arbre.expressions.IdfTab;

public class AffectationTab extends Instruction {

    private IdfTab idft;
    private ExpressionBinaire exp;

    public AffectationTab(int n, IdfTab tab, ExpressionBinaire e) {
        super(n);
        this.idft = tab;
        this.exp = e;
    }

    @Override
    public void verifier() {
        this.idft.verifier();
        this.exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder();
        string.append(this.exp.toMIPS());
        string.append(this.idft.getPosition().toMIPS());
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $v0, 0($sp)\n");
        int enjNeg = this.idft.getEnjambe() - this.idft.getEnjambe() * 2;
        string.append("li $t8, " + enjNeg + "\n");
        string.append("mult $v0, $t8\n");
        string.append("mflo $v0\n");
        string.append("li $t8, " + this.idft.getOrigine() + "\n");
        string.append("add $a1, $v0, $t8\n");
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $v0, 0($sp)\n");
        string.append("sw $v0, 0($a1)\n");
        return string.toString();
    }
}
