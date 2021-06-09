package controller;

import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.classes.Compromisso;
import model.classes.CompromissoRN;
import model.classes.Panes;
import view.TelaConsulta;
import view.TelaInicial;

/**
 * @author RafaelRodrigues1
 */
public class TelaConsultaController {

    private DateTimeFormatter dataForm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter horaForm = DateTimeFormatter.ofPattern("HH:mm");
    private final TelaConsulta telaConsulta;
    private final CompromissoRN compromissoRN = new CompromissoRN(this);
    public TelaConsultaController(TelaConsulta telaConsulta) {
        this.telaConsulta = telaConsulta;
        
    }
    
    public Boolean consultaCompromisso(String data) throws Exception {
        try{            
            return this.preencheTabela(compromissoRN.consultaCompromisso(data));
        }catch(Exception ex){
            ex.printStackTrace();
             throw new Exception();
        }
    }
    
    public void alteraCompromisso(){
        try{
            int row = telaConsulta.getjTable1().getSelectedRow();
            int id = (int) telaConsulta.getjTable1().getValueAt(row, 0);
            if(compromissoRN.alteraCompromisso(id, telaConsulta.getjTable1().getValueAt(row, 3).toString(), 
                    telaConsulta.getjTable1().getValueAt(row, 1).toString(), 
                    telaConsulta.getjTable1().getValueAt(row, 2).toString(), 
                    telaConsulta.getjTable1().getValueAt(row, 4).toString())){
                Panes.mostraMsg("Compromisso alterado com sucesso");
            }else{
                throw new Exception("Erro ao alterar compromisso");
            }
        }catch(Exception ex){
            ex.printStackTrace();
            Panes.mostraMsg(ex.getMessage());
        }
    }
    
    public void deletaCompromisso(){
        try{
            int row = telaConsulta.getjTable1().getSelectedRow();
            int id = (int) telaConsulta.getjTable1().getValueAt(row, 0);
            if(compromissoRN.deletaCompromisso(id)){
                Panes.mostraMsg("Compromisso deletado com sucesso");
                telaConsulta.getjTable1().removeRowSelectionInterval(row, row+3);
            }else{
                throw new Exception("Erro ao deletar compromisso");
            }
        }catch(Exception ex){
            ex.printStackTrace();
            Panes.mostraMsg(ex.getMessage());
        }
    }
    
    public Boolean preencheTabela(List<Compromisso> compromissos){       
        if(!compromissos.isEmpty()){
            DefaultTableModel tableModel = (DefaultTableModel) telaConsulta.getjTable1().getModel();
            tableModel.setNumRows(0);
            for(Compromisso compromisso: compromissos){
                String data = dataForm.format(compromisso.getData());
                String hora = horaForm.format(compromisso.getHora());
                tableModel.addRow(new Object[]{compromisso.getId(), data,
                hora, compromisso.getDescricao(), compromisso.getLocal()});      
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
