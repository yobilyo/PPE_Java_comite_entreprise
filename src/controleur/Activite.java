package controleur;

import java.util.Date;

public class Activite {
	private int idActivite, nb_personnes;
	private String nom, lieu, image_url, lien, description;
	private float budget, prix ;
	private Date date_debut, date_fin;
	
	
	public Activite() {
	}
	

	public Activite(int idActivite,  String nom, String lieu,  float budget, String description, float prix,   int nb_personnes) {
		super();
		this.idActivite = idActivite;
		this.nb_personnes = nb_personnes;
		this.nom = nom;
		this.lieu = lieu;
		this.description = description;
		this.budget = budget;
		this.prix = prix;
	}

	
	public Activite(String nom, String lieu, float budget, String description,  float prix, int nb_personnes) {
		super();
		this.nb_personnes = nb_personnes;
		this.nom = nom;
		this.lieu = lieu;
		this.description = description;
		this.budget = budget;
		this.prix = prix;
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
