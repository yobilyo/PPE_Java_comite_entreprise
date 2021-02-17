package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
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
import controleur.Main;
import controleur.StretchIcon;
import controleur.Tableau;

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
	private JTextField txtBudget = new JTextField(); 
	private JTextField txtDescription = new JTextField(); 
	private JTextField txtPrix= new JTextField();
	private JTextField txtNbPersonnes = new JTextField();
	
	//Construction de la partie Tableau
	private JPanel panelLister = new JPanel(); 
	private JTable uneTable ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau ;

//	Barre de filtrage
//	private JTextField txtMot = new JTextField ();
//	private JButton btFiltrer = new JButton("filtrer"); 
	
	public VueActivite() {
		this.setBounds(100, 100, Main.WIDTH, Main.HEIGHT);
		this.setTitle("Gestion des activités");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.getContentPane().setBackground(new Color (206,214, 224   ));
		
		//installer le bouton retour 
		this.btRetour.setBounds(Main.WIDTH -170, Main.HEIGHT -80, 140, 30);
		this.add(this.btRetour); 
		this.btRetour.addActionListener(this);
		
		//construction du panel Ajout
		this.panelAjout.setBounds(40, 100, 300, 250);
		this.panelAjout.setBackground(new Color (206,214, 224  ));
		this.panelAjout.setLayout(new GridLayout(7,2, 5, 5));
		this.panelAjout.add(new JLabel("Nom activité :")); 
		this.panelAjout.add(this.txtNomAct);
		this.panelAjout.add(new JLabel("Lieu de l'activité :")); 
		this.panelAjout.add(this.txtLieu);
		this.panelAjout.add(new JLabel("Budget de l'activité :")); 
		this.panelAjout.add(this.txtBudget);
		this.panelAjout.add(new JLabel("Description de l'activité :")); 
		this.panelAjout.add(this.txtDescription);
		this.panelAjout.add(new JLabel("Prix de l'activité :")); 
		this.panelAjout.add(this.txtPrix);
		this.panelAjout.add(new JLabel("Nb de personnes de l'activité :")); 
		this.panelAjout.add(this.txtNbPersonnes);
		this.panelAjout.add(this.btAnnuler); 
		this.panelAjout.add(this.btEnregistrer);
		this.add(this.panelAjout);
		
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);

		this.btFiltrer.setBounds(Main.WIDTH /2 - 80, 20, 100, 20);
		this.add(btFiltrer);
		this.btFiltrer.addActionListener(this);
		this.txtFiltrer.setBounds(Main.WIDTH / 2 + 40, 20 , 100, 20);
		this.add(txtFiltrer);
		
	
		remplirPanelLister("");
		this.uneTable.addMouseListener(this);
		
		this.setVisible(true);
		
		initBoutons();
	}

	private void initBoutons() {
		this.btAnnuler.setBackground(new Color(52, 58, 64));
		this.btAnnuler.setForeground(new Color(255, 255, 255));
		this.btAnnuler.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
	
		this.btEnregistrer.setBackground(new Color(52, 58, 64));
		this.btEnregistrer.setForeground(new Color(255, 255, 255));
		this.btEnregistrer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
	
		this.btRetour.setBackground(new Color(31, 61, 128));
		this.btRetour.setForeground(new Color(255, 255, 255));
		this.btRetour.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		
		this.btFiltrer.setBackground(new Color(52, 58, 64));
		this.btFiltrer.setForeground(new Color(255, 255, 255));
		this.btFiltrer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
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
			
		}else if (e.getSource()  == this.btEnregistrer && e.getActionCommand().equals("Modifier")) 
		{
			this.updateActivite();  
		}else if (e.getSource() == this.btFiltrer)
		{
			this.remplirPanelLister(this.txtFiltrer.getText());
		}
	}
	
	public void updateActivite() {
		String nomAct = this.txtNomAct.getText(); 
		String lieu = this.txtLieu.getText(); 
		String description = this.txtDescription.getText(); 
		int nbDePersonnes = 0; 
		float budget = 0, prix = 0;
		try {
			nbDePersonnes = Integer.parseInt(this.txtNbPersonnes.getText());
			budget = Float.parseFloat(this.txtBudget.getText());
			prix = Float.parseFloat(this.txtPrix.getText());
		}
		catch (NumberFormatException exp) {
			JOptionPane.showMessageDialog(this,"Attention au format du nombre d'heures  !");
			nbDePersonnes = -1 ;
		}
		if (nbDePersonnes >=0 ) {
			int numLigne = uneTable.getSelectedRow(); 
			int idActivite = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString ());
			Activite uneActivite = new Activite(idActivite,nomAct, lieu, budget, description, prix, nbDePersonnes);
			//update dans la base de donnÃ©es 
			Main.updateActivite(uneActivite);
			
			//modifiaction dans l'affichage tableau 
			Object ligne[] = {uneActivite.getIdActivite(), nomAct, lieu, budget, description, prix, nbDePersonnes+""};
			this.unTableau.updateLigne(numLigne, ligne);
			
			JOptionPane.showMessageDialog(this,"Modification réussie !");
			this.viderLesChamps();
		} else {
			this.txtNbPersonnes.setBackground(Color.red);
		}
		
	}
	
	
	
	
	public void insertActivite() {
		String nom = this.txtNomAct.getText();
		String lieu = this.txtLieu.getText();
		String description = this.txtDescription.getText();

		int nbPersonnes;
		float budget, prix ;
		
		try {
			budget = Float.parseFloat(this.txtBudget.getText());
			prix = Float.parseFloat(this.txtPrix.getText());
			nbPersonnes = Integer.parseInt(this.txtNbPersonnes.getText());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this,"Attention au format des nombres !");
			budget = -1; 
			prix = -1; 
			nbPersonnes = -1;
		}
	
		if(nbPersonnes >= 1) {
			Activite uneActivite = new Activite(nom, lieu, budget, description, 
				prix, nbPersonnes	);
			Main.insertActivite(uneActivite);
			JOptionPane.showMessageDialog(this,"Insertion réussie !");
			this.viderLesChamps();
		} else {
			this.txtNbPersonnes.setBackground(Color.red);
			JOptionPane.showMessageDialog(this,"Erreur d'insertion vérifier les champs !");
		}
	}
	
	
	public void initPanelLister() {
		//construire le panel Lister 
		this.panelLister.setBackground(new Color (206,214, 224  ));
		this.panelLister.setLayout(null);

		this.panelLister.setBounds(350, 80, 730, 300);
		//this.panelLister.setBorder(new LineBorder(Color.blue));
		
		this.uneScroll.setBounds(010, 10, 710, 280);
		this.panelLister.add(this.uneScroll);
		
		
		this.add(this.panelLister); 
	}
	
	public void remplirPanelLister(String mot) {
		this.panelLister.removeAll();
		String entetes [] = {"IdActivite", "Nom", "Lieu", "Budget", "Description", "Prix", "Nb de Personnes","Opérations"};
		Object donnees [][] = this.getDonnees(mot) ;			
		this.unTableau = new Tableau (donnees, entetes); 
		this.uneTable = new JTable(this.unTableau); 
		this.uneScroll = new JScrollPane(this.uneTable); 
		
		DefaultTableModel model = new DefaultTableModel(donnees, entetes);
		this.uneTable.setModel(model);
		this.uneTable.getColumn("Opérations").setCellRenderer(new BoutonJTable());
		this.uneTable.getColumn("Opérations").setCellEditor(new ButtonEditor(new JCheckBox()));
		this.btDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int ligne = uneTable.getSelectedRow();
				System.out.println(ligne);
				int idActivite = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString()); 
				int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette activité ?", "Suppression", JOptionPane.YES_NO_OPTION); 
				if (retour == 0) {
					//suppression dans la base 
					Main.deleteActivite(idActivite);
					//suppression dans la table d'affichage 
					unTableau.deleteLigne(ligne);
					JOptionPane.showMessageDialog(null, "Suppression réussie");
				}
			}
		});
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
		ArrayList<Activite> lesActivites = Main.selectAllActivites(mot); 
		//transofrmation des pilotes en matrice de donnÃ©es 
		Object donnees [][] = new Object [lesActivites.size()][8];
		int i = 0 ; 
		for (Activite uneActivite : lesActivites) {
			donnees[i][0] = uneActivite.getIdActivite()+""; 
			donnees[i][1] = uneActivite.getNom(); 
			donnees[i][2] = uneActivite.getLieu(); 
			donnees[i][3] = uneActivite.getBudget(); 
			donnees[i][4] = uneActivite.getDescription(); 
			donnees[i][5] = uneActivite.getPrix(); 
			donnees[i][6] = uneActivite.getNb_personnes() ; 

			i++; 
		}
				
		return donnees;
	}

	
	public void viderLesChamps() {
		this.txtNomAct.setText("");
		this.txtLieu.setText("");
		this.txtBudget.setText("");
		this.txtDescription.setText("");
		this.txtPrix.setText("");
		this.txtNbPersonnes.setText("");
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() >=2) {

		}else if (e.getClickCount() ==1) {
			int ligne = uneTable.getSelectedRow();
			txtNomAct.setText(unTableau.getValueAt(ligne, 1).toString());
			txtLieu.setText(unTableau.getValueAt(ligne, 2).toString());
			txtBudget.setText(unTableau.getValueAt(ligne, 3).toString());
			txtDescription.setText(unTableau.getValueAt(ligne, 4).toString());
			txtPrix.setText(unTableau.getValueAt(ligne, 5).toString());
			txtNbPersonnes.setText(unTableau.getValueAt(ligne, 6).toString());
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
