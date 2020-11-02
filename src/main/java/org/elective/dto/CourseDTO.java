package org.elective.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourseDTO {
    private Long id;
    private String teacherName;
    private Long teacherId;
    private String name; // localized
    @NotBlank
    private String nameEN;
    @NotBlank
    private String nameUA;
    private String description;
    @NotBlank
    private String descriptionEN;
    @NotBlank
    private String descriptionUA;
    private String backgroundFile;
    @Pattern(regexp = "^\\d{4}-([0]\\d|1[0-2])-([0-2]\\d|3[01])$")
    private String dateStart;
    @Pattern(regexp = "^\\d{4}-([0]\\d|1[0-2])-([0-2]\\d|3[01])$")
    private String dateEnd;
    @Positive
    private int seats;
    private int signedUp;
    private String subject;
    private String subjectMapping;
}
