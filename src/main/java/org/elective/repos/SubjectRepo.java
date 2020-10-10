package org.elective.repos;

import org.elective.entity.Subject;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubjectRepo  extends CrudRepository<Subject, Long> {
    public Optional<Subject> findSubjectByNameEN(String name);
}
