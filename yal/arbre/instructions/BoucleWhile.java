package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.ExpressionLogique;
import yal.tds.Valeurs;

public class BoucleWhile extends Instruction {

    private ExpressionLogique expL;
    private ArbreAbstrait bloc;

    /**
     * Constructeur d'une instruction
     *
     * @param n numero de ligne
     */
    public BoucleWhile(int n, ArbreAbstrait aa, ExpressionLogique exp) {
        super(n);
        this.expL = exp;
        this.bloc = aa;
    }

    @Override
    public void verifier() {
        this.expL.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("");
        string.append("tantque" + Valeurs.getInstance().getCompteurBoucle() + ": \n");
        string.append(this.expL.toMIPS());
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $v0, 0($sp)\n");
        string.append("beqz $v0, fintantque" + Valeurs.getInstance().getCompteurBoucle() + "\n");
        string.append(this.bloc.toMIPS());
        string.append("b tantque" + Valeurs.getInstance().getCompteurBoucle() + "\n");
        string.append("fintantque" + Valeurs.getInstance().getCompteurBoucle() + ": \n");
        Valeurs.getInstance().incrementerCompteurBoucle();
        return string.toString();
    }
}
