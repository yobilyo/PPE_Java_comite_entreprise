package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controleur.Main;

public class VueTresorerie extends JFrame implements ActionListener{
	
	private JPanel panelTresorerie = new JPanel();
	private JButton btRetour = new JButton("Retour");
	private JLabel txtTresorerie = new JLabel();
	
	private float fonds;
	
	public VueTresorerie() {
		
		this.setBounds(100, 100, Main.getWidth(), Main.getHeight());
		this.setTitle("Trésorerie");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.fonds = Main.selectFonds();
		this.getContentPane().setBackground(new Color (206,214, 224   ));
		//installer le bouton retour 
		
		this.btRetour.setBounds(Main.getWidth() -170, Main.getHeight() -80, 140, 30);
		this.add(this.btRetour); 
		this.btRetour.addActionListener(this);
		

		this.panelTresorerie.setBounds(40, 100, 400, 350);
		this.panelTresorerie.setBackground(new Color (206,214, 224  ));
		this.panelTresorerie.setLayout(new GridLayout(7,2));
		this.panelTresorerie.add(new JLabel()); 
		this.panelTresorerie.add(this.txtTresorerie);
		
		this.panelTresorerie.setVisible(true);
		this.add(this.panelTresorerie);
		
		this.panelTresorerie.setBorder(new LineBorder(Color.black));
		
		this.setVisible(true);
		this.txtTresorerie.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		this.txtTresorerie.setText("Notre tresorerie dispose actuellement de : " + this.fonds +"");
		ImageIcon uneImage = new ImageIcon("src/lib/images/pages/tresorerie_1.png");
		JLabel monLogo = new JLabel(uneImage); 
		//monLogo.setBounds(20, 60, 300, 150); 
		this.panelTresorerie.add(monLogo);
		
	}

	//"Notre tresorerie dispose actuellement de : " + fonds +""
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btRetour) {
			this.dispose();
			Main.rendreVisible(true);
	
	}
	}
}

	


