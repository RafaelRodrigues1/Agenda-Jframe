package controller;

import java.util.Optional;
import model.classes.Compromisso;
import model.classes.CompromissoRN;
import model.classes.Data;
import view.TelaConsulta;
import view.TelaInicial;
import java.lang.Exception;


/**
 *
 * @author RafaelRodrigues1
 */
public class TelaInicialController {
    
    private final TelaInicial telaInicial;
    private final CompromissoRN compromissoRN = new CompromissoRN(this);

    public TelaInicialController(TelaInicial telaInicial) {
        this.telaInicial = telaInicial;
    }
    
    public void addCompromisso(){
        try{
            if(!telaInicial.getjTextAreaCompromisso().getText().isBlank()||telaInicial.getjTextFieldData().getText().isBlank()){
                Data data = new Data(telaInicial.getjTextFieldData().getText());
                Integer horario = Integer.parseInt(telaInicial.getjTextFieldHora().getText());
                String local = telaInicial.getjTextFieldLocal().getText();
                String descricao = telaInicial.getjTextAreaCompromisso().getText();
                Compromisso compromisso = new Compromisso(descricao, data, horario, local);
                if(compromissoRN.adicionaCompromisso(compromisso)){
                    telaInicial.mostraMsg("Compromisso adicionado");
                }else{
                    telaInicial.mostraMsg("Erro ao adicionar compromisso!");
                }
            }else{
                throw new Exception();
            }
        }catch(Exception ex){
            telaInicial.mostraMsg("Erro ao adicionar compromisso!");
        }
    }
    
    public void consultaCompromisso(){
        try{  
            String data = telaInicial.getjTextFieldData().getText();
            TelaConsultaController telaConsultaController = new TelaConsultaController(new TelaConsulta());
            if(data.isBlank()){
                int resp = telaInicial.consultaGeral();
                if(resp==0){
                    if(telaConsultaController.consultaCompromisso(data)){
                        telaInicial.setVisible(false);
                    }
                }                
            }else{               
                if(telaConsultaController.consultaCompromisso(data)){
                    telaInicial.setVisible(false);
                }else{
                    telaInicial.mostraMsg("Não há compromisso no dia " + telaInicial.getjTextFieldData().getText());
                }
            }
        }catch(Exception ex){
            telaInicial.mostraMsg("Erro ao Consultar compromisso!");
        }    
    }
    
}
