package metier;

import java.util.ArrayList;
/**
 * Classe d'objet metier FORME
 * @author justine
 */

public class Effets {
	
	private String name;
	private int id;
	private int grade;
	public static ArrayList<Effets> allTheEffets = new ArrayList<Effets>();
	
	
public Effets(int id,String name, int grade){
	super();
	this.id=id;
	this.grade=grade;
	this.name=name;
	allTheEffets.add(this);
	//System.out.println(Effets.allTheEffets.size());
}


public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getGrade() {
	return grade;
}

public void setGrade(int grade) {
	this.grade = grade;
}

public static  Effets getEffetsById(int id){
	Effets found = null;
	//System.out.println(Effets.allTheEffets.size());
	for(Effets e : Effets.allTheEffets){
		System.out.println(e.getId()+" "+id);
		if(e.getId() == id){
			found=e;
		}
	}
	return found;
}


public static Effets getEffetsByName(String name){
	Effets found = null;
	for(Effets e : Effets.allTheEffets){
		if(e.getName() == name){
			found=e;
			
		}
	}
	return found;
}

public static  Effets getEffetsByGrade(int grade){
	Effets found = null;
	for(Effets e : Effets.allTheEffets){
		if(e.getGrade() == grade){
			found=e;
		}
			
	}
	return found;
}

public static int getNextId(){
	int id = 0;
	for(Effets e : allTheEffets){
		if(e.id>=id)
			id = e.id+1;
	}
	return id;
}


public void addEffets(Effets effets){
	allTheEffets.add(effets);}

public int getId() {
	return this.id;
}



			
}



