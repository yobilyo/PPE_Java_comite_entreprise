package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controleur.Main;
import controleur.Tableau;
import controleur.Utilisateur;

public class VueUtilisateur extends JFrame implements ActionListener{
	
	private final static int WIDTH = 900;
	private final static int HEIGHT = 500;

	
	private static VueConnexion uneVueConnexion; 
	
	private JPanel panelAjoutSalarie = new JPanel();
	private JButton btRetour = new JButton("Retour");
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JButton btSalarie = new JButton("Salarié");
	private JButton btSponsor = new JButton("Sponsor");
	private JPanel panelAjoutSponsor = new JPanel();
	
	//utilisateurSponsor - 9 elements à remplir
	//utilisateur
	private JTextField txtUserSpons = new JTextField();
	private JTextField txtEmailSpons = new JTextField();
	private JPasswordField txtMdpSpons = new JPasswordField();
	private JComboBox<String> cbxDroitsSpons = new JComboBox<String>();
	//sponsor
	private JTextField txtSocieteSpons = new JTextField();
	private JTextField txtImgurlSpons = new JTextField();
	private JTextField txtBudgetSpons = new JTextField();
	private JTextField txtTelSpons = new JTextField();
	private JTextField txtLienSpons = new JTextField();
	
	//utilisateurSalarie - 11 elements à remplir
	//utilisateur
	private JTextField txtUsernameSalarie = new JTextField(); 
	private JTextField txtEmailSalarie = new JTextField(); 
	private JPasswordField txtMdpSalarie = new JPasswordField(); 
	private JComboBox<String> cbxDroitsSalarie = new JComboBox<String>();
	//salarie
	private JTextField txtNomSalarie = new JTextField();
	private JTextField txtPrenomSalarie = new JTextField();
	private JComboBox<String> cbxSexeSalarie = new JComboBox<String>();
	private JTextField txtTelSalarie = new JTextField();
	private JTextField txtAdresseSalarie = new JTextField();
	private JComboBox<String> cbxQuotientFamSalarie = new JComboBox<String>();
	private JComboBox<String> cbxServiceSalarie = new JComboBox<String>();
	
	//Construction de la partie Tableau
	private JPanel panelListerSalarie = new JPanel();
	private JTable uneTableSalarie;
	private JScrollPane uneScrollSalarie;
	private Tableau unTableauSalarie;
	
	public VueUtilisateur() {
		this.setBounds(100, 100, WIDTH, HEIGHT);
		this.setTitle("Gestion des utilisateurs");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		this.getContentPane().setBackground(new Color (206,214, 224  ));
		
		//installer le bouton retour 
		this.btRetour.setBounds(WIDTH -170, HEIGHT -80, 140, 30);
		getContentPane().add(this.btRetour); 
		this.btRetour.addActionListener(this);
		
		//construction du panel Ajout Salarie
		this.remplirPanelAjoutSalarie();
		getContentPane().add(this.panelAjoutSalarie);
		
		//construction du panel Ajout Sponsor
		this.remplirPanelAjoutSponsor();
		
		this.btEnregistrer.setBounds(50, 350, 140, 25);
		this.add(this.btEnregistrer);
		this.btAnnuler.setBounds(200, 350, 140, 25);
		this.add(this.btAnnuler); 
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		
		//PANNEAU SPONSOR
		this.remplirPanelAjoutSponsor();
		this.add(panelAjoutSponsor);
		
		// boutons pour toggle salarie/sponsor
		this.btSalarie.setBounds(50 , 25, 140, 25);
		this.add(btSalarie);
		this.btSalarie.addActionListener(this);
		
		this.btSponsor.setBounds(200 , 25, 140, 25);
		this.add(btSponsor);
		this.btSponsor.addActionListener(this);

		// rendre visible les panels pour mieux comprendre
		this.panelAjoutSponsor.setBorder(LineBorder.createBlackLineBorder());
		this.panelAjoutSalarie.setBorder(LineBorder.createBlackLineBorder());
		
		// Construire le panel Lister, puis le remplir avec une Scroll
		//this.initPanelListerSalarie();
		//this.remplirPanelListerSalarie("");
		
		this.setVisible(true);
	}
	
