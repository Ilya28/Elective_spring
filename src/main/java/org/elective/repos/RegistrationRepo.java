package org.elective.repos;

import org.elective.entity.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepo extends CrudRepository<Registration, Long> {
    Optional<Registration> findByCourse_IdAndUser_Email(Long courseId, String userEmail);
    Page<Registration> findRegistrationByCourse_NameEN(String courseNameEN, Pageable pageable);
    Page<Registration> findRegistrationsByUser_Id(Long id, Pageable pageable);
    Page<Registration> findRegistrationsByUser_Email(String email, Pageable pageable);
    Optional<Registration> findRegistrationByUser_IdAndCourse_Id(Long userId, Long courseId);

    void deleteRegistrationByUser_EmailAndCourse_Id(String email, Long id);
    void deleteRegistrationsByUser_Id(Long id);
    void deleteRegistrationsByCourse_Id(Long id);
}