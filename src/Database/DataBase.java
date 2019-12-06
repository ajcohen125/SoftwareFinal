
package Database;
import java.sql.*;

	public class DataBase {

	    public static int update(String sql) {

	        java.sql.Connection conn = linktodata();
	        int r = -2;

	        try {
	            java.sql.Statement s = conn.createStatement();
	            r = s.executeUpdate (sql);
	        }
	        catch (Exception e) {
	            System.out.println(e);
	        }

	        return (r);

	    }

	    public static ResultSet select(String sql) {

	        java.sql.Connection conn = linktodata();

	        java.sql.ResultSet r = null;

	        try {
	            java.sql.Statement s = conn.createStatement();
	            r = s.executeQuery (sql);	           
	           
	        }
	        catch (Exception e) {
	            return (null);
	        }

	        return r;

	    }


	    private static java.sql.Connection linktodata () {

	        java.sql.Connection conn = null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	        }
	        catch (Exception e) {
	            return conn;
	            // return "Oh dear - code 001 and a quarter";
	        }
	        try {
	            conn = java.sql.DriverManager.getConnection(
	            		"jdbc:mysql://easel2.fulgentcorp.com/kbd357",
	            		"kbd357","ByUMDJDnGk4ak1agMXyw");
	        }
	        catch (Exception e) {
	            return conn;
	            // return "Oh dear - code 001 and a half";
	        }
	        return conn;
	    }
	}

