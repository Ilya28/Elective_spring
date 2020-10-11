package org.elective.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectDTO {
    private String name;
    private String mapping;
    private String backgroundFile;
}
