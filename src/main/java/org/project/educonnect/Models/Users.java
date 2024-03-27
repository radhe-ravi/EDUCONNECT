package org.project.educonnect.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user_info")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Users {

    @Id
    @Column(
            name = "user_id"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;

    @Column(
            name = "name"
    )
    private String name;

    @Column(
            name = "password"
    )
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Course> courses;

    @OneToMany(mappedBy = "user_info",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Enrollment> enrollments;
}
