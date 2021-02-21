package controleur;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import modele.Modele;

import vue.VueConnexion;
import vue.VueDon;
import vue.VueParticiper;
import vue.VueTresorerie;
import vue.VueUtilisateur;
import vue.VueActivite;
import vue.VueCommentaire;


public class Main {

	//Taille des Jpanels
	private final static int WIDTH = 1366;
	private final static int HEIGHT = 768;  
	
	// on stocke l'idUtilisateurConnecte et on l'actualise au cours du programme
	// -1 : par défaut aucun utilisateur n'est connecté
	private static int idUtilisateurConnecte = -1;
	
	public static VueConnexion uneVueConnexion;
	public static VueActivite uneVueActivite;
	public static VueParticiper uneVueParticiper;
	public static VueCommentaire uneVueCommentaire;
	public static VueDon uneVueDon;
	public static VueUtilisateur uneVueUtilisateur;
	public static VueTresorerie uneVueTresorerie;
	
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
	
	public static int getWidth() {
		return Main.WIDTH;
	}
	
	public static int getHeight() {
		return Main.HEIGHT;
	}
	
	/******************* GESTION DE LA CONNEXION EN COURS **************************/
	
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
	
	// nous permet d'obtenir les droits de l'utilisateur connecte
	public static String getDroitsUtilisateurConnecte() {
		String droitsUtilisateurConnecte = Main.selectUtilisateurConnecte(Main.idUtilisateurConnecte).getDroits();
		return droitsUtilisateurConnecte;
	}
	
	/******************* STYLISATION ***********************************/
	
	public static void styleTableau(JTable uneTable) {
		uneTable.setSelectionBackground(Color.RED);
		uneTable.setBackground(new Color(31, 61, 128));
		uneTable.setForeground(new Color(255, 255, 255));
		uneTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	public static void styleBoutonBleu(JButton myButton) {
		myButton.setBackground(new Color(31, 61, 128));
		myButton.setForeground(new Color(255, 255, 255));
		myButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
	}
	
	public static void styleBoutonDark(JButton myButton) {
		myButton.setBackground(new Color(52, 58, 64));
		myButton.setForeground(new Color(255, 255, 255));
		myButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
	}
	
	/********************** INSTANCTIATIONS *********************/
	
	public static void instancierVueConnexion() {
		uneVueConnexion = new VueConnexion();
	}
	
	public static void instancierVueActivite() {
		uneVueActivite = new VueActivite();
	}

	public static void instancierParticiper() {
		uneVueParticiper = new VueParticiper();
	}
	
	public static void instancierVueCommentaire() {
		uneVueCommentaire = new VueCommentaire();
	}
	
	public static void instancierVueDon() {
		uneVueDon = new VueDon();
	}
	
	public static void instancierVueTresorerie() {
		uneVueTresorerie = new VueTresorerie();
	}
	
	public static void instancierVueUtilisateur() {
		uneVueUtilisateur = new VueUtilisateur();
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

	/********************* CONTROLEUR PARTICIPER ***************************/
	
	// TODO insert, delete, selectAll, update
	
	public static void insertParticipation(Participation uneParticipation) {
		Modele.insertParticipation(uneParticipation);
	}
	public static void deleteParticipation (int idutilisateur, int id_activite){
		Modele.deleteParticipation(idutilisateur, id_activite);
	}
	public static ArrayList<Participation> selectAllParticipation(String mot) {
		return Modele.selectAllParticipation(mot);
	}
	public static void updateParticipation(Participation uneParticipation) {
		Modele.updateParticipation(uneParticipation);
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
	
	public static void deleteUtilisateurForeignKeyConstraintsWhere(int idUtilisateur) {
		Modele.deleteUtilisateurForeignKeyConstraintsWhere(idUtilisateur);
	}
	
	/********************* CONTROLEUR UTILISATEUR CONNECTE EN COURS ***************************/
	
	public static Utilisateur selectUtilisateurConnecte(int idUtilisateurConnecte) {
		return Modele.selectUtilisateurConnecte(idUtilisateurConnecte);
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
	
	/********************* CONTROLEUR TRESORERIE ***************************/
	
	public static float selectFonds()
	{
		return Modele.selectFonds();
	}
	
	/************************* CONNEXION *************************/
	
	public static Utilisateur verifConnexion(String email, String mdp) {
		return Modele.verifConnexion(email, mdp);
	}
	
}
