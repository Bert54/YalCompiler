package yal.exceptions;

public class FonctionNonDeclareeException extends AnalyseSemantiqueException {

    public FonctionNonDeclareeException(int ligne, String m) {
        super(ligne, m);
    }

}
