package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import controleur.Main;
import controleur.Participation;
import controleur.Tableau;
import controleur.Utilisateur;

public class VueParticiper extends JFrame implements ActionListener{
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
	
	//Créer date et l'afficher
	//private JLabel labDate = new JLabel();
	//private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	//private Date dateComment = new Date();
	//private JTextField txtDate = new JTextField(sFormat.format(dateComment).toString());
	
	//private JTextField txtDateCommentaire = new JTextField(); 
	private JTextField txtDate = new JTextField(); 
	private JComboBox<String> cbxActivite = new JComboBox<String>();
	private JComboBox<String> cbxUtilisateur = new JComboBox<String>();
	
	private Tableau unTableau;
	private JTable uneTable;
	private JScrollPane uneScroll;
	
	public VueParticiper() {
		super();
		this.setBounds(100, 100, WIDTH, HEIGHT);
		this.setTitle("Gestion des participations :");
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
		this.panelAjout.add(new JLabel("Date d'inscription :")); 
		this.panelAjout.add(this.txtDate);
		this.panelAjout.add(new JLabel("Utilisateur :"));
		this.panelAjout.add(this.cbxUtilisateur);
		this.panelAjout.add(new JLabel("Activité : "));
		this.panelAjout.add(this.cbxActivite);
		
		remplirCBXActivite();
		remplirCBXUtilisateurs();
		remplirPanelLister("");
		
		this.add(panelAjout);
		this.panelAjout.add(btAnnuler);
		this.panelAjout.add(btEnregistrer);
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		this.btFiltrer.setBounds(Main.getWidth() /2 - 200, 20, 100, 20);
		this.add(btFiltrer);
		this.btFiltrer.addActionListener(this);
		this.txtFiltrer.setBounds(Main.getWidth() / 2 - 80, 20 , 100, 20);
		this.add(txtFiltrer);
		
		this.uneTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() >=2) {
					int ligne = uneTable.getSelectedRow();
					System.out.println(ligne);
					int idutilisateur = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString()); 
					int id_activite = Integer.parseInt(unTableau.getValueAt(ligne, 1).toString()); 
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette participation ?", "Suppression", JOptionPane.YES_NO_OPTION); 
					if (retour == 0) {
						//suppression dans la base 
						Main.deleteParticipation(idutilisateur, id_activite);
						//suppression dans la table d'affichage 
						unTableau.deleteLigne(ligne);
						JOptionPane.showMessageDialog(null, "Suppression réussie");
					}
				}else if (e.getClickCount() ==1) {
					int ligne = uneTable.getSelectedRow();
					txtDate.setText(unTableau.getValueAt(ligne, 1).toString());
					cbxUtilisateur.setSelectedItem(unTableau.getValueAt(ligne, 0).toString());
					cbxActivite.setSelectedItem(unTableau.getValueAt(ligne, 2).toString());
					btEnregistrer.setText("Modifier");
				}		
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		initBoutons();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btRetour) {
			this.dispose();
			Main.rendreVisible(true);
		}else if(e.getSource() == this.btAnnuler) {
			
			this.btEnregistrer.setText("Enregistrer");
		}else if(e.getSource() == this.btEnregistrer) {
			insertParticipation();
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
		String entetes [] = {"IdUtilisateur", "IdActivite", "DateInscription" };
		Object donnees [][] = this.getDonnees(mot) ;			
		this.unTableau = new Tableau (donnees, entetes); 
		this.uneTable = new JTable(this.unTableau); 
		this.uneScroll = new JScrollPane(this.uneTable); 
		Main.styleTableau(this.uneTable);
		initPanelLister();
	}
	
	public Object [] [] getDonnees(String mot) {
		//recuperer les pilotes de la bdd 
		ArrayList<Participation> lesParticipations = Main.selectAllParticipation(mot); 
		//transofrmation des pilotes en matrice de donnÃ©es 
		Object donnees [][] = new Object [lesParticipations.size()][3];
		int i = 0 ; 
		for (Participation uneParticipation : lesParticipations) {
			donnees[i][0] = uneParticipation.getIdUtilisateur()+""; 
			donnees[i][1] = uneParticipation.getIdActivite(); 
			donnees[i][2] = uneParticipation.getDateParticipation()
					+ ""; 
			i++; 
		}
		return donnees;
	}

	
	public void viderLesChamps() {
		
	}
	
	
	public void insertParticipation() {
		//String contenu = this.txtContenu.getText();
		String dateComment = this.txtDate.getText(); //récupération de la date dans un String
		String chaineAct = this.cbxActivite.getSelectedItem().toString();
		String tabAct [] = chaineAct.split(" - ");
		int idUtilisateur = 0;
		int idActivite = 0;
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
			idUtilisateur = Integer.parseInt(tabUti[0]);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Impossible de parser l'idUtilisateur");

		}
		try {
			idActivite = Integer.parseInt(tabAct[0]);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Impossible de parser l'idActivité");
		}
	

//		try {
//			dateComment = Date.parse(this.txtDateCommentaire.getText());
//		} catch (NumberFormatException nfe) {
//			JOptionPane.showMessageDialog(this,"Attention au format des nombres !");
//			budget = -1; 
//			prix = -1; 
//			nbPersonnes = -1;
//		}
	
		if(! this.txtDate.getText().equals("")){
			Participation uneParticipation = new Participation(idUtilisateur, idActivite, dateComment);
			Main.insertParticipation(uneParticipation);
			JOptionPane.showMessageDialog(this,"Insertion réussie !");
			this.viderLesChamps();
		} else {
			this.txtDate.setBackground(Color.red);
			JOptionPane.showMessageDialog(this,"Erreur d'insertion vérifier les champs !");
		}
	}
	
}
