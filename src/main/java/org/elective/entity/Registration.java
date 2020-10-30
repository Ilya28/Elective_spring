package org.elective.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Registration implements Serializable{
    private static final long serialVersionUID = 1911782042825898252L;
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class RegistrationPK implements Serializable {
        private static final long serialVersionUID = -2939678521007522701L;
        @Column(nullable = false)
        private Long courseID;
        @Column(nullable = false)
        private Long userID;
    }

    @EmbeddedId
    private RegistrationPK id;


    @JoinColumn(name = "courseID", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
    @JoinColumn(name = "userID", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private int progress;
    private int grade;
}
