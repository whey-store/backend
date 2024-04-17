package begin.flywayspringmaven.common.service;

import begin.flywayspringmaven.config.security.UserPrincipal;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    public String getMessage(String code, String langKey) {
        Locale locale = langKey != null ? Locale.forLanguageTag(langKey) : Locale.US;
        return messageSource.getMessage(code, null, locale);
    }

    public Locale buildLocaleUserLogged() {
        return Locale.US;
    }

    public String buildMessages(String messageKey, String messageValue ) {
        return String.format(this.getMessage(messageKey), this.getMessage(messageValue));
    }
}
