package org.elective.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.dto.CourseDTO;
import org.elective.dto.RegistrationDTO;
import org.elective.entity.Role;
import org.elective.service.CoursesService;
import org.elective.service.RegistrationsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/{locale:\\ben|ua\\b}/home")
public class HomeController {
    private static final String HOME_PAGE = "home";
    private final CoursesService coursesService;
    private final RegistrationsService registrationsService;

    @RequestMapping
    public String homePage(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                                       @PathVariable String locale,
                                       Map<String, Object> model) {
        log.info("Home page");
        String userRole = Role.getCurrentUserRole();
        log.info("User role: {}", userRole);
        if (userRole.equals(Role.ROLE_USER.name())) {
            Page<RegistrationDTO> registrations = registrationsService.getAllRegistrationsForUserWithEmail(
                    SecurityContextHolder.getContext().getAuthentication().getName(),
                    locale,
                    pageable
            );
            log.info("Home page for user");
            model.put("registrations", registrations);
        } else if (userRole.equals(Role.ROLE_TEACHER.name())) {
            Page<CourseDTO> courses = coursesService.getCoursesByTeacherUsername(
                    SecurityContextHolder.getContext().getAuthentication().getName(),
                    locale,
                    pageable
            );
            log.info("Home page for teacher");
            model.put("courses", courses);
        } else {
            log.info("Home page for admin");
        }
        return HOME_PAGE;
    }

    @ModelAttribute
    public void preparePage(@PathVariable String locale,
                            HttpServletRequest request,
                            Map<String, Object> model) {
        coursesService.prepareAllStaticOnPage(model, locale);
        String uri = request.getRequestURI();
        model.put("uri", uri);
    }
}
