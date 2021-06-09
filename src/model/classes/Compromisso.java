package model.classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author RafaelRodrigues1
 */
public class Compromisso implements Comparable<Compromisso> {
    
    private Integer id;
    private String descricao;
    private LocalDateTime dataHora;
    private String local;
    
    
    public Compromisso(String descricao, LocalDateTime dataHora) {
        this.descricao = descricao;
        this.dataHora = dataHora;
    }

    public Compromisso(String descricao, LocalDateTime dataHora, String local) {
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.local = local;
    }

    public Compromisso(Integer id, String descricao, LocalDateTime dataHora, String local) {
        this.id = id;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public LocalDate getData(){
        return dataHora.toLocalDate();
    }
    
    public LocalTime getHora(){
        return dataHora.toLocalTime();
    }

    @Override
    public String toString(){
        return "Data: " + this.getData() + "\n"
                + "Horário: " + this.getHora() + "\n"
                + "Tarefa: " + this.getDescricao();
    }

    public String getLocal() {
        if(local.isEmpty()){
            local =" ";
        }
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Integer getId() {
        return id;
    }
    
    @Override
    public int compareTo(Compromisso o) {
        return this.dataHora.compareTo(o.getDataHora());
    }  
}
