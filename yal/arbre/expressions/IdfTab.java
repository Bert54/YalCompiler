package yal.arbre.expressions;

import yal.exceptions.IdentificateurTableauHorsLimitesException;
import yal.tds.TDS;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

public class IdfTab extends ExpressionBinaire {

    private String nom;
    private static int origine;
    private static int enjambe;
    private ExpressionBinaire position;

    /**
     * COnstructeur d'un identificateur de tableau
     * @param n numéro de ligne
     * @param nom nom du tableau
     * @param exp expression correspondant à l'indice du tableau
     */
    public IdfTab(int n, String nom, ExpressionBinaire exp) {
        super(n);
        this.nom = nom;
        this.position = exp;
    }

    /**
     * retourne l'origine virtuelle du tableau
     * @return l'origine virtuelle du tableau
     */
    public int getOrigine() {
        return this.origine;
    }

    /**
     * retourne l'enjambé du tableau
     * @return l'enjambé du tableau
     */
    public int getEnjambe() {
        return this.enjambe;
    }

    /**
     * retourne l'expression correspondant à l'indice du tableau
     * @return l'expression correspondant à l'indice du tableau
     */
    public ExpressionBinaire getPosition() {
        return this.position;
    }

    @Override
    public void verifier() {
        this.position.verifier();
        Symbole s = TDS.getInstance().getTableLocaleCourante().identifier(new EntreeVariable(this.nom, this.getNoLigne()));
        // Si c'est un tableau statique, alors on peut effectuer la vérification des bornes
        ExpressionBinaire exp = s.getExpression();
        // Cas où indice négatif
        if (this.position instanceof ExpressionBinaireNegative && ((ExpressionBinaireNegative) this.position).getExp() instanceof ConstanteEntiere) {
            throw new IdentificateurTableauHorsLimitesException(this.getNoLigne(), "Indice de tableau statique en dehors des limites");
        }
        // Cas où indice >= borne supérieur du tableau
        if (this.position instanceof ConstanteEntiere && exp instanceof ConstanteEntiere) {
            int constante = Integer.parseInt(position.toString());
            int bornesup = Integer.parseInt(exp.toString());
            if (constante >= bornesup) {
                throw new IdentificateurTableauHorsLimitesException(this.getNoLigne(), "Indice de tableau statique en dehors des limites");
            }
        }
        this.enjambe = s.getEnjambe();
        this.origine = s.getDeplacement();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder();
        string.append(this.position.toMIPS()); // Evaluation de l'expression correspondant à l'indice du tableau
        string.append("addi $sp, $sp, 4\n"); // Récupération de l'indice calculé et calcul de la position dans la pile à partir de cet indice
        string.append("lw $v0, 0($sp)\n");
        int enjNeg = this.enjambe - this.enjambe * 2; // enjambe positif, on doit la rendre négative car on doit descendre dans la pile
        string.append("li $t8, " + enjNeg + "\n");
        string.append("mult $v0, $t8\n");
        string.append("mflo $v0\n");
        string.append("li $t8, " + this.origine + "\n");
        string.append("add $a1, $v0, $t8\n");
        string.append("lw $v0, 0($a1)\n"); // Position de l'indice du tableau retrouvée dans la pile : on charge sa valeur et on la sauvegarde en tête de pile
        string.append("sw $v0, 0($sp)\n");
        string.append("addi $sp, $sp, -4\n");
        return string.toString();
    }

}
