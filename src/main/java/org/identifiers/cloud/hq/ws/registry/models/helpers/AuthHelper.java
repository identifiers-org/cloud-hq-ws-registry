package org.identifiers.cloud.hq.ws.registry.models.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.models.helpers
 * Timestamp: 2019-08-20 14:39
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 *
 * It provides help on working with the authentication layer, specially when it comes to extracting information from it.
 */
@Component
public class AuthHelper {
    // Helpers
    private boolean isAuthenticated() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String) {
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
                return false;
            }
        }
        return true;
    }
    
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (isAuthenticated()) {
            if (authentication.getPrincipal() instanceof Jwt) {
                Jwt jwtToken = (Jwt) authentication.getPrincipal();
                return jwtToken.getClaims().getOrDefault("preferred_username", "UNKNOWN").toString();
            }
            return "UNKNOWN";
        }
        return "ANONYMOUS";
    }
}
