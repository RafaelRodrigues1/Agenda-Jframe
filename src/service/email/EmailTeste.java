/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.email;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Denner Dias
 */
public class EmailTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
        
        Email email = new Email("Teste",
                "Olha o teste\nEstamos em fase teste\nPerdoe o transtorno\n\nAtt. Biblioteca Software",
                "rafaelx7t@hotmail.com");
        
        try {
            email.enviar();
        } catch (EmailException ex) {
            ex.printStackTrace();
        }

//        String meuEmail = "softbibliotecadsb@gmail.com" ;
//        String minhaSenha = "softbiblioteca." ;
//        SimpleEmail email = new SimpleEmail();
//        email.setHostName("smtp.gmail.com");
//        email.setSmtpPort(465);
//        email.setAuthenticator(new DefaultAuthenticator(meuEmail,minhaSenha ));
//        email.setSSLOnConnect(true);
//        try{
//            email.setFrom(meuEmail);
//            email.setSubject("TESTE");
//            email.setMsg("Olha o teste\nEstamos em fase teste"
//                    + "\nPerdoe o transtorno\n\nAtt. Biblioteca Software");
//            email.addTo("rafaelx7t@hotmail.com");
//            email.send();
//            System.out.println("Enviado com sucesso");
//        }catch(EmailException e){
//            e.printStackTrace();
//        }

    }
    
}
