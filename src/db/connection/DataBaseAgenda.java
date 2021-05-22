package db.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author RafaelRodrigues1
 */
public class DataBaseAgenda {

    //private static Connection conn = null;   
    
    public static Connection getConnection(){
        Connection conn = null;
        //if(conn == null){
            try{               
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);  
            }catch(SQLException ex){
                throw new DBException(ex.getMessage());
            }
       // }
        return conn;
    }
    
    public static void closeConnection(Connection conn){
        if(conn != null){
            try{
               conn.close();
            }catch(SQLException ex){
                throw new DBException(ex.getMessage());
            }
        }
    }
    
    public static void closeConnection(Statement st, Connection conn){
        if(st != null){
            try{
                st.close();
            }catch(SQLException ex){
                throw new DBException(ex.getMessage());
            }
        }
        closeConnection(conn);
    }

    public static void closeConnection(ResultSet rs, Statement st, Connection conn){
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException ex){
                throw new DBException(ex.getMessage());
            }
        }
        closeConnection(st, conn);
    }
    
    //C:\Users\RAFAEL\Documents\NetBeansProjects\AppAgenda\src\model\Dao
    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("src\\model\\dao\\db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        }catch(IOException ex){
            throw new DBException(ex.getMessage());
        }
    }
}
