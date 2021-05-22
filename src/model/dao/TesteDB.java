package model.Dao;

import db.connection.DBException;
import db.connection.DataBaseAgenda;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author RafaelRodrigues1
 */
public class TesteDB {
    
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = DataBaseAgenda.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from compromisso");
            while(rs.next()){
                System.out.println(rs.getString("data") + " " +rs.getString("horario") + "\t" +
                        rs.getString("local") + "\t\t\t" +rs.getString("descricao"));
            }
        }catch(SQLException ex){
            throw new DBException(ex.getMessage());
        }finally{
            DataBaseAgenda.closeConnection(rs, st, conn);
        }
        
    }
}
