package begin.flywayspringmaven.common.service.aws.ses;

public interface EmailSender {

    /**
     * Send email.
     *
     * @param emailSubject email Subject
     * @param emailFrom    email From
     * @param emailTo      list email To
     * @param content      content
     */
    void sendEmail(String emailSubject, String emailFrom, String emailTo, String content);
}
