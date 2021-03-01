package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controleur.Main;
import controleur.Salarie;
import controleur.Sponsor;
import controleur.Tableau;

public class VueUtilisateur extends JFrame implements ActionListener {
	
	private static VueConnexion uneVueConnexion; 
	
	private JPanel panelAjoutSalarie = new JPanel();
	private JButton btRetour = new JButton("Retour");
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JButton btSalarie = new JButton("Salarié");
	private JButton btSponsor = new JButton("Sponsor");
	private JPanel panelAjoutSponsor = new JPanel();
	
	// nous permet de savoir si on est sur le bouton salarie ou le bouton sponsor
	private boolean isSalarie;
	
	//utilisateurSponsor - 9 elements à remplir
	//utilisateur
	private JTextField txtUserSpons = new JTextField();
	private JTextField txtEmailSpons = new JTextField();
	private JPasswordField txtMdpSpons = new JPasswordField();
	private JComboBox<String> cbxDroitsSpons = new JComboBox<String>();
	//sponsor
	private JTextField txtSocieteSpons = new JTextField();
	private JTextField txtImgurlSpons = new JTextField();
	private JTextField txtBudgetSpons = new JTextField();
	private JTextField txtTelSpons = new JTextField();
	private JTextField txtLienSpons = new JTextField();
	
	//utilisateurSalarie - 11 elements à remplir
	//utilisateur
	private JTextField txtUsernameSalarie = new JTextField(); 
	private JTextField txtEmailSalarie = new JTextField(); 
	private JPasswordField txtMdpSalarie = new JPasswordField(); 
	private JComboBox<String> cbxDroitsSalarie = new JComboBox<String>();
	//salarie
	private JTextField txtNomSalarie = new JTextField();
	private JTextField txtPrenomSalarie = new JTextField();
	private JComboBox<String> cbxSexeSalarie = new JComboBox<String>();
	private JTextField txtTelSalarie = new JTextField();
	private JTextField txtAdresseSalarie = new JTextField();
	private JComboBox<String> cbxQuotientFamSalarie = new JComboBox<String>();
	private JComboBox<String> cbxServiceSalarie = new JComboBox<String>();
	
	//Construction de la partie Tableau Salarie
	private JPanel panelListerSalarie = new JPanel();
	private JTable uneTableSalarie;
	private JScrollPane uneScrollSalarie;
	private Tableau unTableauSalarie;
	
	//Construction de la partie Tableau Sponsor
	private JPanel panelListerSponsor = new JPanel();
	private JTable uneTableSponsor;
	private JScrollPane uneScrollSponsor;
	private Tableau unTableauSponsor;
	
	//bouton filtrer
	private JButton btFiltrer = new JButton("Filtrer :");
	private JTextField txtFiltrer = new JTextField();
	
	
	public VueUtilisateur() {
		this.setBounds(100, 100, Main.getWidth(), Main.getHeight());
		this.setTitle("Gestion des utilisateurs");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		this.getContentPane().setBackground(new Color (206,214, 224  ));
		
		//installer le bouton retour 
		this.btRetour.setBounds(Main.getWidth() -170, Main.getHeight() -80, 140, 30);
		getContentPane().add(this.btRetour); 
		this.btRetour.addActionListener(this);
		
		//construction du panel Ajout Salarie
		this.remplirPanelAjoutSalarie();
		getContentPane().add(this.panelAjoutSalarie);
		
		//construction du panel Ajout Sponsor
		this.remplirPanelAjoutSponsor();
		this.add(panelAjoutSponsor);
		
		// rendre visible les panels pour mieux comprendre
		this.panelAjoutSponsor.setBorder(LineBorder.createBlackLineBorder());
		this.panelAjoutSalarie.setBorder(LineBorder.createBlackLineBorder());
		
		this.btAnnuler.setBounds(50, 360, 140, 25);
		this.add(this.btAnnuler);
		this.btEnregistrer.setBounds(200, 360, 140, 25);
		this.add(this.btEnregistrer); 
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);

		// boutons pour toggle salarie/sponsor
		this.btSalarie.setBounds(50 , 25, 140, 25);
		this.add(btSalarie);
		this.btSalarie.addActionListener(this);
		
		this.btSponsor.setBounds(200 , 25, 140, 25);
		this.add(btSponsor);
		this.btSponsor.addActionListener(this);
		
		// Construire le panel Lister Salarie, puis le remplir avec une Scroll
		this.initPanelListerSalarie();
		this.remplirPanelListerSalarie("");
		
