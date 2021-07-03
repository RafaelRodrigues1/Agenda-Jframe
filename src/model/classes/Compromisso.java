package model.classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author RafaelRodrigues1
 */

@Entity
@Table(name = "compromisso")
public class Compromisso implements Comparable<Compromisso>, Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;
    
    @Column(name = "data", nullable = false)
    private LocalDate data;
    
    @Column(name = "horario", nullable = false)
    private LocalTime hora;
    
    @Column(name = "local", length = 20)
    private String local;

    public Compromisso() {
    }  
    
    public Compromisso(String descricao, LocalDate data, LocalTime hora) {
        this.descricao = descricao;
        this.data = data;
        this.hora = hora;
    }

    public Compromisso(String descricao, LocalDate data, LocalTime hora, String local) {
        this.descricao = descricao;
        this.data = data;
        this.hora = hora;
        this.local = local;
    }

    public Compromisso(Integer id, String descricao, LocalDate data, LocalTime hora, String local) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.hora = hora;
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public LocalDateTime getDateTime(){
        return LocalDateTime.of(this.data, this.hora);
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
        return this.getDateTime().compareTo(o.getDateTime());
    }  
}
