package model.classes;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author RafaelRodrigues1
 */
public class Compromisso implements Comparable<Compromisso> {
    private final SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat horarioFormatado = new SimpleDateFormat("HH:mm");
    
    private String descricao;
    private Date data;
    private String horario;
    private String local;
    
    public Compromisso(String descricao, Date data) {
        this.descricao = descricao;
        this.data = data;
        this.horario = horarioFormatado.format(data);
    }

    public Compromisso(String descricao, Date data, String local) {
        this.descricao = descricao;
        this.data = data;
        this.local = local;
        this.horario = horarioFormatado.format(data);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return dataFormatada.format(this.data); 
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString(){
        return "Data: " + this.getData() + "\n"
                + "Horário: " + this.horario + " horas" + "\n"
                + "Tarefa: " + this.getDescricao();
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public int compareTo(Compromisso o) {
        return this.data.compareTo(o.data);
    }  
}
