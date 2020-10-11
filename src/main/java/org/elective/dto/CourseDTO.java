package org.elective.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CourseDTO {
    private String name;
    private String description;
    private String mapping;
    private String backgroundFile;
    private String dateStart;
    private String dateEnd;
    private int seats;
    private int signedUp;
    private String subject;
}
