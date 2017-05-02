package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestion.Ctrl;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
/**
 * Classe d�finissant la vue d'accueil des m�dicaments
 * @author xavier
 *
 */
public class MedicineHome extends JFrame implements MyView{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAjout;
	private JButton btnRechercherModifier;
	private JPanel panel;


	/**
	 * Create the frame.
	 */
	public MedicineHome() {
		setTitle("Accueil");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 483, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gestion des médicaments");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(115, 34, 253, 23);
		contentPane.add(lblNewLabel);
		
		btnAjout = new JButton("Ajouter");
		btnAjout.setBackground(Color.WHITE);
		btnAjout.setBounds(115, 105, 253, 23);
		contentPane.add(btnAjout);
		
		btnRechercherModifier = new JButton("Modifier");
		btnRechercherModifier.setBackground(Color.WHITE);
		btnRechercherModifier.setBounds(115, 181, 253, 23);
		contentPane.add(btnRechercherModifier);
		
		JButton btnFermer = new JButton("Fermer");
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFermer.setBounds(369, 254, 89, 23);
		contentPane.add(btnFermer);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 102, 153));
		panel.setBounds(0, 0, 467, 298);
		contentPane.add(panel);
		
		
	}


	@Override
	public void assignListener(Ctrl ctrl) {
		this.btnAjout.setActionCommand("MedicineHome_ajout");
		this.btnAjout.addActionListener(ctrl);
		this.btnRechercherModifier.setActionCommand("MedicineHome_rechercherModifier");
		this.btnRechercherModifier.addActionListener(ctrl);
		
	}
}
