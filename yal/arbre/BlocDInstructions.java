package yal.arbre;

import java.util.ArrayList;

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArrayList<ArbreAbstrait> programme ;

    /**
     * Constructeur d'un bloc d'instruction
     * @param n numero de ligne
     */
    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }

    /**
     * Ajout d'un arbre abstrait au programme
     * @param a nouvel arbre
     */
    public void ajouter(ArbreAbstrait a) {
        programme.add(a) ;
    }
    
    @Override
    public String toString() {
        return programme.toString() ;
    }

    @Override
    public void verifier() {
        for (ArbreAbstrait a: this.programme) { // vérification sémantique de chaque arbre
            a.verifier();
        }
    }
    
    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("");
        for (ArbreAbstrait a: this.programme) { // génération du code MIPS pour chaque arbre
            string.append(a.toMIPS());
        }
        return string.toString();
    }

}