		// Construire le panel Lister Sponsor, puis le remplir avec une Scroll
		this.initPanelListerSponsor();
		this.remplirPanelListerSponsor("");
		
		//rendre les TableSalarie et TableSponsor mouse écoutables
		this.uneTableSalarie.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
				if (e.getClickCount() >= 2) {
					// supprimer
					int ligne = uneTableSalarie.getSelectedRow();
					System.out.println(ligne);
					int idUtilisateurSalarie = Integer.parseInt(unTableauSalarie.getValueAt(ligne, 0).toString()); 
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce Salarié ?", "Suppression", JOptionPane.YES_NO_OPTION); 
					if (retour == 0) {
						//suppression dans la base 
						Main.deleteUtilisateurSalarie(idUtilisateurSalarie);
						//suppression dans la table d'affichage 
						//unTableauSalarie.deleteLigne(ligne);
						// actualiser/regénérer les données du panelListerSalarie
						refreshPanelListerSalarie("");
						JOptionPane.showMessageDialog(null, "Suppression réussie");
					}
				} else if (e.getClickCount() == 1) {
					//modifier
					int ligne = uneTableSalarie.getSelectedRow();
					// utilisateur
					txtUsernameSalarie.setText(unTableauSalarie.getValueAt(ligne, 1).toString());
					txtMdpSalarie.setText(unTableauSalarie.getValueAt(ligne, 2).toString());
					txtEmailSalarie.setText(unTableauSalarie.getValueAt(ligne, 3).toString());
					cbxDroitsSalarie.setSelectedItem(unTableauSalarie.getValueAt(ligne, 4).toString());
					// salarie
					txtNomSalarie.setText(unTableauSalarie.getValueAt(ligne, 5).toString());
					txtPrenomSalarie.setText(unTableauSalarie.getValueAt(ligne, 6).toString());
					txtTelSalarie.setText(unTableauSalarie.getValueAt(ligne, 7).toString());
					txtAdresseSalarie.setText(unTableauSalarie.getValueAt(ligne, 8).toString());
					cbxQuotientFamSalarie.setSelectedItem(unTableauSalarie.getValueAt(ligne, 9).toString());
					cbxServiceSalarie.setSelectedItem(unTableauSalarie.getValueAt(ligne, 10).toString());
					cbxSexeSalarie.setSelectedItem(unTableauSalarie.getValueAt(ligne, 11).toString());
					
					btEnregistrer.setText("Modifier");
				}
				
			}
		});
		this.uneTableSponsor.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
				if (e.getClickCount() >= 2) {
					// supprimer
					int ligne = uneTableSponsor.getSelectedRow();
					System.out.println(ligne);
					int idUtilisateurSponsor = Integer.parseInt(unTableauSponsor.getValueAt(ligne, 0).toString()); 
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce Sponsor ?", "Suppression", JOptionPane.YES_NO_OPTION); 
					if (retour == 0) {
						//suppression dans la base 
						Main.deleteUtilisateurSponsor(idUtilisateurSponsor);
						//suppression dans la table d'affichage 
						//unTableauSponsor.deleteLigne(ligne);
						// actualiser/regénérer les données du panelListerSponsor
						refreshPanelListerSponsor("");
						JOptionPane.showMessageDialog(null, "Suppression réussie");
					}
				} else if (e.getClickCount() == 1) {
					//modifier
					int ligne = uneTableSponsor.getSelectedRow();
					// utilisateur
					txtUserSpons.setText(unTableauSponsor.getValueAt(ligne, 1).toString());
					txtMdpSpons.setText(unTableauSponsor.getValueAt(ligne, 2).toString());
					txtEmailSpons.setText(unTableauSponsor.getValueAt(ligne, 3).toString());
					cbxDroitsSpons.setSelectedItem(unTableauSponsor.getValueAt(ligne, 4).toString());
					// sponsor
					txtSocieteSpons.setText(unTableauSponsor.getValueAt(ligne, 5).toString());
					txtImgurlSpons.setText(unTableauSponsor.getValueAt(ligne, 6).toString());
					txtBudgetSpons.setText(unTableauSponsor.getValueAt(ligne, 7).toString());
					txtTelSpons.setText(unTableauSponsor.getValueAt(ligne, 8).toString());
					txtLienSpons.setText(unTableauSponsor.getValueAt(ligne, 9).toString());
					
					btEnregistrer.setText("Modifier");
				}
			}
		});
		
		//par défaut on commence sur le panel salarie au début
		this.isSalarie = true;
		
		initBoutons();
		this.setVisible(true);
	}
	
	public void remplirPanelAjoutSalarie() {
		this.panelAjoutSalarie.setLayout(new GridLayout(11,2));
		this.panelAjoutSalarie.setBounds(40, 100, 300, 250);
		this.panelAjoutSalarie.setBackground(new Color (206,214, 224   ));
		
		this.panelAjoutSalarie.add(new JLabel("Nom d'utilisateur :"));
		this.panelAjoutSalarie.add(this.txtUsernameSalarie);
		this.panelAjoutSalarie.add(new JLabel("Mot de passe :"));
		this.panelAjoutSalarie.add(this.txtMdpSalarie);
		this.panelAjoutSalarie.add(new JLabel("Email :"));
		this.panelAjoutSalarie.add(this.txtEmailSalarie);
		this.panelAjoutSalarie.add(new JLabel("Droits :"));
		this.panelAjoutSalarie.add(this.cbxDroitsSalarie);
		this.panelAjoutSalarie.add(new JLabel("Nom :"));
		this.panelAjoutSalarie.add(this.txtNomSalarie);
		this.panelAjoutSalarie.add(new JLabel("Prenom :"));
		this.panelAjoutSalarie.add(this.txtPrenomSalarie);
		this.panelAjoutSalarie.add(new JLabel("Téléphone :"));
		this.panelAjoutSalarie.add(this.txtTelSalarie);
		this.panelAjoutSalarie.add(new JLabel("Adresse :"));
		this.panelAjoutSalarie.add(this.txtAdresseSalarie);
		this.panelAjoutSalarie.add(new JLabel("Quotient familial :"));
		this.panelAjoutSalarie.add(this.cbxQuotientFamSalarie);
		this.panelAjoutSalarie.add(new JLabel("Service :"));
		this.panelAjoutSalarie.add(this.cbxServiceSalarie);
		this.panelAjoutSalarie.add(new JLabel("Sexe :"));
		this.panelAjoutSalarie.add(this.cbxSexeSalarie);
		
		//remplir les cbx Salarie
		this.remplircbxDroitsSalarie();
		this.remplircbxQuotientFamSalarie();
		this.remplircbxServiceSalarie();
		this.remplircbxSexeSalarie();
		
	}
	
	public void remplirPanelAjoutSponsor() {
		this.panelAjoutSponsor.setLayout(new GridLayout(9,2));
		this.panelAjoutSponsor.setBounds(40, 100, 300, 250);
		this.panelAjoutSponsor.setBackground(new Color (206,214, 224  ));
		
		this.panelAjoutSponsor.add(new JLabel("Nom d'utilisateur :"));
		this.panelAjoutSponsor.add(txtUserSpons);
		this.panelAjoutSponsor.add(new JLabel("Mot de passe :"));
		this.panelAjoutSponsor.add(txtMdpSpons);
		this.panelAjoutSponsor.add(new JLabel("Email :"));
		this.panelAjoutSponsor.add(txtEmailSpons);
		this.panelAjoutSponsor.add(new JLabel("Droits :"));
		this.panelAjoutSponsor.add(cbxDroitsSpons);
		this.panelAjoutSponsor.add(new JLabel("Nom de société :"));
		this.panelAjoutSponsor.add(txtSocieteSpons);
		this.panelAjoutSponsor.add(new JLabel("URL de l'image :"));
		this.panelAjoutSponsor.add(txtImgurlSpons);
		this.panelAjoutSponsor.add(new JLabel("Budget :"));
		this.panelAjoutSponsor.add(txtBudgetSpons);
		this.panelAjoutSponsor.add(new JLabel("Téléphone :" ));
		this.panelAjoutSponsor.add(txtTelSpons);
		this.panelAjoutSponsor.add(new JLabel("Adresse web :" ));
		this.panelAjoutSponsor.add(txtLienSpons);

		this.remplircbxDroitsSpons();
		this.panelAjoutSponsor.setVisible(false);
	}
	
	public void remplircbxDroitsSalarie()
	{
		//this.cbxDroitsSalarie.removeAllItems();
		this.cbxDroitsSalarie.addItem("salarie");
		this.cbxDroitsSalarie.addItem("admin");
	}
	
	public void remplircbxQuotientFamSalarie()
	{
		this.cbxQuotientFamSalarie.addItem("1");
		this.cbxQuotientFamSalarie.addItem("2");
		this.cbxQuotientFamSalarie.addItem("3");
		this.cbxQuotientFamSalarie.addItem("4");
		this.cbxQuotientFamSalarie.addItem("5");
	}
	
	public void remplircbxServiceSalarie()
	{
		this.cbxServiceSalarie.addItem("comptabilite");
		this.cbxServiceSalarie.addItem("developpeur");
		this.cbxServiceSalarie.addItem("commercial");
		this.cbxServiceSalarie.addItem("ressources_humaines");
	}
	
	public void remplircbxSexeSalarie()
	{
		this.cbxSexeSalarie.addItem("homme");
		this.cbxSexeSalarie.addItem("femme");
	}
	
	public void remplircbxDroitsSpons() {
		this.cbxDroitsSpons.addItem("sponsor");
	}
	
	public void insertUtilisateurSalarie() {
		// utilisateur
		String username = this.txtUsernameSalarie.getText();
		String mdp = new String(this.txtMdpSalarie.getPassword());
		String email = this.txtEmailSalarie.getText();
		String droits = this.cbxDroitsSalarie.getSelectedItem().toString();
		// salarie
		String nom = this.txtNomSalarie.getText();
		String prenom = this.txtPrenomSalarie.getText();
		String tel = this.txtTelSalarie.getText();
		String adresse = this.txtAdresseSalarie.getText();
		String quotient_fam = this.cbxQuotientFamSalarie.getSelectedItem().toString();
		String service = this.cbxServiceSalarie.getSelectedItem().toString();
		String sexe = this.cbxSexeSalarie.getSelectedItem().toString();
	
		try {
			if(!txtUsernameSalarie.getText().equals("") || !txtEmailSalarie.getText().equals("")) {
				Salarie unSalarie = new Salarie(username, mdp, email, droits, nom, prenom, tel, 
						adresse, quotient_fam, service, sexe);
				Main.insertUtilisateurSalarie(unSalarie);
				JOptionPane.showMessageDialog(this,"Insertion réussie !");
				// actualiser/vider les données du panelAjoutSalarie
				this.viderLesChampsSalarie();
				this.btEnregistrer.setText("Enregistrer");
				txtUsernameSalarie.setBackground(Color.WHITE);
				txtEmailSalarie.setBackground(Color.WHITE);
				// actualiser/regénérer les données du panelListerSalarie
				this.refreshPanelListerSalarie("");
			}else {
				txtUsernameSalarie.setBackground(Color.RED);
				txtEmailSalarie.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this,"Erreur d'insertion vérifier les champs !");
			}
		} catch(Exception e) {
			System.out.println("Aie " + e.getStackTrace());	
		}
	}
	
	public void insertUtilisateurSpons() {
		// utilisateur
		String username = this.txtUserSpons.getText();
		String mdp = new String(this.txtMdpSpons.getPassword());
		String email = this.txtEmailSpons.getText();
		String droits = this.cbxDroitsSpons.getSelectedItem().toString();
		// sponsor
		String societe = this.txtSocieteSpons.getText();
		String image_url = this.txtImgurlSpons.getText();
		double budget = 0;
		try {
			budget = Double.parseDouble(this.txtBudgetSpons.getText());
		} catch (NumberFormatException exp) {
			JOptionPane.showMessageDialog(this, "Erreur de saisie de montant");
			// s'il y'a une erreur de saisie de montant on arrête l'insertion
			return;
		}
		String tel = this.txtTelSpons.getText();
		String lien = this.txtLienSpons.getText();
	
		try {
			if(!txtUserSpons.getText().equals("") || !txtEmailSpons.getText().equals("")) {
				Sponsor unSponsor = new Sponsor(username, mdp, email, droits, societe, image_url, budget, 
						tel, lien);
				Main.insertUtilisateurSponsor(unSponsor);
				JOptionPane.showMessageDialog(this,"Insertion réussie !");
				// actualiser/vider les données du panelAjoutSponsor
				this.viderLesChampsSpons();
				this.btEnregistrer.setText("Enregistrer");
				txtUserSpons.setBackground(Color.WHITE);
				txtEmailSpons.setBackground(Color.WHITE);
				// actualiser/regénérer les données du panelListerSponsor
				this.refreshPanelListerSponsor("");
			} else {
				txtUserSpons.setBackground(Color.RED);
				txtEmailSpons.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this,"Erreur d'insertion vérifier les champs !");
			}
		} catch(Exception e) {
			System.out.println("Aie " + e.getStackTrace());	
		}
	}
	
	public void updateUtilisateurSalarie() {
		// utilisateur
		String username = this.txtUsernameSalarie.getText();
		String mdp = new String(this.txtMdpSalarie.getPassword());
		String email = this.txtEmailSalarie.getText();
		String droits = this.cbxDroitsSalarie.getSelectedItem().toString();
		// salarie
		String nom = this.txtNomSalarie.getText();
		String prenom = this.txtPrenomSalarie.getText();
		String tel = this.txtTelSalarie.getText();
		String adresse = this.txtAdresseSalarie.getText();
		String quotient_fam = this.cbxQuotientFamSalarie.getSelectedItem().toString();
		String service = this.cbxServiceSalarie.getSelectedItem().toString();
		String sexe = this.cbxSexeSalarie.getSelectedItem().toString();
	
		try {
			if(!txtUsernameSalarie.getText().equals("") || !txtEmailSalarie.getText().equals("")) {
				// récupération de l'idUtilisateur pour le constructeur du Salarie
				int numLigne = uneTableSalarie.getSelectedRow(); 
				int idUtilisateurSalarie = Integer.parseInt(unTableauSalarie.getValueAt(numLigne, 0).toString ());
				// update dans la base de données
				Salarie unSalarie = new Salarie(idUtilisateurSalarie, username, mdp, email, droits, nom, prenom, tel, 
						adresse, quotient_fam, service, sexe);
				Main.updateUtilisateurSalarie(unSalarie);
				JOptionPane.showMessageDialog(this,"Modification réussie !");
				// actualiser/vider les données du panelAjoutSalarie
				this.viderLesChampsSalarie();
				this.btEnregistrer.setText("Enregistrer");
				txtUserSpons.setBackground(Color.WHITE);
				txtEmailSpons.setBackground(Color.WHITE);
				// actualiser/regénérer les données du panelListerSalarie
				this.refreshPanelListerSalarie("");
			} else {
				txtUsernameSalarie.setBackground(Color.RED);
				txtEmailSalarie.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this,"Erreur de modification, vérifier les champs !");
			}
		} catch(Exception e) {
			System.out.println("Aie " + e.getStackTrace());	
		}
	}
	
	public void updateUtilisateurSpons() {
		// utilisateur
		String username = this.txtUserSpons.getText();
		String mdp = new String(this.txtMdpSpons.getPassword());
		String email = this.txtEmailSpons.getText();
		String droits = this.cbxDroitsSpons.getSelectedItem().toString();
		// sponsor
		String societe = this.txtSocieteSpons.getText();
		String image_url = this.txtImgurlSpons.getText();
		double budget = 0;
		try {
			budget = Double.parseDouble(this.txtBudgetSpons.getText());
		} catch (NumberFormatException exp) {
			JOptionPane.showMessageDialog(this, "Erreur de saisie de montant");
			// s'il y'a une erreur de saisie de montant on arrête l'insertion
			return;
		}
		String tel = this.txtTelSpons.getText();
		String lien = this.txtLienSpons.getText();
		
		try {
			if(!txtUserSpons.getText().equals("") || !txtEmailSpons.getText().equals("")) {
				// récupération de l'idUtilisateur pour le constructeur du Spons
				int numLigne = uneTableSponsor.getSelectedRow(); 
				int idUtilisateurSpons = Integer.parseInt(unTableauSponsor.getValueAt(numLigne, 0).toString ());
				// update dans la base de données
				Sponsor unSponsor = new Sponsor(idUtilisateurSpons, username, mdp, email, droits, societe, image_url, budget, 
						tel, lien);
				Main.updateUtilisateurSponsor(unSponsor);
				JOptionPane.showMessageDialog(this,"Modification réussie !");
				// actualiser/vider les données du panelAjoutSponsor
				this.viderLesChampsSpons();
				this.btEnregistrer.setText("Enregistrer");
				txtUserSpons.setBackground(Color.WHITE);
				txtEmailSpons.setBackground(Color.WHITE);
				// actualiser/regénérer les données du panelListerSponsor
				this.refreshPanelListerSponsor("");
			} else {
				txtUserSpons.setBackground(Color.RED);
				txtEmailSpons.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this,"Erreur de modification, vérifier les champs !");
			}
		} catch(Exception e) {
			System.out.println("Aie " + e.getStackTrace());	
		}
	}

	public void viderLesChampsSalarie() {
		// après insertion réussie, on vide les champs
		// utilisateur
		this.txtUsernameSalarie.setText("");
		this.txtMdpSalarie.setText("");
		this.txtEmailSalarie.setText("");
		this.cbxDroitsSalarie.setSelectedIndex(0);
		// salarie
		this.txtNomSalarie.setText("");
		this.txtPrenomSalarie.setText("");
		this.txtTelSalarie.setText("");
		this.txtAdresseSalarie.setText("");
		this.cbxQuotientFamSalarie.setSelectedIndex(0);
		this.cbxServiceSalarie.setSelectedIndex(0);
		this.cbxSexeSalarie.setSelectedIndex(0);
	}
	
	public void viderLesChampsSpons() {
		// après insertion réussie, on vide les champs
		// utilisateur
		this.txtUserSpons.setText("");
		this.txtMdpSpons.setText("");
		this.txtEmailSpons.setText("");
		this.cbxDroitsSpons.setSelectedIndex(0);
		// sponsor
		this.txtSocieteSpons.setText("");
		this.txtImgurlSpons.setText("");
		this.txtBudgetSpons.setText("");
		this.txtTelSpons.setText("");
		this.txtLienSpons.setText("");
	}
	
	private void initBoutons() {
		Main.styleBoutonDark(this.btAnnuler);
		Main.styleBoutonDark(this.btEnregistrer);
		Main.styleBoutonBleu(this.btRetour);
		Main.styleBoutonDark(this.btFiltrer);
		
		this.btFiltrer.setBounds(Main.getWidth() /2 - 200, 20, 100, 20);
		this.add(btFiltrer);
		this.btFiltrer.addActionListener(this);
		this.txtFiltrer.setBounds(Main.getWidth() / 2 - 80, 20 , 100, 20);
		this.add(txtFiltrer);
		
		Main.styleBoutonDark(this.btSalarie);
		Main.styleBoutonDark(this.btSponsor);
	}
	
	public void initPanelListerSalarie() {
		//construire le panel Lister Salarie
		this.panelListerSalarie.setBackground(new Color (206,214, 224  ));
		this.panelListerSalarie.setLayout(null);
		this.panelListerSalarie.setBounds(365, 80, this.getWidth() - 400, this.getHeight() - 170);

		this.add(this.panelListerSalarie);
	}
	
	public void remplirPanelListerSalarie(String mot) {
		//remplir le panel Lister Salarie
		this.panelListerSalarie.removeAll();
		String entetes [] = { "Id Utilisateur", "Nom d'utilisateur", "Mot de passe", "Email", "Droits", // utilisateur
			"Nom", "Prénom", "Téléphone", "Adresse", "Quotient Familial", "Service", "Sexe" }; // sponsor
		Object donnees [][] = this.getDonneesSalarie (mot) ;
		
		this.unTableauSalarie = new Tableau (donnees, entetes); 
		this.uneTableSalarie = new JTable(this.unTableauSalarie); 
		
		// rendre les colonnes + petites
		this.uneTableSalarie.getColumnModel().getColumn(0).setMaxWidth(50);
		this.uneTableSalarie.getColumnModel().getColumn(4).setMaxWidth(50);
		this.uneTableSalarie.getColumnModel().getColumn(9).setMaxWidth(40);
		this.uneTableSalarie.getColumnModel().getColumn(11).setMaxWidth(50);
		// la colonne appréciation doit être + large pour bien afficher le texte
		//https://stackoverflow.com/questions/953972/java-jtable-setting-column-width
		this.uneTableSalarie.getColumnModel().getColumn(3).setMinWidth(150);
		
		this.uneScrollSalarie = new JScrollPane(this.uneTableSalarie); 
		//this.panelListerSalarie.setBounds(350, 80, 530, 300);
		this.uneScrollSalarie.setBounds(20, 20, this.panelListerSalarie.getWidth() - 40, this.panelListerSalarie.getHeight() - 40);
		
		Main.styleTableau(this.uneTableSalarie);
		this.panelListerSalarie.add(this.uneScrollSalarie);
	}
	
	public void refreshPanelListerSalarie(String mot) {
		//remplir le panel Lister Salarie
		this.panelListerSalarie.removeAll();
		String entetes [] = { "Id Utilisateur", "Nom d'utilisateur", "Mot de passe", "Email", "Droits", // utilisateur
			"Nom", "Prénom", "Téléphone", "Adresse", "Quotient Familial", "Service", "Sexe" }; // sponsor
		Object donnees [][] = this.getDonneesSalarie (mot) ;
		
		this.unTableauSalarie = new Tableau (donnees, entetes);
		
		// pour refresh la JTable tout en gardant les mêmes adresses mémoire
		// de la JTable et Jscroll (afin de conserver les actionListener)
		// on update uniquement la JTable sans la réinstancier
		this.uneTableSalarie.removeAll();
		// deep copy the JTable
		// https://stackoverflow.com/a/38798102
		JTable laNouvelleTable = new JTable(this.unTableauSalarie);
		this.uneTableSalarie.setModel(laNouvelleTable.getModel());
		
		// instanciation d'une nouvelle JScrollPane
		this.uneScrollSalarie = new JScrollPane(this.uneTableSalarie); 
		//this.panelListerSalarie.setBounds(350, 80, 530, 300);
		this.uneScrollSalarie.setBounds(20, 20, this.panelListerSalarie.getWidth() - 40, this.panelListerSalarie.getHeight() - 40);
		this.panelListerSalarie.add(this.uneScrollSalarie);
	}
	
	public Object [] [] getDonneesSalarie(String mot) {
		//TODO
		//recuperer les utilisateursSalariés de la bdd 
		ArrayList<Salarie> lesUtilisateursSalaries = Main.selectAllUtilisateursSalaries(mot);
		
		//transformation des utilisateursSalariés en matrice de données 
		Object donnees [][] = new Object [lesUtilisateursSalaries.size()][12];
		int i = 0;
		for (Salarie unSalarie : lesUtilisateursSalaries) {
			// utilisateur
			donnees[i][0] = unSalarie.getIdUtilisateur()+""; 
			donnees[i][1] = unSalarie.getUsername();
			donnees[i][2] = unSalarie.getPassword();
			donnees[i][3] = unSalarie.getEmail();
			donnees[i][4] = unSalarie.getDroits();
			// salarie
			donnees[i][5] = unSalarie.getNom(); 
			donnees[i][6] = unSalarie.getPrenom();
			donnees[i][7] = unSalarie.getTel();
			donnees[i][8] = unSalarie.getAdresse();
			donnees[i][9] = unSalarie.getQuotient_fam();
			donnees[i][10] = unSalarie.getService();
			donnees[i][11] = unSalarie.getSexe();
			
			i++;
		}
				
		return donnees;
	}
	
	public void initPanelListerSponsor() {
		//TODO
		//construire le panel Lister Sponsor
		this.panelListerSponsor.setBackground(new Color (206,214, 224  ));
		this.panelListerSponsor.setLayout(null);
		this.panelListerSponsor.setBounds(350, 80, this.getWidth() - 400, this.getHeight() - 170);

		this.add(this.panelListerSponsor);
	}
	
	public void remplirPanelListerSponsor(String mot) {
		//remplir le panel Lister Sponsor
		this.panelListerSponsor.removeAll();
		String entetes [] = { "Id Utilisateur", "Nom d'utilisateur", "Mot de passe", "Email", "Droits", // utilisateur
			"Société", "Image (URL)", "Budget", "Téléphone", "Lien" }; // sponsor
		Object donnees [][] = this.getDonneesSponsor (mot) ;
		
		this.unTableauSponsor = new Tableau (donnees, entetes); 
		this.uneTableSponsor = new JTable(this.unTableauSponsor); 
		
		this.uneScrollSponsor = new JScrollPane(this.uneTableSponsor); 
		//this.panelListerSponsor.setBounds(350, 80, 530, 300);
		this.uneScrollSponsor.setBounds(20, 20, this.panelListerSponsor.getWidth() - 40, this.panelListerSponsor.getHeight() - 40);
		this.panelListerSponsor.add(this.uneScrollSponsor);
	}
	
	public void refreshPanelListerSponsor(String mot) {
		//remplir le panel Lister Sponsor
		this.panelListerSponsor.removeAll();
		String entetes [] = { "Id Utilisateur", "Nom d'utilisateur", "Mot de passe", "Email", "Droits", // utilisateur
			"Société", "Image (URL)", "Budget", "Téléphone", "Lien" }; // sponsor
		Object donnees [][] = this.getDonneesSponsor (mot) ;
		
		this.unTableauSponsor = new Tableau (donnees, entetes); 
		
		// pour refresh la JTable tout en gardant les mêmes adresses mémoire
		// de la JTable et Jscroll (afin de conserver les actionListener)
		// on update uniquement la JTable sans la réinstancier
		this.uneTableSponsor.removeAll();
		// deep copy the JTable
		// https://stackoverflow.com/a/38798102
		JTable laNouvelleTable = new JTable(this.unTableauSponsor);
		this.uneTableSponsor.setModel(laNouvelleTable.getModel());
		
		// instanciation d'une nouvelle JScrollPane
		this.uneScrollSponsor = new JScrollPane(this.uneTableSponsor); 
		//this.panelListerSponsor.setBounds(350, 80, 530, 300);
		this.uneScrollSponsor.setBounds(20, 20, this.panelListerSponsor.getWidth() - 40, this.panelListerSponsor.getHeight() - 40);
		this.panelListerSponsor.add(this.uneScrollSponsor);
	}
	
	public Object [] [] getDonneesSponsor(String mot) {
		//TODO
		//recuperer les utilisateursSalariés de la bdd 
		ArrayList<Sponsor> lesUtilisateursSponsors = Main.selectAllUtilisateursSponsors(mot);
		
		//transofrmation des utilisateursSalariés en matrice de données 
		Object donnees [][] = new Object [lesUtilisateursSponsors.size()][10];
		int i = 0;
		for (Sponsor unSponsor : lesUtilisateursSponsors) {
			// utilisateur
			donnees[i][0] = unSponsor.getIdUtilisateur()+""; 
			donnees[i][1] = unSponsor.getUsername();
			donnees[i][2] = unSponsor.getPassword();
			donnees[i][3] = unSponsor.getEmail();
			donnees[i][4] = unSponsor.getDroits();
			// sponsor
			donnees[i][5] = unSponsor.getSociete();
			donnees[i][6] = unSponsor.getImage_url();
			donnees[i][7] = unSponsor.getBudget();
			donnees[i][8] = unSponsor.getTel();
			donnees[i][9] = unSponsor.getLien();
			
			i++;
		}
				
		return donnees;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btRetour) {
			this.dispose();
			Main.rendreVisible(true);
		
		}else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Enregistrer")) {
			// le bouton Enregistrer est enregistrer, donc on fait un insert d'un nouvel utilisateur
			//System.out.println(this.isSalarie);
			if (this.isSalarie == true) {
				//System.out.println("sa");
				this.insertUtilisateurSalarie();
			}
			else {
				//System.out.println("sp");
				this.insertUtilisateurSpons();
			}	
		} else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Modifier")) {
			// le bouton Enregistrer est devenu le bouton modifier, donc on fait un update
			//System.out.println(this.isSalarie);
			if (this.isSalarie == true) {
				//System.out.println("sa");
				this.updateUtilisateurSalarie();
			}
			else {
				//System.out.println("sp");
				this.updateUtilisateurSpons();
			}
		
		}else if (e.getSource() == this.btAnnuler) {
			//System.out.println(this.isSalarie);
			if (this.isSalarie == true) {
				this.viderLesChampsSalarie();
				this.btEnregistrer.setText("Enregistrer"); // après avoir vidé tous les champs, on reset aussi le bouton Enregistrer : le bouton Modifier redevient Enregistrer
				
			}
			else {
				this.viderLesChampsSpons();
				this.btEnregistrer.setText("Enregistrer"); // après avoir vidé tous les champs, on reset aussi le bouton Enregistrer : le bouton Modifier redevient Enregistrer
				
			}

		}else if(e.getSource() == this.btSponsor) {
			this.isSalarie = false;
			this.panelAjoutSalarie.setVisible(false);
			this.panelAjoutSponsor.setVisible(true);
			this.panelListerSalarie.setVisible(false);
			this.panelListerSponsor.setVisible(true);
		}else if(e.getSource() == this.btSalarie) {
			this.isSalarie = true;
			this.panelAjoutSalarie.setVisible(true);
			this.panelAjoutSponsor.setVisible(false);
			this.panelListerSalarie.setVisible(true);
			this.panelListerSponsor.setVisible(false);
		}else if (e.getSource() == this.btFiltrer)
		{
			if (this.isSalarie == true) {
				this.refreshPanelListerSalarie(this.txtFiltrer.getText());
			} else {
				this.refreshPanelListerSponsor(this.txtFiltrer.getText());
			}
		}
	}
	
	/* ancienne methode pour ajouter un label puis un txtfield
	    JLabel label = new JLabel("Nom utilisateur :");
		label.setBounds(0, 0, 150, 25);
		this.panelAjoutSalarie.add(label); 
		txtUsernameSalarie.setBounds(150, 0, 150, 25);
		this.panelAjoutSalarie.add(this.txtUsernameSalarie);
	 */
}
