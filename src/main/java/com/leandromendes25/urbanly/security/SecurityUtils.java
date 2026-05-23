package com.leandromendes25.urbanly.security;

import com.leandromendes25.urbanly.entity.Client;
import com.leandromendes25.urbanly.entity.Seller;
import com.leandromendes25.urbanly.exceptions.UnauthorizedException;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static String getEmailFromContext(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    public static Seller getAuthenticatedSeller() throws UnauthorizedException {

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if(!(principal instanceof Seller seller)){
            throw new UnauthorizedException("Apenas vendedores");
        }

        return seller;
    }

    public static Client getAuthenticatedClient() throws UnauthorizedException {

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if(!(principal instanceof Client client)){
            throw new UnauthorizedException("Apenas clientes");
        }

        return client;
    }}
