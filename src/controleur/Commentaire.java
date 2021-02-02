package controleur;

import java.util.Date;

public class Commentaire {
	
	private int idCommentaire;
	private Date dateComment;
	private String contenu;
	private int idActivite, idUtilisateur;
	


	
	public Commentaire() {
	}
	
	
	
	public Commentaire(int idCommentaire, Date dateComment, String contenu, int idActivite, int idUtilisateur ) {
		this.idCommentaire = idCommentaire;
		this.idActivite = idActivite;
		this.idUtilisateur = idUtilisateur;
		this.dateComment = dateComment;
		this.contenu = contenu;
	}
	
	
	public Commentaire(Date dateComment, String contenu, int idActivite, int idUtilisateur ) {
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
	public Date getDateComment() {
		return dateComment;
	}
	public void setDateComment(Date dateComment) {
		this.dateComment = dateComment;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	
}
