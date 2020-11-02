package org.elective.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Course implements Serializable {
    private static final long serialVersionUID = 1211782142525898152L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameEN;
    private String nameUA;
    @Column(length = 6000)
    private String descriptionEN;
    @Column(length = 6000)
    private String descriptionUA;
    private String backgroundFile;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private int seats;
    private int signedUp;

    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;

    @ManyToOne(fetch = FetchType.EAGER)
    private User teacher;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Registration> registrationSet;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Topic> topicSet;
}
