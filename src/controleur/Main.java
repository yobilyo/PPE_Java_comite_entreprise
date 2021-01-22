package controleur;

import modele.Modele;
import vue.VueActivite;
import vue.VueConnexion;


public class Main {
	public static VueActivite uneVueActivite;
	public static VueConnexion uneVueConnexion;

	public static void main(String[] args) {
		uneVueConnexion = new VueConnexion();
		//new VueActivite();
	}

	public static Utilisateur verifConnexion(String email, String mdp) {
		return Modele.verifConnexion(email, mdp);
	}
	
	public static void instancierVueActivite () {
		uneVueActivite = new VueActivite();
	}
	
	public static void instancierVueConnexion() {
		uneVueConnexion = new VueConnexion();
	}
	
	//méthode static qui permet de rendre le panneauConnexion visible / invisible 
	public static void rendreVisible(boolean action) {
		uneVueConnexion.setVisible(action);
	}
	
	
	/********************* CONTROLEUR ACTIVITE ***************************/
	
	
	public static void insertActivite(Activite uneActivite) {
		Modele.insertActivite(uneActivite);
	}
	
	
	
	
	
	
	
	
}
