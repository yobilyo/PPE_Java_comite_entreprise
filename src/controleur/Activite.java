package controleur;

import java.util.Date;

public class Activite {
	private int idActivite;
	private String nom, lieu, image_url, lien;
	private float budget;
	private String description;
	private Date date_debut, date_fin;
	private float prix ;
	private int nb_personnes, idTresorerie;
	
	public Activite() {
		super();
		this.idActivite = 0;
		this.nom = "";
		this.lieu = "";
		this.image_url = "";
		this.lien = "";
		this.budget = 0;
		this.description = "";
		this.date_debut = new Date();
		this.date_fin = new Date();
		this.prix = 0;
		this.nb_personnes = 0;
		this.idTresorerie = 0;
	}

	public Activite(int idActivite,  String nom, String lieu, String image_url, String lien,
			float budget, String description, Date date_debut, Date date_fin, float prix,
			int nb_personnes, int idTresorerie) {
		super();
		this.idActivite = idActivite;
		this.nom = nom;
		this.lieu = lieu;
		this.image_url = image_url;
		this.lien = lien;
		this.budget = budget;
		this.description = description;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.prix = prix;
		this.nb_personnes = nb_personnes;
		this.idTresorerie = idTresorerie;
	}

	public Activite(String nom, String lieu, String image_url, String lien,
			float budget, String description, Date date_debut, Date date_fin, float prix,
			int nb_personnes, int idTresorerie) {
		super();
		this.idActivite = 0;
		this.nom = nom;
		this.lieu = lieu;
		this.image_url = image_url;
		this.lien = lien;
		this.budget = budget;
		this.description = description;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.prix = prix;
		this.nb_personnes = nb_personnes;
		this.idTresorerie = idTresorerie;
	}

	public int getIdActivite() {
		return idActivite;
	}
	public void setIdActivite(int idActivite) {
		this.idActivite = idActivite;
	}
	public int getNb_personnes() {
		return nb_personnes;
	}
	public void setNb_personnes(int nb_personnes) {
		this.nb_personnes = nb_personnes;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getLien() {
		return lien;
	}
	public void setLien(String lien) {
		this.lien = lien;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getBudget() {
		return budget;
	}
	public void setBudget(float budget) {
		this.budget = budget;
	}
	public int getIdTresorerie() {
		return idTresorerie;
	}
	public void setIdTresorerie(int idTresorerie) {
		this.idTresorerie = idTresorerie;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}
	
}
