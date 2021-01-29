package vue;

import java.awt.EventQueue;

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

	private JButton btActivites = new JButton("Gestion des activit�s"); 
	private JButton btCommentaires = new JButton("Gestion des commentaires"); 
	private JButton btDons = new JButton("Gestion des dons"); 
	private JButton btUtilisateur = new JButton("Gestion des utilisateurs"); 
	private JButton btContact = new JButton("Nous contacter"); 

	private JButton btSeDeconnecter = new JButton("Se d�connecter"); 
	
	
	public VueConnexion() {
		this.setBounds(200, 200, 700, 300);
		this.setTitle("Connexion au CE de 3D Soft");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.getContentPane().setBackground(new Color (206,214, 224 ));
		
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
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		
		//rendre les TXT ecoutable sur frappe de touche 
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		this.add(this.panelConnexion);
		
		//construction du panel Menu 
		this.panelMenu.setLayout(new GridLayout(2, 2, 2, 2));
		this.panelMenu.setBounds(340, 60, 300, 140);
		this.panelMenu.setBackground(new Color (206,214, 224  ));
		
		this.panelMenu.add(this.btActivites); 
		this.panelMenu.add(this.btCommentaires); 
		this.panelMenu.add(this.btDons); 
		this.panelMenu.add(this.btUtilisateur);
		
		this.panelMenu.setVisible(false);
		this.add(this.panelMenu);
		
		//rendre les boutons ecoutables du menu 
		this.btCommentaires.addActionListener(this);
		this.btActivites.addActionListener(this);
		this.btDons.addActionListener(this);
		this.btUtilisateur.addActionListener(this);
		this.btSeDeconnecter.addActionListener(this);
		
		
		//construction du pannel pour le bouton quitter
		this.panelQuitter.setBounds(340, 200, 300, 60);
		this.panelQuitter.setBackground(new Color(206,214, 224  ));
		this.panelQuitter.add(btSeDeconnecter);
		this.panelQuitter.setVisible(false);
		this.add(panelQuitter); 
		
		
		
		
		this.setVisible(true);
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
			int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous revenir au menu principal ?", "Se d�connecter", JOptionPane.YES_NO_OPTION);
			if (retour == 0) {
				this.panelConnexion.setVisible(true);
				this.panelMenu.setVisible(false);
				this.panelQuitter.setVisible(false);
			}
		}else if (e.getSource() == this.btActivites) {
			
			//on rend invisible la vue connexion 
			this.setVisible(false);
			//on instancie la vue Activite 
			Main.instancierVueActivite();
			
		}else if (e.getSource() == this.btCommentaires) {
			
		}else if (e.getSource() == this.btDons) {
			
		}else if (e.getSource() == this.btUtilisateur) {
			this.setVisible(false);
			
			Main.instancierVueUtilisateur();
		}
	}
	
	public void traitement () {
		String email = this.txtEmail.getText(); 
		String mdp = new String (this.txtMdp.getPassword()); 
		
		Utilisateur unUser = Main.verifConnexion(email, mdp); 
		if (unUser == null) {
			JOptionPane.showMessageDialog(this, "Erreur de connexion, v�rifiez vos identifiants");
		}else {
			JOptionPane.showMessageDialog(this, "Bienvenue " + unUser.getUsername()+"  "+unUser.getEmail());
			// Ouverture du menu g�n�ral 
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
		if (e.getKeyCode() == KeyEvent.VK_ENTER) // touche entr�e 
		{
			this.traitement();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}