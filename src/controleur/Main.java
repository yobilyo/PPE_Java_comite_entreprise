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
		uneVueConnexion = new VueConnexion();
		//new VueConnexion();
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
	
	public static Utilisateur selectFindUtilisateur(String username, String email, String droits) {
		return Modele.selectFindUtilisateur(username, email, droits);
	}
	
	/********************* CONTROLEUR UTILISATEUR SALARIE ***************************/
	
	public static void insertUtilisateurSalarie(Salarie unSalarie) {
		Modele.insertUtilisateurSalarie(unSalarie);
	}

	public static ArrayList<Salarie> selectAllUtilisateursSalaries(String mot) {
		return Modele.selectAllUtilisateursSalaries(mot);
	}
	
	public static void deleteUtilisateurSalarie(int idUtilisateurSalarie) {
		Modele.deleteUtilisateurSalarie(idUtilisateurSalarie);
	}
	
	public static void updateUtilisateurSalarie(Salarie unSalarie) {
		Modele.updateUtilisateurSalarie(unSalarie);
	}
	
	/********************* CONTROLEUR UTILISATEUR SPONSOR ***************************/
	
	public static void insertUtilisateurSponsor(Sponsor unSponsor) {
		Modele.insertUtilisateurSponsor(unSponsor);
	}

	public static ArrayList<Sponsor> selectAllUtilisateursSponsors(String mot) {
		return Modele.selectAllUtilisateursSponsors(mot);
	}
	
	public static void deleteUtilisateurSponsor(int idUtilisateurSponsor) {
		Modele.deleteUtilisateurSponsor(idUtilisateurSponsor);
	}
	
	public static void updateUtilisateurSponsor(Sponsor unSponsor) {
		Modele.updateUtilisateurSponsor(unSponsor);
	}
	
}
