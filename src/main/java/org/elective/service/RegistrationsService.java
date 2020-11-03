package org.elective.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.dto.RegistrationDTO;
import org.elective.entity.Course;
import org.elective.entity.Registration;
import org.elective.entity.User;
import org.elective.repos.CourseRepo;
import org.elective.repos.RegistrationRepo;
import org.elective.repos.UserRepo;
import org.elective.service.converters.RegistrationConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * Class for working with registrations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationsService {
    private final RegistrationRepo registrationRepo;
    private final RegistrationConverter registrationConverter;
    private final CourseRepo courseRepo;
    private final UserRepo userRepo;

    /**
     * Get page of all registration for user with the specific id
     * @param id Id of user
     * @param locale Locale (for course name in the specific language)
     * @param pageable for pagination
     * @return page (paginated list) or RegistrationDTO
     */
    public Page<RegistrationDTO> getAllRegistrationsForUser(Long id, String locale, Pageable pageable) {
        Page<Registration> page = registrationRepo.findRegistrationsByUser_Id(id, pageable);
        return page.map(r -> registrationConverter.registrationToRegistrationDTO(r, locale));
    }

    public Page<RegistrationDTO> getAllRegistrationsForUserWithEmail(String email, String locale, Pageable pageable) {
        Page<Registration> page = registrationRepo.findRegistrationsByUser_Email(email, pageable);
        return page.map(r -> registrationConverter.registrationToRegistrationDTO(r, locale));
    }

    public Optional<RegistrationDTO> getRegistrationByUserIdAndCourseId(Long userId, Long courseId, String locale) {
        Optional<Registration> registration = registrationRepo.findRegistrationByUser_IdAndCourse_Id(userId, courseId);
        return registration.map(r -> registrationConverter.registrationToRegistrationDTO(r, locale));
    }

    /**
     * Add registration to user by email for specific course by Id
     * @param email User email (username)
     * @param courseId Id of the course
     */
    @Transactional
    public void registerByEmailAndCourseId(String email, Long courseId) {
        Optional<User> user = userRepo.findByEmail(email);
        Optional<Course> course = courseRepo.findById(courseId);
        if (!user.isPresent()) {
            log.warn("Cant find user with email {}", email);
            return;
        }
        if (!course.isPresent()) {
            log.warn("Cant find course with id {}", courseId);
            return;
        }
        course.get().setSignedUp(course.get().getSignedUp() + 1);
        courseRepo.save(course.get());
        Registration registration = Registration.builder()
                .id(new Registration.RegistrationPK(courseId, user.get().getId()))
                .grade(0)
                .progress(0)
                .build();
        registrationRepo.save(registration);
    }

    /**
     * Cancel registration to user by email for specific course by Id
     * @param email User email (username)
     * @param courseId Id of the course
     */
    @Transactional
    public void cancelRegistrationByEmailAndCourseId(String email, Long courseId) {
        Optional<Course> course = courseRepo.findById(courseId);
        if (!course.isPresent()) {
            log.warn("Cant find course with id {}", courseId);
            return;
        }
        course.get().setSignedUp(course.get().getSignedUp() - 1);
        courseRepo.save(course.get());
        registrationRepo.deleteRegistrationByUser_EmailAndCourse_Id(email, courseId);
    }
}
