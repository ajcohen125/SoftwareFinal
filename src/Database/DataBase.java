package Database;

	public class DataBase {

	    public static String reciept(String names, String values) {

	        java.sql.Connection conn = linktodata();

	        String todo = ("INSERT into staff " +
	                "(" + names + ") values (" + values + ")");

	        try {
	            java.sql.Statement s = conn.createStatement();
	            int r = s.executeUpdate (todo);
	        }
	        catch (Exception e) {
	            return ("Oh oops - code 003\n"+e);
	        }

	        return (todo);

	    }

	    public static String select(String [] fields, String selector) {

	        java.sql.Connection conn = linktodata();

	        StringBuffer reply = new StringBuffer("<table border=1>");

	        String todo = ("SELECT * "+
	                " from staff " + selector);

	        try {
	            java.sql.Statement s = conn.createStatement();
	            java.sql.ResultSet r = s.executeQuery (todo);
	            while(r.next()) {
	                reply.append("<tr>");
	                for (int i=0;i<fields.length;i++) {
	                    reply.append(tabit(r.getString(fields[i])));
	                }
	                reply.append("</tr>");
	            }
	            reply.append("</table>");
	        }
	        catch (Exception e) {
	            return ("Oh oops - code 003\n"+e);
	        }

	        return (reply.toString());

	    }

	    private static String tabit(String box) {
	        return ("<td>"+box+"</td>");
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
	                    "jdbc:mysql://bhajee/J850a?user=jtest&password=");
	        }
	        catch (Exception e) {
	            return conn;
	            // return "Oh dear - code 001 and a half";
	        }
	        return conn;
	    }
	}

