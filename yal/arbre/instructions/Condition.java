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
    public Condition(int n, ExpressionLogique eL, ArbreAbstrait alors, ArbreAbstrait sinon) {
        super(n);
        this.exp = eL;
        this.alors = alors;
        this.sinon = sinon;
    }

    @Override
    public void verifier() {
        exp.verifier();
        if (alors != null) {
            TDS.getInstance().getTableLocaleCourante().setInCondition(1);
            TDS.getInstance().getTableLocaleCourante().incrementerNbCasCondition();
            alors.verifier();
        }
        if(sinon != null) {
            TDS.getInstance().getTableLocaleCourante().setInCondition(2);
            TDS.getInstance().getTableLocaleCourante().incrementerNbCasCondition();
            sinon.verifier();
        }
        TDS.getInstance().getTableLocaleCourante().setInCondition(0);
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("#Condition\n");
        // Créer un compteur à l'aide de Valeur pour les étiquettes
        final int cpt = Valeurs.getInstance().getCompteurCondition();
        Valeurs.getInstance().incrementerCompteurCondition();
        string.append(this.exp.toMIPS());
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $v0, 0($sp)\n");
        string.append("beqz $v0, sinon" + cpt + "\n");
        if (alors != null) {
            string.append(alors.toMIPS());
        }
        string.append("b finCond"+ cpt +"\n");
        string.append("sinon" + cpt + ": \n");
        if(sinon != null) {
            string.append(this.sinon.toMIPS());
        }
        string.append("finCond"+ cpt +":\n");
        return string.toString();
    }
}
