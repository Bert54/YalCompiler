package yal.exceptions;

public class TableauDimensionsIncorrectsException extends AnalyseSemantiqueException {

    public TableauDimensionsIncorrectsException(int ligne, String m) {
        super(ligne, m);
    }

}
