package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.tds.TDS;

import java.util.ArrayList;

public class DeclarerFonction extends Instruction {

    private String nom;
    private ArrayList<Declarer> param;
    private ArbreAbstrait corps;

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
        this.param = parametres;
        this.corps = corps;
    }

    @Override
    public void verifier() {
        TDS.getInstance().entrerBlocVerifier();
        for (Declarer d: this.param) {
            d.verifier();
        }
        this.corps.verifier();
        TDS.getInstance().sortieBlocVerifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder();
        return string.toString();
    }

}
