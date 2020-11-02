package org.elective.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.dto.SubjectDTO;
import org.elective.service.SubjectsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/{locale:\\ben|ua\\b}/subjects")
public class SubjectsController {
    public static final String SUBJECTS_PAGE_NAME = "subjects";
    public static final String ADD_PAGE_NAME = "subject_form";

    private final SubjectsService subjectsService;

    @RequestMapping
    public String subjectsPage(Map<String, Object> model,
                              @PathVariable String locale) {
        return SUBJECTS_PAGE_NAME;
    }

    @GetMapping("/add")
    public String subjectAddPage(Map<String, Object> model,
                               @PathVariable String locale) {
        return ADD_PAGE_NAME;
    }

    @PostMapping("/add")
    public String subjectAdd(@Valid @ModelAttribute SubjectDTO subject,
                                 Map<String, Object> model,
                                 @PathVariable String locale) {
        log.info("saving new subject: {}", subject);
        subjectsService.save(subject);
        return SUBJECTS_PAGE_NAME;
    }

    @PostMapping("/delete")
    public String subjectDelete(@RequestParam String mapping,
                                 Map<String, Object> model,
                                 @PathVariable String locale) {
        if (subjectsService.delete(mapping)) {
            log.info("deleted: {}", mapping);
        } else {
            log.debug("can't delete subject with mapping: {}", mapping);
        }
        return SUBJECTS_PAGE_NAME;
    }

    @ModelAttribute
    public void preparePage(@PathVariable String locale,
                            HttpServletRequest request,
                            Map<String, Object> model) {
        subjectsService.prepareAllStaticOnPage(model, locale);
        String uri = request.getRequestURI();
        model.put("uri", uri);
    }
}
