package yal.arbre.expressions;

public class IdfTab extends ExpressionBinaire {

    private String nom;
    private int origine;
    private ExpressionBinaire position;

    public IdfTab(int n, String nom, ExpressionBinaire exp) {
        super(n);
        this.nom = nom;
        this.position = exp;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder();

        return string.toString();
    }

}
