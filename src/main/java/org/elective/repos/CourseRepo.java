package org.elective.repos;

import org.elective.domain.dto.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepo  extends CrudRepository<Course, Long> {

}
