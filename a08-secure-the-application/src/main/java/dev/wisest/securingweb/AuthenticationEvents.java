package dev.wisest.securingweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEvents {

    Logger logger = LoggerFactory.getLogger(AuthenticationEvents.class);

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        logger.info("Authentication success: {}", success);
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
        logger.info("Authentication failure: {}", failures);
    }

}