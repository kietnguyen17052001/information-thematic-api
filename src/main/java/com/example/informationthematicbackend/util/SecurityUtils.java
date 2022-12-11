package com.example.informationthematicbackend.util;

import com.example.informationthematicbackend.security.CustomUser;
import com.example.informationthematicbackend.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static UserPrincipal getPrincipal() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            CustomUser principal = (CustomUser) auth.getPrincipal();
            UserPrincipal userPrincipal = new UserPrincipal();
            userPrincipal.setUserId(principal.getUserId());
            userPrincipal.setFirstName(principal.getFirstName());
            userPrincipal.setLastName(principal.getLastName());
            userPrincipal.setStreet(principal.getStreet());
            userPrincipal.setDistrict(principal.getDistrict());
            userPrincipal.setCity(principal.getCity());
            userPrincipal.setRole(principal.getRole());
            userPrincipal.setSchoolId(principal.getSchoolId());
            userPrincipal.setCreatedDate(principal.getCreatedDate());
            return userPrincipal;
        }
        return null;
    }
}