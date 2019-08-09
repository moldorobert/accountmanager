package com.bank.retail.accountmanager.util;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public final class AuthUtil {
    private AuthUtil() {
    }

    public static String getLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return ((User) principal).getUsername();
        }
        throw new InsufficientAuthenticationException("No authenticated user");
    }
}
