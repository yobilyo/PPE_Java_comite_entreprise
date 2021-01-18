package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VueActivite extends JFrame implements ActionListener{
	
	private static VueConnexion uneVueConnexion; 
	
	private JPanel panelAjout = new JPanel();
	private JButton btRetour = new JButton();
	private JButton btAnnuler = new JButton();
	private JButton btEnregistrer = new JButton();
	
	private JTextField txtNomAct = new JTextField(); 
	private JTextField txtLieu = new JTextField(); 
	private JTextField txtBudget = new JTextField(); 
	private JTextField txtDescription = new JTextField(); 
	private JTextField txtPrix= new JTextField();
	
	public VueActivite() {
		this.setBounds(100, 100, 900, 500);
		this.setTitle("Gestion des activités");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.getContentPane().setBackground(new Color (51, 215, 255  ));
		
		//installer le bouton retour 
		this.btRetour.setBounds(750, 440, 140, 30);
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
		this.panelAjout.add(new JLabel("Budget de l'activité")); 
		this.panelAjout.add(this.txtBudget);
		this.panelAjout.add(new JLabel("Description de l'activité :")); 
		this.panelAjout.add(this.txtDescription);
		this.panelAjout.add(new JLabel("Prix de l'activité :")); 
		this.panelAjout.add(this.txtPrix);
		this.panelAjout.add(this.btAnnuler); 
		this.panelAjout.add(this.btEnregistrer);
		this.add(this.panelAjout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btRetour) {
			this.dispose();
			uneVueConnexion.setVisible(true);	
		}
		
	}
}
