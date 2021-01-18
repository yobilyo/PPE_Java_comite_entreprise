package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VueConnexion extends JFrame implements ActionListener{

	private static VueActivite uneVueActivite;
	
	private JButton btActivite = new JButton("Gestion des activités"); 
	private JButton btCommentaire = new JButton("Gestion des commentaires"); 
	private JButton btDon = new JButton("Gestion du don"); 
	private JButton btUtilisateur = new JButton("Gestion des utilisateurs");
	private JButton btSeDeconnecter = new JButton("Déconnexion");
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btSeConnecter = new JButton("Se Connecter");



	private JTextField txtEmail = new JTextField("a@gmail.com");
	private JTextField txtMdp = new JTextField("123");
	
	
	private JPanel panelBouton, panelConnexion;  
	
	public VueConnexion() {
		this.setBounds(200, 200, 700, 300);
		this.setTitle("Connexion au CE");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color (206, 214, 224 ));
		this.setAlwaysOnTop(true);
		
		this.panelBouton = new JPanel();
		this.panelConnexion = new JPanel();
		
		
		this.panelConnexion.setLayout(new GridLayout(3, 2));
		this.panelConnexion.setBounds(340, 60, 300, 140);
		this.panelConnexion.setBackground(new Color (206, 214, 224));
		this.panelConnexion.add(new JLabel("Email : ")); 
		this.panelConnexion.add(this.txtEmail); 
		this.panelConnexion.add(new JLabel("MDP : ")); 
		this.panelConnexion.add(this.txtMdp); 
		this.panelConnexion.add(this.btAnnuler); 
		this.btAnnuler.setBackground(new Color(52, 58, 64));
		this.btAnnuler.setForeground(Color.white);
		this.panelConnexion.add(this.btSeConnecter); 
		
		this.panelBouton.setLayout(new GridLayout(3, 2, 2, 2));
		this.panelBouton.setBounds(340, 60, 300, 140);
		this.panelBouton.setBackground(new Color (206, 214, 224 ));
		this.panelBouton.add(this.btActivite); 
		this.panelBouton.add(this.btCommentaire); 
		this.panelBouton.add(this.btDon);
		this.panelBouton.add(this.btUtilisateur);
		this.panelBouton.add(this.btSeDeconnecter); 
		this.panelBouton.setVisible(false);
		
		
		this.add(panelConnexion);
		this.add(panelBouton);
		
		
		ImageIcon uneImage = new ImageIcon("src/images/3Dsoft-logo.png");
		JLabel monLogo = new JLabel(uneImage); 
		monLogo.setBounds(20, 60, 300, 150);
		this.add(monLogo);
		
		
		this.btSeConnecter.addActionListener(this);
		this.btSeDeconnecter.addActionListener(this);
		this.btActivite.addActionListener(this);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btSeConnecter) {
			this.panelConnexion.setVisible(false);
			this.panelBouton.setVisible(true);
		}else if (e.getSource() == this.btSeDeconnecter) {
			int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous vous déconnecter ?", "Se déconnecter", JOptionPane.YES_NO_OPTION);
			if (retour == 0) {
				this.panelConnexion.setVisible(true);
				this.panelBouton.setVisible(false);
		}else if (e.getSource() == this.btActivite) {
			//uneVueActivite.setVisible(true);
			System.out.println("Ok");
			//this.dispose();
		}
	}
}
	
	
}
