package yal.arbre.expressions;

import yal.tds.Valeurs;

public class ExpressionLogiqueNon extends ExpressionLogique {

    ExpressionLogique expl;

    /**
     * Constructeur d'une expression logique avec inversion
     * @param n Numéro de ligne
     * @param e L'expression logique à inverser
     */
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
        // Evaluation de l'expression logique sans le non
        this.expl.toMIPS();
        // Récupération de la valeur de retour de l'expression logique et inversion de son résultat booléen
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
