import java.sql.Connection;
import java.sql.SQLException;

public class DBCon {

	private static Connection con;
	private String url = "jdbc:postgresql://localhost:5432/mail";
	
	public DBCon (){
		Class.forName("org.postresql.Driver").newInstance();
	}
	
	public ResultSet select (String sql) throws SQLException{
		return getCon().prepareStatement(sql).executeQuery();
	}
	
	public int update(String sql) throws SQLException{
        return getCon().prepareStatement(sql).executeUpdate();
    }

    public Connection getCon() throws SQLException{
        if(con == null) {
            con = DriverManager.getConnection(url,"postgres","");
        }
        return con;
    }
    
    public void close(Connection c) throws SQLException{
    	if (c!=null){
    		c.close();
    		c = null;
    	}
    }
}
