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
import controleur.Salarie;
import controleur.Sponsor;
import controleur.Utilisateur;



public class Modele 
{
	//private static Bdd uneBdd = new Bdd ("localhost:8889","airfrance","root","root");
	
	// pour PC : 
	private static Bdd uneBdd = new Bdd ("localhost","ce","root","");
	
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
	
													/************* ACTIVITE ********************/
	
	
	public static void insertActivite (Activite uneActivite) {
		String requete = "insert into activite values (null, '" + uneActivite.getNom() + "','" + uneActivite.getLieu()
		+"', " + "null, "   + "null, " +  uneActivite.getBudget() + ",'" + uneActivite.getDescription()+ "',"  + "null, "
		 + "null, "+ uneActivite.getPrix() +", " + uneActivite.getNb_personnes() 
		+", 1 );" ;
		executerRequete(requete);
		}
	
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
			System.out.println("Erreur d'exécution de la requete : " + requete );
		}
	}
	
	public static int executerRequeteResultShowError (String requete)
	{
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
			// informer du résultat de la requête
			return 0;
		}
		catch(SQLException exp) {
			String errorMsg = "Erreur d'exécution de la requete :" + requete
			+ "\n" + exp.getMessage();
			System.out.println(errorMsg);
			// montrer l'erreur sql à l'utilisateur s'il y'en a une
			JOptionPane.showMessageDialog(null, errorMsg);
			// informer du résultat de la requête
			return 1;
		}
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
						desRes.getInt("id_activite"), desRes.getString("nom"), desRes.getString("lieu"), desRes.getFloat("budget"), 
						desRes.getString("description"), desRes.getFloat("prix"), desRes.getInt("nb_personnes")
						);
				lesActivites.add(uneActivite);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete );
		}
		return lesActivites ; 
	}

	public static void deleteActivite (int idActivite)
	{
		String requete =" delete from activite where id_activite = " + idActivite +" ; " ;
		executerRequete(requete);
	}

	public static void updateActivite(Activite uneActivite) {
		String requete ="update activite set nom = '" + uneActivite.getNom() + "', lieu = '" + uneActivite.getLieu()
		+"', budget = " + uneActivite.getBudget() + ", description = '" + uneActivite.getDescription()
		+ "', prix = " + uneActivite.getPrix() +", nb_personnes= " + uneActivite.getNb_personnes() 
		+ "  where id_activite = " + uneActivite.getIdActivite() + " ;" ;
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
			System.out.println("Erreur d'exécution de la requete : " + requete );
		}
		return lesUtilisateurs ; 
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
			System.out.println("Erreur d'exécution de la requete : " + requete );
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
			System.out.println("Erreur d'exécution de la requete : " + requete );
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

	public static Utilisateur selectFindUtilisateur(String username, String email, String droits) {
		Utilisateur unUtilisateur = null;
		
		String requete = "select * from utilisateur where username like '%"+username+"%' and email like '%"+email+"%' and droits like '%"+droits+"%'; " ;
	
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				unUtilisateur = new Utilisateur (
						desRes.getInt("idutilisateur"), desRes.getString("username"), desRes.getString("password"), desRes.getString("email"), 
						desRes.getString("droits")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete );
		}
		return unUtilisateur; 
		
	}
	
	/************* UTILISATEUR SALARIE ********************/
	
	public static void insertUtilisateurSalarie(Salarie unSalarie) {
		// Utilisateur
		String requeteUtilisateur = "insert into utilisateur values (null, '" + unSalarie.getUsername() + "', '" + unSalarie.getPassword()
		+"', '" + unSalarie.getEmail()+ "', '" + unSalarie.getDroits() +"');";
		
		executerRequete(requeteUtilisateur);
		
		//SelectWhere pour obtenir l'idutilisateur réel inséré:
		int idUtilisateurInsere = Main.selectFindUtilisateur(unSalarie.getUsername(), unSalarie.getEmail(), unSalarie.getDroits()).getIdUtilisateur();
		
		// Salarie
		String requeteSalarie = "insert into salarie values(" + idUtilisateurInsere + ", '" + unSalarie.getNom() + "', '"
		+ unSalarie.getPrenom() + "', '" + unSalarie.getTel() + "', '" + unSalarie.getAdresse() + "', '"
		+ unSalarie.getQuotient_fam() + "', '" + unSalarie.getService() + "', '" + unSalarie.getSexe() + "');";
		
		executerRequete(requeteSalarie);
	}
	
	public static ArrayList<Salarie> selectAllUtilisateursSalaries(String mot) {
		String requete ; 
		// + simple d'utiliser la view sql utilisateur_salarie directement
		if (mot.equals("")) {
			requete ="select * from utilisateur_salarie ;" ;
		}else {
			requete ="select * from utilisateur_salarie where username like '%"+mot+"%' or email like '%"+mot+"%'; " ;
		}
		ArrayList<Salarie> lesUtilisateursSalaries = new ArrayList<Salarie>();  
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Salarie unSalarie = new Salarie (
						// utilisateur
						desRes.getInt("idutilisateur"), desRes.getString("username"), desRes.getString("email"), desRes.getString("password"),  
						desRes.getString("droits"),
						// salarie (suivre l'ordre de la table salarie, pas de la vue utilisateur_salarie)
						desRes.getString("nom"), desRes.getString("prenom"), desRes.getString("tel"), desRes.getString("adresse"),
						desRes.getString("quotient_fam"), desRes.getString("service"), desRes.getString("sexe")
						);
				lesUtilisateursSalaries.add(unSalarie);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete );
		}
		
		return lesUtilisateursSalaries ; 
	}
	
	public static int deleteUtilisateurSalarie(int idUtilisateurSalarie) {
		// on supprime le salarie dans la classe fille sql salarie en premier (car clé étrangère), puis on peut supprimer le salarié dans la classe mère utilisateur
		// Salarie
		String requeteSalarie = "delete from salarie where idutilisateur =" + idUtilisateurSalarie + ";";
		int resultSalarie = executerRequeteResultShowError(requeteSalarie);
		// quitter s'il y'a une erreur et en informer
		if (resultSalarie == 1) {
			return resultSalarie;
		}
		
		// s'il n'ya pas d'erreur pour la suppression du salarié, on continue et on passe à la suppression de l'utilisateur
		// Utilisateur
		String requeteUtilisateur = "delete from utilisateur where idutilisateur =" + idUtilisateurSalarie + ";";
		int resultUtilisateur = executerRequeteResultShowError(requeteUtilisateur);
		// résultat de la requête
		// qu'il y'ait une erreur ou non, dans tous les cas on retourne le résultat final de la dernière requête (ici delete utilisateur)
		//if (resultUtilisateur == 1 || resultUtilisateur == 0) {
		return resultUtilisateur;
		//}
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
						// utilisateur
						desRes.getInt("idutilisateur"), desRes.getString("username"), desRes.getString("email"), desRes.getString("password"),  
						desRes.getString("droits"),
						// sponsor (suivre l'ordre de la table sponsor, pas de la vue utilisateur_sponsor)
						desRes.getString("societe"), desRes.getString("image_url"), desRes.getDouble("budget"), desRes.getString("tel"),
						desRes.getString("lien")
						);
				lesUtilisateursSponsors.add(unSponsor);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete );
		}
		
		return lesUtilisateursSponsors ; 
	}
	
	public static void deleteUtilisateurSponsor(int idUtilisateurSponsor) {
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
	
}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



 





























