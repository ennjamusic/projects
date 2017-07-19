package DBCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {
    private static Connection con;
    private String url = "jdbc:postgresql://localhost:5432/shop";
    
    public DBConnect() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
    	Class.forName("org.postgresql.Driver").newInstance();
    }

    public ResultSet select(String sql) throws SQLException{
        return getCon().prepareStatement(sql).executeQuery();
    }
    
    public PreparedStatement prepare (String sql) throws SQLException{
    	return getCon().prepareStatement(sql);
    }
    
    public int getCount(ResultSet rs) throws SQLException{
    	int i=0;
    	while(rs.next()){
    		i++;
    	}
    	return i;
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