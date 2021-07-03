package controller;

import model.beans.CompromissoRN;
import view.TelaConsulta;
import view.TelaInicial;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.beans.TipoOperacao;
import model.classes.Panes;


/**
 *
 * @author RafaelRodrigues1
 */
public class TelaInicialController {
    
    private final TelaInicial telaInicial;
    private final CompromissoRN compromissoRN;
    
    
    public TelaInicialController(TelaInicial telaInicial) {
        this.telaInicial = telaInicial;
        compromissoRN = new CompromissoRN(this);
    }
    
    public void addCompromisso(){
        try{
            if(!telaInicial.getjTextAreaCompromisso().getText().isBlank()
                    &&!telaInicial.getjTextFieldData().getText().isBlank()
                    &&!telaInicial.getjTextFieldHora().getText().isBlank()){
                String dataStr = telaInicial.getjTextFieldData().getText();
                String hora = telaInicial.getjTextFieldHora().getText();
                String local = telaInicial.getjTextFieldLocal().getText();
                String descricao = telaInicial.getjTextAreaCompromisso().getText();
                if(compromissoRN.direcionar(TipoOperacao.ADICIONAR, descricao, dataStr, hora, local)){
                    Panes.mostraMsg("Compromisso adicionado");
                }else{
                    Panes.mostraMsg("OPA! Erro ao adicionar compromisso!");
                }
            }else{
                throw new Exception();
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            Panes.mostraMsg("Erro ao adicionar compromisso!");
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
                    int resp = Panes.consultaGeral("Deseja consultar todos os compromisso?");
                    if(resp==0){
                        if(telaConsultaController.consultaCompromisso(data)){
                            telaConsultaController.getTelaConsulta().setVisible(true);
                            telaInicial.setVisible(false);                            
                        }
                    }                
                }else{  
                    if(telaConsultaController.consultaCompromisso(data)){
                        telaConsultaController.getTelaConsulta().setVisible(true);
                        telaInicial.setVisible(false);
                    }else{
                        Panes.mostraMsg("Não há compromisso no dia " + telaInicial.getjTextFieldData().getText());
                    }
                }
            }else{
                throw new Exception();
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            Panes.mostraMsg("Erro ao Consultar compromisso!\nFomato da data - DD/MM/YYYY");
        }    
    }
    
    public void imprimePdf(){
        compromissoRN.imprimePdf();
    }
}
