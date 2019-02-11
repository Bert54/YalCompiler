package yal.arbre.expressions;

import yal.tds.Valeurs;

public class ExpressionLogiqueNon extends ExpressionLogique {

    ExpressionLogique expl;

    public ExpressionLogiqueNon(int n, ExpressionLogique e) {
        super(n);
        this.expl = e;
    }

    @Override
    public void verifier() {
        this.expl.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("");
        this.expl.toMIPS();
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $v0, 0($sp)\n");
        string.append("beqz $v0, non" + Valeurs.getInstance().getCompteurExpresionLogiqueNot() + "\n");
        string.append("li $v0, 0\n");
        string.append("b finNon"+Valeurs.getInstance().getCompteurExpresionLogiqueNot()+"\n");
        string.append("non" + Valeurs.getInstance().getCompteurExpresionLogiqueNot()+ ": \n");
        string.append("li $v0, 1\n");
        string.append("finNon"+Valeurs.getInstance().getCompteurExpresionLogiqueNot()+":\n");
        string.append("sw $v0, 0($sp)\n");
        string.append("addi $sp, $sp, -4\n");
        Valeurs.getInstance().incrementerCompteurExpressionLogiqueNot();
        return string.toString();
    }

}
