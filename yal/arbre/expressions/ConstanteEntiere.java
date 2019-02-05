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
        string.append("li $v0, " + this.cste + "\n");
        string.append("sw $v0, 0($sp)\n");
        string.append("addi $sp, $sp, -4\n");
        return string.toString();
    }

}
