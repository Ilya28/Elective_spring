package org.elective.repos;

import org.elective.entity.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepo  extends CrudRepository<Subject, Long> {
    Optional<Subject> findSubjectByNameEN(String name);
    Optional<Subject> findSubjectByMapping(String mapping);
    List<Subject> findAll();

    Long deleteSubjectByMapping(String mapping);
}
