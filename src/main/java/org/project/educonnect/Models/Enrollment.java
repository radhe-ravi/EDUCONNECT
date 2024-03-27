package org.project.educonnect.Models;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enrollments")
@Entity
@Getter
@Setter
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "enrollment_id"
    )
    private Integer id;

    @Column(
            name = "enrollment_date"
    )
    private Timestamp enrollmentDate;

    @Column(
            name = "is_favourite"
    )
    private boolean isFavourite;

    @PrePersist
    public void prePersist(){
        this.enrollmentDate = new Timestamp(System.currentTimeMillis());
    }
    public Timestamp getEnrollment(){
        return this.enrollmentDate;
    }

    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private Users users;

    @ManyToOne
    @JoinColumn(
            name = "course_id"
    )
    private Course course;

}
