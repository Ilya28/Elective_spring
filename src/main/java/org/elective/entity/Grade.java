package org.elective.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Grade implements Serializable{
    private static final long serialVersionUID = 1511412042825897550L;
    @Embeddable
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class GradePK implements Serializable {
        private static final long serialVersionUID = -2939678521007522701L;
        @Column(nullable = false)
        private int topicID;
        @Column(nullable = false)
        private int userID;
    }
    @EmbeddedId
    private GradePK id;

    @JoinColumn(name = "topicID", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Topic topic;
    @JoinColumn(name = "userID", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private int gradeValue;
}
