package project.car_rental.service;

public interface EmailSenderService {
     void sendEmail (String emailTo, String subject, String message);
}
