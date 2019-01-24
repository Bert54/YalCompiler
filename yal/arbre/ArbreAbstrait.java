package yal.arbre;

public abstract class ArbreAbstrait {
    
    // numéro de ligne du début de l'instruction
    protected int noLigne ;

    /**
     * Constructeur de l'arbre abstrait
     * @param n numero de ligne
     */
    protected ArbreAbstrait(int n) {
        noLigne = n ;
    }

    /**
     * Getter sur le numero de la ligne
     * @return numero de la ligne
     */
    public int getNoLigne() {
            return noLigne ;
    }

    /**
     * Verification semantique de l'arbre
     */
    public abstract void verifier();

    /**
     * Ecriture du code MIPS
     * @return code MIPS
     */
    public abstract String toMIPS();

}
