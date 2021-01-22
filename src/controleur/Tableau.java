package controleur;
import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel
{
	private String [] entetes ; 
	private Object [][]donnees ; 
	
	public Tableau (Object[][] donnees , String [] entetes) {
		this.donnees = donnees; 
		this.entetes = entetes; 
	}
	
	@Override
	public String getColumnName(int cl) {
		 
		return  this.entetes[cl];
	}

	@Override
	public int getRowCount() {
		 
		return this.donnees.length;
	}

	@Override
	public int getColumnCount() {
		
		return this.entetes.length;
	}

	@Override
	public Object getValueAt(int lg, int cl) {
		 
		return this.donnees[lg][cl]; //valeur au croisement de la ligne / colonne 
	}
	
	public void insertLigne (Object []ligne)
	{
		Object [][] matrice = new Object [this.donnees.length+1][this.entetes.length];
		for (int i = 0; i < this.donnees.length; i++) {
			matrice[i] = this.donnees[i]; 
		}
		matrice[this.donnees.length] = ligne;
		this.donnees = matrice;
		this.fireTableDataChanged();		
	}
	public void deleteLigne (int ligne) {
		Object [][] matrice = new Object [this.donnees.length-1][this.entetes.length];
		int j = 0 ;
		for (int i = 0; i < this.donnees.length; i++) {
			if (i !=ligne) {
				matrice[j] = this.donnees[i]; 
				j++; 
			}
		}
		this.donnees = matrice;
		this.fireTableDataChanged();
	}
	public void updateLigne(int numLigne, Object [] newLigne)
	{
		Object [][] matrice = new Object [this.donnees.length][this.entetes.length];
		for (int i = 0; i < this.donnees.length; i++) {
			if (i !=numLigne) {
				matrice[i] = this.donnees[i]; 
			}else {
				matrice[i] = newLigne;
			}
		}
		this.donnees = matrice;
		this.fireTableDataChanged();
	}

}