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

    /**
     * Ajoute une entrée dans la TDS
     * @param e L'entrée correspondant au symbole
     * @param s Le symbole résultant de l'entrée
     * @throws DoubleDeclarationException Exception déclenchée lors d'une double déclaration
     */
    public void ajouter(Entree e, Symbole s) throws DoubleDeclarationException {
        /**
         * Utilisation de instanceof car utiliser la méthode "containsKey" avec un objet
         * en tant que clé va comparer les adresses. Or on ne stock pas les Entree.
         * Ainsi, une Entree instanciée avec les même paramaètre ne sera quand même pas
         * reconnue par "containsKey"...
         */
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

    /**
     * Récupère un sylbole dans la TDS associée à une entrée
     * @param e L'entrée du symbole à récupérer
     * @return Le symbole associé à l'entrée
     * @throws VariableNonDeclareeException Exception déclenchée lors de la manipulation
     * d'une entrée inexistante
     */
    public Symbole identifier(Entree e) throws VariableNonDeclareeException {
        /**
         * Pour instanceof, même chose qu'au-dessus
         */
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
