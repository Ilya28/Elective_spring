package org.elective.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject implements Serializable {
    private static final long serialVersionUID = 5516922183825895781L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nameEN;
    private String nameUA;
    private String mapping;
    private String backgroundFile;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Course> courseSet;
}
