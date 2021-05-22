package controller;

import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.classes.CompromissoRN;
import view.TelaConsulta;
import view.TelaInicial;

/**
 * @author RafaelRodrigues1
 */
public class TelaConsultaController {

    private final TelaConsulta telaConsulta;
    private final CompromissoRN compromissoRN = new CompromissoRN(this);
    public TelaConsultaController(TelaConsulta telaConsulta) {
        this.telaConsulta = telaConsulta;
        
    }
    
    public Boolean consultaCompromisso(String data) throws Exception {
        try{            
            return this.preencheTabela(compromissoRN.consultaCompromisso(data));
        }catch(Exception ex){
             throw new Exception();
        }
    }
    
    public Boolean preencheTabela(List<String> compromissoStr){       
        if(!compromissoStr.isEmpty()){
            DefaultTableModel tableModel = (DefaultTableModel) telaConsulta.getjTable1().getModel();
            tableModel.setNumRows(0);
            for(String compromisso: compromissoStr){
                String[] linha = compromisso.split(";");
                tableModel.addRow(new Object[]{linha[0], linha[1],
                linha[2], linha[3]});      
            }
            this.telaConsulta.setVisible(true);
            return true;
        }else{
            return false;
        }
    }
    public void voltar(){        
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
        telaConsulta.setVisible(false);
    }
}
