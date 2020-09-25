package org.elective.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course implements Serializable {
    private static final long serialVersionUID = 1211782142525898152L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nameEN;
    private String nameUA;
    private String descriptionEN;
    private String descriptionUA;
    private String backgroundFile;
    private Date dateStart;
    private Date dateEnd;
    private int seats;
    private int signedUp;

    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Registration> registrationSet;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Topic> topicSet;
}
