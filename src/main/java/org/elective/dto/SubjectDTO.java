package org.elective.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectDTO {
    private String name; // localized
    @NotBlank
    private String nameEN;
    @NotBlank
    private String nameUA;
    @Pattern(regexp = "[a-z_]")
    private String mapping;
    private String backgroundFile;
}
