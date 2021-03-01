package vue;

import java.awt.Font;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Don;
import controleur.Main;
import controleur.Tableau;
import controleur.Utilisateur;

public class VueDon extends JFrame implements ActionListener, MouseListener{
	
	private JPanel panelAjout = new JPanel();
	private JButton btRetour = new JButton("Retour");
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JButton btFiltrer = new JButton("Filtrer :");
	private JTextField txtFiltrer = new JTextField();
	
	//Créer date et l'afficher
	private JLabel labDate = new JLabel();
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	private Date dateDon = new Date();
	private JTextField txtDateDon = new JTextField(sFormat.format(dateDon).toString());
	
	private JTextField txtMontant = new JTextField(); 
	private JTextField txtAppreciation = new JTextField(); 
	//private JTextField txtidutilisateur = new JTextField(); 
	//private JTextField txtid_tresorerie = new JTextField();
	private JComboBox<String> cbxUtilisateur = new JComboBox<String>();
	//private JTextField txtTresorerie = new JTextField("1"); 
	
	//Construction de la partie Tableau
	private JPanel panelLister = new JPanel(); 
	private JTable uneTable ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau ;

//	Barre de filtrage
//	private JTextField txtMot = new JTextField ();
//	private JButton btFiltrer = new JButton("filtrer"); 
	
	public VueDon() {
		this.setBounds(100, 100, Main.getWidth(), Main.getHeight());
		this.setTitle("Gestion des dons");
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
		this.panelAjout.setLayout(new GridLayout(7,2));
		this.panelAjout.add(new JLabel("Date don :")); 
		this.panelAjout.add(this.txtDateDon);
		this.panelAjout.add(new JLabel("Montant :")); 
		this.panelAjout.add(this.txtMontant);
		this.panelAjout.add(new JLabel("Appreciation :")); 
		this.panelAjout.add(this.txtAppreciation);
		this.panelAjout.add(new JLabel("Utilisateur :"));
		this.panelAjout.add(this.cbxUtilisateur);
		//this.panelAjout.add(new JLabel("Trésorerie : "));
		//this.panelAjout.add(this.txtTresorerie);
		this.panelAjout.add(this.btAnnuler); 
		this.panelAjout.add(this.btEnregistrer);
		this.add(this.panelAjout);
		
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);

		this.btFiltrer.setBounds(Main.getWidth() /2 - 200, 20, 100, 20);
		this.add(btFiltrer);
		this.btFiltrer.addActionListener(this);
		this.txtFiltrer.setBounds(Main.getWidth() / 2 - 80, 20 , 100, 20);
		this.add(txtFiltrer);
		
		remplirCBXUtilisateurs();
		remplirPanelLister("");
		this.uneTable.addMouseListener(this);
		
		this.setVisible(true);
		
