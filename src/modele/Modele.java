package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controleur.Activite;
import controleur.Commentaire;
import controleur.Don;
import controleur.Main;
import controleur.Participation;
import controleur.Salarie;
import controleur.Sponsor;
import controleur.Tresorerie;
import controleur.Utilisateur;



public class Modele 
{
	//private static Bdd uneBdd = new Bdd ("localhost:8889","airfrance","root","root");
	
	// pour PC : 
	private static Bdd uneBdd = new Bdd ("localhost","ce","root","");
	
	
	/************* CONNEXION ********************/
	
	
	public static Utilisateur verifConnexion (String email, String password) 
	{
		Utilisateur unUser = null; 
		String requete = "select * from utilisateur where email = '" + email +"'  and password ='" + password + "' ; ";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unRes = unStat.executeQuery(requete); 
			
			if (unRes.next()) {
				unUser = new Utilisateur (
						unRes.getInt("idutilisateur"), unRes.getString("username"),
						unRes.getString("email"), unRes.getString("password"),  unRes.getString("droits")
						);
			}
			unRes.close();
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete );
		}
		
		return unUser; 
	}
	
	
	/************* REQUETE ********************/
	
	
	//methode gÃ©nÃ©rique d'exÃ©cution de n'importe quelle requete nÃ©cessitant pas un retour de rÃ©sultats 
	public static void executerRequete (String requete)
	{
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete
			+ "\n" + exp.getMessage());
			// montrer l'erreur sql à l'utilisateur s'il y'en a une
			//String errorMsg = "Erreur d'exécution de la requete :" + requete + "\n" + exp.getMessage();
			//JOptionPane.showMessageDialog(null, errorMsg);
		}
	}
	
	/************* ACTIVITE ********************/
	
	
	public static void insertActivite (Activite uneActivite) {
		String requete = "insert into activite values (null, '" + uneActivite.getNom() + "', '"
		+ uneActivite.getLieu() + "', '" + uneActivite.getImage_url() + "', '"
		+ uneActivite.getLien() + "', " + uneActivite.getBudget() + ", '"
		+ uneActivite.getDescription()+ "', '"  + uneActivite.getDate_debut() + "', '"
		+ uneActivite.getDate_fin() + "', " + uneActivite.getPrix() + ", "
		+ uneActivite.getNb_personnes() + ", " + uneActivite.getIdTresorerie()
		+ ");";
		executerRequete(requete);
	}
	
	public static ArrayList<Activite> selectAllActivites (String mot){
		
		String requete ; 
		if (mot.equals("")) {
			requete ="select * from activite ;" ;
		}else {
			requete ="select * from activite where nom like '%"+mot+"%'" + " or prix like '%"+mot+"%'"
					+ " or lieu like '%"+mot+"%' or budget like '%" + mot + 
					 "%' or description like '%"+mot+"%' or nb_personnes like '%"+mot+"%' ; " ;
		}
		ArrayList<Activite> lesActivites = new ArrayList<Activite>();  
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Activite uneActivite = new Activite (
					desRes.getInt("id_activite"),
					desRes.getString("nom"), 
					desRes.getString("lieu"),
					desRes.getString("image_url"),
					desRes.getString("lien"),
					desRes.getFloat("budget"), 
					desRes.getString("description"),
					desRes.getDate("date_debut"),
					desRes.getDate("date_fin"),
					desRes.getFloat("prix"),
					desRes.getInt("nb_personnes"),
					desRes.getInt("id_tresorerie")
				);
				lesActivites.add(uneActivite);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete
			+ "\n" + exp.getMessage());
		}
		return lesActivites ; 
	}

	public static void deleteActivite (int idActivite)
	{
		// suppression des possessions dans les tables étrangères d'abord
		String requeteCom =" delete from commentaire where id_activite = " + idActivite +" ; " ;
		executerRequete(requeteCom);
		
		String requetePart =" delete from participer where id_activite = " + idActivite +" ; " ;
		executerRequete(requetePart);
		
		String requete =" delete from activite where id_activite = " + idActivite +" ; " ;
		executerRequete(requete);
	}

	public static void updateActivite(Activite uneActivite) {
		String requete ="update activite set nom = '" + uneActivite.getNom()
		+ "', lieu = '" + uneActivite.getLieu()
		+ "', image_url = '" + uneActivite.getImage_url()
		+ "', lien = '" + uneActivite.getImage_url()
		+ "', budget = " + uneActivite.getBudget()
		+ ", description = '" + uneActivite.getDescription()
		+ "', date_debut = '" + uneActivite.getDate_debut()
		+ "', date_fin = '" + uneActivite.getDate_fin()
		+ "', prix = " + uneActivite.getPrix()
		+ ", nb_personnes= " + uneActivite.getNb_personnes()
		+ ", id_tresorerie = " + uneActivite.getIdTresorerie()
		+ " where id_activite = " + uneActivite.getIdActivite() + " ;" ;
		executerRequete(requete);	
	}

	
	/************* DON ********************/
	
	
	public static void insertDon (Don unDon) {
		String requete = "insert into don values (null, '" + unDon.getDatedon() + "','" + unDon.getMontant() + "','" + unDon.getAppreciation() + "','" + unDon.getIdutilisateur()
		 + "','" + unDon.getId_tresorerie()+"');";
		executerRequete(requete);
		}


	public static ArrayList<Don> selectAllDons (String mot){
		
		String requete ; 
		if (mot.equals("")) {
			requete ="select * from don ;" ;
		}else {
			requete ="select * from don where appreciation like '%"+mot+"%'" + " or montant like '%"+mot+"%'"
					+ " or iddon like '%"+mot+"%' or datedon like '%" + mot + "%' ; " ;
		}
		ArrayList<Don> lesDons = new ArrayList<Don>();  
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Don unDon = new Don (
						desRes.getInt("iddon"), 
						desRes.getInt("idutilisateur"), 
						desRes.getInt("id_tresorerie"), 
						desRes.getString("appreciation"), 
						desRes.getFloat("montant"), 
						desRes.getString("datedon")
						);
				lesDons.add(unDon);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete
			+ "\n" + exp.getMessage());
		}
		return lesDons ; 
	}

	public static void deleteDon (int iddon)
	{
		String requete =" delete from don where iddon = " + iddon +" ; " ;
		executerRequete(requete);
	}

	public static void updateDon(Don unDon) {
		String requete ="update don set datedon = '" + unDon.getDatedon() + "', montant = '" + unDon.getMontant()
		+"', appreciation = '" + unDon.getAppreciation() + "', idutilisateur = " + unDon.getIdutilisateur()
		+ ", id_tresorerie = " + unDon.getId_tresorerie() 
		+ "  where iddon = " + unDon.getIddon() + " ;" ;
		executerRequete(requete);		
	}

	
	/************* COMMENTAIRE ********************/	
	
	
	public static ArrayList<Commentaire> selectAllCommentaires(String mot) {
		String requete ; 
		if (mot.equals("")) {
			requete ="select * from commentaire ;" ;
		}else {
			requete ="select * from commentaire where datecomment like '%"+mot+"%' or contenu like '%"+mot+"%'; " ;
		}
		ArrayList<Commentaire> lesCommentaires = new ArrayList<Commentaire>();  
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Commentaire unCommentaire = new Commentaire (
						desRes.getInt("id_commentaire"), desRes.getDate("datecomment"), desRes.getString("contenu"), desRes.getInt("id_activite"), 
						desRes.getInt("idutilisateur")
						);
				lesCommentaires.add(unCommentaire);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete
			+ "\n" + exp.getMessage());
		}
		return lesCommentaires ; 
	}

	public static void insertCommentaire(Commentaire unCommentaire) {
		String requete = "insert into commentaire values (null, '" + unCommentaire.getDateComment() + "', '" + unCommentaire.getContenu()
		 +"', " + unCommentaire.getIdActivite() + ", " + unCommentaire.getIdUtilisateur() + " "
		 + ");";
		executerRequete(requete);
	}
	
	
	/************* UTILISATEUR ********************/
	
	
	public static void insertUtilisateur(Utilisateur unUtilisateur) {
		String requete = "insert into utilisateur values (null, '" + unUtilisateur.getUsername() + "', '" + unUtilisateur.getPassword()
		+"', '" + unUtilisateur.getEmail()+ "', '" + unUtilisateur.getDroits() +"');";
		
		executerRequete(requete);
	}
	
	public static ArrayList<Utilisateur> selectAllUtilisateurs(String mot) {
		String requete ; 
		if (mot.equals("")) {
			requete ="select * from utilisateur ;" ;
		}else {
			requete ="select * from utilisateur where username like '%"+mot+"%' or email like '%"+mot+"%'; " ;
		}
		ArrayList<Utilisateur> lesUtilisateurs = new ArrayList<Utilisateur>();  
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Utilisateur unUtilisateur = new Utilisateur (
						desRes.getInt("idutilisateur"), desRes.getString("username"), desRes.getString("password"), desRes.getString("email"), 
						desRes.getString("droits")
						);
				lesUtilisateurs.add(unUtilisateur);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete
			+ "\n" + exp.getMessage());
		}
		return lesUtilisateurs ; 
	}
	
	public static Utilisateur selectFindUtilisateur(String username, String email, String droits) {
		Utilisateur unUtilisateur = null;
		
		String requete = "select * from utilisateur where username like '%"+username+"%'"
		+ " and email like '%"+email+"%' and droits like '%"+droits+"%'; " ;
	
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				unUtilisateur = new Utilisateur (
					desRes.getInt("idutilisateur"),
					desRes.getString("username"),
					desRes.getString("password"),
					desRes.getString("email"), 
					desRes.getString("droits")
				);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete
			+ "\n" + exp.getMessage());
		}
		return unUtilisateur; 
		
	}
	
	public static void deleteUtilisateurForeignKeyConstraintsWhere(int idUtilisateur) {
		// Avant de supprimer l'utilisateur, on doit supprimer toutes ses possessions dans les
		// autres tables où il a une clé étrangère:
		
		String requeteDons = "delete from don where idutilisateur = " + idUtilisateur + ";";
		executerRequete(requeteDons);
		
		String requeteParticiper = "delete from participer where idutilisateur = " + idUtilisateur + ";";
		executerRequete(requeteParticiper);
		
		String requeteCommentaire = "delete from commentaire where idutilisateur = " + idUtilisateur + ";";
		executerRequete(requeteCommentaire);
		
		String requeteContact = "delete from contact where idutilisateur = " + idUtilisateur + ";";
		executerRequete(requeteContact);
	}
	
	
	/************* UTILISATEUR CONNECTE EN COURS ********************/
	
	
	public static Utilisateur selectUtilisateurConnecte(int idUtilisateurConnecte) {
		Utilisateur unUser = null;
		String requete = "select * from utilisateur where idutilisateur = " + idUtilisateurConnecte + ";";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unRes = unStat.executeQuery(requete); 
			
			if (unRes.next()) {
				unUser = new Utilisateur (
						unRes.getInt("idutilisateur"), unRes.getString("username"),
						unRes.getString("email"), unRes.getString("password"),  unRes.getString("droits")
						);
			}
			unRes.close();
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete
			+ "\n" + exp.getMessage());
		}
		
		return unUser;
	}
	
	/************* UTILISATEUR SALARIE ********************/
	
	
	public static void insertUtilisateurSalarie(Salarie unSalarie) {
		// Utilisateur
		String requeteUtilisateur = "insert into utilisateur values (null, '"
		+ unSalarie.getUsername() + "', '" + unSalarie.getPassword() +"', '"
		+ unSalarie.getEmail()+ "', '" + unSalarie.getDroits()
		+ "');";
		executerRequete(requeteUtilisateur);
		
		//SelectWhere pour obtenir l'idutilisateur réel inséré:
		int idUtilisateurInsere = Main.selectFindUtilisateur(unSalarie.getUsername(),
				unSalarie.getEmail(), unSalarie.getDroits()).getIdUtilisateur();
		
		// Salarie
		String requeteSalarie = "insert into salarie values(" + idUtilisateurInsere + ", '" 
		+ unSalarie.getNom() + "', '" + unSalarie.getPrenom() + "', '"
		+ unSalarie.getTel() + "', '" + unSalarie.getAdresse() + "', '"
		+ unSalarie.getQuotient_fam() + "', '" + unSalarie.getService() + "', '"
		+ unSalarie.getSexe()
		+ "');";
		executerRequete(requeteSalarie);
	}
	
	public static ArrayList<Salarie> selectAllUtilisateursSalaries(String mot) {
		String requete ; 
		// + simple d'utiliser la view sql utilisateur_salarie directement
		if (mot.equals("")) {
			requete ="select * from utilisateur_salarie ;" ;
		}else {
			requete ="select * from utilisateur_salarie where username like '%"+mot+"%'"
					+ " or email like '%"+mot+"%'"
					+ " or prenom like '%"+mot+"%'"
					+ " or tel like '%"+mot+"%'"
					+ "; " ;
		}
		ArrayList<Salarie> lesUtilisateursSalaries = new ArrayList<Salarie>();  
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Salarie unSalarie = new Salarie (
					// ATTENTION !!
				    //
				    // notre view sql utilisateur_salarie a l'ordre:
					// username/email/password
					// alors que la table utilisateur a l'ordre:
					// username/passowrd/email
					// J'ai (billel) choisi de quand même utiliser la view sql car c'est + simple,
					// et j'ai corrigé lors de l'instanciation d'un salarié l'ordre des variables
					// (desRes.getString("password") puis desRes.getString("email"))
					//
					// utilisateur
					desRes.getInt("idutilisateur"),
					desRes.getString("username"),
					desRes.getString("password"),
					desRes.getString("email"),   
					desRes.getString("droits"),
					// salarie (suivre l'ordre de la table salarie, pas de la vue utilisateur_salarie)
					desRes.getString("nom"),
					desRes.getString("prenom"),
					desRes.getString("tel"),
					desRes.getString("adresse"),
					desRes.getString("quotient_fam"),
					desRes.getString("service"),
					desRes.getString("sexe")
				);
				lesUtilisateursSalaries.add(unSalarie);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete
			+ "\n" + exp.getMessage());
		}
		
		return lesUtilisateursSalaries ; 
	}
	
	public static void deleteUtilisateurSalarie(int idUtilisateurSalarie) {
		// on supprime toutes les possessions du salarie dans les autres tables
		deleteUtilisateurForeignKeyConstraintsWhere(idUtilisateurSalarie);
		
		// on supprime le salarie dans la classe fille sql salarie en premier (car clé étrangère), puis on peut supprimer le salarié dans la classe mère utilisateur
		// Salarie
		String requeteSalarie = "delete from salarie where idutilisateur =" + idUtilisateurSalarie + ";";
		executerRequete(requeteSalarie);
		
		// Utilisateur
		String requeteUtilisateur = "delete from utilisateur where idutilisateur =" + idUtilisateurSalarie + ";";
		executerRequete(requeteUtilisateur);
	}
	
	public static void updateUtilisateurSalarie(Salarie unSalarie) {
		// utilisateur
		String requeteUtilisateur = "update utilisateur set username = '" + unSalarie.getUsername()
		+ "', password = '" + unSalarie.getPassword() + "', email = '" + unSalarie.getEmail()
		+ "', droits = '" + unSalarie.getDroits()
		+ "' where idutilisateur = " + unSalarie.getIdUtilisateur() + ";";
		executerRequete(requeteUtilisateur);
		
		// salarie
		String requeteSalarie = "update salarie set nom = '" + unSalarie.getNom()
		+ "', prenom = '" + unSalarie.getPrenom() + "', tel = '" + unSalarie.getTel()
		+ "', adresse = '" + unSalarie.getAdresse() + "', quotient_fam = '" + unSalarie.getQuotient_fam()
		+ "', service = '" + unSalarie.getService() + "', sexe = '" + unSalarie.getSexe()
		+ "' where idutilisateur = " + unSalarie.getIdUtilisateur() + ";";
		executerRequete(requeteSalarie);
	}
	
	
	/************* UTILISATEUR SPONSOR ********************/
	
	public static void insertUtilisateurSponsor(Sponsor unSponsor) {
		// Utilisateur
		String requeteUtilisateur = "insert into utilisateur values (null, '" + unSponsor.getUsername() + "', '" + unSponsor.getPassword()
		+"', '" + unSponsor.getEmail()+ "', '" + unSponsor.getDroits() +"');";
		executerRequete(requeteUtilisateur);
		
		//SelectWhere pour obtenir l'idutilisateur réel inséré:
		int idUtilisateurInsere = Main.selectFindUtilisateur(unSponsor.getUsername(), unSponsor.getEmail(), unSponsor.getDroits()).getIdUtilisateur();
		
		// Salarie
		String requeteSponsor = "insert into sponsor values(" + idUtilisateurInsere + ", '" + unSponsor.getSociete() + "', '"
		+ unSponsor.getImage_url() + "', '" + unSponsor.getBudget() + "', '" + unSponsor.getTel() + "', '"
		+ unSponsor.getLien() + "');";
		executerRequete(requeteSponsor);
	}
	
	public static ArrayList<Sponsor> selectAllUtilisateursSponsors(String mot) {
		String requete ; 
		// + simple d'utiliser la view sql utilisateur_sponsor directement
		if (mot.equals("")) {
			requete ="select * from utilisateur_sponsor ;" ;
		}else {
			requete ="select * from utilisateur_sponsor where username like '%"+mot+"%' or email like '%"+mot+"%'; " ;
		}
		ArrayList<Sponsor> lesUtilisateursSponsors = new ArrayList<Sponsor>();  
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Sponsor unSponsor = new Sponsor (
					// ATTENTION !!
				    //
				    // notre view sql utilisateur_sponsor a l'ordre:
				    // username/email/password
					// alors que la table utilisateur a l'ordre:
					// username/passowrd/email
					// J'ai (billel) choisi de quand même utiliser la view sql car c'est + simple,
					// et j'ai corrigé lors de l'instanciation d'un salarié l'ordre des variables
					// (desRes.getString("password") puis desRes.getString("email"))
					//
					// utilisateur
					desRes.getInt("idutilisateur"),
					desRes.getString("username"),
					desRes.getString("password"),
					desRes.getString("email"),
					desRes.getString("droits"),
					// sponsor (suivre l'ordre de la table sponsor, pas de la vue utilisateur_sponsor)
					desRes.getString("societe"),
					desRes.getString("image_url"),
					desRes.getDouble("budget"),
					desRes.getString("tel"),
					desRes.getString("lien")
				);
				lesUtilisateursSponsors.add(unSponsor);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete
			+ "\n" + exp.getMessage());
		}
		
		return lesUtilisateursSponsors ; 
	}
	
	public static void deleteUtilisateurSponsor(int idUtilisateurSponsor) {
		// on supprime toutes les possessions du sponsor dans les autres tables
		deleteUtilisateurForeignKeyConstraintsWhere(idUtilisateurSponsor);
		
		// on supprime le sponsor dans la classe fille sql sponsor en premier (car clé étrangère), puis on peut supprimer le sponsor dans la classe mère utilisateur
		// Sponsor
		String requeteSponsor = "delete from sponsor where idutilisateur =" + idUtilisateurSponsor + ";";
		executerRequete(requeteSponsor);
		
		// Utilisateur
		String requeteUtilisateur = "delete from utilisateur where idutilisateur =" + idUtilisateurSponsor + ";";
		executerRequete(requeteUtilisateur);
	}
	
	public static void updateUtilisateurSponsor(Sponsor unSponsor) {
		// utilisateur
		String requeteUtilisateur = "update utilisateur set username = '" + unSponsor.getUsername()
		+ "', password = '" + unSponsor.getPassword() + "', email = '" + unSponsor.getEmail()
		+ "', droits = '" + unSponsor.getDroits()
		+ "' where idutilisateur = " + unSponsor.getIdUtilisateur() + ";";
		executerRequete(requeteUtilisateur);
		
		// sponsor
		String requeteSponsor = "update sponsor set societe = '" + unSponsor.getSociete()
		+ "', image_url = '" + unSponsor.getImage_url() + "', budget = " + unSponsor.getBudget()
		+ ", tel = '" + unSponsor.getTel() + "', lien = '" + unSponsor.getLien()
		+ "' where idutilisateur = " + unSponsor.getIdUtilisateur() + ";";
		executerRequete(requeteSponsor);
	}
	
	/************************ PARTICIPATION ************************************/
	
	//prendre la vue utilisateur_salarie_activite_participer 
	// avec idutilisateur, id_activite, username, email, nom, prenom, tel, adresse, service, nom_activite, date_inscription, lieu, description
	
	
	public static ArrayList<Participation> selectAllParticipation(String mot) {
		String requete ; 
		if (mot.equals("")) {
			requete ="select * from utilisateur_salarie_activite_participer ;" ;
		}else {
			requete ="select * from utilisateur_salarie_activite_participer where date_inscription like '%"+mot+"%'; " ;
		}
		ArrayList<Participation> lesParticipations = new ArrayList<Participation>();  
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Participation uneParticipation = new Participation (
						desRes.getInt("idutilisateur"),
						desRes.getInt("id_activite"),
						desRes.getString("username"),
						desRes.getString("email"),
						desRes.getString("nom"),
						desRes.getString("prenom"),
						desRes.getString("tel"),
						desRes.getString("adresse"),
						desRes.getString("service"),
						desRes.getString("nom_activite"),
						desRes.getString("date_inscription"),
						desRes.getString("lieu"),
						desRes.getString("description")
				);
				lesParticipations.add(uneParticipation);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete
			+ "\n" + exp.getMessage());
		}
		return lesParticipations ; 
	}

	public static void insertParticipation(Participation uneParticipation) {
		String requete = "insert into participer values ('" + uneParticipation.getIdUtilisateur()
		+ "', '" + uneParticipation.getIdActivite() + "', '" + uneParticipation.getDate_inscription()
		+ "' "
		 + ");";
		executerRequete(requete);
	}
	
	public static void deleteParticipation (int idutilisateur, int id_activite)
	{
		String requete =" delete from participer where idutilisateur = " + idutilisateur +" and id_activite = " + id_activite + " ; " ;
		executerRequete(requete);
	}

	public static void updateParticipation(Participation uneParticipation) {
		String requete ="update participer set date_inscription = '" + uneParticipation.getDate_inscription() + "', idutilisateur = '" + uneParticipation.getIdUtilisateur()
		+"', id_activite = " + uneParticipation.getIdActivite() + "  where id_activite = " + uneParticipation.getIdActivite() + " and idutilisateur = " + uneParticipation.getIdUtilisateur()  + " ;" ;
		executerRequete(requete);		
	}
	
	/**********************TRESORERIE**************************************************/

	
	public static float selectFonds() {
		String requete ="select * from tresorerie;" ;
		
	Tresorerie uneTresorerie = null;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				uneTresorerie = new Tresorerie(
					desRes.getInt("id_tresorerie"),
					desRes.getFloat("fonds")
				);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete
			+ "\n" + exp.getMessage());
		}
		return uneTresorerie.getFonds(); 
	}
	
}
