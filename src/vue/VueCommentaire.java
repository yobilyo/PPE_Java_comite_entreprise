package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Activite;
import controleur.Commentaire;
import controleur.Main;
import controleur.Tableau;
import controleur.Utilisateur;

public class VueCommentaire extends JFrame implements ActionListener{
	private final static int WIDTH = 900;
	private final static int HEIGHT = 500;

	
	private static VueConnexion uneVueConnexion; 
	
	private JPanel panelAjout = new JPanel();
	private JButton btRetour = new JButton("Retour");
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JPanel panelLister = new JPanel();
	
	
	private JButton btFiltrer = new JButton("Filtrer :");
	private JTextField txtFiltrer = new JTextField();
	
	//Cr�er date et l'afficher
	private JLabel labDate = new JLabel();
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	private Date dateComment = new Date();
	private JTextField txtDate = new JTextField(sFormat.format(dateComment).toString());
	
	//private JTextField txtDateCommentaire = new JTextField(); 
	private JTextField txtContenu = new JTextField(); 
	private JComboBox<String> cbxActivite = new JComboBox<String>();
	private JComboBox<String> cbxUtilisateur = new JComboBox<String>();
	
	private Tableau unTableau;
	private JTable uneTable;
	private JScrollPane uneScroll;
	
	public VueCommentaire() {
		super();
		this.setBounds(100, 100, WIDTH, HEIGHT);
		this.setTitle("Gestion des commentaires :");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.getContentPane().setBackground(new Color (206,214, 224));
		
		//installer le bouton retour 
		this.btRetour.setBounds(WIDTH -170, HEIGHT -80, 140, 30);
		this.add(this.btRetour); 
		this.btRetour.addActionListener(this);
		
		//construction du panel Ajout
		this.panelAjout.setBounds(40, 100, 300, 250);
		this.panelAjout.setBackground(new Color (206,214, 224  ));
		this.panelAjout.setLayout(new GridLayout(5,2, 4, 4));
		this.panelAjout.add(new JLabel("Date du commentaire :")); 
		this.panelAjout.add(this.txtDate);
		this.panelAjout.add(new JLabel("Contenu du commentaire :")); 
		this.panelAjout.add(this.txtContenu);
		this.panelAjout.add(new JLabel("Utilisateur :"));
		this.panelAjout.add(this.cbxUtilisateur);
		this.panelAjout.add(new JLabel("Activit� : "));
		this.panelAjout.add(this.cbxActivite);
		
		remplirCBXActivite();
		remplirCBXUtilisateurs();
		remplirPanelLister("");
		
		this.add(panelAjout);
		this.panelAjout.add(btAnnuler);
		this.panelAjout.add(btEnregistrer);
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		this.btFiltrer.setBounds(Main.WIDTH /2 - 200, 20, 100, 20);
		this.add(btFiltrer);
		this.btFiltrer.addActionListener(this);
		this.txtFiltrer.setBounds(Main.WIDTH / 2 - 80, 20 , 100, 20);
		this.add(txtFiltrer);
		
		initBoutons();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btRetour) {
			this.dispose();
			Main.rendreVisible(true);
		}else if(e.getSource() == this.btAnnuler) {
			this.txtContenu.setText("");
		}else if(e.getSource() == this.btEnregistrer) {
			insertCommentaire();
		}else if (e.getSource() == this.btFiltrer)
		{
			this.remplirPanelLister(this.txtFiltrer.getText());
		}
	}
	
	public void remplirCBXActivite() {
		ArrayList<Activite>lesActivites = Main.selectAllActivites("");
		for (Activite uneActivite : lesActivites) {
			this.cbxActivite.addItem(uneActivite.getIdActivite() + " - " + uneActivite.getNom()); 
		}
	}
	public void remplirCBXUtilisateurs()
	{
		ArrayList<Utilisateur> lesUtilisateurs = Main.selectAllUtilisateurs("");
		this.cbxUtilisateur.removeAllItems();
		for (Utilisateur unUtilisateur : lesUtilisateurs)
		{
			this.cbxUtilisateur.addItem(unUtilisateur.getIdUtilisateur()+" - "+unUtilisateur.getUsername());
		}
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
	
	public void initPanelLister() {
		//construire le panel Lister 
		this.panelLister.setBackground(new Color (206,214, 224));
		this.panelLister.setLayout(null);

		this.panelLister.setBounds(350, 80, 530, 300);
		
		this.uneScroll.setBounds(010, 10, 510, 280);
		this.panelLister.add(this.uneScroll);
		
		
		this.add(this.panelLister); 
	}
	
	public void remplirPanelLister(String mot) {
		this.panelLister.removeAll();
		String entetes [] = {"IdCommentaire", "Date_Commentaire", "Contenu", "IdActivit�", "IdUtilisateur", };
		Object donnees [][] = this.getDonnees(mot) ;			
		this.unTableau = new Tableau (donnees, entetes); 
		this.uneTable = new JTable(this.unTableau); 
		this.uneScroll = new JScrollPane(this.uneTable); 
		Main.styleTableau(this.uneTable);
		initPanelLister();
	}
	
	public Object [] [] getDonnees(String mot) {
		//recuperer les pilotes de la bdd 
		ArrayList<Commentaire> lesCommentaires = Main.selectAllCommentaires(mot); 
		//transofrmation des pilotes en matrice de données 
		Object donnees [][] = new Object [lesCommentaires.size()][7];
		int i = 0 ; 
		for (Commentaire unCommentaire : lesCommentaires) {
			donnees[i][0] = unCommentaire.getIdCommentaire()+""; 
			donnees[i][1] = unCommentaire.getDateComment(); 
			donnees[i][2] = unCommentaire.getContenu(); 
			donnees[i][3] = unCommentaire.getIdActivite(); 
			donnees[i][4] = unCommentaire.getIdUtilisateur()+ ""; 
			i++; 
		}
		return donnees;
	}

	
	public void viderLesChamps() {
		this.txtContenu.setText("");
	}
	
	
	public void insertCommentaire() {
		String contenu = this.txtContenu.getText();
		String dateComment = this.txtDate.getText(); //r�cup�ration de la date dans un String
		String chaineAct = this.cbxActivite.getSelectedItem().toString();
		String tabAct [] = chaineAct.split(" - ");
		int idActivite = 0;
		int idUtilisateur = 0;
		String chaineUti = this.cbxUtilisateur.getSelectedItem().toString();
		String tabUti [] = chaineUti.split(" - ");
		
		//parser le String date dans un type Date pour permettre l'insertion
		Date ddcoment = null ;
		try {
			ddcoment = java.sql.Date.valueOf(dateComment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			idActivite = Integer.parseInt(tabAct[0]);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Impossible de parser l'idActivit�");
		}
		try {
			idUtilisateur = Integer.parseInt(tabUti[0]);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Impossible de parser l'idUtilisateur");

		}
	

//		try {
//			dateComment = Date.parse(this.txtDateCommentaire.getText());
//		} catch (NumberFormatException nfe) {
//			JOptionPane.showMessageDialog(this,"Attention au format des nombres !");
//			budget = -1; 
//			prix = -1; 
//			nbPersonnes = -1;
//		}
	
		if(! this.txtContenu.getText().equals("")){
			Commentaire unCommentaire = new Commentaire(ddcoment, contenu, idActivite, idUtilisateur);
			Main.insertCommentaire(unCommentaire);
			JOptionPane.showMessageDialog(this,"Insertion r�ussie !");
			this.viderLesChamps();
		} else {
			this.txtContenu.setBackground(Color.red);
			JOptionPane.showMessageDialog(this,"Erreur d'insertion v�rifier les champs !");
		}
	}
	
}