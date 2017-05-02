package gestion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import library.DatesConverter;
import library.Persistence;
import metier.Effets;
import metier.Form;
import metier.Medicine;
import view.EffetAdd;
import view.MedicineAdd;
import view.MedicineChange;
import view.MedicineHome;
import view.MedicineSearch;

public class  Ctrl implements ActionListener, MouseListener{
	
	/**
	 * Constructeur de la classe Ctrl
	 * Ce constructeur permet, en plus de cr√©er une instance de Ctrl, de cr√©er tous les objets de l'application √† partir de la base de donn√©es
	 */
	public Ctrl(){
		//Cr√©ation des objets Forme
		String[][] dataForm = null;
		try {
			dataForm = Persistence.load("forme");
		} catch (SQLException e) {
			String message = "Erreur lors de l'echange avec la base de donnÈes. L'application a rencontrÈ l'erreur : "+e.getMessage();
			JOptionPane.showMessageDialog(null,message,"Erreur SQL",JOptionPane.ERROR_MESSAGE);
		}
		for(int i=0;i<dataForm.length;i++){
			new Form(Integer.parseInt(dataForm[i][0]),dataForm[i][1]);
		}
		//Cr√©ation des objets Effet
		String [][] dataEffet = null;
		try{
			dataEffet = Persistence.load("effetindesirable");
			} catch (SQLException e){
				String message = "Erreur lors de l'Èchange avec la base de donnÈes. L'application a rencontrÈ l'erreur: "+e.getMessage();
				JOptionPane.showMessageDialog(null,message,"Erreur SQL", JOptionPane.ERROR_MESSAGE);
			}
		for(int i=0;i<dataEffet.length;i++){
			new Effets(Integer.parseInt(dataEffet[i][0]),dataEffet[i][1],Integer.parseInt(dataEffet[i][2]));
		}
		//Cr√©ation des objets Medicine
		String[][] dataMed = null;
		try {
			dataMed = Persistence.load("medicament");
		} catch (SQLException e) {
			String message = "Erreur lors de l'echange avec la base de donnÈes. L'application a rencontrÈe l'erreur : "+e.getMessage();
			JOptionPane.showMessageDialog(null,message,"Erreur SQL",JOptionPane.ERROR_MESSAGE);
		}
		for(int i=0;i<dataMed.length;i++){
			new Medicine(dataMed[i][1],Form.getFormById(Integer.parseInt(dataMed[i][5])),DatesConverter.USStringToDate(dataMed[i][2]),Effets.getEffetsById(Integer.parseInt(dataMed[i][6])));
		}
		
		
}