		initBoutons();
	}
	
	public void remplirCBXUtilisateurs() {
		ArrayList<Utilisateur> lesUtilisateurs = Main.selectAllUtilisateurs("");
		this.cbxUtilisateur.removeAllItems();
		for (Utilisateur unUtilisateur : lesUtilisateurs) {
			// uniquement les sponsors peuvent faire des dons, pas les salariés ni les admins
			if (unUtilisateur.getDroits().equals("sponsor")) {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btRetour) {
			this.dispose();
			Main.rendreVisible(true);
		}else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Enregistrer")) {
			this.insertDon();
		}else if (e.getSource() == this.btAnnuler) {
			// si on ne modifie pas on peut réactiver le cbxUtilisateur pour les insertions
			this.cbxUtilisateur.setEnabled(true);
			this.viderLesChamps();
			this.btEnregistrer.setText("Enregistrer");
		}else if (e.getSource()  == this.btEnregistrer && e.getActionCommand().equals("Modifier")) 
		{
			this.updateDon();  
		}else if (e.getSource() == this.btFiltrer)
		{
			this.remplirPanelLister(this.txtFiltrer.getText());
		}
	}
	
	public void updateDon() {
		String datedon = this.txtDateDon.getText(); 
		float montant = 0;
		String appreciation = this.txtAppreciation.getText();
		// récupération de l'ancien idUtilisateur
		int ligne = uneTable.getSelectedRow();
		int idUtilisateurOld = Integer.parseInt(unTableau.getValueAt(ligne, 4).toString()); 
		int id_tresorerie = 1;
		
		try {
			montant = Float.parseFloat(this.txtMontant.getText());
		}
		catch (NumberFormatException exp) {
			JOptionPane.showMessageDialog(this,"Attention au format du nombre d'heures  !");
			montant = -1 ;
		}
		if (montant >=0 ) {
			int numLigne = uneTable.getSelectedRow(); 
			int iddon = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString ());
			Don unDon = new Don(iddon , idUtilisateurOld, id_tresorerie, appreciation, montant, datedon, "", "");
			//update dans la base de donnÃ©es 
			Main.updateDon(unDon);
			
			//modifiaction dans l'affichage tableau 
			/*Object ligne[] = {unDon.getIddon(), datedon, montant, appreciation, idutilisateur, id_tresorerie+""};
			this.unTableau.updateLigne(numLigne, ligne);*/
			// TODO remplacer par la méthode de recréation d'un nouveau tableau
			
			JOptionPane.showMessageDialog(this,"Modification réussie !");
			this.viderLesChamps();
		} else {
			this.txtMontant.setBackground(Color.red);
		}
		
	}
	
	
	
	
	public void insertDon() {
		String datedon = this.txtDateDon.getText(); 
		float montant;
		String appreciation = this.txtAppreciation.getText(); 
		int idUtilisateur= 0;
		int id_tresorerie= 1;
		String chaineUti = this.cbxUtilisateur.getSelectedItem().toString();
		String tabUti [] = chaineUti.split(" - ");
		
		
		try {
			montant = Integer.parseInt(this.txtMontant.getText());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this,"Attention au format des nombres !");
			montant = -1; 
		}
		
		try {
			idUtilisateur = Integer.parseInt(tabUti[0]);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Impossible de parser l'idUtilisateur");

		}
		
	
		if(montant >= 1) {
			Don unDon = new Don(idUtilisateur, id_tresorerie, appreciation, montant, datedon, "", "");
			Main.insertDon(unDon);
			JOptionPane.showMessageDialog(this,"Insertion réussie !");
			this.viderLesChamps();
		} else {
			this.txtMontant.setBackground(Color.red);
			JOptionPane.showMessageDialog(this,"Erreur d'insertion vérifier les champs !");
		}
	}
	
	
	public void initPanelLister() {
		//construire le panel Lister 
		this.panelLister.setBackground(new Color (206,214, 224  ));
		this.panelLister.setLayout(null);

		this.panelLister.setBounds(365, 80, this.getWidth() - 400, this.getHeight() - 170);
		
		this.uneScroll.setBounds(20, 20, this.panelLister.getWidth() - 40, this.panelLister.getHeight() - 40);
		this.panelLister.add(this.uneScroll);
		
		
		this.add(this.panelLister); 
	}
	
	public void remplirPanelLister(String mot) {
		this.panelLister.removeAll();
		String entetes [] = {"ID Don", "Date don", "Montant", "Appreciation", "ID Utilisateur",
				"Nom d'utilisateur", "Société"};
		Object donnees [][] = this.getDonnees(mot) ;			
		this.unTableau = new Tableau (donnees, entetes); 
		this.uneTable = new JTable(this.unTableau); 
		
		// rendre les colonnes id + petites
		this.uneTable.getColumnModel().getColumn(0).setMaxWidth(50);
		this.uneTable.getColumnModel().getColumn(1).setMaxWidth(70);
		this.uneTable.getColumnModel().getColumn(2).setMaxWidth(70);
		this.uneTable.getColumnModel().getColumn(4).setMaxWidth(50);
		this.uneTable.getColumnModel().getColumn(5).setMaxWidth(95);
		this.uneTable.getColumnModel().getColumn(6).setMaxWidth(130);
		// la colonne appréciation doit être + large pour bien afficher le texte
		//https://stackoverflow.com/questions/953972/java-jtable-setting-column-width
		this.uneTable.getColumnModel().getColumn(1).setMinWidth(70);
		this.uneTable.getColumnModel().getColumn(3).setMinWidth(300);
		this.uneTable.getColumnModel().getColumn(5).setMinWidth(95);
		this.uneTable.getColumnModel().getColumn(6).setMinWidth(130);

		this.uneScroll = new JScrollPane(this.uneTable); 
		
		Main.styleTableau(this.uneTable);
		initPanelLister();

	}
	

	
	public Object [] [] getDonnees(String mot) {
		//recuperer les pilotes de la bdd 
		ArrayList<Don> lesDons = Main.selectAllDons(mot); 
		//transofrmation des pilotes en matrice de donnÃ©es 
		Object donnees [][] = new Object [lesDons.size()][7];
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

	public void viderLesChamps() {
		this.txtDateDon.setText("");
		this.txtMontant.setText("");
		this.txtAppreciation.setText("");
		//this.txtidutilisateur.setText("");
		this.cbxUtilisateur.setSelectedIndex(0);
		//this.txtid_tresorerie.setText("");
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() >=2) {
			int ligne = uneTable.getSelectedRow();
			System.out.println(ligne);
			int iddon = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString()); 
			int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce don ?", "Suppression", JOptionPane.YES_NO_OPTION); 
			if (retour == 0) {
				//suppression dans la base 
				Main.deleteDon(iddon);
				//suppression dans la table d'affichage 
				unTableau.deleteLigne(ligne);
				JOptionPane.showMessageDialog(null, "Suppression réussie");
			}
		}else if (e.getClickCount() ==1) {
			int ligne = uneTable.getSelectedRow();
			txtDateDon.setText(unTableau.getValueAt(ligne, 1).toString());
			txtMontant.setText(unTableau.getValueAt(ligne, 2).toString());
			txtAppreciation.setText(unTableau.getValueAt(ligne, 3).toString());
			// idutilisateur est une clé étrangère dans la table don, elle ne
			// peut pas et ne doit pas être modifiée
			cbxUtilisateur.setEnabled(false);
			// mais on affiche quand meme l'utilisateur
			String idUtilisateur = uneTable.getValueAt(ligne, 4).toString();
			String username = uneTable.getValueAt(ligne, 5).toString();
			String cbxTextUtilisateur = Main.genererCbxTextFromId(idUtilisateur, username);
			cbxUtilisateur.setSelectedItem(cbxTextUtilisateur);
			//txtidutilisateur.setText(unTableau.getValueAt(ligne, 4).toString());
			//txtid_tresorerie.setText(unTableau.getValueAt(ligne, 5).toString());
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

