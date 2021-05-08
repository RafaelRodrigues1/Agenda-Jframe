package model.classes;

/**
 * @author RafaelRodrigues1
 */
public class Compromisso implements Comparable<Compromisso> {
    
    private String descricao;
    private Data data;
    private Integer horario;
    private String local;


    public Compromisso(String descricao, Data data, Integer horario) {
        this.descricao = descricao;
        this.data = data;
        this.horario = horario;
    }

    public Compromisso(String descricao, Data data, Integer horario, String local) {
        this.descricao = descricao;
        this.data = data;
        this.horario = horario;
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Integer getHorario() {
        return horario;
    }

    public void setHorario(Integer horario) {
        this.horario = horario;
    }

    @Override
    public String toString(){
        return "Data: " + this.getData() + "\n"
                + "Horário: " + this.horario + " horas" + ""
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
        if(this.data.equals(o.getData())){
            return this.getHorario().compareTo(o.getHorario());
        }else{
            return this.getData().compareTo(o.getData());
        }
    }  
}
