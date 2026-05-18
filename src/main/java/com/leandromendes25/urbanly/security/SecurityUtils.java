package com.leandromendes25.urbanly.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static String getEmailFromContext(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
