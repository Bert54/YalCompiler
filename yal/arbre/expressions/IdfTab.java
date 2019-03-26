package yal.arbre.expressions;

import yal.exceptions.IdentificateurTableauHorsLimitesException;
import yal.tds.TDS;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

public class IdfTab extends ExpressionBinaire {

    private String nom;
    private int origine;
    private int enjambe;
    private ExpressionBinaire position;

    public IdfTab(int n, String nom, ExpressionBinaire exp) {
        super(n);
        this.nom = nom;
        this.position = exp;
    }

    public int getOrigine() {
        return this.origine;
    }

    public int getEnjambe() {
        return this.enjambe;
    }

    public ExpressionBinaire getPosition() {
        return this.position;
    }

    @Override
    public void verifier() {
        this.position.verifier();
        Symbole s = TDS.getInstance().getTableLocaleCourante().identifier(new EntreeVariable(this.nom, this.getNoLigne()));
        // Si c'est un tableau statique, alors on peut effectuer la vérification des bornes
        ExpressionBinaire exp = s.getExpression();
        if (this.position instanceof ExpressionBinaireNegative && ((ExpressionBinaireNegative) this.position).getExp() instanceof ConstanteEntiere) {
            throw new IdentificateurTableauHorsLimitesException(this.getNoLigne(), "Indice de tableau statique en dehors des limites");
        }
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
        string.append(this.position.toMIPS());
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $v0, 0($sp)\n");
        int enjNeg = this.enjambe - this.enjambe * 2;
        string.append("li $t8, " + enjNeg + "\n");
        string.append("mult $v0, $t8\n");
        string.append("mflo $v0\n");
        string.append("li $t8, " + this.origine + "\n");
        string.append("add $a1, $v0, $t8\n");
        string.append("lw $v0, 0($a1)\n");
        string.append("sw $v0, 0($sp)\n");
        string.append("addi $sp, $sp, -4\n");
        return string.toString();
    }

}
