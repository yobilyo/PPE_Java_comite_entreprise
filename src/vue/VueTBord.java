package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controleur.Activite;
import controleur.Don;
import controleur.Main;
import controleur.StretchIcon;
import vue.VueDon.ButtonEditor;


public class VueTBord extends JFrame implements ActionListener {
		
	//Construction de la partie Tableau Salarie
	private JPanel panelListerActivites = new JPanel();
	// on instancie une seule fois la JTable à l'avance,
	// puis on va juste refresh le model à chaque fois
	private JTable uneTableActivites = new JTable();
	private JScrollPane uneScrollActivites;
	//private Tableau unTableauSalarie;
	private DefaultTableModel unTableauActivitesDefault;
	
	//Construction de la partie Tableau Salarie
	private JPanel panelListerDons = new JPanel();
	// on instancie une seule fois la JTable à l'avance,
	// puis on va juste refresh le model à chaque fois
	private JTable uneTableDons = new JTable();
	private JScrollPane uneScrollDons;
	//private Tableau unTableauSalarie;
	private DefaultTableModel unTableauDonsDefault;
	
	private JLabel labelActivites = new JLabel();
	private JLabel labelDons = new JLabel();
	
	private JButton btRetour = new JButton("Retour");
	
	public VueTBord() {
		
		this.setBounds(100, 100, Main.getWidth(), Main.getHeight());
		this.setTitle("Tableau de Bord");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		this.getContentPane().setBackground(new Color (206,214, 224  ));
		
		//installer le bouton retour 
		this.btRetour.setBounds(Main.getWidth() -170, Main.getHeight() -80, 140, 30);
		getContentPane().add(this.btRetour); 
		this.btRetour.addActionListener(this);
		
		remplirpanelListerActivites("");
		//this.uneTableActivites.addMouseListener(this);
		remplirpanelListerDons("");
		//this.uneTableDons.addMouseListener(this);
		
		this.labelActivites.setText("Nos 5 dernières activités :");
		this.labelActivites.setBounds(50, 60, 500, 30);
		this.labelActivites.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		this.add(labelActivites);
		
		this.labelDons.setText("Nos 5 derniers dons :");
		this.labelDons.setBounds(50, 260, 500, 30);
		this.labelDons.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		this.add(labelDons);
		
		initBoutons();
		this.setVisible(true);
		
	}
	
