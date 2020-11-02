package org.elective.entity;

import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_TEACHER, ROLE_ADMIN;
    private static final String DEFAULT_ROLE = "ROLE_ANONYMOUS";

    @Override
    public String getAuthority() {
        return name();
    }

    /**
     * Get role of current user or returns DEFAULT_ROLE (anonimous)
     * @return Role as string
     */
    public static String getCurrentUserRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        val authorities = auth.getAuthorities();
        return authorities.stream()
                .findFirst()
                .map(GrantedAuthority::toString)
                .orElse(DEFAULT_ROLE);
    }
}