	public void remplirPanelAjoutSalarie() {
		this.panelAjoutSalarie.setLayout(new GridLayout(11,2));
		this.panelAjoutSalarie.setBounds(40, 100, 300, 250);
		this.panelAjoutSalarie.setBackground(new Color (206,214, 224   ));
		JLabel label = new JLabel("Nom utilisateur :");
		label.setBounds(0, 0, 150, 25);
		this.panelAjoutSalarie.add(label); 
		txtUsernameSalarie.setBounds(150, 0, 150, 25);
		this.panelAjoutSalarie.add(this.txtUsernameSalarie);
		
		JLabel label_2 = new JLabel("Mot de passe :");
		label_2.setBounds(0, 25, 150, 25);
		this.panelAjoutSalarie.add(label_2); 
		txtMdpSalarie.setBounds(150, 25, 150, 25);
		this.panelAjoutSalarie.add(this.txtMdpSalarie);
		
		JLabel label_1 = new JLabel("Email :");
		label_1.setBounds(0, 50, 150, 25);
		this.panelAjoutSalarie.add(label_1); 
		txtEmailSalarie.setBounds(150, 50, 150, 25);
		this.panelAjoutSalarie.add(this.txtEmailSalarie);
		
		JLabel label_3 = new JLabel("Droits : ");
		label_3.setBounds(0, 75, 150, 25);
		this.panelAjoutSalarie.add(label_3);
		cbxDroitsSalarie.setBounds(150, 75, 150, 25);
		this.panelAjoutSalarie.add(this.cbxDroitsSalarie);
		
		//remplir les cbx 
		this.remplircbxDroitsSalarie();
	}
	
	public void remplirPanelAjoutSponsor() {
		this.panelAjoutSponsor.setLayout(new GridLayout(6,2));
		this.panelAjoutSponsor.setBounds(40, 100, 300, 250);
		this.panelAjoutSponsor.setBackground(new Color (206,214, 224  ));
		this.panelAjoutSponsor.add(new JLabel("Username du sponsor :"));
		this.panelAjoutSponsor.add(txtUserSpons);
		this.panelAjoutSponsor.add(new JLabel("Email du sponsor :"));
		this.panelAjoutSponsor.add(txtEmailSpons);
		this.panelAjoutSponsor.add(new JLabel("Nom de société  :"));
		this.panelAjoutSponsor.add(txtSocieteSpons);
		this.panelAjoutSponsor.add(new JLabel("Budget du sponsor :"));
		this.panelAjoutSponsor.add(txtBudgetSpons);
		this.panelAjoutSponsor.add(new JLabel("Tel du sponsor :" ));
		this.panelAjoutSponsor.add(txtTelSpons);
		this.panelAjoutSponsor.add(new JLabel("Droits du sponsor :"));
		this.panelAjoutSponsor.add(cbxDroitsSpons);
		this.remplircbxDroitsSpons();
		this.panelAjoutSponsor.setVisible(false);
	}
	
	public void remplircbxDroitsSalarie()
	{
		//this.cbxDroitsSalarie.removeAllItems();
		this.cbxDroitsSalarie.addItem("salarie");
		this.cbxDroitsSalarie.addItem("admin");
	}
	
	public void remplircbxDroitsSpons() {
		this.cbxDroitsSpons.addItem("sponsor");
	}
	
