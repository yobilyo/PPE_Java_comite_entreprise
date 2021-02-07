package vue;

import java.awt.EventQueue;

import java.awt.Font;
import java.awt.Graphics;


import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import controleur.Don;
import controleur.Main;
import controleur.Tableau;

public class VueDon extends JFrame implements ActionListener, MouseListener{

	private final static int WIDTH = 900;
	private final static int HEIGHT = 500;

	
	private JPanel panelAjout = new JPanel();
	private JButton btRetour = new JButton("Retour");
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JButton btFiltrer = new JButton("Filtrer :");
	private JTextField txtFiltrer = new JTextField();
	
	private JTextField txtDateDon = new JTextField(); 
	private JTextField txtMontant = new JTextField(); 
	private JTextField txtAppreciation = new JTextField(); 
	private JTextField txtidutilisateur = new JTextField(); 
	private JTextField txtid_tresorerie = new JTextField();
	
	//Construction de la partie Tableau
	private JPanel panelLister = new JPanel(); 
	private JTable uneTable ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau ;

//	Barre de filtrage
//	private JTextField txtMot = new JTextField ();
//	private JButton btFiltrer = new JButton("filtrer"); 
	
	public VueDon() {
		this.setBounds(100, 100, WIDTH, HEIGHT);
		this.setTitle("Gestion des dons");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.getContentPane().setBackground(new Color (206,214, 224   ));
		
		//installer le bouton retour 
		this.btRetour.setBounds(WIDTH -170, HEIGHT -80, 140, 30);
		this.add(this.btRetour); 
		this.btRetour.addActionListener(this);
		
		//construction du panel Ajout
		this.panelAjout.setBounds(40, 100, 300, 250);
		this.panelAjout.setBackground(new Color (206,214, 224  ));
		this.panelAjout.setLayout(new GridLayout(7,2));
		this.panelAjout.add(new JLabel("Date don :")); 
		this.panelAjout.add(this.txtDateDon);
		this.panelAjout.add(new JLabel("Montant :")); 
		this.panelAjout.add(this.txtMontant);
		this.panelAjout.add(new JLabel("Appreciation :")); 
		this.panelAjout.add(this.txtAppreciation);
		this.panelAjout.add(new JLabel("Utilisateur :")); 
		this.panelAjout.add(this.txtidutilisateur);
		this.panelAjout.add(new JLabel("Trésorerie :")); 
		this.panelAjout.add(this.txtid_tresorerie);
		this.panelAjout.add(this.btAnnuler); 
		this.panelAjout.add(this.btEnregistrer);
		this.add(this.panelAjout);
		
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);

		this.btFiltrer.setBounds(WIDTH /2 - 80, 20, 100, 20);
		this.add(btFiltrer);
		this.btFiltrer.addActionListener(this);
		this.txtFiltrer.setBounds(WIDTH / 2 + 40, 20 , 100, 20);
		this.add(txtFiltrer);
		
		
		remplirPanelLister("");
		this.uneTable.addMouseListener(this);
		
		this.setVisible(true);
		
