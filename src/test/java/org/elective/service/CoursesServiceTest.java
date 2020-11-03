package org.elective.service;

import org.elective.dto.CourseDTO;
import org.elective.entity.Course;
import org.elective.entity.Subject;
import org.elective.entity.User;
import org.elective.repos.CourseRepo;
import org.elective.repos.RegistrationRepo;
import org.elective.repos.SubjectRepo;
import org.elective.repos.UserRepo;
import org.elective.service.converters.CourseConverter;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CoursesServiceTest {
    @Autowired private CoursesService coursesService;

    @MockBean private CourseRepo courseRepo;
    @MockBean private SubjectRepo subjectRepo;
    @MockBean private UserRepo userRepo;

    @Test
    void saveCourse() {
        CourseDTO course = CourseDTO.builder()
                .nameEN("nameen")
                .nameUA("nameua")
                .descriptionEN("descin")
                .descriptionUA("decua")
                .subjectMapping("mapping")
                .dateStart("2020-01-01")
                .dateEnd("2021-01-01")
                .build();
        Mockito.doReturn(Optional.of(new Subject())).when(subjectRepo).findSubjectByMapping("java");
        Mockito.doReturn(Optional.of(new User())).when(userRepo).findByEmail("teacher");
        coursesService.saveCourse(course, "teacher", "java");
        Mockito.verify(courseRepo, Mockito.times(1)).save(ArgumentMatchers.any(Course.class));
    }

    @Test
    void getCourseStatus() {
        Course course_completed = Course.builder()
                .dateStart(LocalDate.parse("1920-01-01"))
                .dateEnd(LocalDate.parse("1921-01-01"))
                .build();
        Course course_in_progress = Course.builder()
                .dateStart(LocalDate.parse("1920-01-01"))
                .dateEnd(LocalDate.parse("2121-01-01"))
                .build();
        Course course_open = Course.builder()
                .dateStart(LocalDate.parse("2120-01-01"))
                .dateEnd(LocalDate.parse("2121-01-01"))
                .build();
        Assert.assertEquals("completed", coursesService.getCourseStatus(course_completed));
        Assert.assertEquals("in_progress", coursesService.getCourseStatus(course_in_progress));
        Assert.assertEquals("open", coursesService.getCourseStatus(course_open));
    }
}