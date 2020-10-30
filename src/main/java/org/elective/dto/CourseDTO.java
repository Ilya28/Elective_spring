package org.elective.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CourseDTO {
    private Long id;
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
    @Pattern(regexp = "[\\w_]+")
    private String backgroundFile;
    private String dateStart;
    private String dateEnd;
    private int seats;
    private int signedUp;
    private String subject;
    private String subjectMapping;
}
