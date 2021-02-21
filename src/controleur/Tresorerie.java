package controleur;

public class Tresorerie {
	private int idTresorerie;
	private float fonds;
	
	public Tresorerie() {
		this.idTresorerie = 0;
		this.fonds = 0;
	}
	
	public Tresorerie(float fonds) {
		this.idTresorerie = 0;
		this.fonds = fonds;
	}
	
	public Tresorerie(int idTresorerie, float fonds) {
		this.idTresorerie = idTresorerie;
		this.fonds = fonds;
	}

	public int getIdTresorerie() {
		return idTresorerie;
	}

	public void setIdTresorerie(int idTresorerie) {
		this.idTresorerie = idTresorerie;
	}

	public float getFonds() {
		return fonds;
	}

	public void setFonds(float fonds) {
		this.fonds = fonds;
	}

}
