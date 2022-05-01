package xmacho11_projekt_PC2T_2022;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

public class SQL {
		
	public Connection conn; //promena pro pripojeni
	
	public boolean connect() { //funkce pro pripojeni se k databazi
		conn = null; 
	    try {
	    	conn = DriverManager.getConnection("jdbc:sqlite:DatabazeStudentu.db");                       
	    }
	    catch (SQLException e) { 
	    	System.out.println(e.getMessage());
	        return false;
	    }
	    return true;
	}
	
	public void disconnect() { //odpojeni se od databaze
		if (conn != null) {
			try {conn.close();} 
			catch (SQLException ex) { System.out.println(ex.getMessage() + "\nSQLite nebyla odpojena"); }
		}
	}
	
	public boolean vytvoreniTabulek() {
	    
		if (conn == null)
	    	return false;
		
	    String sql = "CREATE TABLE IF NOT EXISTS Studenti (" + "id integer PRIMARY KEY," + "skupina integer NOT NULL," + "jmeno varchar(255) NOT NULL, " + "prijmeni varchar(255) NOT NULL, " + "rok int NOT NULL," + "prumer float," + "pocetZnamek int" + ");";
	    
	    try{
	    	Statement stmt = conn.createStatement(); 
	        stmt.execute(sql);
	        return true;
	    } 
	    catch (SQLException e) {
	    System.out.println(e.getMessage());
	    }
	    
	    return false;
	}
	
	public boolean vycisteniSQL() {
		if (conn == null)
	    	return false;
		
	    String sql = "DELETE FROM Studenti";
	    
	    try{
	    	Statement stmt = conn.createStatement(); 
	        stmt.execute(sql);
	        return true;
	    } 
	    catch (SQLException e) {
	    System.out.println(e.getMessage());
	    }
	    
	    return false;
	}
	
	public void ulozeniZaznamu(int ID, int skupina, String jmeno, String prijmeni, int rok, double prumer, int pocetZnamek) {
        String sql = "INSERT INTO Studenti(id, skupina, jmeno, prijmeni, rok, prumer, pocetZnamek) VALUES(?,?,?,?,?,?,?)";
        try {
        	PreparedStatement pstmt = conn.prepareStatement(sql); 
            pstmt.setInt(1, ID);
            pstmt.setInt(2, skupina);
            pstmt.setString(3, jmeno);
            pstmt.setString(4, prijmeni);
            pstmt.setInt(5, rok);
            pstmt.setDouble(6, prumer);
            pstmt.setInt(7, pocetZnamek);
            
            if(pstmt.executeUpdate() == 0)
            	System.err.println("ERROR: zaznam nebyl zapsan");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}	
}
