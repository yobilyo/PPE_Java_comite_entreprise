package controleur;

public class Salarie extends Utilisateur {
	private String nom, prenom, tel, adresse, quotient_fam, service, sexe;
	
	public Salarie(int idUtilisateur, String username, String password, String email, String droits, //Utilisateur
			String nom, String prenom, String tel, String adresse, String quotient_fam, String service, String sexe) { // Salarie
		super(idUtilisateur, username, password, email, droits);
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.adresse = adresse;
		this.quotient_fam = quotient_fam;
		this.service = service;
		this.sexe = sexe;
	}
	
	public Salarie(String username, String password, String email, String droits, //Utilisateur
			String nom, String prenom, String tel, String adresse, String quotient_fam, String service, String sexe) { // Salarie
		super(username, password, email, droits);
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.adresse = adresse;
		this.quotient_fam = quotient_fam;
		this.service = service;
		this.sexe = sexe;
	}
	
	public Salarie() {
		super();
		this.nom = "";
		this.prenom = "";
		this.tel = "";
		this.adresse = "";
		this.quotient_fam = "";
		this.service = "";
		this.sexe = "";
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

	public String getQuotient_fam() {
		return quotient_fam;
	}

	public void setQuotient_fam(String quotient_fam) {
		this.quotient_fam = quotient_fam;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	
	
}
