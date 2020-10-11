package org.elective.service.converters;

import org.elective.dto.SubjectDTO;
import org.elective.entity.Subject;
import org.springframework.stereotype.Service;

@Service
public class SubjectConverter {
    public SubjectDTO SubjectToSubjectDTO(Subject subject, String locale) {
        String i18nName = "ua".equalsIgnoreCase(locale)? subject.getNameUA(): subject.getNameEN();
        return SubjectDTO.builder()
                .backgroundFile(subject.getBackgroundFile())
                .mapping(subject.getMapping())
                .name(i18nName)
                .build();
    }
}
