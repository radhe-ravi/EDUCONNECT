package org.project.educonnect.Repository;

import org.project.educonnect.DTO.CourseDTO;
import org.project.educonnect.DTO.CourseResponseDTO;
import org.project.educonnect.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    String QUERY_ONE = "";
    @Query("SELECT NEW org.project.educonnect.DTO.CourseResponseDTO(c.id as courseId, c.courseTitle as courseTitle, c.courseDescription as courseDescription, c.author.id as authorId)"
    + "FROM Course c WHERE c.courseTitle = :courseTitle")
    Optional<CourseResponseDTO> findByTitle(@Param("courseTitle") String courseTitle);

    @Query("SELECT NEW org.project.educonnect.DTO.CourseDTO(c.id, u.id, c.courseTitle, c.courseDescription) "
            + "FROM Course c JOIN c.author u")
    List<CourseDTO> getCourses();
}
