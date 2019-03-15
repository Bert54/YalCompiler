package yal.tds;

import yal.exceptions.DoubleDeclarationException;
import yal.exceptions.VariableNonDeclareeException;
import yal.tds.entree.Entree;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TableLocale implements Iterable<TableLocale> {

        private HashMap<Entree, Symbole> table;
        private int numBloc;
        private TableLocale pere;
        private ArrayList<TableLocale> filles;
        private int nbRetour;
        private int nbParams;

        /**
         * Constructeur de Table locale
         */
        public TableLocale(int numBloc, TableLocale pere, int nbParams) {
            this.table = new HashMap<>();
            this.filles = new ArrayList<>();
            this.numBloc = numBloc;
            this.pere = pere;
            this.nbRetour = 0;
            this.nbParams = nbParams;
        }

        /**
         * Ajoute une entrée dans la table locale
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
                typeE = 1;
            }
            for (Entree en : this.table.keySet()) {
                if (en instanceof EntreeVariable) {
                    typeEn = 1;
                }
                if (typeE == typeEn && en.getNom().equals(e.getNom())) {
                    throw new DoubleDeclarationException("Variable déjà déclarée : " + e.getNom());
                }
            }
            this.table.put(e, s);
        }

        /**
         * Récupère un sylbole dans la table locale associée à une entrée
         * @param e L'entrée du symbole à récupérer
         * @return Le symbole associé à l'entrée
         * @throws VariableNonDeclareeException Exception déclenchée lors de la manipulation
         * d'une entrée inexistante
         */
        public Symbole identifier(Entree e){
            /**
             * Pour instanceof, même chose qu'au-dessus
             */
            int typeE = 0;
            int typeEn = 0;
            if (e instanceof EntreeVariable) {
                typeE = 1;
            }
            for (Entree en : this.table.keySet()) {
                if (en instanceof EntreeVariable) {
                    typeEn = 1;
                }
                if (typeE == typeEn && en.getNom().equals(e.getNom())) {
                    return this.table.get(en);
                }
                if (typeE == typeEn && en.getNom().equals(e.getNom())) {
                    return this.table.get(en);
                }
            }
            return null;
        }

    /**
     * Ajoute une table locale fille
     * @param fille Table locale fille
     */
    public void ajouterFille(TableLocale fille){
        filles.add(fille);
    }


    /**
     * Récupère la table locale du bloc englobant
     * @return la table locale du bloc englobant
     */
    public TableLocale getTableLocalPere() {
        return this.pere;
    }

    /**
     * Récupère le numéro de ce bloc
     * @return le numéro de ce bloc
     */
    public int getNumBloc() {
        return this.numBloc;
    }

    /**
     *
     * @return le nombre de variable locale
     */
    public int getNbVariable(){
        return table.size();
    }

    /**
     * Retourne le nombre d'instruction retourner trouvés dans ce bloc
     * @return le nombre d'instruction retourner trouvés dans ce bloc
     */
    public int getNbRetour() {
        return this.nbRetour;
    }

    /**
     * Méthode d'incrémentation du nombre d'instruction retourner dans ce bloc
     */
    public void incrementerNbRetour() {
        this.nbRetour++;
    }

    /**
     * Récupère le nombre de paramètres de ce bloc
     * @return le nombre de paramètres
     */
    public int getNbParams() {
        return this.nbParams;
    }

    /**
     * Set le nombre de paramètres dans ce bloc
     * @param nb nombre de paramètres
     */
    public void setNbParams(int nb) {
        this.nbParams = nb;
    }

    /**
     * Itérateur sur les tables des blocs englobés par celui-ci
     * @return l'itérateur des tables englobés
     */
    public Iterator<TableLocale> iterator() {
        return this.filles.iterator();
    }

}