	/**
	 * MÔøΩthode dÔøΩclanchÔøΩe lors de clics sur les boutons de l'application
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		//RÔøΩcupÔøΩration de l'action
		String action = evt.getActionCommand();
		//DÔøΩcoupage et analyse de celle-ci
		String details[] = action.split("_");
		//who : QUI ? Quelle vue a effectuÔøΩ l'action ?
		String who = details[0];
		//what : QUOI ? Qu'est-ce-que cette vue souhaite faire ?
		String what = details[1];
		//switch-case de traitement des diffÔøΩrents cas	
		switch(who){
		case "MainView":
			switch(what){
			case "export":
				break;
			case "manuel":
				//CrÔøΩation de la vue d'accueil des mÔøΩdicaments
				MedicineHome frame = new MedicineHome();
				//Assignation d'un observateur sur cette vue
				frame.assignListener(this);
				//Affichage de la vue
				frame.setVisible(true);
				break;
			case "addEffet":
				//CrÔøΩation de la vue d'accueil des mÔøΩdicaments
				EffetAdd frame1 = new EffetAdd();
				//Assignation d'un observateur sur cette vue
				frame1.assignListener(this);
				//Affichage de la vue
				frame1.setVisible(true);
				break;
			}
			break;
		case "MedicineHome":
			switch(what){
			case "ajout":
				//CrÔøΩation de la vue d'ajout d'un mÔøΩdicament
				MedicineAdd frame = new MedicineAdd(this.formsBox(),this.effetsBox());
				//Assignation d'un observateur sur cette vue
				frame.assignListener(this);
				//Affichage de la vue
				frame.setVisible(true);
				break;
			case "rechercherModifier":				
				String[][] dataTable = this.medicinesTable();
				String[] dataColumns = {"Nom","Forme","Brevet","Effet indesirable","Grade"};
				//CrÔøΩation de la vue de recherche d'un mÔøΩdicament
				MedicineSearch frame1 = new MedicineSearch(dataTable,dataColumns);
				//Assignation d'un observateur sur cette vue
				frame1.assignListener(this);
				//Affichage de la vue
				frame1.setVisible(true);
				break;
			}
			break;
			
		case "EffetAdd":
			switch(what){
			case"Ajouter":
				String nom = EffetAdd.getTxtName();
				if(nom.equals("")){
				JOptionPane.showMessageDialog(null,"Grade incorrect","Erreur",JOptionPane.WARNING_MESSAGE);
				EffetAdd.focusTxtName();}
				else{
				
					int grade = EffetAdd.getGradeSelectionner();
					Effets effet = Effets.getEffetsByGrade(grade);
					new Effets(Effets.getNextId(),nom,grade);
				try{
					Persistence.insertEffets( nom,grade);
					JOptionPane.showMessageDialog(null, "L'effet a bien ÈtÈ enregistrÈ","Enregistrement",JOptionPane.INFORMATION_MESSAGE);
					EffetAdd.init();
				}
				catch(SQLException e){
					String message ="Erreur lors de l'echange avec la base de donnÈes. L'application a rencontrÈ l'erreur : "+e.getMessage();
					JOptionPane.showMessageDialog(null,message,"Erreur SQL",JOptionPane.ERROR_MESSAGE);
				}
			}
				break;
			case"Annuler":
				EffetAdd.init();
				break;
			}
	
				break;
				
			case "MedicineAdd":
			switch(what){
			case "valider":
				//RÔøΩcupÔøΩration des informations saisies par l'utilisateur
				String nom = MedicineAdd.getTxtName();
				if(nom.equals("")){
					JOptionPane.showMessageDialog(null,"Le nom du mÈdicament a ÈtÈ omis. Veillez le saisir correctement.","Erreur Saisie",JOptionPane.WARNING_MESSAGE);
					MedicineAdd.focusTxtName();
				}
				else{
					String nomF = MedicineAdd.getTxtForm();
					Form forme = Form.getFormByName(nomF);
					String dateB = MedicineAdd.getTxtPatentDate();
					String nomE = MedicineAdd.getTxtEffets();
					Effets effet = Effets.getEffetsByName(nomE);
					new Medicine(nom,forme,DatesConverter.USStringToDate(dateB),effet);
					
					//INSERT dans la BD
					try {
						Persistence.insertMedicine(nom,forme.getId(),DatesConverter.USStringToDate(dateB),effet.getId());
						//CrÔøΩation du nouvel objet Medicine
						
						//Message de confirmation pour l'utilisateur
						JOptionPane.showMessageDialog(null,"Le mÈdicament a bien ÈtÈ ajoutÈ","Confirmation Enregistrement",JOptionPane.INFORMATION_MESSAGE);
						
						//RÔøΩinitialisation des champs
						MedicineAdd.init();
					} catch (SQLException e) {
						String message = "Erreur lors de l'echange avec la base de donnÈes. L'application a rencontrÈ l'erreur : "+e.getMessage();
						JOptionPane.showMessageDialog(null,message,"Erreur SQL",JOptionPane.ERROR_MESSAGE);
					}
				}
				break;
			case "annuler":
				//RÔøΩnitialisation des champs
				MedicineAdd.init();
				break;
			}
			break;
		case "MedicineSearch":
				break;
		case "MedicineChange":
			switch(what){
			case "valider":
				//RÔøΩcupÔøΩration des informations saisies par l'utilisateur
				String nom = MedicineChange.getTxtName();
				String nomF = MedicineChange.getTxtForm();
				String nomE = MedicineChange.getTxtEffets();
				String dateB = MedicineChange.getTxtPatentDate();
				Form forme = Form.getFormByName(nomF);
				Effets effets = Effets.getEffetsByName(nomE);
				
				
				//RÔøΩcupÔøΩration de l'objet Medicine ÔøΩ modifier
				Medicine med = Medicine.getMedicineByName(nom);
				//Modification de celui-ci ÔøΩ travers les setteurs
				med.setItsForm(forme);
				med.setPatentDate(DatesConverter.FRStringToDate(dateB));
				med.setItsEffets(effets);
				
				
				//UPDATE dans la BD
				try {
					Persistence.updateMedicine(med.getName(),med.getItsForm().getId(),med.getPatentDate(),med.getItsEffets().getId());
					//Mise ÔøΩ jour de la jtable
					String[][] dataTable = this.medicinesTable();
					String[] dataColumns = {"Nom","Forme","Brevet","Effet indesirable","Grade"};
					MedicineSearch.setTable(dataTable, dataColumns);
					//Modification du bouton (annuler devient fermer)
					MedicineChange.btnAnnuler.setText("Fermer");
					//Message de confirmation pour l'utilisateur
					JOptionPane.showMessageDialog(null,"Le m√©dicament a bien ÈtÈ modifiÈ","Confirmation Enregistrement",JOptionPane.INFORMATION_MESSAGE);					
				} catch (SQLException e) {
					String message = "Erreur lors de l'echange avec la base de donn√©es. L'application a rencontrÈ l'erreur : "+e.getMessage();
					JOptionPane.showMessageDialog(null,message,"Erreur SQL",JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			break;
		}	
	}

	/**
	 * MÔøΩthode permettant d'interroger le modÔøΩle afin de construire un tableau contenant tous les mÔøΩdicaments
	 * @return un tableau ÔøΩ deux dimensions contenant tous les mÔøΩdicaments (nom,idForme,dateBrevet,effetIndesirable,grade)
	 */
	private String[][] medicinesTable() {
		int i=0;
		String[][] liste=new String[Medicine.allTheMedicines.size()][5];
		for(Medicine m : Medicine.allTheMedicines){
			liste[i][0]=m.getName();
			liste[i][1]=m.getItsForm().getName();
			liste[i][2]=DatesConverter.dateToStringFR(m.getPatentDate());
			liste[i][3]=m.getItsEffets().getName();
			liste[i][4]=String.valueOf(m.getItsEffets().getGrade());
			i++;
		}
		return liste;
	}

