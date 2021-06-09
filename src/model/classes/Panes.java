package model.classes;

import javax.swing.JOptionPane;

/**
 * @author RafaelRodrigues1
 */
public class Panes {
    
    public static Integer consultaGeral(String string){
        int resp = JOptionPane.showConfirmDialog(null, string,"Confirmação", JOptionPane.YES_NO_OPTION);
        return resp;
    }
            
    
    public static void mostraMsg(String string){
        JOptionPane.showMessageDialog(null, string);
    }
}
