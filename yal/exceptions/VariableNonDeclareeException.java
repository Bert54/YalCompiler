package yal.exceptions;

public class VariableNonDeclareeException extends AnalyseSemantiqueException {

    public VariableNonDeclareeException(int ligne, String m) {
        super(ligne, m);
    }

}
