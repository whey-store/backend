package begin.flywayspringmaven.api.email;

import begin.flywayspringmaven.api.email.dto.EmailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/email")
@Validated
public class EmailController {
    private final EmailService emailService;
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody EmailDTO emailDTO) throws Exception{
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailDTO.getFromEmail());
        simpleMailMessage.setTo(emailDTO.getToEmail());
        simpleMailMessage.setSubject(emailDTO.getSubject());
        simpleMailMessage.setText(emailDTO.getBody());
        this.emailService.sendMessage(simpleMailMessage);
        return "Email sent successfully";
    }
}
