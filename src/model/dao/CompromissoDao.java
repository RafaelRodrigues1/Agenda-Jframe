package model.Dao;

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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import model.classes.Compromisso;
import model.classes.CompromissoRN;

/**
 * @author RafaelRodrigues1
 */
public class CompromissoDao {
    
    
    private CompromissoRN compromissoRN;
    
    private Connection con = null;
    
    public CompromissoDao(CompromissoRN compromissoRN) {
        this.compromissoRN = compromissoRN;
        con = DataBaseAgenda.getConnection();
    }
    
    public Boolean adicionaCompromisso(Compromisso compromisso){  
        PreparedStatement pst = null;
        long dataHora = compromisso.getDataHora().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        try{            
            String sql = "INSERT INTO compromisso(data, horario, local, descricao) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setDate(1, new Date(dataHora));
            pst.setTime(2, new Time(dataHora));
            pst.setString(3, compromisso.getLocal());
            pst.setString(4, compromisso.getDescricao());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected>0;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally{
            DataBaseAgenda.closeConnection(pst, con);
        }
    }
    
    public List<Compromisso> consultaCompromisso(LocalDate consultaDB){
        List<Compromisso> compromissoList = new ArrayList<>();
        Statement stat = null;
        ResultSet resu = null;
        try{
            stat = con.createStatement(); //Dando bronca a partir da segunda consulta
            if(consultaDB == null){
                resu = stat.executeQuery("select * from compromisso");
            }else{
                resu = stat.executeQuery("select * from compromisso where data = '" + consultaDB+"'");
            } 
            while(resu.next()){
                LocalDate data = resu.getDate("data").toLocalDate();
                LocalTime hora = resu.getTime("horario").toLocalTime();
                compromissoList.add(new Compromisso(resu.getString("descricao"), LocalDateTime.of(data, hora),
                        resu.getString("local")));
            }
            return compromissoList;
        }catch(SQLException ex){
            throw new DBException(ex.getMessage());
        }finally{
            DataBaseAgenda.closeConnection(resu, stat, con);
        }
    }
}