	private void initBoutons() {
		Main.styleBoutonBleu(this.btRetour);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btRetour) {
			this.dispose();
			Main.rendreVisible(true);
		}
	
	}
	
	public void initpanelListerActivites() {
		//construire le panel Lister 
		this.panelListerActivites.setBackground(new Color (206,214, 224  ));
		this.panelListerActivites.setLayout(null);

		this.panelListerActivites.setBounds(15, 100, this.getWidth() - 40, 145);
		
		this.uneScrollActivites.setBounds(20, 20, this.panelListerActivites.getWidth() - 40, this.panelListerActivites.getHeight() - 40);
		this.panelListerActivites.add(this.uneScrollActivites);
		
		
		this.add(this.panelListerActivites); 
	}
	
	public void remplirpanelListerActivites(String mot) {
		this.panelListerActivites.removeAll();
		String entetes [] = {"IdActivite", "Nom", "Lieu", "Image (URL)", "Lien", "Budget",
				"Description", "Date de Début", "Date de Fin", "Prix", "Nb de Personnes"};
		Object donnees [][] = this.getDonneesActivites(mot) ;			
		//this.unTableau = new Tableau (donnees, entetes); 
		this.unTableauActivitesDefault = new DefaultTableModel(donnees, entetes);
		
		//this.uneTableActivites = new JTable(this.unTableau); 
		this.uneTableActivites.removeAll();
		// deep copy the JTable
		// https://stackoverflow.com/a/38798102
		JTable laNouvelleTable = new JTable(this.unTableauActivitesDefault);
		this.uneTableActivites.setModel(laNouvelleTable.getModel());
		// rendre les colonnes id + petites
		this.uneTableActivites.getColumnModel().getColumn(0).setMaxWidth(50);
		this.uneTableActivites.getColumnModel().getColumn(1).setMaxWidth(150);
		this.uneTableActivites.getColumnModel().getColumn(2).setMaxWidth(70);
		this.uneTableActivites.getColumnModel().getColumn(4).setMaxWidth(50);
		this.uneTableActivites.getColumnModel().getColumn(5).setMaxWidth(95);
		this.uneTableActivites.getColumnModel().getColumn(6).setMaxWidth(180);
		this.uneTableActivites.getColumnModel().getColumn(7).setMaxWidth(70);
		this.uneTableActivites.getColumnModel().getColumn(8).setMaxWidth(70);
		this.uneTableActivites.getColumnModel().getColumn(9).setMaxWidth(70);
		this.uneTableActivites.getColumnModel().getColumn(10).setMaxWidth(70);
		//this.uneTableActivites.getColumnModel().getColumn(7).setMaxWidth(80);
		// la colonne appréciation doit être + large pour bien afficher le texte
		//https://stackoverflow.com/questions/953972/java-jtable-setting-column-width
		this.uneTableActivites.getColumnModel().getColumn(1).setMinWidth(150);
		this.uneTableActivites.getColumnModel().getColumn(3).setMinWidth(220);
		this.uneTableActivites.getColumnModel().getColumn(5).setMinWidth(95);
		this.uneTableActivites.getColumnModel().getColumn(6).setMinWidth(180);
		//this.uneTableActivites.getColumnModel().getColumn(7).setMinWidth(80);

		//ajout du btDelete à la JTable
		// on instancie un nouveau btDelete pour détruire l'ActionListener précédent
		//this.btDelete = new JButton("Delete", new StretchIcon("src/images/sup.png"));
		//this.uneTableActivites.getColumn("Opérations").setCellRenderer(new BoutonJTable());
		//this.uneTableActivites.getColumn("Opérations").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		//System.out.println("c0");
		//this.btDelete.removeActionListener(this);
		//this.btDelete.addActionListener(new ActionListener() {	
			
		/*	@Override
			public void actionPerformed(ActionEvent e) {
				int ligne = uneTableActivites.getSelectedRow();
				//if (isRefreshed == false) {
					System.out.println("ligne du tableau :" + ligne);
					int iddon = Integer.parseInt(unTableauActivitesDefault.getValueAt(ligne, 0).toString()); 
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce don ?", "Suppression", JOptionPane.YES_NO_OPTION); 
					if (retour == 0) {
						//suppression dans la base 
						Main.deleteDon(iddon);
						//suppression dans la table d'affichage 
						//unTableauActivitesDefault.deleteLigne(ligne);
						// refresh du tableau
						remplirpanelListerActivites("");
						JOptionPane.showMessageDialog(null, "Suppression réussie");
					}
					//isRefreshed = true;
				//}
			}
		});
		System.out.println("c00");
		*/
		
		this.uneScrollActivites = new JScrollPane(this.uneTableActivites); 
		
		Main.styleTableau(this.uneTableActivites);
		initpanelListerActivites();
		
		//isRefreshed = true;

	}
	
	public Object [] [] getDonneesActivites(String mot) {
		//recuperer les pilotes de la bdd 
		ArrayList<Activite> lesActivites = Main.selectAllActivitesLast(mot); 
		//transofrmation des pilotes en matrice de donnÃ©es 
		
		int length = 11; // 11 SQL rows , no + 1 Opération(BtDelete)
		
		Object donnees [][] = new Object [lesActivites.size()][length];
		int i = 0 ; 
		for (Activite uneActivite : lesActivites) {
			donnees[i][0] = uneActivite.getIdActivite()+""; 
			donnees[i][1] = uneActivite.getNom(); 
			donnees[i][2] = uneActivite.getLieu();
			donnees[i][3] = uneActivite.getImage_url();
			donnees[i][4] = uneActivite.getLien();
			donnees[i][5] = uneActivite.getBudget();
			donnees[i][6] = uneActivite.getDescription();
			donnees[i][7] = uneActivite.getDate_debut();
			donnees[i][8] = uneActivite.getDate_fin();
			donnees[i][9] = uneActivite.getPrix(); 
			donnees[i][10] = uneActivite.getNb_personnes();
			//donnees[i][11] = uneActivite.getIdTresorerie();

			i++; 
		}
				
		return donnees;
	}
	
	public void initPanelListerDons() {
		//construire le panel Lister 
		this.panelListerDons.setBackground(new Color (206,214, 224  ));
		this.panelListerDons.setLayout(null);

		this.panelListerDons.setBounds(15, 100 + 50 + 145, this.getWidth() - 40, 145);
		
		this.uneScrollDons.setBounds(20, 20, this.panelListerDons.getWidth() - 40, this.panelListerDons.getHeight() - 40);
		this.panelListerDons.add(this.uneScrollDons);
		
		
		this.add(this.panelListerDons); 
	}
	
	public void remplirpanelListerDons(String mot) {
		this.panelListerDons.removeAll();
		String entetes [] = {"ID Don", "Date don", "Montant", "Appreciation", "ID Utilisateur",
				"Nom d'utilisateur", "Société"};
		Object donnees [][] = this.getDonneesDons(mot) ;			
		//this.unTableau = new Tableau (donnees, entetes); 
		this.unTableauDonsDefault = new DefaultTableModel(donnees, entetes);
		
		//this.uneTableDons = new JTable(this.unTableau); 
		this.uneTableDons.removeAll();
		// deep copy the JTable
		// https://stackoverflow.com/a/38798102
		JTable laNouvelleTable = new JTable(this.unTableauDonsDefault);
		this.uneTableDons.setModel(laNouvelleTable.getModel());
		// rendre les colonnes id + petites
		this.uneTableDons.getColumnModel().getColumn(0).setMaxWidth(50);
		this.uneTableDons.getColumnModel().getColumn(1).setMaxWidth(70);
		this.uneTableDons.getColumnModel().getColumn(2).setMaxWidth(70);
		this.uneTableDons.getColumnModel().getColumn(4).setMaxWidth(50);
		this.uneTableDons.getColumnModel().getColumn(5).setMaxWidth(95);
		this.uneTableDons.getColumnModel().getColumn(6).setMaxWidth(130);
		//this.uneTableDons.getColumnModel().getColumn(7).setMaxWidth(80);
		// la colonne appréciation doit être + large pour bien afficher le texte
		//https://stackoverflow.com/questions/953972/java-jtable-setting-column-width
		this.uneTableDons.getColumnModel().getColumn(1).setMinWidth(70);
		this.uneTableDons.getColumnModel().getColumn(3).setMinWidth(300);
		this.uneTableDons.getColumnModel().getColumn(5).setMinWidth(95);
		this.uneTableDons.getColumnModel().getColumn(6).setMinWidth(130);
		//this.uneTableDons.getColumnModel().getColumn(7).setMinWidth(80);

		//ajout du btDelete à la JTable
		// on instancie un nouveau btDelete pour détruire l'ActionListener précédent
		//this.btDelete = new JButton("Delete", new StretchIcon("src/images/sup.png"));
		//this.uneTableDons.getColumn("Opérations").setCellRenderer(new BoutonJTable());
		//this.uneTableDons.getColumn("Opérations").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		//this.btDelete.removeActionListener(this);
		//this.btDelete.addActionListener(new ActionListener() {	

		
		this.uneScrollDons = new JScrollPane(this.uneTableDons); 
		
		Main.styleTableau(this.uneTableDons);
		initPanelListerDons();
		
		//isRefreshed = true;

	}
	
	public Object [] [] getDonneesDons(String mot) {
		//recuperer les pilotes de la bdd 
		ArrayList<Don> lesDons = Main.selectAllDonsLast(mot); 
		
		int length = 7; // 7 SQL rows , + no Opération(BtDelete)
		//transofrmation des pilotes en matrice de donnÃ©es 
		Object donnees [][] = new Object [lesDons.size()][length];
		int i = 0 ; 
		for (Don unDon : lesDons) {
			donnees[i][0] = unDon.getIddon()+""; 
			donnees[i][1] = unDon.getDatedon(); 
			donnees[i][2] = unDon.getMontant(); 
			donnees[i][3] = unDon.getAppreciation(); 
			donnees[i][4] = unDon.getIdutilisateur(); 
			donnees[i][5] = unDon.getUsername(); 
			donnees[i][6] = unDon.getSociete();
			i++; 
		}
				
		return donnees;
	}


}
