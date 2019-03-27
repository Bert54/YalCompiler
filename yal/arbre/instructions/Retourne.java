package yal.arbre.instructions;

import yal.arbre.expressions.ExpressionBinaire;
import yal.arbre.expressions.Idf;
import yal.arbre.expressions.IdfTab;
import yal.exceptions.RetournerIllegalException;
import yal.tds.TDS;
import yal.tds.Valeurs;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;
import yal.tds.symbole.SymboleTableau;

public class Retourne extends Instruction {

    ExpressionBinaire exp;

    /**
     * Constructeur d'un retour de fonction
     * @param n numero de ligne
     * @param exp expression de retour
     */
    public Retourne(int n, ExpressionBinaire exp) {
        super(n);
        this.exp = exp;
    }

    @Override
    public void verifier() {
        if (TDS.getInstance().getTableLocaleCourante().getNumBloc() == 0) {
            throw new RetournerIllegalException(this.getNoLigne(), "Instruction de retour trouvée dans la fonction principale");
        }
        if (exp instanceof Idf) {
            Symbole s = TDS.getInstance().getTableLocaleCourante().identifier(new EntreeVariable(((Idf) exp).getNom(), 0));
            if (s instanceof SymboleTableau) {
                throw new RetournerIllegalException(this.getNoLigne(), "Retour de tableau non autorisé");
            }
        }
        switch (TDS.getInstance().getTableLocaleCourante().getInCondition()) {
            case 0:
                TDS.getInstance().getTableLocaleCourante().incrementerNbRetour();
                break;
            case 1:
            case 2:
                TDS.getInstance().getTableLocaleCourante().incrementerNbRetourCondition();
                if (TDS.getInstance().getTableLocaleCourante().getNbRetourCondition() == TDS.getInstance().getTableLocaleCourante().getNbCasCondition()) {
                    TDS.getInstance().getTableLocaleCourante().incrementerNbRetour();
                }
                break;
        }
        this.exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder("#Retour de la fonction \n");
        sb.append(this.exp.toMIPS()); // Génération du code MIPS de l'expression à retourner
        sb.append("addi $sp,$sp, 4\n");
        sb.append("lw $v0, 0($sp)\n");
        // Dépilement des variables locaux
        int nbEmpilement = Valeurs.getInstance().getTaillePile(TDS.getInstance().getTableLocaleCourante().getNumBloc()); // Ne sert plus avec la nouvelle méthode.
        nbEmpilement = Math.abs(nbEmpilement) + 8;
        /*//Valeurs.getInstance().depiler(TDS.getInstance().getTableLocaleCourante().getNumBloc());
        // Restauration du pointeur de la pile
        sb.append("addi $sp,$sp,"+nbEmpilement+"\n");
        // Restauration de la base locale
        // Attention: ceci n'est pas un dépilement
        sb.append("lw $t8, 0($sp)\n");
        sb.append("addi $sp,$sp, 4\n");
        // Restaurer le compteur ordinal
        sb.append("lw $ra,($sp)\n");
        // On stocke la valeur de retour
        sb.append("addi $sp, $sp, 4\n");
        sb.append("move $s7, $t8\n");*/

        // Nouvelle méthode : restauration de la base locale du bloc englobant et valeur de retour à partir de la base locale courante
        sb.append("move $sp, $s7\n");
        sb.append("lw $ra, 12($s7)\n");
        sb.append("lw $s7, 8($s7)\n");
        sb.append("addi $sp, $sp, 12\n");
        int par = TDS.getInstance().getTableLocaleCourante().getNbParams() - 1;
        if (par < 0) {
            par = 0;
        }
        // On empile la valeur de retour dans l'espacé réservé lors de l'appel de fonction
        sb.append("sw $v0, " + (par * 4 + 8) + "($sp)\n");
        if (TDS.getInstance().getTableLocaleCourante().getNbParams() > 0) {
            sb.append("addi $sp, $sp, -4\n");
        }
        // Retour à l'endroit où on a appelé la fonction
        sb.append("jr $ra\n");
        return sb.toString();
    }
}
