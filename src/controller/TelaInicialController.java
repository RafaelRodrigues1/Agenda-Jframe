package controller;

import model.classes.Compromisso;
import model.classes.CompromissoRN;
import view.TelaConsulta;
import view.TelaInicial;
import java.lang.Exception;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author RafaelRodrigues1
 */
public class TelaInicialController {
    
    private final TelaInicial telaInicial;
    private final CompromissoRN compromissoRN = new CompromissoRN(this);
    
    private final SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    public TelaInicialController(TelaInicial telaInicial) {
        this.telaInicial = telaInicial;
    }
    
    public void addCompromisso(){
        try{
            if(!telaInicial.getjTextAreaCompromisso().getText().isBlank()
                    ||!telaInicial.getjTextFieldData().getText().isBlank()
                    ||!telaInicial.getjTextFieldHora().getText().isBlank()){
                String dataStr = telaInicial.getjTextFieldData().getText();
                String hora = telaInicial.getjTextFieldHora().getText();
                System.out.println(dataStr + " " + hora);
                Date data = dataFormatada.parse(dataStr + " " + hora);
                System.out.println(data);
                String local = telaInicial.getjTextFieldLocal().getText();
                String descricao = telaInicial.getjTextAreaCompromisso().getText();
                Compromisso compromisso = new Compromisso(descricao, data, local);
                System.out.println(compromisso);
                if(compromissoRN.adicionaCompromisso(compromisso)){
                    telaInicial.mostraMsg("Compromisso adicionado");
                }else{
                    telaInicial.mostraMsg("OPAErro ao adicionar compromisso!");
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
