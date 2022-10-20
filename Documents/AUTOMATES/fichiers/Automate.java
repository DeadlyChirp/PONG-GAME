import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class Automate extends HashSet<Etat> {

    private Etat init;

    public Automate(Etat e) {
	super();
	this.init = e;
	this.initialiseAutomate(e);
    }

    @Override
    public String toString() {
	String s = "" + this.size() + " états; ";
	s += "état intial : " + init.getId()+ "\n\n";
	for (Etat e : this) {
	    s += e.toString() + "\n";
	}
	return s;
    }

    // Retourne true si et seulement si e n'était pas déjà dans l'ensemble.
    boolean ajouteEtatSeul(Etat e) {
	return this.add(e);
    }

    // Construit un automate à partir de l'ensemble des états accessibles
    // depuis l'état initial.
    // Retourne true si et seulement si l'état initial n'était pas déjà
    // présent dans l'automate.
    boolean initialiseAutomate(Etat e) {
	if (!ajouteEtatSeul(e))
	    return false;
	for (char c : e.alphabet()) {
	    for (Etat e1: e.succ(c))
		initialiseAutomate(e1);
	}
	return true;
    }

    // Retourne le nombre de transitions dans l'automate
    int nombreTransitions() {
	// à écrire
	return 0;
    }

    // Retourne l'ensemble des lettres utilisées dans l'automate.
    Set<Character> alphabet() {
        // à écrire
	return null;
    }

    // Retourne true si et seulement si l'automate est complet
    boolean estComplet() {
	// à écrire
	return false;
    }

    // Pour true si et seulement si l'automate est déterministe
    boolean estDeterministe() {
	// à écrire
	return true;
    }

    // premier algorithme d'acceptation d'un mot
    boolean accepte(String mot) {
      	return this.init.accepte(mot);
    }

    // algorithme plus efficace
    boolean accepte2(String mot) {
	// écrire ici l'algorithme plus efficace
	return true;
    }

    // Pour enlever les états co-accessibles
    void enleverEtatsCoAccessible() {
        // à écrire
    }

    // Déterminisation
    Automate determinisation(){
	// à écrire
	return null;
    }
}
