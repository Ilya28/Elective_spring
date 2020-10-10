package org.elective.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private String role;
    private boolean blocked;
    private String language;
}
