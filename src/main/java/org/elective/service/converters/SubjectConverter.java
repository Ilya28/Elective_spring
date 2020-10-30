package org.elective.service.converters;

import org.elective.dto.CourseDTO;
import org.elective.dto.SubjectDTO;
import org.elective.entity.Course;
import org.elective.entity.Subject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectConverter {
    public SubjectDTO subjectToSubjectDTO(Subject subject, String locale) {
        String i18nName = "ua".equalsIgnoreCase(locale)? subject.getNameUA(): subject.getNameEN();
        return SubjectDTO.builder()
                .backgroundFile(subject.getBackgroundFile())
                .mapping(subject.getMapping())
                .name(i18nName)
                .build();
    }

    public List<SubjectDTO> subjectsToSubjectsDTO(List<Subject> subjects, String locale) {
        return subjects.stream()
                .map(c -> subjectToSubjectDTO(c, locale))
                .collect(Collectors.toList());
    }

    public Subject subjectDTOToSubject(SubjectDTO subjectDTO) {
        return Subject.builder()
                .backgroundFile(subjectDTO.getBackgroundFile())
                .mapping(subjectDTO.getMapping())
                .nameEN(subjectDTO.getNameEN())
                .nameUA(subjectDTO.getNameUA())
                .build();
    }
}
