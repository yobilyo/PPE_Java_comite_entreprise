package controleur;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;

import modele.Modele;

import vue.VueConnexion;
import vue.VueDon;
import vue.VueUtilisateur;
import vue.VueActivite;
import vue.VueCommentaire;


public class Main {

	//Taille des Jpanels
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 500;  
	
	// on stocke l'idUtilisateurConnecte et on l'actualise au cours du programme
	// -1 : par défaut aucun utilisateur n'est connecté
	private static int idUtilisateurConnecte = -1;
	
	public static VueUtilisateur uneVueUtilisateur;
	public static VueConnexion uneVueConnexion;
	public static VueActivite uneVueActivite;

	public static VueCommentaire uneVueCommentaire;

	public static VueDon uneVueDon;


	
	
	public static void main(String[] args) {
		//lancement plus propre
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				uneVueConnexion = new VueConnexion();
				//new VueConnexion();
				//new VueUtilisateur();
				//new VueCommentaire();
			//	new VueActivite();
			}
		});
	}
	
	public static int getIdUtilisateurConnecte() {
		return Main.idUtilisateurConnecte;
	}
	
	public static void setIdUtilisateurConnecte(int idUtilisateurConnecte) {
		// Lors de la connexion on enregistre l'idUtilisateurConnecté
		Main.idUtilisateurConnecte = idUtilisateurConnecte;
	}
	
	public static void disableIdUtilisateurConnecte() {
		// Lors de la déconnexion on reset l'idutilisateur connecté sur -1
		Main.idUtilisateurConnecte = -1;
	}

	//méthode static qui permet de rendre le panneauConnexion visible / invisible 
	public static void rendreVisible(boolean action) {
		uneVueConnexion.setVisible(action);
	}
	
	/******************* STYLISATION ***********************************/
	
	public static void styleTableau(JTable uneTable) {
		uneTable.setSelectionBackground(Color.RED);
		uneTable.setBackground(new Color(31, 61, 128));
		uneTable.setForeground(new Color(255, 255, 255));
		uneTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	/********************** INSTANCTIATIONS *********************/
	
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
	
	/********************* CONTROLEUR UTILISATEUR ***************************/
	
	public static Utilisateur selectFindUtilisateur(String username, String email, String droits) {
		return Modele.selectFindUtilisateur(username, email, droits);
	}
	
	// TODO: ancienne méthode pour debugger les autres VueDon et VueCommentaire, à améliorer + tard
	public static ArrayList<Utilisateur> selectAllUtilisateurs(String mot) {
		return Modele.selectAllUtilisateurs(mot);
	}
	
	public static int deleteUtilisateurForeignKeyConstraintsWhere(int idUtilisateur) {
		return Modele.deleteUtilisateurForeignKeyConstraintsWhere(idUtilisateur);
	}
	
	/********************* CONTROLEUR UTILISATEUR SALARIE ***************************/
	
	public static int insertUtilisateurSalarie(Salarie unSalarie) {
		return Modele.insertUtilisateurSalarie(unSalarie);
	}

	public static ArrayList<Salarie> selectAllUtilisateursSalaries(String mot) {
		return Modele.selectAllUtilisateursSalaries(mot);
	}
	
	public static int deleteUtilisateurSalarie(int idUtilisateurSalarie) {
		return Modele.deleteUtilisateurSalarie(idUtilisateurSalarie);
	}
	
	public static int updateUtilisateurSalarie(Salarie unSalarie) {
		return Modele.updateUtilisateurSalarie(unSalarie);
	}
	
	/********************* CONTROLEUR UTILISATEUR SPONSOR ***************************/
	
	public static int insertUtilisateurSponsor(Sponsor unSponsor) {
		return Modele.insertUtilisateurSponsor(unSponsor);
	}

	public static ArrayList<Sponsor> selectAllUtilisateursSponsors(String mot) {
		return Modele.selectAllUtilisateursSponsors(mot);
	}
	
	public static int deleteUtilisateurSponsor(int idUtilisateurSponsor) {
		return Modele.deleteUtilisateurSponsor(idUtilisateurSponsor);
	}
	
	public static int updateUtilisateurSponsor(Sponsor unSponsor) {
		return Modele.updateUtilisateurSponsor(unSponsor);
	}

	
	/************************* CONNEXION *************************/
	public static Utilisateur verifConnexion(String email, String mdp) {
		return Modele.verifConnexion(email, mdp);
	}
	
}
