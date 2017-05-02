package view;

import javax.swing.JFrame;
import gestion.Ctrl;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;


public class MedicineAdd extends JFrame implements MyView{

	
	private static JTextField txtNom;
	private static JTextField txtBrevet;
	private JButton btnAnnuler;
	private JButton btnValider;
	private JButton btnFermer;
	private static JComboBox<String>cbxFormes;
	private static JComboBox<String>cbxEffets;

	// r�nitialise les champs
		public static void init(){
			txtBrevet.setText("");
			txtNom.setText("");
		}
	 //obtenir le contenu du champ texte nom

		public static String getTxtName(){
			return txtNom.getText();
		}
		
		// obtenir la s�lection de la liste d�roulante formes
		
		public static String getTxtForm(){
			return (String) cbxFormes.getSelectedItem();
		}
		
		public static String getTxtEffets(){
			return (String) cbxEffets.getSelectedItem();
		}
		
		//obtenir le contenu du champ texte date brevet
		
		public static String getTxtPatentDate(){
			if(txtBrevet.getText().equals(""))
				return null;
			return txtBrevet.getText();
		}
		
		 //placer le curseur dans le champ texte nom
		 
		public static void focusTxtName(){
			MedicineAdd.txtNom.requestFocus();
		}

	
	public MedicineAdd(String[]forms,String[]effets) {
		getContentPane().setBackground(new Color(0, 102, 153));
		getContentPane().setLayout(null);
		
		txtNom = new JTextField();
		txtNom.setBounds(52, 88, 129, 20);
		getContentPane().add(txtNom);
		txtNom.setColumns(10);
		
		JLabel lblNom = new JLabel("NOM :");
		lblNom.setForeground(Color.WHITE);
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNom.setBounds(10, 89, 46, 14);
		getContentPane().add(lblNom);
		
		txtBrevet = new JTextField();
		txtBrevet.setBounds(290, 88, 178, 20);
		getContentPane().add(txtBrevet);
		txtBrevet.setColumns(10);
		
		JLabel lblDateBrevet = new JLabel("DATE BREVET :");
		lblDateBrevet.setForeground(Color.WHITE);
		lblDateBrevet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDateBrevet.setBounds(191, 89, 101, 14);
		getContentPane().add(lblDateBrevet);
		
		JLabel lblForme = new JLabel("FORME :");
		lblForme.setForeground(Color.WHITE);
		lblForme.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblForme.setBounds(10, 165, 66, 14);
		getContentPane().add(lblForme);
		
		cbxFormes = new JComboBox<String>(forms);
		cbxFormes.setBounds(64, 164, 117, 20);
		getContentPane().add(cbxFormes);
		
		JLabel lblEffetIndesirable = new JLabel("EFFET INDESIRABLE :");
		lblEffetIndesirable.setForeground(Color.WHITE);
		lblEffetIndesirable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEffetIndesirable.setBounds(191, 165, 148, 14);
		getContentPane().add(lblEffetIndesirable);
		
		cbxEffets = new JComboBox<String>(effets);
		cbxEffets.setBounds(328, 164, 111, 20);
		getContentPane().add(cbxEffets);
		
		
		setBounds(100, 100, 513, 342);
		

		JLabel lblAjouterUnMdicament = new JLabel("AJOUTER UN MEDICAMENT");
		lblAjouterUnMdicament.setForeground(Color.WHITE);
		lblAjouterUnMdicament.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAjouterUnMdicament.setBounds(148, 11, 224, 14);
		getContentPane().add(lblAjouterUnMdicament);
		
		btnValider = new JButton("VALIDER");
		btnValider.setBackground(Color.WHITE);
		btnValider.setBounds(286, 230, 89, 23);
		getContentPane().add(btnValider);
		
		btnAnnuler = new JButton("ANNULER");
		btnAnnuler.setBackground(Color.WHITE);
		btnAnnuler.setBounds(379, 230, 89, 23);
		getContentPane().add(btnAnnuler);
		
		btnFermer = new JButton("FERMER");
		btnFermer.setBackground(Color.WHITE);
		btnFermer.setBounds(286, 264, 182, 23);
		getContentPane().add(btnFermer);
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					dispose();
				
			}
		});
	

		
	
	}
	public void assignListener(Ctrl ctrl) {
		this.btnValider.setActionCommand("MedicineAdd_valider");
		this.btnValider.addActionListener(ctrl);
		this.btnAnnuler.setActionCommand("MedicineAdd_annuler");
		this.btnAnnuler.addActionListener(ctrl);
		
	}

}
