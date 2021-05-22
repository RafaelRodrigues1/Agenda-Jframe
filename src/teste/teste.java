package teste;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



/**
 * @author RafaelRodrigues1
 */
public class teste {
    
    public static void main(String[] args) {
        //Programa para transformar o arquivo .txt numa inserção do Mysql
        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\RAFAEL\\Documents\\NetBeansProjects\\AppAgenda\\DB\\DataBase.txt"))){
            String str = "";
            while((str = br.readLine())!= null){
                String[] linha = str.split(";");
                String[] data = linha[0].split("\\/");
                System.out.println("('"+data[2]+"-"+data[1]+"-"+ data[0]+"', '"+ linha[1]+ "', '"+
                        linha[2] +"', '" + linha[3]  +"'),");
                
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
    }
}
