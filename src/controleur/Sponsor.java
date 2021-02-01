package controleur;

public class Sponsor extends Utilisateur {
	private String societe, image_url, tel, lien;
	private double budget;
	
	public Sponsor(int idUtilisateur, String username, String password, String email, String droits, //Utilisateur
			String societe, String image_url, double budget, String tel, String lien) { // Sponsor
		super(idUtilisateur, username, password, email, droits);
		this.societe = societe;
		this.image_url = image_url;
		this.budget = budget;
		this.tel = tel;
		this.lien = lien;
	}
	
	public Sponsor(String username, String password, String email, String droits, //Utilisateur
			String societe, String image_url, double budget, String tel, String lien) { // Sponsor
		super(username, password, email, droits);
		this.societe = societe;
		this.image_url = image_url;
		this.budget = budget;
		this.tel = tel;
		this.lien = lien;
	}
	
	public Sponsor() {
		super();
		this.societe = "";
		this.image_url = "";
		this.budget = 0;
		this.tel = "";
		this.lien = "";
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	
}
