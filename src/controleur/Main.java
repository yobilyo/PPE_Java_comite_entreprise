package controleur;

import java.util.ArrayList;

import modele.Modele;

import vue.VueConnexion;
import vue.VueDon;
import vue.VueUtilisateur;
import vue.VueActivite;
import vue.VueCommentaire;


public class Main {

	public static VueUtilisateur uneVueUtilisateur;
	public static VueConnexion uneVueConnexion;
	public static VueActivite uneVueActivite;

	public static VueCommentaire uneVueCommentaire;

	public static VueDon uneVueDon;


	
	
	public static void main(String[] args) {
		uneVueConnexion = new VueConnexion();
		//new VueConnexion();
		//new VueUtilisateur();
		//new VueCommentaire();
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

	public static void instancierVueCommentaire() {
		uneVueCommentaire = new VueCommentaire();
	}

	
	public static void instancierVueDon() {
		uneVueDon = new VueDon();
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

	public static ArrayList<Utilisateur> selectAllUtilisateurs(String mot) {
		return Modele.selectAllUtilisateurs(mot);
	}

	/********************* CONTROLEUR COMMENTAIRE ***************************/
	
	
	public static ArrayList<Commentaire> selectAllCommentaires(String mot) {
		return Modele.selectAllCommentaires(mot);
	}

	public static void insertCommentaire(Commentaire unCommentaire) {
		Modele.insertCommentaire(unCommentaire); 
	}
	
	/********************* CONTROLEUR DON ***************************/
	
	public static void insertDon(Don unDon) {
		Modele.insertDon(unDon);
	}
	public static void deleteDon (int iddon){
		Modele.deleteDon(iddon);
	}
	public static ArrayList<Don> selectAllDons(String mot) {
		return Modele.selectAllDons(mot);
	}
	public static void updateDon(Don unDon) {
		Modele.updateDon(unDon);
	}
}
