package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestion.Ctrl;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;


public class MainView extends JFrame implements MyView{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnManuel;
	private JButton btnAjouterUnEffet;
	private JLabel lblNewLabel;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Cr�ation du contr�leur
					Ctrl ctrl = new Ctrl();
					//Cr�ation de la vue g�n�rale
					MainView frame = new MainView();
					//Assignation d'un observateur sur cette vue
					frame.assignListener(ctrl);
					//Affichage de la vue
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 * Create the frame.
	 */
	public MainView() {
		setTitle("MedocLab - Accueil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 377);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnManuel = new JButton("Gestion des m\u00E9dicaments");
		btnManuel.setForeground(new Color(0, 0, 0));
		btnManuel.setBackground(Color.WHITE);
		btnManuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnManuel.setBounds(48, 156, 470, 29);
		contentPane.add(btnManuel);
		
			
			JButton btnFermer = new JButton("Quitter");
			btnFermer.setBounds(430, 282, 88, 29);
			contentPane.add(btnFermer);
			btnFermer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		
		btnAjouterUnEffet = new JButton("Ajouter un effet");
		btnAjouterUnEffet.setBackground(Color.WHITE);
		btnAjouterUnEffet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAjouterUnEffet.setBounds(48, 214, 470, 29);
		contentPane.add(btnAjouterUnEffet);
		
		JPanel panel = new JPanel();
		panel.setForeground(UIManager.getColor("Button.background"));
		panel.setBorder(null);
		panel.setBackground(new Color(0, 102, 153));
		panel.setBounds(0, 0, 553, 339);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\rigaudeau\\Downloads\\logo.png"));
		lblNewLabel.setBounds(10, 11, 233, 112);
		panel.add(lblNewLabel);}
		

	@Override
	public void assignListener(Ctrl ctrl) {
		this.btnManuel.setActionCommand("MainView_manuel");
		this.btnManuel.addActionListener(ctrl);
		this.btnAjouterUnEffet.setActionCommand("MainView_addEffet");
		this.btnAjouterUnEffet.addActionListener(ctrl);
	}
}
