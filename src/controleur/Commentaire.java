package controleur;

import java.util.Date;

public class Commentaire {
	
	private int idCommentaire;
	private String dateComment;
	private String contenu;
	private int idActivite, idUtilisateur;
	


	
	public Commentaire() {
	}
	
	
	
	public Commentaire(int idCommentaire, String dateComment, String contenu, int idActivite, int idUtilisateur ) {
		this.idCommentaire = idCommentaire;
		this.idActivite = idActivite;
		this.idUtilisateur = idUtilisateur;
		this.dateComment = dateComment;
		this.contenu = contenu;
	}
	
	
	public Commentaire(String dateComment, String contenu, int idActivite, int idUtilisateur ) {
		this.idActivite = idActivite;
		this.idUtilisateur = idUtilisateur;
		this.dateComment = dateComment;
		this.contenu = contenu;
	}


	public int getIdCommentaire() {
		return idCommentaire;
	}
	public void setIdCommentaire(int idCommentaire) {
		this.idCommentaire = idCommentaire;
	}
	public int getIdActivite() {
		return idActivite;
	}
	public void setIdActivite(int idActivite) {
		this.idActivite = idActivite;
	}
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public String getDateComment() {
		return dateComment;
	}
	public void setDateComment(String dateComment) {
		this.dateComment = dateComment;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	
}
