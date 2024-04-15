package begin.flywayspringmaven.api.email;

import begin.flywayspringmaven.api.email.dto.EmailDTO;
import begin.flywayspringmaven.api.email.dto.EmailResponseDTO;
import begin.flywayspringmaven.common.response.APIResponse;
import begin.flywayspringmaven.common.service.aws.ses.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/email")
public class EmailAWSController {

    @Autowired
    private EmailSender emailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody EmailDTO emailDTO) throws Exception{
        Map<String, Object> model = new HashMap<>();
        this.emailService.sendEmail(emailDTO.getSubject(), emailDTO.getFromEmail(), emailDTO.getToEmail(), emailDTO.getBody());
        EmailResponseDTO apiResponse = new EmailResponseDTO(
            "SEND Mail Success"
        );
        return APIResponse.okStatus(apiResponse);
    }
}
