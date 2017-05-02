package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestion.Ctrl;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import java.awt.Color;
/**
 * Classe dï¿½finissant la vue de modification d'un mï¿½dicament
 * @author xavier
 *
 */
public class MedicineChange extends JDialog implements MyView{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnValider;
	public static JButton btnAnnuler;
	private static JTextField txtNom;
	private static JComboBox<String> cbxFormes;
	private static JComboBox<String> cbxEffets;
	private static JTextField txtBrevet;
	/**
	 * Mï¿½thode statique permettant d'obtenir le contenu du champ texte nom
	 * @return le contenu du champ texte nom
	 */
	public static String getTxtName(){
		return txtNom.getText();
	}
	/**
	 * Mï¿½thode statique permettant d'obtenir la sï¿½lection de la liste dï¿½roulante forme
	 * @return la selection de la liste dï¿½roulante forme
	 */
	public static String getTxtForm(){
		return (String) cbxFormes.getSelectedItem();
	}
	
	public static String getTxtEffets(){
		return (String) cbxEffets.getSelectedItem();
	}
	/**
	 * Mï¿½thode statique permettant d'obtenir le contenu du champ texte date brevet
	 * @return le contenu du champ texte date brevet
	 */
	public static String getTxtPatentDate(){
		if(txtBrevet.getText().equals(""))
			return null;
		return txtBrevet.getText();
	}
	
	/**
	 * Create the dialog.
	 * @param forms les formes ï¿½ intï¿½grer dans la liste dï¿½roulante
	 * @param medicine le dï¿½tail du mï¿½dicament ï¿½ modifier
	 */
	public MedicineChange(String[] forms,String[] effets, String[] medicine) {
		setTitle("M\u00E9dicament - Modifier");
		setModal(true);
		setBounds(100, 100, 571, 412);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom.setBounds(132, 39, 50, 14);
		contentPanel.add(lblNom);
		
		txtNom = new JTextField();
		txtNom.setBackground(Color.WHITE);
		txtNom.setEnabled(false);
		txtNom.setBounds(189, 36, 192, 20);
		contentPanel.add(txtNom);
		txtNom.setColumns(10);
		txtNom.setText(medicine[0]);
		
		JLabel lblForme = new JLabel("Forme :");
		lblForme.setHorizontalAlignment(SwingConstants.RIGHT);
		lblForme.setBounds(112, 111, 70, 14);
		contentPanel.add(lblForme);
		
		cbxFormes = new JComboBox<String>(forms);
		cbxFormes.setBounds(189, 108, 192, 20);
		contentPanel.add(cbxFormes);
		cbxFormes.setSelectedItem(medicine[1]);
		
		JLabel lblDateBrevet = new JLabel("Date brevet :");
		lblDateBrevet.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateBrevet.setBounds(102, 80, 80, 14);
		contentPanel.add(lblDateBrevet);
		
		txtBrevet = new JTextField();
		txtBrevet.setBounds(189, 77, 192, 20);
		contentPanel.add(txtBrevet);
		txtBrevet.setColumns(10);
		txtBrevet.setText(medicine[2]);
		
		JLabel effet1 = new JLabel("Effet indésirable :");
		effet1.setHorizontalAlignment(SwingConstants.RIGHT);
		effet1.setBounds(58, 147, 124, 14);
		contentPanel.add(effet1);
		
		cbxEffets = new JComboBox<String>(effets);
		cbxEffets.setBounds(190, 144, 192, 20);
		contentPanel.add(cbxEffets);
		cbxEffets.setSelectedItem(medicine[3]);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnValider = new JButton("Valider");
				buttonPane.add(btnValider);
				getRootPane().setDefaultButton(btnValider);
			}
			{
				btnAnnuler = new JButton("Annuler");
				btnAnnuler.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				buttonPane.add(btnAnnuler);
			}
		}
	}


	@Override
	public void assignListener(Ctrl ctrl) {
		this.btnValider.setActionCommand("MedicineChange_valider");
		this.btnValider.addActionListener(ctrl);
	}
}
