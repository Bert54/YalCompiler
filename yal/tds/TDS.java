package yal.tds;

import yal.exceptions.DoubleDeclarationException;
import yal.exceptions.FonctionNonDeclareeException;
import yal.tds.entree.Entree;
import yal.tds.entree.EntreeFonction;
import yal.tds.symbole.Symbole;

import java.util.HashMap;

public class TDS {

    private static TDS instance = new TDS();

    public static TDS getInstance() {
        return instance;
    }

    private HashMap<Entree, Symbole> table;
    private int compteurBloc;
    private TableLocale tableLocaleCourante;
    private final TableLocale racine;

    /**
     * Constructeur
     */
    private TDS() {
        this.table = new HashMap<>();
        this.compteurBloc = 0;
        this.racine = new TableLocale(compteurBloc, null, 0);
        this.tableLocaleCourante = this.racine;
    }

    /**
     * Sortie d'un bloc lors de l'analyse syntaxique
     */
    public void sortieBloc() {
        this.tableLocaleCourante = this.tableLocaleCourante.getTableLocalPere();
    }

    /**
     * Récupère la table courante actuellement active
     * @return la table courante actuellement active
     */
    public TableLocale getTableLocaleCourante() {
        return this.tableLocaleCourante;
    }


    /**
     * Entrée dans un bloc lors de l'analyse syntaxique
     */
    public void entreeBloc(){
        compteurBloc++;
        Valeurs.getInstance().ajouterCompteurPile(compteurBloc);
        TableLocale tableLocale = new TableLocale(compteurBloc, this.tableLocaleCourante, 0);
        tableLocaleCourante.ajouterFille(tableLocale);
        tableLocaleCourante = tableLocale;
    }

    /**
     * Entrée dans un bloc lors de l'analyse sémantique
     * @param numBloc le numéro du bloc dans lequel entrer
     */
    public void entreeBlocVerifier(int numBloc) {
        TableLocale table = null;
        for (TableLocale tl: this.tableLocaleCourante) {
            if (tl.getNumBloc() == numBloc) {
                table = tl;
            }
        }
        this.tableLocaleCourante = table;
    }

    /**
     * Méthode de sortie d'un bloc lors de l'analyse sémantique. Ici, on rècupre donc juste la table locale englobante
     */
    public void sortieBlocVerifier() {
        this.tableLocaleCourante = this.tableLocaleCourante.getTableLocalPere();
    }

    /**
     * Méthode d'ajout d'une nouvelle entrée de bloc avec son symbole correspondant
     * @param e la nouvelle entrée
     * @param s le nouveau symbole associé à l'entrée
     * @throws DoubleDeclarationException Exception déclenché lors de l'ajout d'une fonction déjà présente avec le même nombre de paramètres
     */
    public void ajouter(Entree e, Symbole s) {
        int typeE = 0;
        int typeEn = 0;
        // Utilisation de instanceof : voir la classe TableLocale.
        if (e instanceof EntreeFonction) {
            typeE = 1;
        }
        for (Entree en : this.table.keySet()) {
            if (en instanceof EntreeFonction) {
                typeEn = 1;
            }
            if (typeE == typeEn && en.getNom().equals(e.getNom()) && en.getNbParams() == e.getNbParams()) {
                throw new DoubleDeclarationException("Fonction déjà déclarée : " + e.getNom());
            }
        }
        this.table.put(e, s);
    }


    /**
     * Méthode d'identification d'un bloc
     * @param e l'entrée à identifier
     * @return le symbole correspondant à l'entrée
     * @throws FonctionNonDeclareeException Exception déclenchée lors de l'identification d'un appel de fonction non déclarée
     */
    public Symbole identifier(Entree e) {
        int typeE = 0;
        int typeEn = 0;
        if (e instanceof EntreeFonction) {
            typeE = 1;
        }
        // Utilisation de instanceof : voir la classe TableLocale.
        for (Entree en : this.table.keySet()) {
            if (en instanceof EntreeFonction) {
                typeEn = 1;
            }
            if (typeE == typeEn && en.getNom().equals(e.getNom()) && en.getNbParams() == e.getNbParams()) {
                return this.table.get(en);
            }
        }
        throw new FonctionNonDeclareeException(e.getLigne(), "Fonction non déclarée : " + e.getNom());
    }

}
