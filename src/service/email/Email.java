/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.email;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Classe responsável por enviar E-mail.
 * @author Denner Dias
 */
public class Email {
    static String REMETENTE_NOME = "softbibliotecadsb@gmail.com" ;
    static String REMETENTE_SENHA = "softbiblioteca." ;
    private String assunto ;
    private String mensagem;
    private String destinatario;
    
    /**
     * 
     * @param assunto  Assunto para envio do E-mail.
     * @param mensagem  Mensagem qual vai ser enviado ao destinatário.
     * @param destinatario  E-mail do destinatário.
     */
    public Email(String assunto, String mensagem, String destinatario) {
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.destinatario = destinatario;
    }

    
    
    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
   
    /**
     * Função para enviar o e-mail ao destinatário.
     */
    public void enviar () throws EmailException {
        
        SimpleEmail email = new SimpleEmail();
            email.setSSLOnConnect(true);
//            email.setSSL(true);
//            email.setTLS(true);
            
            email.setHostName("smtp.gmail.com");
            email.setSslSmtpPort("465");
            email.setAuthentication(REMETENTE_NOME, REMETENTE_SENHA);
       try {
           email.setFrom(REMETENTE_NOME);
           
           email.setDebug(true);

           email.setSubject(this.assunto);
           email.setMsg(this.mensagem);
           email.addTo(this.destinatario);//por favor trocar antes de testar!!!!

           email.send();

       } catch (EmailException e) {
           e.printStackTrace();
       }
    }
    
    private static Properties loadProperties() {
        try(FileInputStream fis = new FileInputStream("src\\service\\email\\emaildata.properties")){
            Properties prop = new Properties();
            prop.load(fis);
            return prop;
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
