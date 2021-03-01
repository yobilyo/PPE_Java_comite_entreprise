package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import controleur.Participation;
import controleur.StretchIcon;
import controleur.Tableau;
import controleur.Utilisateur;

public class VueParticiper extends JFrame implements ActionListener{
	
	private JButton btDelete = new JButton("Delete", new StretchIcon("src/images/sup.png"));

	
	private JPanel panelAjout = new JPanel();
	private JButton btRetour = new JButton("Retour");
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JPanel panelLister = new JPanel();
	
	
	private JButton btFiltrer = new JButton("Filtrer :");
	private JTextField txtFiltrer = new JTextField();
	
	//Créer date et l'afficher
	//private JLabel labDate = new JLabel();
	//private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	//private Date dateComment = new Date();
	//private JTextField txtDate = new JTextField(sFormat.format(dateComment).toString());
	
	//private JTextField txtDateCommentaire = new JTextField(); 
	private JTextField txtDate = new JTextField(); 
	private JComboBox<String> cbxActivite = new JComboBox<String>();
	private JComboBox<String> cbxUtilisateur = new JComboBox<String>();
	
	//private Tableau unTableau;
	private DefaultTableModel unTableauDefault;
	// on instancie une seule fois la JTable à l'avance,
	// puis on va juste refresh le model à chaque fois
	private JTable uneTable = new JTable();
	private JScrollPane uneScroll;
	
