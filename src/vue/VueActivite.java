package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
//import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controleur.Activite;
import controleur.Main;
import controleur.StretchIcon;
//import controleur.Tresorerie;

public class VueActivite extends JFrame implements ActionListener, MouseListener{
	
	private JButton btDelete = new JButton("Delete", new StretchIcon("src/images/sup.png"));
	

	private JPanel panelAjout = new JPanel();
	private JButton btRetour = new JButton("Retour");
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JButton btFiltrer = new JButton("Filtrer :");
	private JTextField txtFiltrer = new JTextField();
	
	private JTextField txtNomAct = new JTextField(); 
	private JTextField txtLieu = new JTextField(); 
	private JTextField txtImageUrl = new JTextField();
	private JTextField txtLien = new JTextField();
	private JTextField txtBudget = new JTextField(); 
	private JTextField txtDescription = new JTextField(); 
	
	//Créer dateDebut et l'afficher
	private JLabel labDateDebut = new JLabel();
	private SimpleDateFormat sFormatDebut = new SimpleDateFormat("yyyy-MM-dd");
	private Date dateDebut = new Date();
	private JTextField txtDateDebut = new JTextField(sFormatDebut.format(dateDebut).toString());
	
	//Créer dateFin et l'afficher
	private JLabel labDateFin = new JLabel();
	private SimpleDateFormat sFormatFin = new SimpleDateFormat("yyyy-MM-dd");
	private Date dateFin = new Date();
	private JTextField txtDateFin = new JTextField(sFormatFin.format(dateFin).toString());
	
	private JTextField txtPrix= new JTextField();
	private JTextField txtNbPersonnes = new JTextField();
	//private JComboBox<String> cbxTresorerie = new JComboBox<String>();
	
	//Construction de la partie Tableau
	private JPanel panelLister = new JPanel(); 
	// on instancie une seule fois la JTable à l'avance,
	// puis on va juste refresh le model à chaque fois
	private JTable uneTable = new JTable(); 
	private JScrollPane uneScroll ; 
	//private Tableau unTableau ;
	private DefaultTableModel unTableauDefault;
	//ajout d'une détection du refresh pour éviter de le refaire
	//private boolean isRefreshed;
	
	public VueActivite() {
		this.setBounds(100, 100, Main.getWidth(), Main.getHeight());
		this.setTitle("Gestion des activités");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.getContentPane().setBackground(new Color (206,214, 224   ));
		
		//installer le bouton retour 
		this.btRetour.setBounds(Main.getWidth() -170, Main.getHeight() -80, 140, 30);
		this.add(this.btRetour); 
		this.btRetour.addActionListener(this);
		
		//construction du panel Ajout
		this.panelAjout.setBounds(40, 100, 300, 250);
		this.panelAjout.setBackground(new Color (206,214, 224  ));
		this.panelAjout.setLayout(new GridLayout(11, 2, 5, 5));
		this.panelAjout.add(new JLabel("Nom activité :")); 
		this.panelAjout.add(this.txtNomAct);
		this.panelAjout.add(new JLabel("Lieu de l'activité :")); 
		this.panelAjout.add(this.txtLieu);
		this.panelAjout.add(new JLabel("Image (URL) :")); 
		this.panelAjout.add(this.txtImageUrl);
		this.panelAjout.add(new JLabel("Budget de l'activité :")); 
		this.panelAjout.add(this.txtBudget);
		this.panelAjout.add(new JLabel("Description de l'activité :")); 
		this.panelAjout.add(this.txtDescription);
		this.panelAjout.add(new JLabel("Date de début :")); 
		this.panelAjout.add(this.txtDateDebut);
		this.panelAjout.add(new JLabel("Date de Fin :")); 
		this.panelAjout.add(this.txtDateFin);
		this.panelAjout.add(new JLabel("Prix de l'activité :")); 
		this.panelAjout.add(this.txtPrix);
		this.panelAjout.add(new JLabel("Nb de personnes de l'activité :")); 
		this.panelAjout.add(this.txtNbPersonnes);
		//this.panelAjout.add(new JLabel("Trésorerie :")); 
		//this.panelAjout.add(this.cbxTresorerie);
		this.panelAjout.add(this.btAnnuler); 
		this.panelAjout.add(this.btEnregistrer);
		this.add(this.panelAjout);
		
		//remplir les cbx Tresorerie
		//this.remplircbxTresorerie();
		
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);

		this.btFiltrer.setBounds(Main.getWidth() /2 - 200, 20, 100, 20);
		this.add(btFiltrer);
		this.btFiltrer.addActionListener(this);
		this.txtFiltrer.setBounds(Main.getWidth() / 2 - 80, 20 , 100, 20);
		this.add(txtFiltrer);
		
	
		remplirPanelLister("");
		this.uneTable.addMouseListener(this);
		
