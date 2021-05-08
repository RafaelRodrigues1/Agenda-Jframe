package model.classes;

import controller.TelaConsultaController;
import controller.TelaInicialController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Dao.CompromissoDao;

/**
 * @author RafaelRodrigues1
 */
public class CompromissoRN {
    
    private TelaInicialController telaInicialController;
    private TelaConsultaController telaConsultaController;
    private final CompromissoDao compromissoDao = new CompromissoDao(this);

    public CompromissoRN(TelaInicialController telaInicialController) {
        this.telaInicialController = telaInicialController;
    }

    public CompromissoRN(TelaConsultaController telaConsultaController) {
        this.telaConsultaController = telaConsultaController;
    }
    
    public Boolean adicionaCompromisso(Compromisso compromisso){
        try{    
            String data = compromisso.getData().toString();
            String hora = compromisso.getHorario().toString();
            String local = compromisso.getLocal();
            String descricao = compromisso.getDescricao();
            String compromissoDB = data + ";" + hora + ";" + local + ";" + descricao;           
            return compromissoDao.adicionaCompromisso(compromissoDB);
        }catch(Exception ex){
            return false;
        }
    }
    
    public List<Compromisso> consultaCompromisso(String data1){
        
        List<Compromisso> compromissoList = new ArrayList<>();
        List<String> listStr = compromissoDao.consultaCompromisso(data1);
        for(String linha: listStr){
            String[] separaDados = linha.split(";");
            String data = separaDados[0];
            Integer horario = Integer.parseInt(separaDados[1]);
            String local = separaDados[2];
            String descricao = separaDados[3];
            Compromisso compromisso = new Compromisso(descricao, new Data(data), horario, local);
            compromissoList.add(compromisso);
        }
        Collections.sort(compromissoList);
        return compromissoList;
    }
}
