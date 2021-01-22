package controleur;

import modele.Modele;

import vue.VueConnexion;
import vue.VueUtilisateur;


public class Main {

	public static VueUtilisateur uneVueUtilisateur;
	public static VueConnexion uneVueConnexion;

	public static void main(String[] args) {
		uneVueConnexion = new VueConnexion();
		//new VueActivite();
	}

	public static Utilisateur verifConnexion(String email, String mdp) {
		return Modele.verifConnexion(email, mdp);
	}
	
	public static void instancierVueConnexion() {
		uneVueConnexion = new VueConnexion();
	}
	
	public static void instancierVueUtilisateur() {
		uneVueUtilisateur = new VueUtilisateur();
	}
	
	//méthode static qui permet de rendre le panneauConnexion visible / invisible 
	public static void rendreVisible(boolean action) {
		uneVueConnexion.setVisible(action);
	}
	
	
	/********************* CONTROLEUR ACTIVITE ***************************/
	
	
	public static void insertActivite(Activite uneActivite) {
		Modele.insertActivite(uneActivite);
	}
	

	/********************* CONTROLEUR UTILISATEUR ***************************/
/*	
	public static void insertActivite(Utilisateur unUtilisateur) {
		Modele.insertUtilisateur(unUtilisateur);
	}
	
	*/
	
	
	
	
	
}
