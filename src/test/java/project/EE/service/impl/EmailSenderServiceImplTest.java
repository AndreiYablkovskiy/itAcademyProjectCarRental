package project.EE.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailSenderServiceImplTest {
    @InjectMocks
    EmailSenderServiceImpl emailSenderService;
    @Mock
    private JavaMailSender javaMailSender;

    @Test
    void sendEmail() {
        emailSenderService.sendEmail("some@mail.ru","some subject", "some message");

        Mockito.verify(javaMailSender, Mockito.times(1)).send(ArgumentMatchers.any(SimpleMailMessage.class));

    }
}