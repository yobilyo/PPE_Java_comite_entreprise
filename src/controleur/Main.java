package controleur;

import modele.Modele;
import vue.VueConnexion;


public class Main {

	public static void main(String[] args) {
			new VueConnexion();
	}

	public static Utilisateur verifConnexion(String email, String mdp) {
		return Modele.verifConnexion(email, mdp);
	}
	
	public static void instancierVuePilote () {
		//uneVuePilote =new VuePilote(); 
	}

}
//tests