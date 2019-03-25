package yal.exceptions;

public class IdentificateurTableauHorsLimitesException extends AnalyseSemantiqueException {

    public IdentificateurTableauHorsLimitesException(int ligne, String m) {
        super(ligne, m);
    }

}
