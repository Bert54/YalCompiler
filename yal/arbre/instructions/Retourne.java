package yal.arbre.instructions;

import yal.arbre.expressions.ExpressionBinaire;
import yal.exceptions.RetournerIllegalException;
import yal.tds.TDS;

public class Retourne extends Instruction {

    ExpressionBinaire exp;

    public Retourne(int n, ExpressionBinaire exp) {
        super(n);
        this.exp = exp;
    }

    @Override
    public void verifier() {
        if (TDS.getInstance().getTableLocaleCourante().getNumBloc() == 0) {
            throw new RetournerIllegalException(this.getNoLigne(), "Instruction de retour trouvée dans la fonction principale");
        }
        TDS.getInstance().getTableLocaleCourante().incrementerNbRetour();
        this.exp.verifier();
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
