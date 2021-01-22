package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controleur.Activite;
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
	
	//methode générique d'exécution de n'importe quelle requete nécessitant pas un retour de résultats 
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
			System.out.println("Erreur d'ex�cution de la requete : " + requete );
		}
	}
}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Les méthodes de gestion de la table Pilote 
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
			System.out.println("Erreur d'exécution de la requete : " + requete );
		}
		return unPilote ; 
	}
	
	//surcharge de la méthode avec de nouveaux arguments 
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
			System.out.println("Erreur d'exécution de la requete : " + requete );
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
			System.out.println("Erreur d'exécution de la requete : " + requete );
		}
		return lesPilotes ; 
	}
	
	
	*/



 





























