package model.classes;

/**
 * @author RafaelRodrigues1
 */
public class Data implements Comparable<Data>{
    
    private Integer dia;
    private Integer mes;
    private Integer ano;

    public Data(String data) {
        String[] datas = data.split("/");
        this.dia = Integer.parseInt(datas[0]);
        this.mes = Integer.parseInt(datas[1]);
        this.ano = Integer.parseInt(datas[2]);
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return String.format("%02d", getDia()) + "/" + String.format("%02d", getMes())  + "/" +String.format("%04d", getAno()) ;
    }    

    @Override
    public int compareTo(Data o) {
        if(this.getAno().equals(o.getAno())){
            if(this.getMes().equals(o.getMes())){
                return this.getDia().compareTo(o.getDia());
            }else{
                return this.getMes().compareTo(o.getMes());
            }
        }else{
            return this.toString().compareTo(o.toString());
        }       
    }
}
