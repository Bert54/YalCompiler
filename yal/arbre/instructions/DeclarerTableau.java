package yal.arbre.instructions;

import yal.arbre.expressions.ConstanteEntiere;
import yal.arbre.expressions.ExpressionBinaire;
import yal.arbre.expressions.ExpressionBinaireNegative;
import yal.exceptions.IdentificateurTableauHorsLimitesException;
import yal.exceptions.TableauDimensionsIncorrectsException;
import yal.tds.TDS;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

public class DeclarerTableau extends Instruction {

    private String nom;
    private int origine;
    private ExpressionBinaire taille;

    public DeclarerTableau(int n, String nom, ExpressionBinaire exp) {
        super(n);
        this.nom = nom;
        this.taille = exp;
    }

    @Override
    public void verifier() {
        Symbole s = TDS.getInstance().getTableLocaleCourante().identifier(new EntreeVariable(this.nom, this.getNoLigne()));
        if ((this.taille instanceof ExpressionBinaireNegative && ((ExpressionBinaireNegative) this.taille).getExp() instanceof ConstanteEntiere) || (this.taille instanceof ConstanteEntiere && Integer.parseInt(this.taille.toString()) == 0)) {
            throw new TableauDimensionsIncorrectsException(this.getNoLigne(), "Dimensions du tableau nulles ou négatives");
        }
        this.origine = s.getDeplacement();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder();

        return string.toString();
    }
}
