package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
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
	
	private JButton btActivites = new JButton("Gestion des activités"); 
	private JButton btCommentaires = new JButton("Gestion des commentaires"); 
	private JButton btDons = new JButton("Gestion des dons"); 
	private JButton btQuitter = new JButton("Quitter l'application"); 
	
	
	public VueConnexion() {
		this.setBounds(200, 200, 700, 300);
		this.setTitle("Connexion à l'application Air France");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.getContentPane().setBackground(new Color (206,214, 224 ));
		
		this.panelConnexion.setLayout(new GridLayout(3, 2));
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
		this.panelMenu.setLayout(new GridLayout(2, 2));
		this.panelMenu.setBounds(340, 60, 300, 140);
		this.panelMenu.setBackground(new Color (51, 215, 255  ));
		
		this.panelMenu.add(this.btActivites); 
		this.panelMenu.add(this.btCommentaires); 
		this.panelMenu.add(this.btDons); 
		this.panelMenu.add(this.btQuitter); 
		
		this.panelMenu.setVisible(false);
		this.add(this.panelMenu);
		
		//rendre les boutons ecoutables du menu 
		this.btCommentaires.addActionListener(this);
		this.btActivites.addActionListener(this);
		this.btDons.addActionListener(this);
		this.btQuitter.addActionListener(this);
		
		
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
		else if (e.getSource() == this.btQuitter) {
			int retour = JOptionPane.showConfirmDialog(this, "Voulez-Vous Quitter l'Application !", "Quitter L'application", JOptionPane.YES_NO_OPTION);
			if (retour == 0) {
				this.panelConnexion.setVisible(true);
				this.panelMenu.setVisible(false);
			}
		}else if (e.getSource() == this.btActivites) {
			
			//on rend invisible la vue connexion 
			this.setVisible(false);
			//on instancie la vue pilotes 
			Main.instancierVuePilote();
			
		}else if (e.getSource() == this.btCommentaires) {
			
		}else if (e.getSource() == this.btDons) {
			
		}
	}
	
	public void traitement () {
		String email = this.txtEmail.getText(); 
		String mdp = new String (this.txtMdp.getPassword()); 
		
		Utilisateur unUser = Main.verifConnexion(email, mdp); 
		if (unUser == null) {
			JOptionPane.showMessageDialog(this, "Erreur de connexion, vérifiez vos identifiants");
		}else {
			JOptionPane.showMessageDialog(this, "Bienvenue " + unUser.getEmail()+"  "+unUser.getPassword());
			// Ouverture du menu général 
			this.panelConnexion.setVisible(false);
			this.panelMenu.setVisible(true);
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