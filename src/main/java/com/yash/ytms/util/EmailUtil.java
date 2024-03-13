package com.yash.ytms.util;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * Project Name - ytms-api
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yash.raj
 * @since - 25-01-2024
 */
@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String mailUsername;
    @Value("${spring.mail.password}")
    private String mailPassword;

    @Value("${attachFile.filePath}")
    private String filePath;

    @Value("${attachFile.fileExt}")
    private String fileExt;

    public void sendSetPasswordEmail(String email) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("SetPassword");
        mimeMessageHelper.setText("""
                	        
                 <a href="http://localhost:4200/reset-password?session=%s">click here to reset password</a>
                	        
                """.formatted(Base64.getEncoder().encodeToString(email.getBytes())), true);

        javaMailSender.send(mimeMessage);
    }

    public void sendNotificationMailForTechnicalManage(List<String> usersList) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        String[] to =  createStringArray(usersList);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject("Notification Mail");
        mimeMessageHelper.setText(" has been submitted a request, if you want to Approved/Reject, " + "Please go to dashboard and take action ");

        javaMailSender.send(mimeMessage);
    }

    private String[] createStringArray(List<String> usersList) {
        String[] emailIds = new String[usersList.size()];

        for (int i = 0; i < usersList.size(); i++) {
            emailIds[i] = usersList.get(i);
        }
        return  emailIds;
    }

    public void sendNotificationMailForRequestApproved(String email, String fileName) throws MessagingException {
        Session session = javaMailSender.createMimeMessage().getSession();
        session = Session.getInstance(session.getProperties(), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUsername, mailPassword);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Notification Mail ");
            // Create the email body
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(email + " your request has been Approved");
            // Attach the file
            File f = new File(filePath+fileName+fileExt);

            // Checking if the specified file exists or not
            if (f.exists()) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(filePath + fileName + fileExt);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                // attachmentBodyPart.setFileName("Demo.xlsx");
                attachmentBodyPart.setFileName(fileName + fileExt);
                // Create a multipart message
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                multipart.addBodyPart(attachmentBodyPart);
                // Set the content of the message
                message.setContent(multipart);
            }
            // Send the email
            Transport.send(message);

        } catch (Exception ex) {
            System.out.println("Error   " + ex.getMessage());
        }
    }

    public void sendNotificationMailForRequestReject(String email) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Notification Mail");
        mimeMessageHelper.setText(email + " request has been Rejected");

        javaMailSender.send(mimeMessage);
    }

}
