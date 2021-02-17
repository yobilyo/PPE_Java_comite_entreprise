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
		this.setBounds(100, 100, Main.WIDTH, Main.HEIGHT);
		this.setTitle("Gestion des participations");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.getContentPane().setBackground(new Color (206,214, 224   ));
		
		//installer le bouton retour 
		this.btRetour.setBounds(Main.WIDTH -170, Main.HEIGHT -80, 140, 30);
		this.add(this.btRetour); 
		this.btRetour.addActionListener(this);
		
		initBoutons();
		
		this.btFiltrer.setBounds(Main.WIDTH /2 - 80, 20, 100, 20);
		this.add(btFiltrer);
		this.btFiltrer.addActionListener(this);
		this.txtFiltrer.setBounds(Main.WIDTH / 2 + 40, 20 , 100, 20);
		this.add(txtFiltrer);
		
		this.setVisible(true);
	}
	
	private void initBoutons() {
		this.btAnnuler.setBackground(new Color(52, 58, 64));
		this.btAnnuler.setForeground(new Color(255, 255, 255));
		this.btAnnuler.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
	
		this.btEnregistrer.setBackground(new Color(52, 58, 64));
		this.btEnregistrer.setForeground(new Color(255, 255, 255));
		this.btEnregistrer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
	
		this.btRetour.setBackground(new Color(31, 61, 128));
		this.btRetour.setForeground(new Color(255, 255, 255));
		this.btRetour.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		
		this.btFiltrer.setBackground(new Color(52, 58, 64));
		this.btFiltrer.setForeground(new Color(255, 255, 255));
		this.btFiltrer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btRetour) {
			this.dispose();
			Main.rendreVisible(true);
		}
		
	}
}
