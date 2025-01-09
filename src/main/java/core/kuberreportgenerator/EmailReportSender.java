package core.kuberreportgenerator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailReportSender implements ReportSender {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String emailFrom;

    @Override
    public void sendReport(UserInfoDto userInfoDto, String report) {
        log.info("Sending report to email: {}", userInfoDto.getEmail());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(userInfoDto.getEmail());
        message.setSubject("Generated Plain Report");
        message.setText(report);
        try {
            mailSender.send(message);
        } catch (MailException e) {
            log.error("Error while sending email", e);
            throw e;   //якщо не вдалося відправити емейл, і виникла помилка то kafka буду пробувати відправити повідомлення консюмеру ще раз
        }
    }
}


