package org.elective.dto;

import lombok.*;

@Builder
@Data
public class RegistrationDTO {
    private Long courseId;
    private String courseName;
    private String courseStatus;
    private Long userId;

    private int progress;
    private int grade;
}
