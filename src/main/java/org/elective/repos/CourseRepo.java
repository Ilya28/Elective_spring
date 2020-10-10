package org.elective.repos;

import org.elective.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CourseRepo  extends CrudRepository<Course, Long> {
    public List<Course> findCoursesBySubjectId(Integer id);
    public List<Course> findCoursesBySubjectNameEN(String name);
    Optional<Course> findCourseBySubjectNameEN(String name);
    Optional<Course> findCourseByNameEN(String name);
}
