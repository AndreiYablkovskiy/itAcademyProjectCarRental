package project.EE.service;

public interface EmailSenderService {
     void sendEmail (String emailTo, String subject, String message);
}
