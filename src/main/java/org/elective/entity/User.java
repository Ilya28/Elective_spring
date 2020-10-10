package org.elective.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "usr")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    enum Role {
        USER, TEACHER, ADMIN;
    }
    private static final long serialVersionUID = 1911711142825348252L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private String name;
    private String email;
    private byte[] password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean blocked;
    private String language;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Registration> registrationSet;
}
