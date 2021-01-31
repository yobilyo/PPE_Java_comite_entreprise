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
	
	private JTextField txtEmailSpons = new JTextField();
	private JTextField txtUserSpons = new JTextField();
	private JTextField txtNomSoc = new JTextField();
	private JTextField txtBudgetSpons = new JTextField();
	private JTextField txtTelSpons = new JTextField();
	private JComboBox<String> cbxSponsor = new JComboBox<String>();
	
	private JTextField txtUsername = new JTextField(); 
	private JPasswordField txtMdp = new JPasswordField(); 
	private JTextField txtEmail = new JTextField(); 
	private JComboBox<String> cbxDroits = new JComboBox<String>();
	
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
		
		//construction du panel Ajout
		this.panelAjoutSalarie.setLayout(new GridLayout(4,2));
		this.panelAjoutSalarie.setBounds(40, 100, 300, 250);
		this.panelAjoutSalarie.setBackground(new Color (206,214, 224   ));
		panelAjoutSalarie.setLayout(null);
		JLabel label = new JLabel("Nom utilisateur :");
		label.setBounds(0, 0, 150, 25);
		this.panelAjoutSalarie.add(label); 
		txtUsername.setBounds(150, 0, 150, 25);
		this.panelAjoutSalarie.add(this.txtUsername);
		
		JLabel label_2 = new JLabel("Mot de passe :");
		label_2.setBounds(0, 25, 150, 25);
		this.panelAjoutSalarie.add(label_2); 
		txtMdp.setBounds(150, 25, 150, 25);
		this.panelAjoutSalarie.add(this.txtMdp);
		
		JLabel label_1 = new JLabel("Email :");
		label_1.setBounds(0, 50, 150, 25);
		this.panelAjoutSalarie.add(label_1); 
		txtEmail.setBounds(150, 50, 150, 25);
		this.panelAjoutSalarie.add(this.txtEmail);
		
		JLabel label_3 = new JLabel("Droits : ");
		label_3.setBounds(0, 75, 150, 25);
		this.panelAjoutSalarie.add(label_3);
		cbxDroits.setBounds(150, 75, 150, 25);
		this.panelAjoutSalarie.add(this.cbxDroits);
		
		//remplir les cbx 
		this.remplirCBXDroits();
	
		btEnregistrer.setBounds(50, 350, 140, 25);
		this.add(this.btEnregistrer);
		
		this.btAnnuler.setBounds(200, 350, 140, 25);
		this.add(this.btAnnuler); 

		getContentPane().add(this.panelAjoutSalarie);
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		
		//PANNEAU SPONSOR
		this.panelAjoutSponsor.setLayout(new GridLayout(6,2));
		this.panelAjoutSponsor.setBounds(40, 100, 300, 250);
		this.panelAjoutSponsor.setBackground(new Color (206,214, 224  ));
		this.panelAjoutSponsor.add(new JLabel("Username du sponsor :"));
		this.panelAjoutSponsor.add(txtUserSpons);
		this.panelAjoutSponsor.add(new JLabel("Email du sponsor :"));
		this.panelAjoutSponsor.add(txtEmailSpons);
		this.panelAjoutSponsor.add(new JLabel("Nom de société  :"));
		this.panelAjoutSponsor.add(txtNomSoc);
		this.panelAjoutSponsor.add(new JLabel("Budget du sponsor :"));
		this.panelAjoutSponsor.add(txtBudgetSpons);
		this.panelAjoutSponsor.add(new JLabel("Tel du sponsor :" ));
		this.panelAjoutSponsor.add(txtTelSpons);
		this.panelAjoutSponsor.add(new JLabel("Droits du sponsor :"));
		this.panelAjoutSponsor.add(cbxSponsor);
		this.remplirCBXSponsor();
		this.panelAjoutSponsor.setVisible(false);
		
		this.add(panelAjoutSponsor);
		
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
		this.initPanelListerSalarie();
		this.remplirPanelListerSalarie("");
		
		this.setVisible(true);
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
	
	public void remplirCBXDroits()
	{
		//this.cbxDroits.removeAllItems();
		this.cbxDroits.addItem("salarie");
		this.cbxDroits.addItem("admin");
		//this.cbxDroits.addItem("sponsor");
	}
	
	public void remplirCBXSponsor() {
		this.cbxSponsor.addItem("sponsor");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btRetour) {
			this.dispose();
			Main.rendreVisible(true);
		}else if (e.getSource() == this.btEnregistrer) {
			this.insertUtilisateur();
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
	
	public void insertUtilisateur() {
		String username = this.txtUsername.getText();
		String mdp = new String(this.txtMdp.getPassword());
		String email = this.txtEmail.getText();
		String droits = this.cbxDroits.getSelectedItem().toString();
	
		try {
			if(!txtUsername.getText().equals("") || !txtEmail.getText().equals("")) {
			Utilisateur unUtilisateur = new Utilisateur(username, mdp, email, droits);
			Main.insertUtilisateur(unUtilisateur);
			JOptionPane.showMessageDialog(this,"Insertion réussie !");
			this.viderLesChamps();
			txtUsername.setBackground(Color.WHITE);
			txtEmail.setBackground(Color.WHITE);
			}else {
				txtUsername.setBackground(Color.RED);
				txtEmail.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this,"Erreur d'insertion vérifier les champs !");
			}
		} catch(Exception e) {
			System.out.println("Aie " + e.getStackTrace());	
		}
	}
	
	/*	POUR AFFICHER DANS LE TABLEAU PLUS TARD
	//recuperation de l'id a travers un select where 
	unPilote = Main.selectWherePilote(email, bip);
	
	//insertion dans l'affichage tableau 
	Object ligne[] = {unPilote.getIdpilote(), nom, prenom, email, bip, nationalite, nbHeuresVols+""};
	this.unTableau.insertLigne(ligne);
	 */
	public void viderLesChamps() {
		this.txtUsername.setText("");
		this.txtMdp.setText("");
		this.txtEmail.setText("");
		this.cbxDroits.setSelectedIndex(0);
	}
}