	public VueParticiper() {
		super();
		this.setBounds(100, 100, Main.getWidth(), Main.getHeight());
		this.setTitle("Gestion des participations :");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		this.getContentPane().setBackground(new Color (206,214, 224));
		
		//installer le bouton retour 
		this.btRetour.setBounds(Main.getWidth() -170, Main.getHeight() -80, 140, 30);
		getContentPane().add(this.btRetour); 
		this.btRetour.addActionListener(this);
		
		//construction du panel Ajout
		this.panelAjout.setBounds(40, 100, 300, 250);
		this.panelAjout.setBackground(new Color (206,214, 224  ));
		this.panelAjout.setLayout(new GridLayout(5,2, 4, 4));
		this.panelAjout.add(new JLabel("Date d'inscription :")); 
		this.panelAjout.add(this.txtDate);
		this.panelAjout.add(new JLabel("Utilisateur :"));
		this.panelAjout.add(this.cbxUtilisateur);
		this.panelAjout.add(new JLabel("Activité : "));
		this.panelAjout.add(this.cbxActivite);
		
		remplirCBXActivite();
		remplirCBXUtilisateurs();
		remplirPanelLister("");
		
		getContentPane().add(panelAjout);
		this.panelAjout.add(btAnnuler);
		this.panelAjout.add(btEnregistrer);
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		this.btFiltrer.setBounds(299, 50, 100, 20);
		getContentPane().add(btFiltrer);
		this.btFiltrer.addActionListener(this);
		this.txtFiltrer.setBounds(409, 51 , 100, 20);
		getContentPane().add(txtFiltrer);
		this.setVisible(true);
		initBoutons();
		this.uneTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() ==1) {
					int ligne = uneTable.getSelectedRow();
					
					//cbxUtilisateur.setSelectedItem(unTableau.getValueAt(ligne, 0).toString());
					//System.out.println(unTableau.getValueAt(ligne, 0).toString());
					// idutilisateur est une clé étrangère dans la table participer, elle ne
					// peut pas et ne doit pas être modifiée
					cbxUtilisateur.setEnabled(false);
					// mais on affiche quand meme l'utilisateur
					String idUtilisateur = uneTable.getValueAt(ligne, 0).toString();
					String username = uneTable.getValueAt(ligne, 2).toString();
					String cbxTextUtilisateur = Main.genererCbxTextFromId(idUtilisateur, username);
					cbxUtilisateur.setSelectedItem(cbxTextUtilisateur);
					
					//cbxActivite.setSelectedItem(unTableau.getValueAt(ligne, 1).toString());
					//System.out.println(unTableau.getValueAt(ligne, 1).toString());
					cbxActivite.setEnabled(false);
					String idActivite = uneTable.getValueAt(ligne, 0).toString();
					String nomActivite = uneTable.getValueAt(ligne, 9).toString();
					String cbxTextActivite = Main.genererCbxTextFromId(idActivite, nomActivite);
					cbxActivite.setSelectedItem(cbxTextActivite);
					
					// maintenant que participer est une view sql, la date
					// est passée de la position 2 à la position 10 dans les données
					txtDate.setText(unTableauDefault.getValueAt(ligne, 10).toString());
					
					btEnregistrer.setText("Modifier");
				}		
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btRetour) {
			this.dispose();
			Main.rendreVisible(true);
		}else if(e.getSource() == this.btAnnuler) {
			//on réactive les cbx
			this.cbxUtilisateur.setEnabled(true);
			this.cbxActivite.setEnabled(true);
			this.viderLesChamps();
			
			this.btEnregistrer.setText("Enregistrer");
		}else if(e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Enregistrer")) {
			insertParticipation();
		}else if(e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Modifier")) {
			updateParticipation();
		}else if (e.getSource() == this.btFiltrer)
		{
			this.remplirPanelLister(this.txtFiltrer.getText());
		}
	}
	
	public void remplirCBXActivite() {
		ArrayList<Activite>lesActivites = Main.selectAllActivites("");
		for (Activite uneActivite : lesActivites) {
			String cbxTextActivite = Main.genererCbxTextFromId(
					Integer.toString(uneActivite.getIdActivite()), uneActivite.getNom());
			this.cbxActivite.addItem(cbxTextActivite); 
		}
	}
	public void remplirCBXUtilisateurs() {
		ArrayList<Utilisateur> lesUtilisateurs = Main.selectAllUtilisateurs("");
		this.cbxUtilisateur.removeAllItems();
		for (Utilisateur unUtilisateur : lesUtilisateurs) {
			// les sponsors ne peuvent pas participer à des activités, uniquement les salarie et admins
			if (!unUtilisateur.getDroits().equals("sponsor")) {
				String cbxTextUtilisateur = Main.genererCbxTextFromId(
						Integer.toString(unUtilisateur.getIdUtilisateur()), unUtilisateur.getUsername());
				this.cbxUtilisateur.addItem(cbxTextUtilisateur);
			}
		}
	}
	
	private void initBoutons() {
		Main.styleBoutonDark(this.btAnnuler);
		Main.styleBoutonDark(this.btEnregistrer);
		Main.styleBoutonBleu(this.btRetour);
		Main.styleBoutonDark(this.btFiltrer);
	}
	
	public void initPanelLister() {
		//construire le panel Lister 
		this.panelLister.setBackground(new Color (206,214, 224));
		this.panelLister.setLayout(null);

		this.panelLister.setBounds(365, 80, this.getWidth() - 400, this.getHeight() - 170);
		
		this.uneScroll.setBounds(20, 20, this.panelLister.getWidth() - 40, this.panelLister.getHeight() - 40);
		this.panelLister.add(this.uneScroll);
		
		
		getContentPane().add(this.panelLister); 
	}
	
	public void remplirPanelLister(String mot) {
		this.panelLister.removeAll();
		String entetes [] = {"IdUtilisateur", "IdActivite", 
							"Username", "Email", "Nom", "Prenom",
							"Tel","Adresse","Service","Activite", 
							"Date inscription","Lieu", "Description", "Opérations"};
		Object donnees [][] = this.getDonnees(mot) ;			
		this.unTableauDefault = new DefaultTableModel(donnees, entetes);
		
		// on instancie une seule fois la JTable dans le private puis plus jamais après
		//this.uneTable = new JTable(this.unTableau); 
		this.uneTable.removeAll();
		// deep copy the JTable
		// https://stackoverflow.com/a/38798102
		JTable laNouvelleTable = new JTable(this.unTableauDefault);
		this.uneTable.setModel(laNouvelleTable.getModel());
		
		// rendre les colonnes + petites
		this.uneTable.getColumnModel().getColumn(0).setMaxWidth(50);
		this.uneTable.getColumnModel().getColumn(1).setMaxWidth(50);
		this.uneTable.getColumnModel().getColumn(6).setMaxWidth(70);
		this.uneTable.getColumnModel().getColumn(7).setMaxWidth(70);
		this.uneTable.getColumnModel().getColumn(8).setMaxWidth(70);
		this.uneTable.getColumnModel().getColumn(10).setMaxWidth(70);
		this.uneTable.getColumnModel().getColumn(11).setMaxWidth(80);
		this.uneTable.getColumnModel().getColumn(13).setMaxWidth(80);
		// la colonne appréciation doit être + large pour bien afficher le texte
		//https://stackoverflow.com/questions/953972/java-jtable-setting-column-width
		this.uneTable.getColumnModel().getColumn(2).setMinWidth(80);
		this.uneTable.getColumnModel().getColumn(3).setMinWidth(130);
		this.uneTable.getColumnModel().getColumn(6).setMinWidth(70);
		this.uneTable.getColumnModel().getColumn(7).setMinWidth(70);
		this.uneTable.getColumnModel().getColumn(8).setMinWidth(70);
		this.uneTable.getColumnModel().getColumn(10).setMinWidth(70);
		this.uneTable.getColumnModel().getColumn(11).setMinWidth(80);
		this.uneTable.getColumnModel().getColumn(13).setMinWidth(80);
		
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
					int idUtilisateur = Integer.parseInt(unTableauDefault.getValueAt(ligne, 0).toString());
					int idActivite = Integer.parseInt(unTableauDefault.getValueAt(ligne, 1).toString()); 
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette participation ?", "Suppression", JOptionPane.YES_NO_OPTION); 
					if (retour == 0) {
						//suppression dans la base 
						Main.deleteParticipation(idUtilisateur, idActivite);
						//suppression dans la table d'affichage 
						//unTableauDefault.deleteLigne(ligne);
						// refresh du tableau après delete
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
		ArrayList<Participation> lesParticipations = Main.selectAllParticipations(mot);
		
		int length = 14; // 7 SQL rows + 1 Opération(BtDelete)
		//transofrmation des pilotes en matrice de donnÃ©es 
		Object donnees [][] = new Object [lesParticipations.size()][length];
		int i = 0 ; 
		for (Participation uneParticipation : lesParticipations) {
			donnees[i][0] = uneParticipation.getIdUtilisateur()+""; 
			donnees[i][1] = uneParticipation.getIdActivite(); 
			donnees[i][2] = uneParticipation.getUsername(); 
			donnees[i][3] = uneParticipation.getEmail(); 
			donnees[i][4] = uneParticipation.getNom(); 
			donnees[i][5] = uneParticipation.getPrenom(); 
			donnees[i][6] = uneParticipation.getTel(); 
			donnees[i][7] = uneParticipation.getAdresse(); 
			donnees[i][8] = uneParticipation.getService();
			donnees[i][9] = uneParticipation.getNom_activite(); 
			donnees[i][10] = uneParticipation.getDate_inscription();
			donnees[i][11] = uneParticipation.getLieu();
			donnees[i][12] = uneParticipation.getDescription()
					+ ""; 
			i++; 
		}
		return donnees;
	}

	
	public void viderLesChamps() {
		this.txtDate.setText("");
		this.cbxUtilisateur.setSelectedIndex(0);
		this.cbxActivite.setSelectedIndex(0);
	}
	
	public void updateParticipation() {
		/*String idActiviteBox = this.cbxActivite.getSelectedItem().toString();
		System.out.println(idActiviteBox);
		String idActiviteNum = Main.extraireIdFromCbxText(idActiviteBox);
		int idActivite = Integer.parseInt(idActiviteNum);
		System.out.println(idActivite);
		
		String idUtilisateurBox = this.cbxUtilisateur.getSelectedItem().toString();
		System.out.println(idUtilisateurBox);
		String idUtilisateurNum = Main.extraireIdFromCbxText(idUtilisateurBox);
		int idUtilisateur = Integer.parseInt(idUtilisateurNum);
		System.out.println(idUtilisateur);*/
		
		// récupération de la nouvelle date
		String dateParticipation = this.txtDate.getText();
		
		// récupération de l'ancien idUtilisateur et ancien idActivite
		int ligne = uneTable.getSelectedRow();
		int idUtilisateurOld = Integer.parseInt(unTableauDefault.getValueAt(ligne, 0).toString()); 
		int idActiviteOld = Integer.parseInt(unTableauDefault.getValueAt(ligne, 1).toString()); 
		
		// création de la nouvelle participation
		// idutlisateur et id_activite sont des clés étrangères dans la table sql participer
		// donc on ne peut pas et on ne doit pas les modifier
		Participation uneParticipation = new Participation (idUtilisateurOld, idActiviteOld, dateParticipation);
		
		//update dans la base de donnÃ©es 
		Main.updateParticipation(uneParticipation, idUtilisateurOld, idActiviteOld);
		
		//modifiaction dans l'affichage tableau 
		/*Object ligne[] = {uneParticipation.getIdUtilisateur(), uneParticipation.getIdActivite(), dateParticipation+""};
		this.unTableau.updateLigne(numLigne, ligne);*/
		// refresh du tableau après update
		this.remplirPanelLister("");
		
		JOptionPane.showMessageDialog(this,"Modification réussie !");
		this.viderLesChamps();
		// on vide aussi le bouton "Modifier" => "Enregistrer"
		this.btEnregistrer.setText("Enregistrer");
	}
	
	public void insertParticipation() {
		//String contenu = this.txtContenu.getText();
		String dateComment = this.txtDate.getText(); //récupération de la date dans un String
		String chaineAct = this.cbxActivite.getSelectedItem().toString();
		String tabAct [] = chaineAct.split(" - ");
		int idUtilisateur = 0;
		int idActivite = 0;
		String chaineUti = this.cbxUtilisateur.getSelectedItem().toString();
		String tabUti [] = chaineUti.split(" - ");
		
		//parser le String date dans un type Date pour permettre l'insertion
		Date ddcoment = null ;
		try {
			ddcoment = java.sql.Date.valueOf(dateComment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			idUtilisateur = Integer.parseInt(tabUti[0]);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Impossible de parser l'idUtilisateur");

		}
		try {
			idActivite = Integer.parseInt(tabAct[0]);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Impossible de parser l'idActivité");
		}
	

//		try {
//			dateComment = Date.parse(this.txtDateCommentaire.getText());
//		} catch (NumberFormatException nfe) {
//			JOptionPane.showMessageDialog(this,"Attention au format des nombres !");
//			budget = -1; 
//			prix = -1; 
//			nbPersonnes = -1;
//		}
	
		if(! this.txtDate.getText().equals("")){
			Participation uneParticipation = new Participation(idUtilisateur, idActivite, dateComment);
			Main.insertParticipation(uneParticipation);
			//refresh du tableau après insert
			remplirPanelLister("");
			JOptionPane.showMessageDialog(this,"Insertion réussie !");
			this.viderLesChamps();
		} else {
			this.txtDate.setBackground(Color.red);
			JOptionPane.showMessageDialog(this,"Erreur d'insertion vérifier les champs !");
		}
	}
	
}
