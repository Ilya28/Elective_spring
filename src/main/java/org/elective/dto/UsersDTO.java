package org.elective.dto;

import lombok.*;
import org.elective.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsersDTO {
    private Iterable<User> users;
}
