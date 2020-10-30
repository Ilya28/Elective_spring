package org.elective.repos;

import org.elective.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepo  extends CrudRepository<Course, Long> {
    Page<Course> findCoursesBySubjectMapping(String mapping, Pageable pageable);
    public Page<Course> findAll(Pageable pageable);
    Optional<Course> findCourseByNameEN(String name);
}
