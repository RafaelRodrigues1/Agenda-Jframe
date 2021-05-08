package model.Dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.classes.CompromissoRN;

/**
 * @author RafaelRodrigues1
 */
public class CompromissoDao {
    
    private CompromissoRN compromissoRN;
    private final String path = "C:\\Users\\RAFAEL\\Documents\\NetBeansProjects\\AppAgenda\\DB\\DataBase.txt";
    
    public CompromissoDao(CompromissoRN compromissoRN) {
        this.compromissoRN = compromissoRN;
    }
    
    public Boolean adicionaCompromisso(String compromissoDB){      
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))){
            bw.write(compromissoDB);
            bw.newLine();
            return true;
        }catch(IOException ex){
            return false;
        }       
    }
    
    public List<String> consultaCompromisso(String consultaDB){
        List<String> listaConsulta = new ArrayList();        
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String compromissoDB = br.readLine();
            while(compromissoDB!=null){
                if(!consultaDB.isBlank()){
                    if(compromissoDB.contains(consultaDB)){
                        listaConsulta.add(compromissoDB);
                    }
                    compromissoDB = br.readLine();
                }else{
                    listaConsulta.add(compromissoDB);
                    compromissoDB = br.readLine();
                }
            }
            return listaConsulta;
        }catch(IOException ex){
            return listaConsulta;
        }       
    }
}
