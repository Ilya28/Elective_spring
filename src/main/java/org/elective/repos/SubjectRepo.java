package org.elective.repos;

import org.elective.entity.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepo  extends CrudRepository<Subject, Long> {
    public Optional<Subject> findSubjectByNameEN(String name);
    List<Subject> findAll();
}
