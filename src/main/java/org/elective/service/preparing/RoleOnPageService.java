package org.elective.service.preparing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoleOnPageService {
    private static final String ROLE_HOLDER = "role";
    private static final String DEFAULT_ROLE = "ROLE_ANONYMOUS";

    public void putRole(Map<String, Object> model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        val authorities = auth.getAuthorities();
        model.put(ROLE_HOLDER, authorities.stream()
                .findFirst()
                .map(GrantedAuthority::toString)
                .orElse(DEFAULT_ROLE));
    }
}
