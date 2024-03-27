package org.project.educonnect.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "courses")
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int id;

    @Column(name = "course_title")
    private String courseTitle;

    @Column(name = "course_description")
    private String courseDescription;

    //for courses to user
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Users author;

    //for course to enrollments
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Enrollment> enrollments;
}
