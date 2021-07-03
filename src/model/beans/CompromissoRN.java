package model.beans;

import controller.TelaConsultaController;
import controller.TelaInicialController;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.time.format.DateTimeFormatter;
import model.classes.Compromisso;
import model.classes.ServicoImpressao;
import model.dao.CompromissoDaoJPA;

/**
 * @author RafaelRodrigues1
 */
public class CompromissoRN {
    
    
    private final DateTimeFormatter horaForm = DateTimeFormatter.ofPattern("HH:mm");
    private final DateTimeFormatter dataForm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private TelaInicialController telaInicialController;
    private TelaConsultaController telaConsultaController;

    public CompromissoRN(TelaInicialController telaInicialController) {
        this.telaInicialController = telaInicialController;
    }

    public CompromissoRN(TelaConsultaController telaConsultaController) {
        this.telaConsultaController = telaConsultaController;
    }
    
    public Boolean adicionaCompromisso(Compromisso compromisso){
        try{    
            new CompromissoDaoJPA().abrirTransaction().salvar(compromisso).commitarTransaction().fechar();
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public Boolean alteraCompromisso(Compromisso compromisso){
        try{
            new CompromissoDaoJPA().abrirTransaction().alterar(compromisso).commitarTransaction().fechar();
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public Boolean deletaCompromisso(Compromisso compromisso){
        try {
            new CompromissoDaoJPA().abrirTransaction().apagar(compromisso).commitarTransaction().fechar();
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public List<Compromisso> consultaCompromisso(String data1){
        List<Compromisso> compromissoList;
        if(!data1.isBlank()){
            LocalDate dataT = LocalDate.parse(data1, dataForm);
            compromissoList = new CompromissoDaoJPA().buscar(dataT);
        }else{
            compromissoList = new CompromissoDaoJPA().buscar();
        } 
        Collections.sort(compromissoList);        
        return compromissoList;
    }
    
    public void imprimePdf(){
        List<Compromisso> compromissoList = consultaCompromisso(null);
        Collections.sort(compromissoList); 
        ServicoImpressao.run(compromissoList);
    }
    
    public Boolean direcionar(TipoOperacao operacao, String... dados){
        LocalDate dataF = LocalDate.parse(dados[1], dataForm);
        LocalTime horaF = LocalTime.parse(dados[2], horaForm);
        
        if(null != operacao)switch (operacao) {
            case ADICIONAR -> {
                Compromisso compromisso = new Compromisso(dados[0], dataF, horaF, dados[3]);
                return adicionaCompromisso(compromisso);
            }
            case ALTERAR -> {
                Compromisso compromisso = new Compromisso(Integer.parseInt(dados[4]), dados[0], dataF, horaF, dados[3]);
                return alteraCompromisso(compromisso);
            }
            case APAGAR -> {
                Compromisso compromisso = new Compromisso(Integer.parseInt(dados[4]), dados[0], dataF, horaF, dados[3]);
                return deletaCompromisso(compromisso);
            }
            default -> {}
        }
        return false;
    }
}
