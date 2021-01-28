package vue;

import java.awt.Color;
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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controleur.Main;
import controleur.Pilote;
import controleur.Utilisateur;

public class VueUtilisateur extends JFrame implements ActionListener{
	
	private final static int WIDTH = 900;
	private final static int HEIGHT = 500;

	
	private static VueConnexion uneVueConnexion; 
	
	private JPanel panelAjout = new JPanel();
	private JButton btRetour = new JButton("Retour au menu");
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JTextField txtUsername = new JTextField(); 
	private JPasswordField txtMdp = new JPasswordField(); 
	private JTextField txtEmail = new JTextField(); 
	private JComboBox<String> cbxDroits = new JComboBox<String>();
	
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
		this.panelAjout.setBounds(40, 100, 300, 250);
		this.panelAjout.setBackground(new Color (206,214, 224   ));
		panelAjout.setLayout(null);
		JLabel label = new JLabel("Nom utilisateur :");
		label.setBounds(0, 0, 150, 25);
		this.panelAjout.add(label); 
		txtUsername.setBounds(150, 0, 150, 25);
		this.panelAjout.add(this.txtUsername);
		
		JLabel label_2 = new JLabel("Mot de passe :");
		label_2.setBounds(0, 25, 150, 25);
		this.panelAjout.add(label_2); 
		txtMdp.setBounds(150, 25, 150, 25);
		this.panelAjout.add(this.txtMdp);
		
		JLabel label_1 = new JLabel("Email :");
		label_1.setBounds(0, 50, 150, 25);
		this.panelAjout.add(label_1); 
		txtEmail.setBounds(150, 50, 150, 25);
		this.panelAjout.add(this.txtEmail);
		
		JLabel label_3 = new JLabel("Droits: ");
		label_3.setBounds(0, 75, 150, 25);
		this.panelAjout.add(label_3);
		cbxDroits.setBounds(150, 75, 150, 25);
		this.panelAjout.add(this.cbxDroits);
		//remplir les cbx 
		this.remplirCBXDroits();
	
		this.panelAjout.add(this.btAnnuler); 
		btEnregistrer.setBounds(0, 225, 150, 25);
		this.panelAjout.add(this.btEnregistrer);
		getContentPane().add(this.panelAjout);
		
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		
		this.setVisible(true);
	}

	public void remplirCBXDroits()
	{
		//this.cbxDroits.removeAllItems();
		this.cbxDroits.addItem("salarie");
		this.cbxDroits.addItem("admin");
		this.cbxDroits.addItem("sponsor");
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
		}
	}
	
	public void insertUtilisateur() {
		String username = this.txtUsername.getText();
		String mdp = new String(this.txtMdp.getPassword());
		String email = this.txtEmail.getText();
		String droits = this.cbxDroits.getSelectedItem().toString();
	
		try {
			Utilisateur unUtilisateur = new Utilisateur(username, mdp, email, droits);
			Main.insertUtilisateur(unUtilisateur);
			JOptionPane.showMessageDialog(this,"Insertion réussie !");
			this.viderLesChamps();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this,"Erreur d'insertion vérifier les champs !");
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