	public void insertUtilisateurSalarie() {
		//TODO
		// utilisateur
		String username = this.txtUsernameSalarie.getText();
		String mdp = new String(this.txtMdpSalarie.getPassword());
		String email = this.txtEmailSalarie.getText();
		String droits = this.cbxDroitsSalarie.getSelectedItem().toString();
		// salarie
		String societe = this.txtSocieteSpons.getText();
		String image_url = this.txtImgurlSpons.getText();
		String budget = this.txt
	
		try {
			if(!txtUsernameSalarie.getText().equals("") || !txtEmailSalarie.getText().equals("")) {
			Utilisateur unUtilisateur = new Utilisateur(username, mdp, email, droits);
			Main.insertUtilisateurSalarie(unUtilisateur);
			JOptionPane.showMessageDialog(this,"Insertion réussie !");
			this.viderLesChamps();
			txtUsernameSalarie.setBackground(Color.WHITE);
			txtEmailSalarie.setBackground(Color.WHITE);
			}else {
				txtUsernameSalarie.setBackground(Color.RED);
				txtEmailSalarie.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this,"Erreur d'insertion vérifier les champs !");
			}
		} catch(Exception e) {
			System.out.println("Aie " + e.getStackTrace());	
		}
	}
	
	public void viderLesChamps() {
		this.txtUsernameSalarie.setText("");
		this.txtMdpSalarie.setText("");
		this.txtEmailSalarie.setText("");
		this.cbxDroitsSalarie.setSelectedIndex(0);
	}

	public void initPanelListerSalarie() {
		//construire le panel Lister Salarie
		this.panelListerSalarie.setBackground(new Color (206,214, 224  ));
		this.panelListerSalarie.setLayout(null);
		this.panelListerSalarie.setBounds(350, 80, 530, 300);

		this.add(this.panelListerSalarie);
	}
	
	public void remplirPanelListerSalarie(String mot) {
		//remplir le panel Lister Salarie
		this.panelListerSalarie.removeAll();
		String entetes [] = {"IdUtilisateur", "Nom d'utilisateur", "Mot de passe", "Email","Droits"};
		Object donnees [][] = this.getDonnees (mot) ;
		
		this.unTableauSalarie = new Tableau (donnees, entetes); 
		this.uneTableSalarie = new JTable(this.unTableauSalarie); 
		
		this.uneScrollSalarie = new JScrollPane(this.uneTableSalarie); 
		//this.panelListerSalarie.setBounds(350, 80, 530, 300);
		this.uneScrollSalarie.setBounds(20, 20, 510, 280);
		this.panelListerSalarie.add(this.uneScrollSalarie);
	}
	
	public Object [] [] getDonnees(String mot) {
		//recuperer les Avions de la bdd 
		ArrayList<Utilisateur> lesUtilisateurs = Main.selectAllUtilisateurs(mot); 
		//transofrmation des Avions en matrice de données 
		Object donnees [][] = new Object [lesUtilisateurs.size()][5];
		int i = 0 ; 
		for (Utilisateur unUtilisateur : lesUtilisateurs) {
			donnees[i][0] = unUtilisateur.getIdUtilisateur()+""; 
			donnees[i][1] = unUtilisateur.getUsername();
			donnees[i][2] = unUtilisateur.getPassword();
			donnees[i][3] = unUtilisateur.getEmail();
			donnees[i][4] = unUtilisateur.getDroits();
			i++;
		}
				
		return donnees;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btRetour) {
			this.dispose();
			Main.rendreVisible(true);
		}else if (e.getSource() == this.btEnregistrer) {
			this.insertUtilisateurSalarie();
		}else if (e.getSource() == this.btAnnuler) {
			this.viderLesChamps();
		}else if(e.getSource() == this.btSponsor) {
			this.panelAjoutSalarie.setVisible(false);
			this.panelAjoutSponsor.setVisible(true);
		}else if(e.getSource() == this.btSalarie) {
			this.panelAjoutSalarie.setVisible(true);
			this.panelAjoutSponsor.setVisible(false);
		}
	}
	
	/*	POUR AFFICHER DANS LE TABLEAU PLUS TARD
	//recuperation de l'id a travers un select where 
	unPilote = Main.selectWherePilote(email, bip);
	
	//insertion dans l'affichage tableau 
	Object ligne[] = {unPilote.getIdpilote(), nom, prenom, email, bip, nationalite, nbHeuresVols+""};
	this.unTableau.insertLigne(ligne);
	 */

}