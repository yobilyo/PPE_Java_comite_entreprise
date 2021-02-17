package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Main;
import controleur.Tableau;

public class VueParticiper extends JFrame implements ActionListener {
	//Construction du panelAjout
	private JPanel panelAjout = new JPanel();
	
	private JButton btRetour = new JButton("Retour");
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JButton btFiltrer = new JButton("Filtrer :");
	private JTextField txtFiltrer = new JTextField();
	
	//Construction de la partie Tableau
	private JPanel panelLister = new JPanel(); 
	private JTable uneTable ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau ;
	
	public VueParticiper() {
		this.setBounds(100, 100, Main.getWidth(), Main.getHeight());
		this.setTitle("Gestion des participations");
		
		// TEST !
		this.setResizable(true);
		// TEST !
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.getContentPane().setBackground(new Color (206,214, 224   ));
		
		//installer le bouton retour 
		this.btRetour.setBounds(Main.getWidth() -170, Main.getHeight() -80, 140, 30);
		this.add(this.btRetour); 
		this.btRetour.addActionListener(this);
		
		initBoutons();
		
		this.btFiltrer.setBounds(Main.getWidth() /2 - 80, 20, 100, 20);
		this.add(btFiltrer);
		this.btFiltrer.addActionListener(this);
		this.txtFiltrer.setBounds(Main.getWidth() / 2 + 40, 20 , 100, 20);
		this.add(txtFiltrer);
		
		this.setVisible(true);
	}
	
	private void initBoutons() {
		Main.styleBoutonDark(this.btAnnuler);
		Main.styleBoutonDark(this.btEnregistrer);
		Main.styleBoutonDark(this.btFiltrer);
		Main.styleBoutonBleu(this.btRetour);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btRetour) {
			this.dispose();
			Main.rendreVisible(true);
		}
		
	}
}
