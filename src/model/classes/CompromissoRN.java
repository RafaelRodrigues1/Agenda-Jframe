package model.classes;

import controller.TelaConsultaController;
import controller.TelaInicialController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import model.Dao.CompromissoDao;
import model.classes.Compromisso;

/**
 * @author RafaelRodrigues1
 */
public class CompromissoRN {
    private final SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm");
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
            String data = compromisso.getData();
            String hora = compromisso.getHorario();
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
        try{
            List<String> listStr = compromissoDao.consultaCompromisso(data1);
            for(String linha: listStr){
                String[] separaDados = linha.split(";");
    //            String datastr = separaDados[0];
    //            Integer horariostr = Integer.parseInt(separaDados[1]);
                Date data = dataFormatada.parse(separaDados[0] + " " + separaDados[1]);
                String local = separaDados[2];            
                String descricao = separaDados[3];
                Compromisso compromisso = new Compromisso(descricao, data,  local);
                compromissoList.add(compromisso);
            }
            Collections.sort(compromissoList);
            return compromissoList;
        }catch(ParseException ex){
            System.out.println("Erro no RN");
            System.out.println(ex.getMessage());
            return compromissoList;
        }
    }
}
