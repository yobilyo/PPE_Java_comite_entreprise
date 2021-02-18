package controleur;

public class Participation {
	private int idUtilisateur, idActivite;
	private String dateParticipation;
	
	public Participation() {
		this.idUtilisateur = 0; // clé primaire
		this.idActivite = 0;    // 2ème clé primaire
		this.dateParticipation = "";
	}
	
	public Participation(String dateParticipation) {
		this.idUtilisateur = 0;
		this.idActivite = 0;
		this.dateParticipation = dateParticipation;
	}
	
	// les 2 clés primaires
	public Participation(int idUtilisateur, int idActivite, String dateParticipation) {
		this.idUtilisateur = idUtilisateur;
		this.idActivite = idActivite;
		this.dateParticipation = dateParticipation;
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

	public String getDateParticipation() {
		return dateParticipation;
	}

	public void setDateParticipation(String dateParticipation) {
		this.dateParticipation = dateParticipation;
	}
}
