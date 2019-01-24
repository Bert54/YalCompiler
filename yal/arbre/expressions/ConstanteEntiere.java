package yal.arbre.expressions;

public class ConstanteEntiere extends Constante {

    /**
     * Constructeur d'une constante entiere
     * @param texte valeur de la constante en chaine de caractères
     * @param n numero de ligne
     */
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("");
        string.append(this.cste);
        return string.toString();
    }

}
