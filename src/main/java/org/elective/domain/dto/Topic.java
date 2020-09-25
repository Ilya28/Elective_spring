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
public class Topic implements Serializable {
    private static final long serialVersionUID = 5511412183825895567L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nameEN;
    private String nameUA;

    @ManyToOne(fetch = FetchType.EAGER)
    Course course;
}
