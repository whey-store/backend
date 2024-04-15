package begin.flywayspringmaven.common.service.aws.ses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService implements EmailSender{

    @Autowired
    private JavaMailSender javaMailSender;
    private final Logger log = LoggerFactory.getLogger(EmailService.class);

    /**
     * Call SES to send email type mime message.
     *
     * @param emailSubject email subject
     * @param emailTo email list to send
     * @param content content of email
     */
    @Override
    public void sendEmail(String emailSubject, String emailFrom, String emailTo, String content) {
        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            log.info("Email Subject:" + emailSubject);
            log.info("From Email:" + emailFrom);
//            log.info("To Emails:" + Arrays.toString(emailTo));
            helper = new MimeMessageHelper(mimeMessage, false, StandardCharsets.UTF_8.name());
            helper.setFrom(emailFrom);
            helper.setTo(emailTo);
            helper.setText(content, true);
            helper.setSubject(emailSubject);
            this.javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            log.info("sendEmail error, error msg: {}", e.getMessage());
        }
    }

}
