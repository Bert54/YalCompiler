package yal.arbre.expressions;

public abstract class Constante extends ExpressionBinaire {

    protected String cste ;

    /**
     * Constructeur d'une constante
     * @param texte constante en chaine de caracteres
     * @param n numero de ligne
     */
    protected Constante(String texte, int n) {
        super(n) ;
        cste = texte ;
    }
    
    @Override
    public void verifier() {
    }

    @Override
    public String toString() {
        return cste ;
    }

}
