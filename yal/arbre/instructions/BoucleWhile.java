package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.ExpressionLogique;
import yal.tds.Valeurs;

public class BoucleWhile extends Instruction {

    private ExpressionLogique expL;
    private ArbreAbstrait bloc;

    /**
     * Constructeur d'une instruction
     * @param n numero de ligne
     * @param aa arbre abstrait contenant une liste d'instructions
     * @param exp expression logique a evaluer pour entrer ou non dans la boucle
     */
    public BoucleWhile(int n, ArbreAbstrait aa, ExpressionLogique exp) {
        super(n);
        this.expL = exp;
        this.bloc = aa;
    }

    @Override
    public void verifier() {
        this.expL.verifier();
        this.bloc.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("#Boucle tantque\n");
        string.append("tantque" + Valeurs.getInstance().getCompteurBoucle() + ": \n");  // étiquette tantque
        string.append(this.expL.toMIPS());
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $v0, 0($sp)\n");
        string.append("beqz $v0, fintantque" + Valeurs.getInstance().getCompteurBoucle() + "\n");   // on se dirige
        string.append(this.bloc.toMIPS());                              // vers fintantque si condition non remplie
        string.append("b tantque" + Valeurs.getInstance().getCompteurBoucle() + "\n");  // on retourne au tantque
        string.append("fintantque" + Valeurs.getInstance().getCompteurBoucle() + ": \n");   // étiquette fintantque
        Valeurs.getInstance().incrementerCompteurBoucle();
        return string.toString();
    }
}
