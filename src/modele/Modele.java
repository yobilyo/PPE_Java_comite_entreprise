package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Activite;
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

	public static ArrayList<Activite> selectAllActivites (String mot){
		
		String requete ; 
		if (mot.equals("")) {
			requete ="select * from activite ;" ;
		}else {
			requete ="select * from activite where nom like '%"+mot+"%'" + " or prix like '%"+mot+"%'"
					+ " or lieu like '%"+mot+"%' or description like '%"+mot+"%' or nb_personnes like '%"+mot+"%' ; " ;
					// budget est de type double, on ne le cherche pas avec des like '%string%'
					// ex: select * from sponsor where budget = 8000 or societe like '%Lys D\'Or%';
					// donc on ne le met pas ici
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
	

}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Les mÃ©thodes de gestion de la table Pilote 
/*	public static void insertPilote (Pilote unPilote)
	{
		String requete ="insert into pilote values (null, '" + unPilote.getNom() + "','" + unPilote.getPrenom()
		+"','" + unPilote.getNationalite() + "','" + unPilote.getEmail()+ "','" + unPilote.getBip() +"', " + unPilote.getNbheuresvol() +" );" ;
		executerRequete(requete);
	}
	
	public static void deletePilote (int idPilote)
	{
		String requete =" delete from pilote where idpilote = " + idPilote +" ; " ;
		executerRequete(requete);
	}
	
	public static void updatePilote (Pilote unPilote)
	{
		String requete ="update pilote set nom = '" + unPilote.getNom() + "', prenom = '" + unPilote.getPrenom()
		+"', nationalite = '" + unPilote.getNationalite() + "', email = '" + unPilote.getEmail()
		+ "', bip = '" + unPilote.getBip() +"', nbheuresvol= " + unPilote.getNbheuresvol() 
		+ "  where idpilote = " + unPilote.getIdpilote() + " ;" ;
		executerRequete(requete);
	}
	
	public static Pilote selectWherePilote (int idPilote)
	{
		String requete ="select * from pilote where idpilote = "+ idPilote +";" ;
		Pilote unPilote = null ; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				unPilote = new Pilote (
						unRes.getInt("idpilote"), unRes.getString("nom"), unRes.getString("prenom"), unRes.getString("email"), 
						unRes.getString("bip"), unRes.getString("nationalite"), unRes.getInt("nbheuresvol")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exÃ©cution de la requete : " + requete );
		}
		return unPilote ; 
	}
	
	//surcharge de la mÃ©thode avec de nouveaux arguments 
	public static Pilote selectWherePilote (String email, String bip)
	{
		String requete ="select * from pilote where email = '"+ email +"' and bip = '"+bip +"' ;" ;
		Pilote unPilote = null ; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				unPilote = new Pilote (
						unRes.getInt("idpilote"), unRes.getString("nom"), unRes.getString("prenom"), unRes.getString("email"), 
						unRes.getString("bip"), unRes.getString("nationalite"), unRes.getInt("nbheuresvol")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exÃ©cution de la requete : " + requete );
		}
		return unPilote ; 
	}

	public static ArrayList<Pilote> selectAllPilotes (String mot){
		
		String requete ; 
		if (mot.equals("")) {
			requete ="select * from pilote ;" ;
		}else {
			requete ="select * from pilote where nom like '%"+mot+"%' or prenom like '%"+mot+"%' or nationalite like '%" + mot + "%' ; " ;
		}
		ArrayList<Pilote> lesPilotes = new ArrayList<Pilote>();  
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Pilote unPilote = new Pilote (
						desRes.getInt("idpilote"), desRes.getString("nom"), desRes.getString("prenom"), desRes.getString("email"), 
						desRes.getString("bip"), desRes.getString("nationalite"), desRes.getInt("nbheuresvol")
						);
				lesPilotes.add(unPilote);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exÃ©cution de la requete : " + requete );
		}
		return lesPilotes ; 
	}
	
	
	*/



 





























