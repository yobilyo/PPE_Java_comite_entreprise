package controleur;

public class Participation {
	private int idUtilisateur, idActivite;
	private String username, email, nom, prenom, tel, adresse, service, nom_activite, date_inscription, lieu, description;
	
	public Participation() {
		this.idUtilisateur = 0; // clé primaire
		this.idActivite = 0;    // 2ème clé primaire
		this.username ="";
		this.email="";
		this.nom = "";
		this.prenom="";
		this.tel="";
		this.adresse="";
		this.service="";
		this.nom_activite="";
		this.date_inscription="";
		this.lieu="";
		this.description="";
		
	}
	
	
	
	public Participation(int idUtilisateur, int idActivite, String username, String email,
			String nom, String prenom, String tel, String adresse, String service, String nom_activite,
			String date_inscription, String lieu, String description) {
	
		this.idUtilisateur = idUtilisateur;
		this.idActivite = idActivite;
		this.username = username;
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.adresse = adresse;
		this.service = service;
		this.nom_activite = nom_activite;
		this.date_inscription = date_inscription;
		this.lieu = lieu;
		this.description = description;
	}
	
	public Participation(int idUtilisateur, int idActivite,
			String date_inscription) {
	
		this.idUtilisateur = idUtilisateur;
		this.idActivite = idActivite;
		this.date_inscription = date_inscription;
	}



	public Participation(String username, String email,
			String nom, String prenom, String tel, String adresse, String service, String nom_activite,
			String date_inscription, String lieu, String description) {
		this.idUtilisateur = 0;
		this.idActivite = 0;
		this.username = username;
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.adresse = adresse;
		this.service = service;
		this.nom_activite = nom_activite;
		this.date_inscription = date_inscription;
		this.lieu = lieu;
		this.description = description;
	}



	public int getIdUtilisateur() {
		return idUtilisateur;
	}



	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}



	public int getIdActivite() {
		return idActivite;
	}



	public void setIdActivite(int idActivite) {
		this.idActivite = idActivite;
	}




	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public String getAdresse() {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	public String getService() {
		return service;
	}



	public void setService(String service) {
		this.service = service;
	}



	public String getNom_activite() {
		return nom_activite;
	}



	public void setNom_activite(String nom_activite) {
		this.nom_activite = nom_activite;
	}



	public String getDate_inscription() {
		return date_inscription;
	}



	public void setDate_inscription(String date_inscription) {
		this.date_inscription = date_inscription;
	}



	public String getLieu() {
		return lieu;
	}



	public void setLieu(String lieu) {
		this.lieu = lieu;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}
	

}
