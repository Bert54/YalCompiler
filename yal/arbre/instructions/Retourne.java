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
        StringBuilder sb = new StringBuilder("#Retourne de la fonction \n");
        int nbEmpilement = TDS.getInstance().getTableLocaleCourante().getNbVariable()*4 + 12;
        // Restauration du pointeur de la pile
        sb.append("addi $sp,$sp,"+nbEmpilement+"\n");
        // Restauration de la base locale
        // TODO (8+4*nbParam quand il y aura les param)
        // Attention: ceci n'est pas un dépilement
        sb.append("lw $s7,8($s7)\n");
        // Restaurer le compteur ordinal
        sb.append("lw $ra,($sp)\n");
        // On stocke la valeur de retour
        sb.append("addi $sp,$sp,8\n");
        sb.append("sw $v0,($sp)\n");
        sb.append("addi $sp,$sp,-4\n");
        sb.append("jr $ra\n");
        return sb.toString();
    }
}
