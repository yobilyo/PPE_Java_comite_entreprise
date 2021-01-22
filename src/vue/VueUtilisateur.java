package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controleur.Utilisateur;
import controleur.Main;

public class VueUtilisateur extends JFrame implements ActionListener{
	
	private final static int WIDTH = 900;
	private final static int HEIGHT = 500;

	
	private static VueConnexion uneVueConnexion; 
	
	private JPanel panelAjout = new JPanel();
	private JButton btRetour = new JButton("Retour au menu");
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JTextField txtNomUtil = new JTextField(); 
	private JTextField txtEmail = new JTextField(); 
	private JTextField txtMdp = new JTextField(); 
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom= new JTextField();
	private JTextField txtTel = new JTextField();
	private JTextField txtAdresse = new JTextField();
    private JRadioButton radioHomme = new JRadioButton("H");
    private JRadioButton radioFemme = new JRadioButton("F");
	
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
		txtNomUtil.setBounds(150, 0, 150, 25);
		this.panelAjout.add(this.txtNomUtil);
		JLabel label_1 = new JLabel("Email :");
		label_1.setBounds(0, 25, 150, 25);
		this.panelAjout.add(label_1); 
		txtEmail.setBounds(150, 25, 150, 25);
		this.panelAjout.add(this.txtEmail);
		JLabel label_2 = new JLabel("Mot de passe :");
		label_2.setBounds(0, 50, 150, 25);
		this.panelAjout.add(label_2); 
		txtMdp.setBounds(150, 50, 150, 25);
		this.panelAjout.add(this.txtMdp);
		JLabel label_3 = new JLabel("Nom :");
		label_3.setBounds(0, 75, 150, 25);
		this.panelAjout.add(label_3); 
		txtNom.setBounds(150, 75, 150, 25);
		this.panelAjout.add(this.txtNom);
		JLabel label_4 = new JLabel("Prenom :");
		label_4.setBounds(0, 100, 150, 25);
		this.panelAjout.add(label_4); 
		txtPrenom.setBounds(150, 100, 150, 25);
		this.panelAjout.add(this.txtPrenom);
		JLabel label_5 = new JLabel("Téléphone :");
		label_5.setBounds(0, 125, 150, 25);
		this.panelAjout.add(label_5); 
		txtTel.setBounds(150, 125, 150, 25);
		this.panelAjout.add(this.txtTel);
		JLabel label_6 = new JLabel("Adresse :");
		label_6.setBounds(0, 150, 150, 25);
		this.panelAjout.add(label_6); 
		txtAdresse.setBounds(150, 150, 150, 25);
		this.panelAjout.add(this.txtAdresse);
		
		
		
		JLabel label_7 = new JLabel("Sexe :");
		label_7.setBounds(0, 175, 150, 25);
		this.panelAjout.add(label_7);
		radioHomme.setBounds(150, 175, 76, 25);
		this.panelAjout.add(this.radioHomme);
		radioFemme.setBounds(224, 175, 76, 25);
		this.panelAjout.add(this.radioFemme);
		btAnnuler.setBounds(150, 225, 150, 25);

		this.panelAjout.add(this.btAnnuler); 
		btEnregistrer.setBounds(0, 225, 150, 25);
		this.panelAjout.add(this.btEnregistrer);
		getContentPane().add(this.panelAjout);
		
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btRetour) {
			this.dispose();
			Main.rendreVisible(true);
		}else if (e.getSource() == this.btEnregistrer) {
			this.insertActivite();
		}else if (e.getSource() == this.btAnnuler) {
			this.viderLesChamps();
		}
	}
	
	
	public void insertActivite() {
		String nom = this.txtNomUtil.getText();
		String lieu = this.txtEmail.getText();
		String description = this.txtNom.getText();

		int tel;
		float mdp, prix ;
		
		try {
			mdp = Float.parseFloat(this.txtMdp.getText());
			tel = Integer.parseInt(this.txtTel.getText());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this,"Attention au format des nombres !");
			mdp = -1; 
			 
			tel = -1;
		}
	
		if(tel >= 1) {
			Utilisateur unUtilisateur = new Utilisateur(nom, lieu, budget, description, 
				prix, tel	);
			System.out.println("la ca va");
			Main.insertUtilisateur(unUtilisateur);
/*		
 * 	POUR AFFICHER DANS LE TABLEAU PLUS TARD
			//recuperation de l'id a travers un select where 
			unPilote = Main.selectWherePilote(email, bip);
			
			//insertion dans l'affichage tableau 
			Object ligne[] = {unPilote.getIdpilote(), nom, prenom, email, bip, nationalite, nbHeuresVols+""};
			this.unTableau.insertLigne(ligne);
	*/
			JOptionPane.showMessageDialog(this,"Insertion réussie !");
			this.viderLesChamps();
		} else {
			this.txtTel.setBackground(Color.red);
			JOptionPane.showMessageDialog(this,"Erreur d'insertion vérifier les champs !");
		}
	}
	
	
	public void viderLesChamps() {
		this.txtNomUtil.setText("");
		this.txtEmail.setText("");
		this.txtMdp.setText("");
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtTel.setText("");
		this.txtAdresse.setText("");
	}
}