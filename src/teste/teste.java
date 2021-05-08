package teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.classes.Compromisso;
import view.TelaInicial;

/**
 * @author RafaelRodrigues1
 */
public class teste {
    
    public static void main(String[] args) throws ParseException {
        TelaInicial telaInicial = new TelaInicial();
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date data = dataFormatada.parse("08/05/2021" + " " + "16:30");
        System.out.println(data);
        Compromisso compromisso = new Compromisso("OPA", data, "tt");
        System.out.println(compromisso);
        
    }
}
