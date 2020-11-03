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
    /**
     * Converts subject entity to DTO object (and localized it)
     * @param subject Entity
     * @param locale Locale
     * @return DTO object
     */
    public SubjectDTO subjectToSubjectDTO(Subject subject, String locale) {
        String i18nName = "ua".equalsIgnoreCase(locale)? subject.getNameUA(): subject.getNameEN();
        return SubjectDTO.builder()
                .backgroundFile(subject.getBackgroundFile())
                .mapping(subject.getMapping())
                .name(i18nName)
                .build();
    }

    /**
     * Converts DTO to subject entity
     * @param subjectDTO DTO
     * @return Entity
     */
    public Subject subjectDTOToSubject(SubjectDTO subjectDTO) {
        return Subject.builder()
                .backgroundFile(subjectDTO.getBackgroundFile())
                .mapping(subjectDTO.getMapping())
                .nameEN(subjectDTO.getNameEN())
                .nameUA(subjectDTO.getNameUA())
                .build();
    }
}
