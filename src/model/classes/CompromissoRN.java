package model.classes;

import controller.TelaConsultaController;
import controller.TelaInicialController;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Dao.CompromissoDao;
import java.time.format.DateTimeFormatter;

/**
 * @author RafaelRodrigues1
 */
public class CompromissoRN {
    
    
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private DateTimeFormatter dataForm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter horaForm = DateTimeFormatter.ofPattern("HH:mm");
    private TelaInicialController telaInicialController;
    private TelaConsultaController telaConsultaController;
    private final CompromissoDao compromissoDao;

    public CompromissoRN(TelaInicialController telaInicialController) {
        this.telaInicialController = telaInicialController;
        compromissoDao = new CompromissoDao(this);
    }

    public CompromissoRN(TelaConsultaController telaConsultaController) {
        this.telaConsultaController = telaConsultaController;
        compromissoDao = new CompromissoDao(this);
    }
    
    public Boolean adicionaCompromisso(String descricao, String data, String hora, String local){
        try{    
            LocalDateTime dataHora = LocalDateTime.parse(data + " " + hora, dtf);
            Compromisso compromisso = new Compromisso(descricao, dataHora, local);         
            return compromissoDao.adicionaCompromisso(compromisso);
        }catch(Exception ex){
            return false;
        }
    }
    
    public Boolean alteraCompromisso(Integer id, String descricao, String data, String hora, String local){
        LocalDateTime dataHora = LocalDateTime.parse(data + " " + hora, dtf);
        Compromisso compromisso = new Compromisso(id, descricao, dataHora, local); 
        return compromissoDao.alteraCompromisso(compromisso);
    }
    
    public Boolean deletaCompromisso(Integer id){
        return compromissoDao.deletaCompromisso(id);
    }
    
    public List<Compromisso> consultaCompromisso(String data1) throws Exception{
        List<Compromisso> compromissoList;
        if(!data1.isBlank()){
            LocalDate dataT = LocalDate.parse(data1, dataForm);
            compromissoList = compromissoDao.consultaCompromisso(dataT);
        }else{
            compromissoList = compromissoDao.consultaCompromisso(null);
        } 
        Collections.sort(compromissoList);        
        return compromissoList;
    }
    
    public void imprimePdf(){
        List<Compromisso> compromissoList = compromissoDao.consultaCompromisso(null);
        Collections.sort(compromissoList); 
        ServicoImpressao serv = new ServicoImpressao(compromissoList);
    }
}
