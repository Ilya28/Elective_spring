package org.elective.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.dto.SubjectDTO;
import org.elective.entity.Subject;
import org.elective.repos.SubjectRepo;
import org.elective.service.converters.SubjectConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class NavbarService {
    public static final String SUBJECTS_LIST = "subjects";

    private final SubjectRepo subjectRepo;
    private final SubjectConverter subjectConverter;

    public void putSubjects(Map<String, Object> model, String locale) {
        List<SubjectDTO> subjects = subjectRepo.findAll().stream()
                    .map(subject -> subjectConverter.SubjectToSubjectDTO(subject, locale))
                    .collect(Collectors.toList());
        model.put(SUBJECTS_LIST, subjects);
    }
}
