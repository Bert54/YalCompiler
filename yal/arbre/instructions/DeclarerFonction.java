package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.exceptions.RetournerManquantException;
import yal.tds.TDS;
import yal.tds.entree.EntreeFonction;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

import java.util.ArrayList;

public class DeclarerFonction extends Instruction {

    private String nom;
    private ArrayList<Declarer> params;
    private ArbreAbstrait corps;
    private int numBloc;

    /**
     * Constructeur d'une declaration
     * @param n numero de ligne
     * @param nom nom de la variable
     */
    public DeclarerFonction(int n, String nom) {
        super(n);
        this.nom = nom;
    }

    public DeclarerFonction(int n, String nom, ArrayList<Declarer> parametres, ArbreAbstrait corps) {
        super(n);
        this.nom = nom;
        this.params = parametres;
        this.corps = corps;
    }

    @Override
    public void verifier() {
        Symbole s = TDS.getInstance().identifier(new EntreeFonction(this.nom, this.getNoLigne(), this.params.size()));
        this.numBloc = s.getNumBloc();
        TDS.getInstance().entreeBlocVerifier(this.numBloc);
        for (Declarer d: this.params) {
            d.verifier();
        }
        this.corps.verifier();
        if (TDS.getInstance().getTableLocaleCourante().getNbRetour() == 0) {
            throw new RetournerManquantException(this.getNoLigne(), "Pas de retour dans la fonction '" + this.nom + "'");
        }
        TDS.getInstance().sortieBlocVerifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder();
        return string.toString();
    }

}
