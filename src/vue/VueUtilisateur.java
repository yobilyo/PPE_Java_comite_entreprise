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
import controleur.Salarie;
import controleur.Sponsor;
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
	
	// nous permet de savoir si on est sur le bouton salarie ou le bouton sponsor
	private boolean isSalarie;
	
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
		this.add(panelAjoutSponsor);
		
		// rendre visible les panels pour mieux comprendre
		this.panelAjoutSponsor.setBorder(LineBorder.createBlackLineBorder());
		this.panelAjoutSalarie.setBorder(LineBorder.createBlackLineBorder());
		
		this.btEnregistrer.setBounds(50, 350, 140, 25);
		this.add(this.btEnregistrer);
		this.btAnnuler.setBounds(200, 350, 140, 25);
		this.add(this.btAnnuler); 
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);

		// boutons pour toggle salarie/sponsor
		this.btSalarie.setBounds(50 , 25, 140, 25);
		this.add(btSalarie);
		this.btSalarie.addActionListener(this);
		
		this.btSponsor.setBounds(200 , 25, 140, 25);
		this.add(btSponsor);
		this.btSponsor.addActionListener(this);
		
		// Construire le panel Lister Salarie, puis le remplir avec une Scroll
		//this.initPanelListerSalarie();
		//this.remplirPanelListerSalarie("");
		
		//par défaut on commence sur le panel salarie au début
		this.isSalarie = true;
		
		this.setVisible(true);
	}
	
	public void remplirPanelAjoutSalarie() {
		this.panelAjoutSalarie.setLayout(new GridLayout(11,2));
		this.panelAjoutSalarie.setBounds(40, 100, 300, 250);
		this.panelAjoutSalarie.setBackground(new Color (206,214, 224   ));
		
		this.panelAjoutSalarie.add(new JLabel("Nom d'utilisateur :"));
		this.panelAjoutSalarie.add(this.txtUsernameSalarie);
		this.panelAjoutSalarie.add(new JLabel("Mot de passe :"));
		this.panelAjoutSalarie.add(this.txtMdpSalarie);
		this.panelAjoutSalarie.add(new JLabel("Email :"));
		this.panelAjoutSalarie.add(this.txtEmailSalarie);
		this.panelAjoutSalarie.add(new JLabel("Droits :"));
		this.panelAjoutSalarie.add(this.cbxDroitsSalarie);
		this.panelAjoutSalarie.add(new JLabel("Nom :"));
		this.panelAjoutSalarie.add(this.txtNomSalarie);
		this.panelAjoutSalarie.add(new JLabel("Prenom :"));
		this.panelAjoutSalarie.add(this.txtPrenomSalarie);
		this.panelAjoutSalarie.add(new JLabel("Téléphone :"));
		this.panelAjoutSalarie.add(this.txtTelSalarie);
		this.panelAjoutSalarie.add(new JLabel("Adresse :"));
		this.panelAjoutSalarie.add(this.txtAdresseSalarie);
		this.panelAjoutSalarie.add(new JLabel("Quotient familial :"));
		this.panelAjoutSalarie.add(this.cbxQuotientFamSalarie);
		this.panelAjoutSalarie.add(new JLabel("Service :"));
		this.panelAjoutSalarie.add(this.cbxServiceSalarie);
		this.panelAjoutSalarie.add(new JLabel("Sexe :"));
		this.panelAjoutSalarie.add(this.cbxSexeSalarie);
		
		//remplir les cbx Salarie
		this.remplircbxDroitsSalarie();
		this.remplircbxQuotientFamSalarie();
		this.remplircbxServiceSalarie();
		this.remplircbxSexeSalarie();
		
		/*JLabel label = new JLabel("Nom utilisateur :");
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
		this.panelAjoutSalarie.add(this.cbxDroitsSalarie);*/
		
	}
	
	public void remplirPanelAjoutSponsor() {
		this.panelAjoutSponsor.setLayout(new GridLayout(9,2));
		this.panelAjoutSponsor.setBounds(40, 100, 300, 250);
		this.panelAjoutSponsor.setBackground(new Color (206,214, 224  ));
		
		this.panelAjoutSponsor.add(new JLabel("Nom d'utilisateur :"));
		this.panelAjoutSponsor.add(txtUserSpons);
		this.panelAjoutSponsor.add(new JLabel("Mot de passe :"));
		this.panelAjoutSponsor.add(txtMdpSpons);
		this.panelAjoutSponsor.add(new JLabel("Email :"));
		this.panelAjoutSponsor.add(txtEmailSpons);
		this.panelAjoutSponsor.add(new JLabel("Droits :"));
		this.panelAjoutSponsor.add(cbxDroitsSpons);
		this.panelAjoutSponsor.add(new JLabel("Nom de société :"));
		this.panelAjoutSponsor.add(txtSocieteSpons);
		this.panelAjoutSponsor.add(new JLabel("URL de l'image :"));
		this.panelAjoutSponsor.add(txtImgurlSpons);
		this.panelAjoutSponsor.add(new JLabel("Budget :"));
		this.panelAjoutSponsor.add(txtBudgetSpons);
		this.panelAjoutSponsor.add(new JLabel("Téléphone :" ));
		this.panelAjoutSponsor.add(txtTelSpons);
		this.panelAjoutSponsor.add(new JLabel("Adresse web :" ));
		this.panelAjoutSponsor.add(txtLienSpons);

		this.remplircbxDroitsSpons();
		this.panelAjoutSponsor.setVisible(false);
	}
	
	public void remplircbxDroitsSalarie()
	{
		//this.cbxDroitsSalarie.removeAllItems();
		this.cbxDroitsSalarie.addItem("salarie");
		this.cbxDroitsSalarie.addItem("admin");
	}
	
	public void remplircbxQuotientFamSalarie()
	{
		this.cbxQuotientFamSalarie.addItem("1");
		this.cbxQuotientFamSalarie.addItem("2");
		this.cbxQuotientFamSalarie.addItem("3");
		this.cbxQuotientFamSalarie.addItem("4");
		this.cbxQuotientFamSalarie.addItem("5");
	}
	
	public void remplircbxServiceSalarie()
	{
		this.cbxServiceSalarie.addItem("comptabilite");
		this.cbxServiceSalarie.addItem("developpeur");
		this.cbxServiceSalarie.addItem("commercial");
		this.cbxServiceSalarie.addItem("ressources_humaines");
	}
	
	public void remplircbxSexeSalarie()
	{
		this.cbxSexeSalarie.addItem("homme");
		this.cbxSexeSalarie.addItem("femme");
	}
	
	public void remplircbxDroitsSpons() {
		this.cbxDroitsSpons.addItem("sponsor");
	}
	
	public void insertUtilisateurSalarie() {
		// utilisateur
		String username = this.txtUsernameSalarie.getText();
		String mdp = new String(this.txtMdpSalarie.getPassword());
		String email = this.txtEmailSalarie.getText();
		String droits = this.cbxDroitsSalarie.getSelectedItem().toString();
		// salarie
		String nom = this.txtNomSalarie.getText();
		String prenom = this.txtPrenomSalarie.getText();
		String tel = this.txtTelSalarie.getText();
		String adresse = this.txtAdresseSalarie.getText();
		String quotient_fam = this.cbxQuotientFamSalarie.getSelectedItem().toString();
		String service = this.cbxServiceSalarie.getSelectedItem().toString();
		String sexe = this.cbxSexeSalarie.getSelectedItem().toString();
	
		try {
			if(!txtUsernameSalarie.getText().equals("") || !txtEmailSalarie.getText().equals("")) {
				Salarie unSalarie = new Salarie(username, mdp, email, droits, nom, prenom, tel, 
						adresse, quotient_fam, service, sexe);
				Main.insertUtilisateurSalarie(unSalarie);
				JOptionPane.showMessageDialog(this,"Insertion réussie !");
				this.viderLesChampsSalarie();
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
	
	public void insertUtilisateurSpons() {
		// utilisateur
		String username = this.txtUserSpons.getText();
		String mdp = new String(this.txtMdpSpons.getPassword());
		String email = this.txtEmailSpons.getText();
		String droits = this.cbxDroitsSpons.getSelectedItem().toString();
		// sponsor
		String societe = this.txtSocieteSpons.getText();
		String image_url = this.txtImgurlSpons.getText();
		double budget = 0;
		try {
			budget = Double.parseDouble(this.txtBudgetSpons.getText());
		} catch (NumberFormatException exp) {
			JOptionPane.showMessageDialog(this, "Erreur de saisie de montant");
		}
		String tel = this.txtTelSpons.getText();
		String lien = this.txtLienSpons.getText();
	
		try {
			if(!txtUserSpons.getText().equals("") || !txtEmailSpons.getText().equals("")) {
				Sponsor unSponsor = new Sponsor(username, mdp, email, droits, societe, image_url, budget, 
						tel, lien);
				Main.insertUtilisateurSponsor(unSponsor);
				JOptionPane.showMessageDialog(this,"Insertion réussie !");
				this.viderLesChampsSpons();
				txtUserSpons.setBackground(Color.WHITE);
				txtEmailSpons.setBackground(Color.WHITE);
			}else {
				txtUserSpons.setBackground(Color.RED);
				txtEmailSpons.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this,"Erreur d'insertion vérifier les champs !");
			}
		} catch(Exception e) {
			System.out.println("Aie " + e.getStackTrace());	
		}
	}

	public void viderLesChampsSalarie() {
		// après insertion réussie, on vide les champs
		// utilisateur
		this.txtUsernameSalarie.setText("");
		this.txtMdpSalarie.setText("");
		this.txtEmailSalarie.setText("");
		this.cbxDroitsSalarie.setSelectedIndex(0);
		// salarie
		this.txtNomSalarie.setText("");
		this.txtPrenomSalarie.setText("");
		this.txtTelSalarie.setText("");
		this.txtAdresseSalarie.setText("");
		this.cbxQuotientFamSalarie.setSelectedIndex(0);
		this.cbxServiceSalarie.setSelectedIndex(0);
		this.cbxSexeSalarie.setSelectedIndex(0);
	}
	
	public void viderLesChampsSpons() {
		// après insertion réussie, on vide les champs
		// utilisateur
		this.txtUserSpons.setText("");
		this.txtMdpSpons.setText("");
		this.txtEmailSpons.setText("");
		this.cbxDroitsSpons.setSelectedIndex(0);
		// sponsor
		this.txtSocieteSpons.setText("");
		this.txtImgurlSpons.setText("");
		this.txtBudgetSpons.setText("");
		this.txtTelSpons.setText("");
		this.txtLienSpons.setText("");
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
			//System.out.println(this.isSalarie);
			if (this.isSalarie == true) {
				//System.out.println("sa");
				this.insertUtilisateurSalarie();
			}
			else {
				//System.out.println("sp");
				this.insertUtilisateurSpons();
			}
		}else if (e.getSource() == this.btAnnuler) {
			//System.out.println(this.isSalarie);
			if (this.isSalarie == true) {
				this.viderLesChampsSalarie();
			}
			else {
				this.viderLesChampsSpons();
			}
		}else if(e.getSource() == this.btSponsor) {
			this.isSalarie = false;
			this.panelAjoutSalarie.setVisible(false);
			this.panelAjoutSponsor.setVisible(true);
		}else if(e.getSource() == this.btSalarie) {
			this.isSalarie = true;
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