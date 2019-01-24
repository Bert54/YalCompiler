package yal.arbre;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArrayList<ArbreAbstrait> programme ;

    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }
    
    public void ajouter(ArbreAbstrait a) {
        programme.add(a) ;
    }
    
    @Override
    public String toString() {
        return programme.toString() ;
    }

    @Override
    public void verifier() {
        for (ArbreAbstrait a: this.programme) {
            a.verifier();
        }
    }
    
    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("");
        for (ArbreAbstrait a: this.programme) {
            string.append(a.toMIPS());
        }
        return string.toString();
    }

}
