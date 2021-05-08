package controller;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.classes.Compromisso;
import model.classes.CompromissoRN;
import model.classes.Data;
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
    
    public Boolean consultaCompromisso(String data){
        try{
            
            return this.preencheTabela(compromissoRN.consultaCompromisso(data));
        }catch(Exception ex){
            return false;
        }
    }
    
    public Boolean preencheTabela(List<Compromisso> compromissoList){       
        if(!compromissoList.isEmpty()){
            DefaultTableModel tableModel = (DefaultTableModel) telaConsulta.getjTable1().getModel();
            tableModel.setNumRows(0);
            for(Compromisso compromisso: compromissoList){
                tableModel.addRow(new Object[]{compromisso.getData().toString(),compromisso.getHorario().toString(),
                                   compromisso.getDescricao(), compromisso.getLocal()});      
            }
            this.telaConsulta.setVisible(true);
            return true;
            }else{
                telaConsulta.setVisible(false);
                return false;
            }
    }
    
    public void voltar(){        
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
        telaConsulta.setVisible(false);
    }
}
