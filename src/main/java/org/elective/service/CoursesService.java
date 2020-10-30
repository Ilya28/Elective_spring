package org.elective.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.dto.CourseDTO;
import org.elective.entity.Course;
import org.elective.entity.Registration;
import org.elective.entity.Subject;
import org.elective.entity.User;
import org.elective.repos.CourseRepo;
import org.elective.repos.RegistrationRepo;
import org.elective.repos.SubjectRepo;
import org.elective.repos.UserRepo;
import org.elective.service.converters.CourseConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CoursesService {
    private static final String DEFAULT_DESCRIPTION_EN = "No description";
    private static final String DEFAULT_DESCRIPTION_UA = "Немає опису";

    private final CourseRepo courseRepo;
    private final SubjectRepo subjectRepo;
    private final UserRepo userRepo;
    private final RegistrationRepo registrationRepo;
    private final CourseConverter courseConverter;

    /**
     * Mapping path for this course
     * Pattern: "/course_{id}"
     * @param course course
     * @return string with mapping path
     */
    public String getCoursePath(Course course) {
        return course.getSubject().getNameEN() + "/course_" + course.getId();
    }

    /**
     * Redirect url for this course
     * @param course course
     * @return url
     */
    public String getCourseRedirect(Course course) {
        return "redirect:/" + getCoursePath(course);
    }

    public Optional<CourseDTO> getCourseLocalizedById(Long id, String locale) {
        Optional<Course> course = courseRepo.findById(id);
        return course.map(value -> courseConverter.courseToCourseDTO(value, locale));
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepo.findById(id);
    }

    /**
     * Method returns list of 'CourseDTO' objects from CourseRepo
     * ('Course' objects converted to 'CourseDTO' objects)
     * @param locale locale (EN or UA)
     * @return List of 'CourseDTO' objects from CourseRepo
     */
    public Page<CourseDTO> getAllCoursesLocalized(String locale, Pageable pageable) {
        Page<Course> page = courseRepo.findAll(pageable);
        return page.map(e -> courseConverter.courseToCourseDTO(e, locale));
    }

    public Page<CourseDTO> findCoursesBySubjectMappingLocalized(String mapping, String locale, Pageable pageable) {
        Page<Course> page = courseRepo.findCoursesBySubjectMapping(mapping, pageable);
        return page.map(e -> courseConverter.courseToCourseDTO(e, locale));
    }

    public void saveCourse(CourseDTO courseDTO, String subjectMapping) {
        Optional<Subject> subject = subjectRepo.findSubjectByMapping(subjectMapping);
        if (!subject.isPresent()) {
            log.error("Unable to save course. Unknown subject: '{}'", subjectMapping);
            return;
        }
        if (courseDTO.getDescriptionEN() == null || courseDTO.getDescriptionEN().isEmpty())
            courseDTO.setDescriptionEN(DEFAULT_DESCRIPTION_EN);
        if (courseDTO.getDescriptionUA() == null || courseDTO.getDescriptionUA().isEmpty())
            courseDTO.setDescriptionUA(DEFAULT_DESCRIPTION_UA);
        Course course = courseConverter.courseDTOToCourse(courseDTO);
        course.setSubject(subject.get());
        course.setSignedUp(0);
        courseRepo.save(course);
    }

    public String getCourseStatus(Course course) {
        LocalDate now = LocalDate.now();
        if (course.getDateStart().isBefore(now)) {
            if (course.getDateEnd().isBefore(now))
                return "completed";
            else
                return "in_progress";
        }
        return "open";
    }

    public String getCourseStatusById(Long id) {
        Optional<Course> course = getCourseById(id);
        if (!course.isPresent())
            return "does not exist";
        return getCourseStatus(course.get());
    }

    public boolean isUserRegisteredForCourse(String email, Long courseId) {
        Optional<Registration> reg = registrationRepo.findByCourse_IdAndUser_Email(courseId, email);
        return reg.isPresent();
    }

    public void deleteCourseById(Long id) {
        courseRepo.deleteById(id);
    }
}
