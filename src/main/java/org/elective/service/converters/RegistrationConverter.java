package org.elective.service.converters;

import lombok.RequiredArgsConstructor;
import org.elective.dto.CourseDTO;
import org.elective.dto.RegistrationDTO;
import org.elective.entity.Registration;
import org.elective.service.CoursesService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationConverter {
    private final CourseConverter courseConverter;
    private final CoursesService coursesService;

    public RegistrationDTO registrationToRegistrationDTO(Registration registration, String locale) {
        CourseDTO course = courseConverter.courseToCourseDTO(registration.getCourse(), locale);
        return RegistrationDTO.builder()
                .courseId(course.getId())
                .courseName(course.getName())
                .userId(registration.getUser().getId())
                .grade(registration.getGrade())
                .progress(registration.getProgress())
                .courseStatus(coursesService.getCourseStatus(registration.getCourse()))
                .build();
    }
}
