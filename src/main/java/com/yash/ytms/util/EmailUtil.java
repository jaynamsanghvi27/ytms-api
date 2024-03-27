package com.yash.ytms.util;

import java.io.File;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.yash.ytms.domain.TrainingRequestForm;
import com.yash.ytms.domain.YtmsUser;
import com.yash.ytms.repository.YtmsUserRepository;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

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
    
    @Autowired
    YtmsUserRepository userRepository;

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

    public void sendNotificationMailForTechnicalManage(List<String> usersList,String email) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        String[] to =  createStringArray(usersList);
        YtmsUser user=userRepository.getById(email);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject("New Training Request Notification");
        mimeMessageHelper.setText(user.getFullName()+" has been submitted a request, if you want to Approved/Reject, " + "Please go to dashboard and take action ");

        javaMailSender.send(mimeMessage);
    }

    private String[] createStringArray(List<String> usersList) {
        String[] emailIds = new String[usersList.size()];

        for (int i = 0; i < usersList.size(); i++) {
            emailIds[i] = usersList.get(i);
        }
        return  emailIds;
    }

    public void sendNotificationMailForRequestApproved(String email, String fileName, TrainingRequestForm formDto) throws MessagingException {
        Session session = javaMailSender.createMimeMessage().getSession();
        session = Session.getInstance(session.getProperties(), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUsername, mailPassword);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Notification for Training Approval");
//            message.setContent(email + " your request has been Approved.<br><br>"+approveMailBody(formDto),"text/html");
            // Create the email body
            YtmsUser user=userRepository.getById(email);
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("Hi "+user.getFullName() + "<br> your request has been Approved.<br> Please find below training details and attachment for the training plan"+approveMailBody(formDto)+"<br><br> Thanks & Regards<br> YTMS Team","text/html; charset=utf-8");
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
    
    public String approveMailBody(TrainingRequestForm formDto) {
    	StringBuilder tableBuilder = new StringBuilder();
    	tableBuilder.append("<table border='1'>");
    	tableBuilder.append("<tr>");
    	 
    	// Add table headers
	    	tableBuilder.append("<th>").append("Training Name").append("</th>");
	    	tableBuilder.append("<td>").append(formDto.getTrainingName()).append("</td>");
    	tableBuilder.append("</tr>");
    	tableBuilder.append("<tr>");
	    	tableBuilder.append("<th>").append("Start Date").append("</th>");
	    	tableBuilder.append("<td>").append(formDto.getActualStartDate()).append("</td>");
    	tableBuilder.append("</tr>");
    	tableBuilder.append("<tr>");
	    	tableBuilder.append("<th>").append("End Date").append("</th>");
	    	tableBuilder.append("<td>").append(formDto.getActualEndDate().toString()).append("</td>");
    	tableBuilder.append("<tr>");
    	tableBuilder.append("</tr>");
	    	tableBuilder.append("<th>").append("Trainer Name").append("</th>");
	    	tableBuilder.append("<td>").append(formDto.getTrainer()).append("</td>");
    	 
    	tableBuilder.append("</tr>");
    	 
    	// Add table rows with data
    	
//    	  tableBuilder.append("<tr>");
//    	    tableBuilder.append("<td>").append(formDto.getTrainingName()).append("</td>");
//    	    tableBuilder.append("<td>").append(formDto.getActualStartDate()).append("</td>");
//    	    tableBuilder.append("<td>").append(formDto.getActualEndDate()).append("</td>");
//    	    tableBuilder.append("<td>").append(formDto.getTrainer()).append("</td>");
//    	  tableBuilder.append("</tr>");
    	
    	 
    	tableBuilder.append("</table>");
    	return tableBuilder.toString();
	}

    public void sendNotificationMailForRequestReject(String email,TrainingRequestForm formDto) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Notification for Training Rejection");
        mimeMessageHelper.setText(email + " request has been Rejected due to below reason.\n"+formDto.getDeclinedMessage());

        javaMailSender.send(mimeMessage);
    }

    public void sendMailToTechnicalManager(List<String> usersList) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        String[] to =  createStringArray(usersList);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject("New User Request Notification");
        List<YtmsUser> pendingUsers = userRepository.getAllPendingUsers();
        
        String emailText = "The following users have submitted a request:\n\n" +
                pendingUsers.stream()
                            .map(YtmsUser::getFullName)
                            .collect(Collectors.joining("\n")) +
                "\n\nIf you want to Approve/Reject, please go to the dashboard and take action.";
        
        mimeMessageHelper.setText(emailText.toString());
             
        System.out.println("==============="+emailText+"================");
        
        javaMailSender.send(mimeMessage);
    }
    
    
}
