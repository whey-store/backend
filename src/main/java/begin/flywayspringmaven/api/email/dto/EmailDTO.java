package begin.flywayspringmaven.api.email.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class EmailDTO {
    private String fromEmail;
    private String toEmail;
    private String subject;
    private String body;
}
