/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xtension;

import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
     *
 * @author ASUS
 */
public class SendEmail {
    // scif wnqm fvbe vdqv
    private final String from = "dttung2113@gmail.com";
    private final String password = "scif wnqm fvbe vdqv";
    private Properties prop;
    private Authenticator auth;
    private MimeMessage msg;
    private Session session;
    public SendEmail()
    {
        prop = new Properties();
	prop.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
	prop.put("mail.smtp.port", "587"); // TLS 587 SSL 465
	prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
            // TODO Auto-generated method stub
                return new PasswordAuthentication(from, password);
            }
        };

        session = Session.getInstance(prop, auth);

        msg = new MimeMessage(session);
    }
    public boolean sendVertification(String to, String code)
    {
	try {
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(from);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject("Email xác thực");
            msg.setSentDate(new Date());
            msg.setContent("Mã code xác thực: " + code + "\n(Lưu ý mã code tồn tại trong 1 phút)", "text/HTML; charset=UTF-8");
            Transport.send(msg);
            return true;
	} 
        catch (MessagingException e) 
        {
            return false;
	}
    }
}
