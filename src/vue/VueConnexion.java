package vue;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Main;

import controleur.Utilisateur;

public class VueConnexion extends JFrame implements ActionListener, KeyListener
{
	private JButton btSeConnecter = new JButton("Se Connecter"); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JTextField txtEmail = new JTextField("a@gmail.com"); 
	private JPasswordField txtMdp = new JPasswordField(""); 
	
	private JPanel panelConnexion = new JPanel(); 
	private JPanel panelMenu = new JPanel (); 
	private JPanel panelQuitter = new JPanel (); 

	private JButton btActivites = new JButton("Activités");  
	private JButton btDons = new JButton("Dons"); 
	private JButton btUtilisateur = new JButton("Utilisateurs"); 
	private JButton btParticiper = new JButton("Participer"); 
	private JButton btTresorerie = new JButton("Trésorerie");
	private JButton btTBord = new JButton("Tableau de Bord");
	//private JButton btContact = new JButton("Nous contacter"); 

	private JButton btSeDeconnecter = new JButton("Se déconnecter"); 
	
	
	public VueConnexion() {
		this.setBounds(200, 200, 700, 300);
		this.setTitle("Connexion au CE de 3D Soft");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.getContentPane().setBackground(new Color (206,214, 224 ));
		
		//construction du panel Connexion
		this.panelConnexion.setLayout(new GridLayout(3, 2, 3, 3));
		this.panelConnexion.setBounds(340, 60, 300, 140);
		this.panelConnexion.setBackground(new Color (206,214, 224 ));
		this.panelConnexion.add(new JLabel("Email : ")); 
		this.panelConnexion.add(this.txtEmail); 
		this.panelConnexion.add(new JLabel("MDP : ")); 
		this.panelConnexion.add(this.txtMdp); 
		this.panelConnexion.add(this.btAnnuler); 
		this.panelConnexion.add(this.btSeConnecter); 
		
		ImageIcon uneImage = new ImageIcon("src/images/3Dsoft-logo.png");
		JLabel monLogo = new JLabel(uneImage); 
		monLogo.setBounds(20, 60, 300, 150); 
		this.add(monLogo);
		
		//rendre les boutons ecoutables du panel Connexion
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		
		//rendre les TXT ecoutable sur frappe de touche 
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		this.add(this.panelConnexion);
		
		//construction du panel Menu 
		this.panelMenu.setLayout(new GridLayout(3, 2, 2, 2));
		this.panelMenu.setBounds(340, 60, 300, 140);
		this.panelMenu.setBackground(new Color (206,214, 224  ));
		
		this.panelMenu.add(this.btActivites); 
		this.panelMenu.add(this.btParticiper);
		this.panelMenu.add(this.btDons); 
		this.panelMenu.add(this.btUtilisateur);
		this.panelMenu.add(this.btTresorerie);
		this.panelMenu.add(this.btTBord);
		
		this.panelMenu.setVisible(false);
		this.add(this.panelMenu);
		
		//rendre les boutons ecoutables du panelMenu 
		this.btActivites.addActionListener(this);
		this.btParticiper.addActionListener(this);	
		this.btDons.addActionListener(this);
		this.btUtilisateur.addActionListener(this);
		this.btTresorerie.addActionListener(this);
		this.btTBord.addActionListener(this);
		
		//construction du pannelQuitter pour le bouton quitter
		this.panelQuitter.setBounds(340, 200, 300, 60);
		this.panelQuitter.setBackground(new Color(206,214, 224  ));
		this.panelQuitter.add(btSeDeconnecter);
		this.panelQuitter.setVisible(false);
		this.add(panelQuitter);
		
		// rendre les boutons écoutables du panelQuitter
		this.btSeDeconnecter.addActionListener(this);
		
		initBoutons();
		
		this.setVisible(true);
	}

	public void initBoutons() {
		Main.styleBoutonBleu(this.btSeConnecter);
		Main.styleBoutonBleu(this.btAnnuler);
		Main.styleBoutonDark(this.btActivites);
		Main.styleBoutonDark(this.btParticiper);
		Main.styleBoutonDark(this.btDons);
		Main.styleBoutonDark(this.btUtilisateur);
		Main.styleBoutonBleu(this.btSeDeconnecter);
		Main.styleBoutonDark(this.btTresorerie);
		Main.styleBoutonDark(this.btTBord);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}else if (e.getSource() == this.btSeConnecter) {
			this.traitement();
		}
		else if (e.getSource() == this.btSeDeconnecter) {
			int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous revenir au menu principal ?", "Se déconnecter", JOptionPane.YES_NO_OPTION);
			if (retour == 0) {
				// l'user vient de se déconnecter, donc on désactive l'idutilisateur connecté
				// on le remet à -1
				Main.disableIdUtilisateurConnecte();
				System.out.println("idutilisateur déconnecté : " + Main.getIdUtilisateurConnecte());
				
				this.panelConnexion.setVisible(true);
				this.panelMenu.setVisible(false);
				this.panelQuitter.setVisible(false);
			}
		}else if (e.getSource() == this.btActivites) {
			//on rend invisible la vue connexion 
			this.setVisible(false);
			//on instancie la vue Activite 
			Main.instancierVueActivite();
		}else if (e.getSource() == this.btParticiper) {
			//on rend invisible la vue connexion 
			this.setVisible(false);
			//on instancie la vue Participer 
			Main.instancierParticiper();
		}else if (e.getSource() == this.btDons) {
			this.setVisible(false);
			Main.instancierVueDon();
		}else if (e.getSource() == this.btUtilisateur) {
			this.setVisible(false);
			Main.instancierVueUtilisateur();
		}else if (e.getSource() == this.btTresorerie) {
			this.setVisible(false);
			Main.instancierVueTresorerie();
		}else if (e.getSource() == this.btTBord) {
			this.setVisible(false);
			Main.instancierVueTBord();
		}
	}
	
	public void traitement () {
		String email = this.txtEmail.getText(); 
		String mdp = new String (this.txtMdp.getPassword()); 
		
		Utilisateur unUser = Main.verifConnexion(email, mdp); 

		if (unUser == null) {
			JOptionPane.showMessageDialog(this, "Erreur de connexion, vérifiez vos identifiants");
		} else if (!unUser.getDroits().equals("admin")) {
			System.out.println("utilisateur non autorisé : id " + unUser.getIdUtilisateur()
			+ " " + unUser.getDroits());
			String msg = "Ce logiciel est réservé uniquement aux administrateurs pour gérer"
			+ " la base de données.\nVous n'êtes pas administrateur, veuillez utiliser le"
			+ " site internet du comité d'entreprise pour gérer vos activités personnelles.";
			JOptionPane.showMessageDialog(this, msg);
		} else {
			// on stocke l'idUtilisateur qui s'est connecté
			Main.setIdUtilisateurConnecte(unUser.getIdUtilisateur());
			System.out.println("idutilisateur connecté: " + Main.getIdUtilisateurConnecte());
			System.out.println(Main.getDroitsUtilisateurConnecte());
			
			JOptionPane.showMessageDialog(this, "Bienvenue " + unUser.getUsername()+"  "+unUser.getEmail());
			// Ouverture du menu général 
			this.panelConnexion.setVisible(false);
			this.panelMenu.setVisible(true);
			this.panelQuitter.setVisible(true);
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) // touche entrée 
		{
			this.traitement();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}