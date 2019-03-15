package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.BlocDInstructions;
import yal.arbre.expressions.ExpressionLogique;
import yal.tds.TDS;
import yal.tds.Valeurs;

public class Condition extends Instruction {

    protected ExpressionLogique exp;
    protected ArbreAbstrait alors;
    protected ArbreAbstrait sinon;

    /**
     * Constructeur d'une conditionnelle
     *
     * @param n numero de ligne
     */
    public Condition(int n, ExpressionLogique eL, ArbreAbstrait alors) {
        super(n);
        this.exp = eL;
        this.alors = alors;
    }

    /**
     * Constructeur d'une condistionnelle avec sinon
     *
     * @param n numero de ligne
     */
    public Condition(int n, ExpressionLogique eL, ArbreAbstrait alors,ArbreAbstrait sinon) {
        super(n);
        this.exp = eL;
        this.alors = alors;
        this.sinon = sinon;
    }

    @Override
    public void verifier() {
        TDS.getInstance().getTableLocaleCourante().setInCondition();
        exp.verifier();
        alors.verifier();
        if(sinon != null) {
            sinon.verifier();
        }
        TDS.getInstance().getTableLocaleCourante().unsetInCondition();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("#Condition\n");
        // Créer un compteur à l'aide de Valeur pour les étiquettes
        string.append(this.exp.toMIPS());
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $v0, 0($sp)\n");
        string.append("beqz $v0, sinon" + Valeurs.getInstance().getCompteurCondition() + "\n");
        string.append(alors.toMIPS());
        string.append("b finCond"+Valeurs.getInstance().getCompteurCondition()+"\n");
        string.append("sinon" + Valeurs.getInstance().getCompteurCondition() + ": \n");
        if(sinon != null) {
            string.append(this.sinon.toMIPS());
        }
        string.append("finCond"+Valeurs.getInstance().getCompteurCondition()+":\n");
        Valeurs.getInstance().incrementerCompteurCondition();

        return string.toString();
    }
}
