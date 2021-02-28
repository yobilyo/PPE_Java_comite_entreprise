package controleur;

public class Don {
	private int iddon, idutilisateur, id_tresorerie;
	private String appreciation;
	private Float montant;
	private String datedon, username, societe;
	
	public Don() {
		super();
		this.idutilisateur = 0;
		this.id_tresorerie = 0;
		this.appreciation = "";
		this.montant = (float) 0;
		this.datedon = "";
		this.username = "";
		this.societe = "";
	}
	
	public Don(int iddon, int idutilisateur, int id_tresorerie, String appreciation, Float montant, String datedon,
			String username, String societe) {
		super();
		this.iddon = iddon;
		this.idutilisateur = idutilisateur;
		this.id_tresorerie = id_tresorerie;
		this.appreciation = appreciation;
		this.montant = montant;
		this.datedon = datedon;
		this.username = username;
		this.societe = societe;
	}
	
	public Don(int idutilisateur, int id_tresorerie, String appreciation, Float montant, String datedon,
			String username, String societe) {
		super();
		this.idutilisateur = idutilisateur;
		this.id_tresorerie = id_tresorerie;
		this.appreciation = appreciation;
		this.montant = montant;
		this.datedon = datedon;
		this.username = username;
		this.societe = societe;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public int getIddon() {
		return iddon;
	}

	public void setIddon(int iddon) {
		this.iddon = iddon;
	}

	public int getIdutilisateur() {
		return idutilisateur;
	}

	public void setIdutilisateur(int idutilisateur) {
		this.idutilisateur = idutilisateur;
	}

	public int getId_tresorerie() {
		return id_tresorerie;
	}

	public void setId_tresorerie(int id_tresorerie) {
		this.id_tresorerie = id_tresorerie;
	}

	public String getAppreciation() {
		return appreciation;
	}

	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}

	public Float getMontant() {
		return montant;
	}

	public void setMontant(Float montant) {
		this.montant = montant;
	}

	public String getDatedon() {
		return datedon;
	}

	public void setDatedon(String datedon) {
		this.datedon = datedon;
	}
	
}