		initBoutons();
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
		}else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Enregistrer")) {
			this.insertDon();
		}else if (e.getSource() == this.btAnnuler) {
			this.viderLesChamps();
		}else if (e.getSource()  == this.btEnregistrer && e.getActionCommand().equals("Modifier")) 
		{
			this.updateDon();  
		}else if (e.getSource() == this.btFiltrer)
		{
			this.remplirPanelLister(this.txtFiltrer.getText());
		}
	}
	
	public void updateDon() {
		String datedon = this.txtDateDon.getText(); 
		float montant = 0;
		String appreciation = this.txtAppreciation.getText(); 
		int idutilisateur = 0;
		int id_tresorerie = 0;
		
		try {
			montant = Integer.parseInt(this.txtMontant.getText());
		}
		catch (NumberFormatException exp) {
			JOptionPane.showMessageDialog(this,"Attention au format du nombre d'heures  !");
			montant = -1 ;
		}
		if (montant >=0 ) {
			int numLigne = uneTable.getSelectedRow(); 
			int iddon = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString ());
			Don unDon = new Don(iddon ,idutilisateur, id_tresorerie, appreciation, montant, datedon);
			//update dans la base de donnÃ©es 
			Main.updateDon(unDon);
			
			//modifiaction dans l'affichage tableau 
			Object ligne[] = {unDon.getIddon(), datedon, montant, appreciation, idutilisateur, id_tresorerie+""};
			this.unTableau.updateLigne(numLigne, ligne);
			
			JOptionPane.showMessageDialog(this,"Modification réussie !");
			this.viderLesChamps();
		} else {
			this.txtMontant.setBackground(Color.red);
		}
		
	}
	
	
	
	
	public void insertDon() {
		String datedon = this.txtDateDon.getText(); 
		float montant;
		String appreciation = this.txtAppreciation.getText(); 
		int idutilisateur= 0;
		int id_tresorerie= 0;
		
		
		try {
			montant = Integer.parseInt(this.txtMontant.getText());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this,"Attention au format des nombres !");
			montant = -1; 
		}
	
		if(montant >= 1) {
			Don unDon = new Don(idutilisateur, id_tresorerie, appreciation, montant, datedon);
			Main.insertDon(unDon);
			JOptionPane.showMessageDialog(this,"Insertion réussie !");
			this.viderLesChamps();
		} else {
			this.txtMontant.setBackground(Color.red);
			JOptionPane.showMessageDialog(this,"Erreur d'insertion vérifier les champs !");
		}
	}
	
	
	public void initPanelLister() {
		//construire le panel Lister 
		this.panelLister.setBackground(new Color (206,214, 224  ));
		this.panelLister.setLayout(null);

		this.panelLister.setBounds(350, 80, 530, 300);
		
		this.uneScroll.setBounds(010, 10, 510, 280);
		this.panelLister.add(this.uneScroll);
		
		
		this.add(this.panelLister); 
	}
	
	public void remplirPanelLister(String mot) {
		this.panelLister.removeAll();
		String entetes [] = {"ID Don", "Date don", "Montant", "Appreciation", "ID Utilisateur", "ID Tresorerie"};
		Object donnees [][] = this.getDonnees(mot) ;			
		this.unTableau = new Tableau (donnees, entetes); 
		this.uneTable = new JTable(this.unTableau); 
		this.uneScroll = new JScrollPane(this.uneTable); 
		
		Main.styleTableau(this.uneTable);
		initPanelLister();

	}
	

	
	public Object [] [] getDonnees(String mot) {
		//recuperer les pilotes de la bdd 
		ArrayList<Don> lesDons = Main.selectAllDons(mot); 
		//transofrmation des pilotes en matrice de donnÃ©es 
		Object donnees [][] = new Object [lesDons.size()][7];
		int i = 0 ; 
		for (Don unDon : lesDons) {
			donnees[i][0] = unDon.getIddon()+""; 
			donnees[i][1] = unDon.getDatedon(); 
			donnees[i][2] = unDon.getMontant(); 
			donnees[i][3] = unDon.getAppreciation(); 
			donnees[i][4] = unDon.getIdutilisateur(); 
			donnees[i][5] = unDon.getId_tresorerie() + ""; 
			i++; 
		}
				
		return donnees;
	}

	
	public void viderLesChamps() {
		this.txtDateDon.setText("");
		this.txtMontant.setText("");
		this.txtAppreciation.setText("");
		this.txtidutilisateur.setText("");
		this.txtid_tresorerie.setText("");
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() >=2) {
			int ligne = uneTable.getSelectedRow();
			System.out.println(ligne);
			int iddon = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString()); 
			int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce don ?", "Suppression", JOptionPane.YES_NO_OPTION); 
			if (retour == 0) {
				//suppression dans la base 
				Main.deleteDon(iddon);
				//suppression dans la table d'affichage 
				unTableau.deleteLigne(ligne);
				JOptionPane.showMessageDialog(null, "Suppression réussie");
			}
		}else if (e.getClickCount() ==1) {
			int ligne = uneTable.getSelectedRow();
			txtDateDon.setText(unTableau.getValueAt(ligne, 1).toString());
			txtMontant.setText(unTableau.getValueAt(ligne, 2).toString());
			txtAppreciation.setText(unTableau.getValueAt(ligne, 3).toString());
			txtidutilisateur.setText(unTableau.getValueAt(ligne, 4).toString());
			txtid_tresorerie.setText(unTableau.getValueAt(ligne, 5).toString());
			btEnregistrer.setText("Modifier");
		}		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

