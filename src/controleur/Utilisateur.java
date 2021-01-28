package controleur;

public class Utilisateur {

	private int idUtilisateur;
	private String email, username, password, droits;
	

	public Utilisateur(int idUtilisateur, String username, String password, String email, String droits) {
		this.idUtilisateur = idUtilisateur;
		this.username = username;
		this.password = password;
		this.email = email;
		this.droits = droits;
	}
	
	public Utilisateur(String username, String password, String email, String droits) {
		this.idUtilisateur = 0;
		this.username = username;
		this.password = password;
		this.email = email;
		this.droits = droits;
	} 
	public Utilisateur() {
		this.idUtilisateur = 0;
		this.username = "";
		this.password = "";
		this.email = "";
		this.droits = "";
	}
	
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDroits() {
		return droits;
	}
	public void setDroits(String droits) {
		this.droits = droits;
	} 
	
	
}
