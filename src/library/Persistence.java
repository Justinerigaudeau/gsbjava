package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
/**
 * Classe de persistance des objets dans une base SQL
 * @author xavier
 *
 */
public abstract class Persistence {
	
	/**
	 * M�thode d'INSERT d'un nouveau m�dicament
	 * @param name le nom du nouveau m�dicament
	 * @param idForm l'identifiant de la forme du nouveau m�dicament
	 * @param patentDate la date d'obtention du brevet du nouveau m�dicament
	 * @return le code du nouveau medicament ajout�
	 * @throws SQLException l'exception SQL lev�e
	 */
	public static int insertMedicine(String name, int idForm, GregorianCalendar patentDate, int idEffets) throws SQLException{
		Connection cn = Persistence.connection();
		Statement stmt;
		try{
			 stmt = cn.createStatement();
			 if(patentDate!=null){
				 String req="INSERT INTO medicament (nom,idForme,dateBrevet,idEffets) VALUES ('"+name+"',"+idForm+",'"+DatesConverter.dateToStringUS(patentDate)+"',"+idEffets+")";
				 System.out.println(req);
				 stmt.executeUpdate(req);
			 }
			 else
				 stmt.executeUpdate("INSERT INTO medicament (nom,idForme,dateBrevet,idEffets) VALUES ('"+name+"',"+idForm+",null,"+idEffets+")");
			 ResultSet rs = stmt.executeQuery("SELECT @@IDENTITY");
			 rs.next();
			 return rs.getInt(1);
		}catch (SQLException e){
			throw e;
		}
		finally{
			Persistence.closeConnection(cn);
		}
	}
	/**
	 * M�thode d'INSERT d'une nouvelle forme
	 * @param name le nom de la nouvelle forme
	 * @throws SQLException l'exception SQL lev�e
	 */
	public static void insertForm(String name) throws SQLException{
		Connection cn = Persistence.connection();
		Statement stmt;
		
		try {
			 stmt = cn.createStatement();
			stmt.executeUpdate("INSERT INTO forme (nom) VALUES ('"+name+"')");
		} catch (SQLException e) {
			throw e;
		}
		finally{
			Persistence.closeConnection(cn);
		}
	}

	public static void insertEffets(String name,int grade) throws SQLException{
		Connection cn = Persistence.connection();
		Statement stmt;
		
		try {
			 stmt = cn.createStatement();
			 String requete = "INSERT INTO effetindesirable (nom,idGrade) VALUES ('"+name+"',"+grade+")";
			 System.out.println(requete);
			stmt.executeUpdate(requete);
		} catch (SQLException e) {
			throw e;
		}
		finally{
			Persistence.closeConnection(cn);
		}
	}
	

	/**
	 * M�thode de SELECT des tables
	 * @param table le nom de la table SQL � s�lectionner
	 * @return un tableau � deux dimensions contenant tous les tuples de la table
	 * @throws SQLException l'exception SQL lev�e
	 */
	public static String[][] load(String table) throws SQLException{	
		//D�claration des variables
		Connection cn = Persistence.connection();
		Statement stmt; 
		ResultSet rs = null;
		ResultSetMetaData metadata;
		int rowCount,columnCount,rowNum;
		String columnName;
		String[][] result = null;
		
	    try 
	    {
	    	stmt= cn.createStatement();
	    	//D�finition de la requete pour construire le jeu d'enregistrement
	    	rs = stmt.executeQuery("SELECT count(*) FROM "+table);
			//R�cup�ration du nombre de lignes du jeu d'enregistrement
	    	rs.next();
			rowCount=rs.getInt(1);
	    	//D�finition de la requete pour construire le jeu d'enregistrement
	    	rs = stmt.executeQuery("SELECT * FROM "+table);
			metadata = rs.getMetaData();
			//R�cup�ration du nombre de colonnes du jeu d'enregistrement
			columnCount = metadata.getColumnCount();
			//D�claration du tableau qui contiendra toutes les informations
			result = new String[rowCount][columnCount];
			//PArcours du jeu d'enregistrement
			rowNum = 0;
	        while (rs.next()) 
	        {
	        	for (int numCol=0; numCol<columnCount; numCol++)
	        	{
	        		//Insertion de la valeur dans une case du tableau
	        		columnName = metadata.getColumnName(numCol+1);
		        	result[rowNum][numCol] = rs.getString(columnName);
	        	}
	        	rowNum++;
	        }
	        
		} catch (SQLException e) 
		{
			throw e;
		}
	    finally{
	    	Persistence.closeConnection(cn);
	    }
	return result;
	}

	/**
	 * M�thode de connexion � la BD
	 * @return une connexion active sur la BD
	 * @throws SQLException l'exception SQL lev�e
	 */
	private static Connection connection() throws SQLException{
		String host = "172.21.105.1";
		//String host = "localhost";
		String base = "gsb";
		String user = "valentin";
		String passwd = "root";
		Connection conn = null;
		try
		{
		//String connectionString ="jdbc:sqlserver://"+host+";database="+base+";user="+user+";password="+passwd;
			String connectionString ="jdbc:mysql://"+host+"/"+base+"?user="+user+"&password="+passwd;
			conn = DriverManager.getConnection(connectionString);
		}
		catch (SQLException e) 
		{
			throw e;
		}
		return conn; 
	}
	
	/**
	 * M�thode de cl�ture de connexion
	 * @param conn la connexion � fermer
	 * @throws SQLException l'exception SQL lev�e
	 */
	private static void closeConnection(Connection conn) throws SQLException{
		try {
			conn.close();
		} catch (SQLException e) {
			throw e;
		}
	}

	/**
	 * M�thode d'UPDATE d'un m�dicament
	 * @param name le nom du m�dicament � modifier
	 * @param idForm la nouvelle forme du m�dicament � modifier
	 * @param patentDate la nouvelle date d'obtention du brevet du m�dicament � modifier
	 * @throws SQLException l'exception SQL lev�e
	 */
	public static void updateMedicine(String name, int idForm, GregorianCalendar patentDate,int idEffets) throws SQLException {
		Connection cn = Persistence.connection();
		Statement stmt;
		try{
			 stmt = cn.createStatement();
			 stmt.executeUpdate("UPDATE medicament SET idForme="+idForm+" WHERE nom='"+name+"'");
			 stmt.executeUpdate("UPDATE medicament SET idEffets="+idEffets+" WHERE nom='"+name+"'");
			 if(patentDate!=null)
				 stmt.executeUpdate("UPDATE medicament SET dateBrevet='"+DatesConverter.dateToStringUS(patentDate)+"' WHERE nom='"+name+"'");
		}catch (SQLException e){
			throw e;
		}
		finally{
			Persistence.closeConnection(cn);
		}
	}

}
