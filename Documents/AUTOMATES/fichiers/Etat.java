import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class Etat {
    private final int id;
    private final boolean etatAcc;
    private Map<Character, Set<Etat>> transitions = new HashMap<Character, Set<Etat>>();

    public Etat(int id, boolean etatAcc) {
	this.id = id;
	this.etatAcc = etatAcc;
    }

    public int getId() {
	return id;
    }

    public boolean estAcceptant() {
	return etatAcc;
    }

    public final boolean equals(Object e){
	if(!(e instanceof Etat))
	    return false;
	return (((Etat)e).id == id);
    }

    public final int hashCode(){
	return id;
    }

    // Ensemble des lettres étiquetant une transition sortante.
    public Set<Character> alphabet() {
	return this.transitions.keySet();
    }

    // pour l'affichage
    public String toString() {
	String s = "Etat " + id;
	if(estAcceptant())
	    s+=" (acceptant)";
	s += "\n";
	for (char c : this.alphabet()) {
	    for (Etat e: this.transitions.get(c))
		    s += c + " " +  e.id + "\n";
	}
	return s;
    }

    // Retourne null si c n'étiquette aucune transition.
    public Set<Etat> succ(char c) {
	return transitions.get(c);
    }

    // Si c étiquetait déjà une transition, l'ancienne valeur est remplacée par e.
    public void ajouteTransition(char c, Etat e) {
	Set<Etat> es = new HashSet<Etat>();
	if(transitions.get(c) != null) es.addAll(transitions.get(c));
	es.add(e);
	transitions.put(c, es);
    }

    // premier algorithme d'acceptation d'un mot
    boolean accepte(String mot) {
	// si on a fini de lire
	if (mot.length() == 0)
	    // c'est accepté si état acceptant
	    return estAcceptant();
	else { // on suit les transition possibles de la première lettre
	    Set<Etat> es = this.succ(mot.charAt(0));
	    // si pas de transition c'est un échec
	    if(es == null)
		return false;
	    else {
		for (Etat e: es)
		    if (e.accepte(mot.substring(1))) return true;
		return false;
	    }
	}
    }
}
