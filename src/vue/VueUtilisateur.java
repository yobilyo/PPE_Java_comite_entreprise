package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
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
		this.setLayout(null);
		
		this.getContentPane().setBackground(new Color (206,214, 224  ));
		
		//installer le bouton retour 
		this.btRetour.setBounds(WIDTH -170, HEIGHT -80, 140, 30);
		this.add(this.btRetour); 
		this.btRetour.addActionListener(this);
		
		//construction du panel Ajout
		this.panelAjout.setBounds(40, 100, 300, 250);
		this.panelAjout.setBackground(new Color (206,214, 224   ));
		this.panelAjout.setLayout(new GridLayout(10,2));
		this.panelAjout.add(new JLabel("Nom utilisateur :")); 
		this.panelAjout.add(this.txtNomUtil);
		this.panelAjout.add(new JLabel("Email :")); 
		this.panelAjout.add(this.txtEmail);
		this.panelAjout.add(new JLabel("Mot de passe :")); 
		this.panelAjout.add(this.txtMdp);
		this.panelAjout.add(new JLabel("Nom :")); 
		this.panelAjout.add(this.txtNom);
		this.panelAjout.add(new JLabel("Prenom :")); 
		this.panelAjout.add(this.txtPrenom);
		this.panelAjout.add(new JLabel("Téléphone :")); 
		this.panelAjout.add(this.txtTel);
		this.panelAjout.add(new JLabel("Adresse :")); 
		this.panelAjout.add(this.txtAdresse);
		
		
		
		this.panelAjout.add(new JLabel("Sexe :"));
		this.panelAjout.add(this.radioHomme);
		this.panelAjout.add(this.radioFemme);

		this.panelAjout.add(this.btAnnuler); 
		this.panelAjout.add(this.btEnregistrer);
		this.add(this.panelAjout);
		
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