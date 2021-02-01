package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Activite;
import controleur.Commentaire;
import controleur.Don;
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
		+"', appreciation = " + unDon.getAppreciation() + ", idutilisateur = '" + unDon.getIdutilisateur()
		+ "', id_tresorerie = " + unDon.getId_tresorerie() 
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
						desRes.getInt("id_commentaire"), desRes.getString("datecomment"), desRes.getString("contenu"), desRes.getInt("id_activite"), 
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
		 +"', " + unCommentaire.getIdActivite() + "', '" + unCommentaire.getIdUtilisateur() + "' "
		 + ");";
		executerRequete(requete);
	}
}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



 





