	/**
	 * MÔøΩthode permettant d'interroger le modÔøΩle afin de construire un tableau contenant toutes les formes
	 * @return un tableau ÔøΩ une dimension contenant toutes les formes (nom)
	 */
	private String[] formsBox(){
		int i=0;
		String[] liste=new String[Form.allTheForms.size()];
		for(Form f : Form.allTheForms){
			liste[i]=f.getName();
			i++;
		}
		return liste;
	}
	
	private String[] effetsBox(){
		int i=0;
		String[] liste=new String[Effets.allTheEffets.size()];
		for(Effets e : Effets.allTheEffets){
			liste[i]=e.getName();
			i++;
		}
		return liste;
	}

	/**
	 * MÔøΩthode dÔøΩclanchÔøΩe lors de clics souris sur l'application
	 */
	@Override
	public void mouseClicked(MouseEvent evt) {
		//S'il s'agit d'un double-clic
		if(evt.getClickCount()==2){
			//RÔøΩcupÔøΩration de la jtable dans laquelle l'utilisateur a double-cliquÔøΩ
			JTable laTable = (JTable)evt.getComponent();
			//RÔøΩcupÔøΩration du numÔøΩro de la ligne de cette jtable sur laquelle il a double-cliquÔøΩ
			int row=laTable.rowAtPoint(evt.getPoint());
			//RÔøΩcupÔøΩration du mÔøΩdicament ÔøΩ partir de ces informations
			Medicine med = Medicine.getMedicineByName(laTable.getValueAt(row,0).toString());
			//CrÔøΩation d'un tableau contenant le dÔøΩtail du mÔøΩdicament
			String[] data = new String[4];
			data[0]=med.getName();
			data[1]=med.getItsForm().getName();
			data[2]=DatesConverter.dateToStringFR(med.getPatentDate());
			data[3]=med.getItsEffets().getName();
			
			//CrÔøΩation de la vue de modification du mÔøΩdicament sÔøΩlectionnÔøΩ dans la jtable
			MedicineChange frame = new MedicineChange(this.formsBox(),this.effetsBox(),data);
			//Assignation d'un observateur sur cette vue
			frame.assignListener(this);
			//Affichage de la vue
			frame.setVisible(true);
		 } 
	
	}
	
	
	

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
