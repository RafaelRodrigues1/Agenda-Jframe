package controller;

import model.classes.Compromisso;
import model.classes.CompromissoRN;
import view.TelaConsulta;
import view.TelaInicial;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
            if(!telaInicial.getjTextAreaCompromisso().getText().isBlank()
                    ||!telaInicial.getjTextFieldData().getText().isBlank()
                    ||!telaInicial.getjTextFieldHora().getText().isBlank()){
                String dataStr = telaInicial.getjTextFieldData().getText();
                String hora = telaInicial.getjTextFieldHora().getText();
                String local = telaInicial.getjTextFieldLocal().getText();
                String descricao = telaInicial.getjTextAreaCompromisso().getText();
                if(compromissoRN.adicionaCompromisso(descricao, dataStr, hora, local)){
                    telaInicial.mostraMsg("Compromisso adicionado");
                }else{
                    telaInicial.mostraMsg("OPA! Erro ao adicionar compromisso!");
                }
            }else{
                throw new Exception();
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            telaInicial.mostraMsg("Erro ao adicionar compromisso!");
        }
    }
    
    public void consultaCompromisso(){
        try{  
            String data = telaInicial.getjTextFieldData().getText();
            Pattern pattern = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
            Matcher matcher = pattern.matcher(data);
            if(matcher.find() || data.isBlank()){
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
            }else{
                throw new Exception();
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            telaInicial.mostraMsg("Erro ao Consultar compromisso!\nFomato da data - DD/MM/YYYY");
        }    
    }
    
}
