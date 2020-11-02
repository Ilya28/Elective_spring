package org.elective.service.converters;

import org.elective.dto.CourseDTO;
import org.elective.entity.Course;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseConverter {
    public CourseDTO courseToCourseDTO(Course course, String locale) {
        String i18nDescription = "ua".equalsIgnoreCase(locale)?
                course.getDescriptionUA(): course.getDescriptionEN();
        String i18nName = "ua".equalsIgnoreCase(locale)?
                course.getNameUA(): course.getNameEN();
        String i18nSubject = "ua".equalsIgnoreCase(locale)?
                course.getSubject().getNameUA(): course.getSubject().getNameEN();
        return CourseDTO.builder()
                .id(course.getId())
                .backgroundFile(course.getBackgroundFile())
                .dateStart(course.getDateStart().toString())
                .dateEnd(course.getDateEnd().toString())
                .description(i18nDescription)
                .name(i18nName)
                .seats(course.getSeats())
                .signedUp(course.getSignedUp())
                .subject(i18nSubject)
                .subjectMapping(course.getSubject().getMapping())
                .teacherId(course.getTeacher().getId())
                .teacherName(course.getTeacher().getName())
                .build();
    }

    public List<CourseDTO> coursesToCoursesDTO(List<Course> courses, String locale) {
        return courses.stream()
                .map(c -> courseToCourseDTO(c, locale))
                .collect(Collectors.toList());
    }

    public Course courseDTOToCourse(CourseDTO courseDTO) {
        return Course.builder()
                .nameEN(courseDTO.getNameEN())
                .nameUA(courseDTO.getNameUA())
                .backgroundFile(courseDTO.getBackgroundFile())
                .dateStart(LocalDate.parse(courseDTO.getDateStart()))
                .dateEnd(LocalDate.parse(courseDTO.getDateEnd()))
                .seats(courseDTO.getSeats())
                .descriptionEN(courseDTO.getDescriptionEN())
                .descriptionUA(courseDTO.getDescriptionUA())
                .signedUp(courseDTO.getSignedUp())
                .build();
    }
}
