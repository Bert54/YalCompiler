package yal.arbre.instructions;

import yal.arbre.expressions.ConstanteEntiere;
import yal.arbre.expressions.ExpressionBinaire;
import yal.arbre.expressions.ExpressionBinaireNegative;
import yal.exceptions.TableauDimensionsIncorrectsException;
import yal.tds.TDS;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

public class DeclarerTableau extends Instruction {

    private String nom;
    private int origine;
    private ExpressionBinaire taille;
    private int enjambe;

    /**
     * COnstructeur d'une déclaration de tableau
     * @param n numéro de ligne
     * @param nom identificateur du tableau
     * @param exp expression correspondant à la taille du tableau
     */
    public DeclarerTableau(int n, String nom, ExpressionBinaire exp) {
        super(n);
        this.nom = nom;
        this.taille = exp;
    }

    @Override
    public void verifier() {
        this.taille.verifier();
        // Si on vérifie un tableau statique, on vérifie que sa taille est strictement positive
        Symbole s = TDS.getInstance().getTableLocaleCourante().identifier(new EntreeVariable(this.nom, this.getNoLigne()));
        if ((this.taille instanceof ExpressionBinaireNegative && ((ExpressionBinaireNegative) this.taille).getExp() instanceof ConstanteEntiere) || (this.taille instanceof ConstanteEntiere && Integer.parseInt(this.taille.toString()) == 0)) {
            throw new TableauDimensionsIncorrectsException(this.getNoLigne(), "Dimensions du tableau nulles ou négatives");
        }
        this.origine = s.getDeplacement();
        this.enjambe = s.getEnjambe();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder();
        // Si le tableau est statique, on a rien à faire puisque la place a déjà été réservée
        // Cependant, on est obligé de faire quelque chose si le tableau est dynamique, étant donné qu'on a pas encore réservé de place pour lui
        if (!(this.taille instanceof ConstanteEntiere)) {
            string.append(this.taille.toMIPS()); // Evaluation de l'expression correspondant à la taille du tableau
            string.append("addi $sp, $sp, 4\n"); // Recupération de la taille calculée et allocation de la place nécessaire
            string.append("lw $v0, 0($sp)\n");
            int enjNeg = this.enjambe - this.enjambe * 2; // enjambe positif, on doit la rendre négative car on doit descendre dans la pile
            string.append("li $t8, " + enjNeg + "\n");
            string.append("mult $v0, $t8\n");
            string.append("mflo $v0\n");
            string.append("sw $sp, " + this.origine +"($s7)\n"); // enregistrement de l'adresse d'implémentation du tableau dans la place réservé par le compilateur
            string.append("add $sp, $sp, $v0\n");
        }
        return string.toString();
    }
}
