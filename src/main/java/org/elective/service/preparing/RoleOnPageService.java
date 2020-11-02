package org.elective.service.preparing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.entity.Role;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoleOnPageService {
    private static final String ROLE_HOLDER = "role";
    private static final String DEFAULT_ROLE = "ROLE_ANONYMOUS";

    public void putRole(Map<String, Object> model) {
        model.put(ROLE_HOLDER, Role.getCurrentUserRole());
    }
}
