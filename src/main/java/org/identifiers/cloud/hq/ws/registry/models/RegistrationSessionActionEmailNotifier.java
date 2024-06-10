package org.identifiers.cloud.hq.ws.registry.models;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.identifiers.cloud.hq.ws.registry.configuration.CommonEmailProperties;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

@Slf4j
public abstract class RegistrationSessionActionEmailNotifier <S, R> {
    protected static final int MAIL_REQUEST_RETRY_MAX_ATTEMPTS = 12;
    protected static final int MAIL_REQUEST_RETRY_BACK_OFF_PERIOD = 1500; // 1.5 seconds

    protected final CommonEmailProperties emailCommons;
    protected final String emailCc;
    protected final String emailBcc;
    protected final String emailSubject;
    protected final Resource emailBodyFileResource;
    protected final JavaMailSender javaMailSender;

    @RequiredArgsConstructor
    public enum Actions {
        ACCEPTANCE("acceptance", "requester"),
        CURATOR_START("start", "curator"),
        REJECTION("rejection", "requester"),
        REQUESTER_START("start", "requester");

        final String action;
        final String target;
    }

    protected RegistrationSessionActionEmailNotifier(Actions actionToNotify, CommonEmailProperties emailCommons,
                                                     JavaMailSender javaMailSender, Environment env,
                                                     ResourceLoader resourceLoader) {
        this.javaMailSender = javaMailSender;
        this.emailCommons = emailCommons;

        String basePropPrefix = "org.identifiers.cloud.hq.ws.registry.notifiers";
        String mailInfoPropPrefix = String.format("%s.%s.%s.%s.", basePropPrefix,
                actionToNotify.target, getRequestTypePropPrefix(), actionToNotify.action);

        emailCc = env.getRequiredProperty(mailInfoPropPrefix + "cc");
        emailBcc = env.getRequiredProperty(mailInfoPropPrefix + "cco");
        emailSubject = env.getRequiredProperty(mailInfoPropPrefix + "subject");
        String resourceLocation = env.getRequiredProperty(mailInfoPropPrefix + "body.filename");
        emailBodyFileResource = resourceLoader.getResource(resourceLocation);
    }


    protected abstract String getPrefixFromSession(S session);
    protected abstract String getRequesterEmailFromSession(S session);
    protected abstract String parseEmailBody(S session);
    protected abstract R newReport();
    protected abstract String getRequestTypePropPrefix();

    // Helpers
    protected String parseEmailSubject(S session) {
        return emailSubject.replace(emailCommons.getPlaceholderPrefix(),
                                    getPrefixFromSession(session));
    }

    // Interface
    @Retryable(maxAttempts = MAIL_REQUEST_RETRY_MAX_ATTEMPTS,
            backoff = @Backoff(delay = MAIL_REQUEST_RETRY_BACK_OFF_PERIOD))
    public R performAction(S session) throws PrefixRegistrationSessionActionException {
        R report = newReport();
        // Get a plain text message
        SimpleMailMessage emailMessage = getBaseEmailMessage(session);
        emailMessage.setSubject(parseEmailSubject(session));
        emailMessage.setText(parseEmailBody(session));
        javaMailSender.send(emailMessage);
        // TODO It would be nice to set something on the report
        return report;
    }

    private SimpleMailMessage getBaseEmailMessage(S session) {
        String requesterEmail = getRequesterEmailFromSession(session);
        SimpleMailMessage emailMessage = new SimpleMailMessage();
        // Set message parameters
        emailMessage.setFrom(emailCommons.getEmailSender());
        emailMessage.setReplyTo(emailCommons.getEmailReplyTo());
        emailMessage.setTo(requesterEmail);
        if (!emailCc.equals(emailCommons.getPlaceholderDoNotUse())) emailMessage.setCc(emailCc.split(","));
        if (!emailBcc.equals(emailCommons.getPlaceholderDoNotUse())) emailMessage.setBcc(emailBcc.split(","));
        return emailMessage;
    }
}
