package begin.flywayspringmaven.common.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Message bundle.
 *
 */
@Component
public class MessageService {
    private MessageSource messageSource;

    public MessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, this.buildLocaleUserLogged());
    }

    /**
     * Get lang key of user logged
     *
     */
    public Locale buildLocaleUserLogged() {
        return Locale.US;
    }

    public String buildMessages(String messageKey, String messageValue ) {
        return String.format(this.getMessage(messageKey), this.getMessage(messageValue));
    }
}
