package org.elective.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.dto.SubjectDTO;
import org.elective.entity.Course;
import org.elective.entity.Subject;
import org.elective.repos.SubjectRepo;
import org.elective.service.converters.SubjectConverter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubjectsService {
    private final SubjectRepo subjectRepo;
    private final SubjectConverter subjectConverter;

    public boolean exist(String mapping) {
        Optional<Subject> subject = subjectRepo.findSubjectByMapping(mapping);
        return subject.isPresent();
    }

    public void save(SubjectDTO subjectDTO) {
        Subject subject = subjectConverter.subjectDTOToSubject(subjectDTO);
        subjectRepo.save(subject);
    }

    public boolean delete(String mapping) {
        return subjectRepo.deleteSubjectByMapping(mapping) > 0;
    }

    public String getSubjectName(String mapping, String locale) {
        Optional<Subject> subject = subjectRepo.findSubjectByMapping(mapping);
        return subject.map(s -> "ua".equalsIgnoreCase(locale) ? s.getNameUA(): s.getNameEN()).orElse("");
    }
}
