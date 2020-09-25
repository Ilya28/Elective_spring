package org.elective.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "usr")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1911711142825348252L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private String name;
    private String email;
    private byte[] password;
    private String role;
    private boolean blocked;
    private String language;

    @OneToMany(mappedBy = "usr", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Registration> registrationSet;
}
