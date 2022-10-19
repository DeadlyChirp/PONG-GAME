package model;

public class Feature {
	
	private static boolean deuxcontredeux = false;
	//private static boolean quatrejoueurs;
	
	public boolean getDeuxcontredeux() { return this.deuxcontredeux; }
	//public boolean getQuatrejoueurs() { return this.quatrejoueurs; }
	
	public void setDeuxcontredeux() {
		
		if (deuxcontredeux) deuxcontredeux = false;
		else {
			deuxcontredeux = true;
			//quatrejoueurs = false;
		}
		
	}
	
	//public void featureQuatreJoueurs() {
		
		//if (quatrejoueurs) quatrejoueurs = false;
		//else {
			//quatrejoueurs = true;
			//deuxcontredeux = false;
		//}
		
	//}
	
}
