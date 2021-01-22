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
import javax.swing.JTextField;

import controleur.Activite;
import controleur.Main;

public class VueActivite extends JFrame implements ActionListener{
	
	private final static int WIDTH = 900;
	private final static int HEIGHT = 500;

	
	private static VueConnexion uneVueConnexion; 
	
	private JPanel panelAjout = new JPanel();
	private JButton btRetour = new JButton("Retour au menu");
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JTextField txtNomAct = new JTextField(); 
	private JTextField txtLieu = new JTextField(); 
	private JTextField txtBudget = new JTextField(); 
	private JTextField txtDescription = new JTextField(); 
	private JTextField txtPrix= new JTextField();
	private JTextField txtNbPersonnes = new JTextField();
	
	public VueActivite() {
		this.setBounds(100, 100, WIDTH, HEIGHT);
		this.setTitle("Gestion des activités");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.getContentPane().setBackground(new Color (51, 215, 255  ));
		
		//installer le bouton retour 
		this.btRetour.setBounds(WIDTH -170, HEIGHT -80, 140, 30);
		this.add(this.btRetour); 
		this.btRetour.addActionListener(this);
		
		//construction du panel Ajout
		this.panelAjout.setBounds(40, 100, 300, 250);
		this.panelAjout.setBackground(new Color (51, 215, 255  ));
		this.panelAjout.setLayout(new GridLayout(7,2));
		this.panelAjout.add(new JLabel("Nom activité :")); 
		this.panelAjout.add(this.txtNomAct);
		this.panelAjout.add(new JLabel("Lieu de l'activité :")); 
		this.panelAjout.add(this.txtLieu);
		this.panelAjout.add(new JLabel("Budget de l'activité :")); 
		this.panelAjout.add(this.txtBudget);
		this.panelAjout.add(new JLabel("Description de l'activité :")); 
		this.panelAjout.add(this.txtDescription);
		this.panelAjout.add(new JLabel("Prix de l'activité :")); 
		this.panelAjout.add(this.txtPrix);
		this.panelAjout.add(new JLabel("Nb de personnes de l'activité :")); 
		this.panelAjout.add(this.txtNbPersonnes);
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
		String nom = this.txtNomAct.getText();
		String lieu = this.txtLieu.getText();
		String description = this.txtDescription.getText();

		int nbPersonnes;
		float budget, prix ;
		
		try {
			budget = Float.parseFloat(this.txtBudget.getText());
			prix = Float.parseFloat(this.txtBudget.getText());
			nbPersonnes = Integer.parseInt(this.txtNbPersonnes.getText());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this,"Attention au format des nombres !");
			budget = -1; 
			prix = -1; 
			nbPersonnes = -1;
		}
	
		if(nbPersonnes >= 1) {
			Activite uneActivite = new Activite(nom, lieu, budget, description, 
				prix, nbPersonnes	);
			System.out.println("la ca va");
			Main.insertActivite(uneActivite);
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
			this.txtNbPersonnes.setBackground(Color.red);
			JOptionPane.showMessageDialog(this,"Erreur d'insertion vérifier les champs !");
		}
	}
	
	
	public void viderLesChamps() {
		this.txtNomAct.setText("");
		this.txtLieu.setText("");
		this.txtBudget.setText("");
		this.txtDescription.setText("");
		this.txtPrix.setText("");
		this.txtNbPersonnes.setText("");
	}
}
