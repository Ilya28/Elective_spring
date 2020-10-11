package org.elective.service.converters;

import org.elective.dto.CourseDTO;
import org.elective.entity.Course;
import org.springframework.stereotype.Service;

@Service
public class CourseConverter {
    public CourseDTO CourseToCourseDTO(Course course, String locale) {
        String i18nDescription = "ua".equalsIgnoreCase(locale)?
                course.getDescriptionUA(): course.getDescriptionEN();
        String i18nName = "ua".equalsIgnoreCase(locale)?
                course.getNameUA(): course.getNameEN();
        String i18nSubject = "ua".equalsIgnoreCase(locale)?
                course.getSubject().getNameUA(): course.getSubject().getNameEN();
        return CourseDTO.builder()
                .backgroundFile(course.getBackgroundFile())
                .dateStart(course.getDateStart().toString())
                .dateEnd(course.getDateEnd().toString())
                .mapping(course.getMapping())
                .description(i18nDescription)
                .name(i18nName)
                .seats(course.getSeats())
                .signedUp(course.getSignedUp())
                .subject(i18nSubject)
                .build();
    }
}
