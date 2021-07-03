package model.dao;

import db.connection.DBException;
import db.connection.DataBaseAgenda;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import model.classes.Compromisso;
import model.beans.CompromissoRN;

/**
 * @author RafaelRodrigues1
 */
public class CompromissoDaoJDBC {
    
    private Connection con = null;
    private Statement stat = null;
    private PreparedStatement pst = null;
    private ResultSet resu = null;
    private final CompromissoRN compromissoRN;
    
    public CompromissoDaoJDBC(CompromissoRN compromissoRN) {
        this.compromissoRN = compromissoRN;
        
    }

    public CompromissoDaoJDBC() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Boolean adicionaCompromisso(Compromisso compromisso){  
        long dataHora = compromisso.getDateTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        try{        
            con = DataBaseAgenda.getConnection();
            String sql = "INSERT INTO compromisso(data, horario, local, descricao) VALUES (?, ?, ?, ?);";
            pst = con.prepareStatement(sql);
            pst.setDate(1, new Date(dataHora));
            pst.setTime(2, new Time(dataHora));
            pst.setString(3, compromisso.getLocal());
            pst.setString(4, compromisso.getDescricao());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected == 1;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally{
            DataBaseAgenda.closeConnection(pst, con);
        }
    }
    
    public List<Compromisso> consultaCompromisso(LocalDate consultaDB){
        List<Compromisso> compromissoList = new ArrayList<>();
        try{
            con = DataBaseAgenda.getConnection();
            stat = con.createStatement();
            if(consultaDB == null){
                resu = stat.executeQuery("select * from compromisso;");
            }else{
                resu = stat.executeQuery("select * from compromisso where data = '" + consultaDB+"';");
            } 
            while(resu.next()){
                LocalDate data = resu.getDate("data").toLocalDate();
                LocalTime hora = resu.getTime("horario").toLocalTime();
                compromissoList.add(new Compromisso(resu.getInt("id"), 
                        resu.getString("descricao"), data, hora,
                        resu.getString("local")));
            }
            return compromissoList;
        }catch(SQLException ex){
            ex.printStackTrace();
            throw new DBException(ex.getMessage());
        }finally{
            DataBaseAgenda.closeConnection(resu, stat, con);
        }
    }
    
    public Boolean alteraCompromisso(Compromisso compromisso){
        try{
            long dataHora = compromisso.getDateTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            con = DataBaseAgenda.getConnection();
            String sql = "UPDATE compromisso "
                    + "SET data = ? , horario = ? , local = ? , descricao = ? "
                    + "WHERE id = ? LIMIT 1 ";
            pst = con.prepareStatement(sql);
            pst.setDate(1, new Date(dataHora));
            pst.setTime(2, new Time(dataHora));
            pst.setString(3, compromisso.getLocal());
            pst.setString(4, compromisso.getDescricao());
            pst.setInt(5, compromisso.getId());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected == 1;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally{
            DataBaseAgenda.closeConnection(pst, con);
        }
    }
    
    public Boolean deletaCompromisso(Integer id){
        try{
            con = DataBaseAgenda.getConnection();
            String sql = "DELETE FROM compromisso WHERE id = ? LIMIT 1;";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected == 1;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally{
            DataBaseAgenda.closeConnection(pst, con);
        }
    }
}
