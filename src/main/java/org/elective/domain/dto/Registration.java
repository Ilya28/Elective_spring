package org.elective.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Registration implements Serializable{
    private static final long serialVersionUID = 1911782042825898252L;
    @Embeddable
    @NoArgsConstructor
    public static class RegistrationPK implements Serializable {
        private static final long serialVersionUID = -2939678521007522701L;
        @Column(nullable = false)
        private int courseID;
        @Column(nullable = false)
        private int userID;
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
