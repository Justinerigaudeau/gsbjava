package metier;

import java.util.ArrayList;
import java.util.GregorianCalendar;
/**
 * Classe d'objet metier MEDICAMENT
 * @author justine
 *
 */
public class Medicine {
	

	private String name;

	/**
	 * Le nom du m�dicament
	 */
	private Form itsForm;
	/**
	 * Forme pharmaceutique du m�dicament
	 */
	
	private GregorianCalendar patentDate;
	/**
	 * Date d'obtention du brevet pharmaceutique
	 */
	/**
	 * Liste statique de tous les m�dicaments
	 */
	private Effets itsEffets;
	
	public static ArrayList<Medicine> allTheMedicines = new ArrayList<Medicine>();
	
	/**
	 * Construcuteur de la classe Medicament
	 * @param name nom du nouveau m�dicament
	 * @param itsForm forme pharmaceutique du nouveau m�dicament
	 * @param patentDate date d'obtention du brevet du nouveau m�dicament
	 */
	public Medicine(String name, Form itsForm, GregorianCalendar patentDate,Effets itsEffets) {
		super();
		this.name = name;
		this.itsForm = itsForm;
		this.patentDate = patentDate;
		this.itsEffets = itsEffets;
		allTheMedicines.add(this);
	
	}
	
	/**
	 * Accesseur en lecture sur le nom du m�dicament
	 * @return le nom du m�dicament
	 */
	public String getName() {
		return name;
	}

	/**
	 * Accesseur en lecture sur la forme du m�dicament
	 * @return la forme du m�dicament
	 */
	public Form getItsForm() {
		return itsForm;
	}
	
	public Effets getItsEffets() {
		return itsEffets;
	}


	/**
	 * Accesseur en lecture sur la date d'obtention du brevet du m�dicament
	 * @return la date d'obtention du brevet du m�dicament
	 */
	public GregorianCalendar getPatentDate() {
		return patentDate;
	}
	
	
	
	/**
	 * M�thode permettant de rechercher parmi tous les m�dicaments
	 * celui ayant un nom correspondant � celui pass� en param�tre
	 * @param name le nom � rechercher
	 * @return le Medicament correspondant
	 */
	public static Medicine getMedicineByName(String name) {
		Medicine found = null;
		for(Medicine m : Medicine.allTheMedicines){
			if(m.getName().equals(name))
				found=m;
		}
		return found;
	}
	
	/** M�thode permettant de trouver le m�dicament par le code */
	public static  Medicine getMedecineByCode(int id){
		Medicine found = null;
		for(Medicine m : Medicine.allTheMedicines){
			if(m.getid() == id){
				found=m;
			}
				
		}
		return found;
	}
		

	private int getid() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * Accesseur en �criture sur la forme du m�dicament
	 * @param itsForm la nouvelle forme du m�dicament
	 */
	public void setItsForm(Form itsForm) {
		this.itsForm = itsForm;
	}
	
	public void setItsEffets(Effets itsEffets) {
		this.itsEffets=itsEffets;
	}

	/**
	 * Accesseur en �criture sur la date d'obtention du brevet du m�dicament
	 * @param patentDate la nouvelle date d'obtention du brevet du m�dicament
	 */
	public void setPatentDate(GregorianCalendar patentDate) {
		this.patentDate = patentDate;
	}

	



	

	
}
