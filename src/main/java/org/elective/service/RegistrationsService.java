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

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationsService {
    private final RegistrationRepo registrationRepo;
    private final RegistrationConverter registrationConverter;
    private final CourseRepo courseRepo;
    private final UserRepo userRepo;

    public Page<RegistrationDTO> getAllRegistrationsForUser(Long id, String locale, Pageable pageable) {
        Page<Registration> page = registrationRepo.findRegistrationsByUser_Id(id, pageable);
        return page.map(r -> registrationConverter.registrationToRegistrationDTO(r, locale));
    }

    public Optional<RegistrationDTO> getRegistrationByUserIdAndCourseId(Long userId, Long courseId, String locale) {
        Optional<Registration> registration = registrationRepo.findRegistrationByUser_IdAndCourse_Id(userId, courseId);
        return registration.map(r -> registrationConverter.registrationToRegistrationDTO(r, locale));
    }

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
        Registration registration = Registration.builder()
                .id(new Registration.RegistrationPK(courseId, user.get().getId()))
                .grade(0)
                .progress(0)
                .build();
        registrationRepo.save(registration);
    }
}