		this.setVisible(true);
		
		initBoutons();
		
		//isRefreshed = false; // par défaut
	}

	/*public void remplircbxTresorerie() {
		this.cbxTresorerie.removeAllItems();
		
		ArrayList<Tresorerie>lesTresoreries = Main.selectAllTresoreries("");
		for (Tresorerie uneTresorerie : lesTresoreries) {
			this.cbxTresorerie.addItem(uneTresorerie.getIdTresorerie() + ""); 
		}
	}*/
	
	private void initBoutons() {
		Main.styleBoutonDark(this.btAnnuler);
		Main.styleBoutonDark(this.btEnregistrer);
		Main.styleBoutonBleu(this.btRetour);
		Main.styleBoutonDark(this.btFiltrer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btRetour) {
			this.dispose();
			Main.rendreVisible(true);
		}else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Enregistrer")) {
			this.insertActivite();
		}else if (e.getSource() == this.btAnnuler) {
			this.btEnregistrer.setText("Enregistrer");
			this.viderLesChamps();
		}else if (e.getSource()  == this.btEnregistrer && e.getActionCommand().equals("Modifier")) {
			this.updateActivite();  
		}else if (e.getSource() == this.btFiltrer) {
			this.remplirPanelLister(this.txtFiltrer.getText());
		}
	}
	
	public void updateActivite() {
		String nom = this.txtNomAct.getText();
		String lieu = this.txtLieu.getText();
		String imageUrl = this.txtImageUrl.getText();
		String lien = this.txtImageUrl.getText();
		float budget;
		String description = this.txtDescription.getText();
		String dateDebut = this.txtDateDebut.getText();
		String dateFin = this.txtDateFin.getText();
		float prix;	
		int nbPersonnes;
		//int idTresorerie;
		int idTresorerie = 1;
		
		try {
			nbPersonnes = Integer.parseInt(this.txtNbPersonnes.getText());
			budget = Float.parseFloat(this.txtBudget.getText());
			prix = Float.parseFloat(this.txtPrix.getText());
			//idTresorerie = Integer.parseInt(this.cbxTresorerie.getSelectedItem().toString());
		}
		catch (NumberFormatException exp) {
			JOptionPane.showMessageDialog(this,"Attention au format du nombre d'heures  !");
			// annuler l'opération s'il y'a une erreur sur ce champ
			return;
		}
		//parser le String date dans un type Date pour permettre l'insertion
		Date ddactiviteDebut = null ;
		Date ddactiviteFin = null ;
		try {
			ddactiviteDebut = java.sql.Date.valueOf(dateDebut);
			ddactiviteFin = java.sql.Date.valueOf(dateFin);
		} catch (Exception e) {
			e.printStackTrace();
			// annuler l'opération s'il y'a une erreur sur ces champs
			JOptionPane.showMessageDialog(this,"Attention au format des dates (impossible "
			+ " de convertir le texte date en format Date Java, erreur de format de date.");
			return;
		}	
		
		try {
			if (nbPersonnes >=0) {
				int numLigne = uneTable.getSelectedRow(); 
				System.out.println("b");
				int idActivite = Integer.parseInt(unTableauDefault.getValueAt(numLigne, 0).toString ());
				// pour le update on utilise le constructeur qui a l'id_activite (pour l'insert
				// champ vide car = null)
				Activite uneActivite = new Activite(idActivite, nom, lieu, imageUrl, lien, budget, description,
						ddactiviteDebut, ddactiviteFin,	prix, nbPersonnes, idTresorerie);
				//update dans la base de donnÃ©es 
				Main.updateActivite(uneActivite);
				
				//modifiaction dans l'affichage tableau 
				//Object ligne[] = {uneActivite.getIdActivite(), nom, lieu, budget, description, prix, nbPersonnes+""};
				//this.unTableau.updateLigne(numLigne, ligne);
				remplirPanelLister("");
				
				JOptionPane.showMessageDialog(this,"Modification réussie !");
				this.btEnregistrer.setText("Enregistrer");
				this.viderLesChamps();
			} else {
				this.txtNbPersonnes.setBackground(Color.red);
			}
		} catch(Exception e) {
			System.out.println("Aie " + e.getStackTrace());	
		}
	}
	
	public void insertActivite() {
		String nom = this.txtNomAct.getText();
		String lieu = this.txtLieu.getText();
		String imageUrl = this.txtImageUrl.getText();
		String lien = this.txtImageUrl.getText();
		float budget;
		String description = this.txtDescription.getText();
		String dateDebut = this.txtDateDebut.getText();
		String dateFin = this.txtDateFin.getText();
		float prix;	
		int nbPersonnes;
		//int idTresorerie;
		int idTresorerie = 1;

		try {
			budget = Float.parseFloat(this.txtBudget.getText());
			prix = Float.parseFloat(this.txtPrix.getText());
			nbPersonnes = Integer.parseInt(this.txtNbPersonnes.getText());
			//idTresorerie = Integer.parseInt(this.cbxTresorerie.getSelectedItem().toString());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this,"Attention au format des nombres !");
			// annuler l'opération s'il y'a une erreur sur ces champs
			return;
		}
		//parser le String date dans un type Date pour permettre l'insertion
		Date ddactiviteDebut = null ;
		Date ddactiviteFin = null ;
		try {
			ddactiviteDebut = java.sql.Date.valueOf(dateDebut);
			ddactiviteFin = java.sql.Date.valueOf(dateFin);
		} catch (Exception e) {
			e.printStackTrace();
			// annuler l'opération s'il y'a une erreur sur ces champs
			JOptionPane.showMessageDialog(this,"Attention au format des dates (impossible "
			+ " de convertir le texte date en format Date Java, erreur de format de date.");
			return;
		}
	
		// pour l'insert le nombre de personnes est géré par les triggers automatiquement
		// donc il faut initialiser à 0
		if(nbPersonnes < 0) {
			this.txtNbPersonnes.setBackground(Color.red);
			JOptionPane.showMessageDialog(this,"Erreur d'insertion vérifier les champs !"
			+ "\nLe nombre de personnes ne peut pas être négatif.");
			// annuler l'opération s'il y'a une erreur sur ces champs
			return;
			
		} else if (nbPersonnes > 0) {
			this.txtNbPersonnes.setBackground(Color.red);
			JOptionPane.showMessageDialog(this,"Erreur d'insertion vérifier les champs !"
			+ "\nPour une insertion il faut toujours initialiser à 0, le nombre de personnes"
			+ "doit être égal à 0, puis ce sont les triggers qui vont l'actualiser.");
			// annuler l'opération s'il y'a une erreur sur ces champs
			return;
		} else {
			Activite uneActivite = new Activite(nom, lieu, imageUrl, lien, budget, description,
				ddactiviteDebut, ddactiviteFin,	prix, nbPersonnes, idTresorerie);
			Main.insertActivite(uneActivite);
			JOptionPane.showMessageDialog(this,"Insertion réussie !");
			remplirPanelLister("");
			this.viderLesChamps();
		}
	}
	
	public void initPanelLister() {
		//construire le panel Lister 
		this.panelLister.setBackground(new Color (206,214, 224  ));
		this.panelLister.setLayout(null);

		this.panelLister.setBounds(365, 80, this.getWidth() - 400, this.getHeight() - 170);
		//this.panelLister.setBorder(new LineBorder(Color.blue));
		
		this.uneScroll.setBounds(20, 20, this.panelLister.getWidth() - 40, this.panelLister.getHeight() - 40);
		this.panelLister.add(this.uneScroll);
		
		
		this.add(this.panelLister); 
	}
	
	public void remplirPanelLister(String mot) {
		//isRefreshed = false;
		
		this.panelLister.removeAll();
		String entetes [] = {"IdActivite", "Nom", "Lieu", "Image (URL)", "Lien", "Budget",
		"Description", "Date de Début", "Date de Fin", "Prix", "Nb de Personnes", "Opérations"};
		Object donnees [][] = this.getDonnees(mot) ;			
		//this.unTableau = new Tableau (donnees, entetes); 
		this.unTableauDefault = new DefaultTableModel(donnees, entetes);
		//this.uneTable = new JTable(this.unTableau); 

		//DefaultTableModel model = new DefaultTableModel(donnees, entetes);
		//this.uneTable.setModel(this.unTableau);
		// pour refresh la JTable tout en gardant les mêmes adresses mémoire
		// de la JTable et Jscroll (afin de conserver les actionListener)
		// on update uniquement la JTable sans la réinstancier
		this.uneTable.removeAll();
		// deep copy the JTable
		// https://stackoverflow.com/a/38798102
		JTable laNouvelleTable = new JTable(this.unTableauDefault);
		this.uneTable.setModel(laNouvelleTable.getModel());
		
		// rendre les colonnes + petites
		this.uneTable.getColumnModel().getColumn(0).setMaxWidth(50);
		this.uneTable.getColumnModel().getColumn(7).setMaxWidth(70);
		this.uneTable.getColumnModel().getColumn(8).setMaxWidth(70);
		this.uneTable.getColumnModel().getColumn(10).setMaxWidth(50);
		this.uneTable.getColumnModel().getColumn(11).setMaxWidth(80);
		// la colonne appréciation doit être + large pour bien afficher le texte
		//https://stackoverflow.com/questions/953972/java-jtable-setting-column-width
		this.uneTable.getColumnModel().getColumn(1).setMinWidth(150);
		this.uneTable.getColumnModel().getColumn(2).setMinWidth(120);
		this.uneTable.getColumnModel().getColumn(7).setMinWidth(70);
		this.uneTable.getColumnModel().getColumn(8).setMinWidth(70);
		this.uneTable.getColumnModel().getColumn(11).setMinWidth(80);
		
		//ajout du btDelete à la JTable
		// on instancie un nouveau btDelete pour détruire l'ActionListener précédent
		this.btDelete = new JButton("Delete", new StretchIcon("src/images/sup.png"));
		this.uneTable.getColumn("Opérations").setCellRenderer(new BoutonJTable());
		this.uneTable.getColumn("Opérations").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		System.out.println("c0");
		this.btDelete.removeActionListener(this);
		this.btDelete.addActionListener(new ActionListener() {	
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int ligne = uneTable.getSelectedRow();
				//if (isRefreshed == false) {
					System.out.println("ligne du tableau :" + ligne);
					int idActivite = Integer.parseInt(unTableauDefault.getValueAt(ligne, 0).toString()); 
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette activité ?", "Suppression", JOptionPane.YES_NO_OPTION); 
					if (retour == 0) {
						//suppression dans la base 
						Main.deleteActivite(idActivite);
						//suppression dans la table d'affichage 
						//unTableauDefault.deleteLigne(ligne);
						remplirPanelLister("");
						JOptionPane.showMessageDialog(null, "Suppression réussie");
					}
					//isRefreshed = true;
				//}
			}
		});
		System.out.println("c00");
		
		this.uneScroll = new JScrollPane(this.uneTable); 
		
		Main.styleTableau(this.uneTable);
		initPanelLister();
		
		//isRefreshed = true;
	}
	
	
	

	  class ButtonEditor extends DefaultCellEditor 
	  {
	    private String label;
	    
	    public ButtonEditor(JCheckBox checkBox)
	    {
	      super(checkBox);
	    }
	    public Component getTableCellEditorComponent(JTable table, Object value,
	    boolean isSelected, int row, int column) 
	    {
	      label = (value == null) ? "Delete" : value.toString();
	      btDelete.setText(label);
	      return btDelete;
	    }
	    public Object getCellEditorValue() 
	    {
	      return new String(label);
	    }
	  }
	
	  
	  
	
	public Object [] [] getDonnees(String mot) {
		//recuperer les pilotes de la bdd 
		ArrayList<Activite> lesActivites = Main.selectAllActivites(mot); 
		//transofrmation des pilotes en matrice de donnÃ©es 
		
		int length = 12; // 11 SQL rows + 1 Opération(BtDelete)
		
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
	
	public void viderLesChamps() {
		this.txtNomAct.setText("");
		this.txtLieu.setText("");
		this.txtImageUrl.setText("");
		this.txtLien.setText("");
		this.txtBudget.setText("");
		this.txtDescription.setText("");
		this.txtDateDebut.setText("");
		this.txtDateFin.setText("");
		this.txtPrix.setText("");
		this.txtNbPersonnes.setText("");
		//this.cbxTresorerie.setSelectedIndex(0);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() >=2) {

		}else if (e.getClickCount() ==1) {
			int ligne = uneTable.getSelectedRow();
			System.out.println("a");
			txtNomAct.setText(unTableauDefault.getValueAt(ligne, 1).toString());
			txtLieu.setText(unTableauDefault.getValueAt(ligne, 2).toString());
			txtImageUrl.setText(unTableauDefault.getValueAt(ligne, 3).toString());
			txtLien.setText(unTableauDefault.getValueAt(ligne, 4).toString());
			txtBudget.setText(unTableauDefault.getValueAt(ligne, 5).toString());
			txtDescription.setText(unTableauDefault.getValueAt(ligne, 6).toString());
			txtDateDebut.setText(unTableauDefault.getValueAt(ligne, 7).toString());
			txtDateFin.setText(unTableauDefault.getValueAt(ligne, 8).toString());
			txtPrix.setText(unTableauDefault.getValueAt(ligne, 9).toString());
			txtNbPersonnes.setText(unTableauDefault.getValueAt(ligne, 10).toString());
			//cbxTresorerie.setSelectedItem(unTableauDefault.getValueAt(ligne, 11).toString());
			btEnregistrer.setText("Modifier");
		}		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
