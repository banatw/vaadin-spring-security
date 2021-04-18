package com.example.application.securityhelper;

import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import com.vaadin.flow.server.HandlerHelper.RequestType;
import com.vaadin.flow.shared.ApplicationConstants;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {
    public SecurityUtils() {
        // Util methods only
    }

    public static boolean isFrameworkInternalRequest(HttpServletRequest request) {
        final String parameterValue = request.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER);
        return parameterValue != null
                && Stream.of(RequestType.values()).anyMatch(r -> r.getIdentifier().equals(parameterValue));
    }

    public static boolean isUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
                && authentication.isAuthenticated();
    }
}
