package controleur;

import java.util.ArrayList;

import modele.Modele;

import vue.VueConnexion;
import vue.VueUtilisateur;
import vue.VueActivite;


public class Main {

	public static VueUtilisateur uneVueUtilisateur;
	public static VueConnexion uneVueConnexion;
	public static VueActivite uneVueActivite;
	

	
	
	public static void main(String[] args) {
		//uneVueConnexion = new VueConnexion();
		new VueActivite();
		//new VueUtilisateur();
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
	
	public static void instancierVueActivite() {
		uneVueActivite = new VueActivite();
	}
	
	//méthode static qui permet de rendre le panneauConnexion visible / invisible 
	public static void rendreVisible(boolean action) {
		uneVueConnexion.setVisible(action);
	}
	
	
	
	
	/********************* CONTROLEUR ACTIVITE ***************************/
	
	
	public static void insertActivite(Activite uneActivite) {
		Modele.insertActivite(uneActivite);
	}
	public static void deleteActivite (int idActivite){
		Modele.deleteActivite(idActivite);
	}
	public static ArrayList<Activite> selectAllActivites(String mot) {
		return Modele.selectAllActivites(mot);
	}

	public static void updateActivite(Activite uneActivite) {
		Modele.updateActivite(uneActivite);
		
	}
	
	

	/********************* CONTROLEUR UTILISATEUR ***************************/
	
	public static void insertUtilisateur(Utilisateur unUtilisateur) {
		Modele.insertUtilisateur(unUtilisateur);
	}


	
	
	
	
	
}
