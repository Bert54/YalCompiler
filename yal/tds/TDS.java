package yal.tds;

import yal.exceptions.DoubleDeclarationException;
import yal.exceptions.VariableNonDeclareeException;
import yal.tds.entree.Entree;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

import java.util.HashMap;

public class TDS {
    private static TDS instance = new TDS();

    public static TDS getInstance() {
        return instance;
    }

    private HashMap<Entree, Symbole> table;

    private TDS() {
        this.table = new HashMap<>();
    }

    public void ajouter(Entree e, Symbole s) throws DoubleDeclarationException {
        int typeE = 0;
        int typeEn = 0;
        if (e instanceof EntreeVariable) {
            typeE = 0;
        }
        for (Entree en : this.table.keySet()) {
            if (en instanceof EntreeVariable) {
                typeEn = 0;
            }
            if (typeE == typeEn && en.getNom().equals(e.getNom())) {
                throw new DoubleDeclarationException(e.getLigne(), "Variable déjà déclarée");
            }
        }
        this.table.put(e, s);
    }

    public Symbole identifier(Entree e) throws VariableNonDeclareeException {
        int typeE = 0;
        int typeEn = 0;
        if (e instanceof EntreeVariable) {
            typeE = 0;
        }
        for (Entree en : this.table.keySet()) {
            if (en instanceof EntreeVariable) {
                typeEn = 0;
            }
            if (typeE == typeEn && en.getNom().equals(e.getNom())) {
                return this.table.get(en);
            }
        }
        throw new VariableNonDeclareeException(e.getLigne(), "Variable non déclarée");
    }

}
