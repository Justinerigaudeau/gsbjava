package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestion.Ctrl;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

/**
 * Classe d�finissant la vue d'accueil des m�dicaments
 * @author xavier
 *
 */
public class EffetAdd extends JFrame implements MyView{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JRadioButton btnGrade1;
	private static JRadioButton btnGrade2;
	private static JRadioButton btnGrade3;
	private static JRadioButton btnGrade4;
	private JButton btnValider;
	private JButton btnAnnuler;
	private static JTextField txtNom;


	// rénitialise les champs
	public static void init(){
		txtNom.setText("");
	}
	 //obtenir le contenu du champ texte nom

	public static String getTxtName(){
		return txtNom.getText();
	}
	

	 //placer le curseur dans le champ texte nom
	 
	public static void focusTxtName(){
		EffetAdd.txtNom.requestFocus();
	}
	
	public EffetAdd() {
		setTitle("Ajouter médicament");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 555, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNom = new JTextField();
		txtNom.setBounds(87, 82, 187, 26);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		

		
		JLabel lblEffet = new JLabel("Effet :");
		lblEffet.setForeground(new Color(255, 255, 255));
		lblEffet.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblEffet.setBounds(29, 87, 61, 16);
		contentPane.add(lblEffet);
		
		JLabel lblNomF = new JLabel("Definir un grade");
		lblNomF.setForeground(Color.WHITE);
		lblNomF.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomF.setBounds(207, 23, 195, 16);
		contentPane.add(lblNomF);
		
		btnGrade1 = new JRadioButton("Grade 1");
		btnGrade1.setBackground(new Color(0, 102, 153));
		btnGrade1.setForeground(new Color(255, 255, 255));
		btnGrade1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnGrade1.setBounds(87, 125, 141, 23);
		contentPane.add(btnGrade1);
		
		btnGrade2 = new JRadioButton("Grade 2");
		btnGrade2.setBackground(new Color(0, 102, 153));
		btnGrade2.setForeground(new Color(255, 255, 255));
		btnGrade2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnGrade2.setBounds(87, 160, 141, 23);
		contentPane.add(btnGrade2);
		
		btnGrade3 = new JRadioButton("Grade 3");
		btnGrade3.setBackground(new Color(0, 102, 153));
		btnGrade3.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnGrade3.setForeground(new Color(255, 255, 255));
		btnGrade3.setBounds(87, 195, 141, 23);
		contentPane.add(btnGrade3);
		
		btnGrade4 = new JRadioButton("Grade 4");
		btnGrade4.setBackground(new Color(0, 102, 153));
		btnGrade4.setForeground(new Color(255, 255, 255));
		btnGrade4.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnGrade4.setBounds(87, 230, 141, 23);
		contentPane.add(btnGrade4);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 153));
		panel.setBounds(0, 0, 543, 371);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnValider = new JButton("Valider");
		btnValider.setBounds(333, 299, 93, 23);
		panel.add(btnValider);
		btnValider.setBackground(Color.WHITE);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBackground(Color.WHITE);
		btnAnnuler.setBounds(436, 299, 99, 23);
		panel.add(btnAnnuler);
		
		JButton btnFermer = new JButton("Fermer");
		btnFermer.setBounds(333, 334, 202, 26);
		panel.add(btnFermer);
		btnFermer.setBackground(Color.WHITE);
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			});
		
		// Permet de séléctionner un seul boutton a la fois
		ButtonGroup btn = new ButtonGroup();
		btn.add(btnGrade1);
		btn.add(btnGrade2);
		btn.add(btnGrade3);
		btn.add(btnGrade4);
	
	}
	
	
	
	public static int getGradeSelectionner(){
		int grade=0;
		if(btnGrade1.isSelected()){
			grade=1;}
		if(btnGrade2.isSelected()){
			grade=2;}
		if(btnGrade3.isSelected()){
			grade=3;}
		if(btnGrade4.isSelected()){
			grade=4;}
		return grade;
	}


	@Override
	public void assignListener(Ctrl ctrl) {
		this.btnValider.setActionCommand("EffetAdd_Ajouter");
		this.btnValider.addActionListener(ctrl);
		this.btnAnnuler.setActionCommand("EffetAdd_Annuler");
		this.btnAnnuler.addActionListener(ctrl);
		
	}
}