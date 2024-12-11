package org.project.educonnect.Repository;


import org.project.educonnect.Models.Enrollment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepositoryImplementation<Enrollment, Integer> {

	@Query("SELECT COUNT(e) > 0 FROM Enrollment e WHERE e.users.id = :userId AND e.course.id = :courseId")
	boolean existsByUserIdAndCourseId(int userId, int courseId);

	@Query(value = "SELECT is_favourite FROM enrollments WHERE user_id = :userId AND course_id = :courseId", nativeQuery = true)
	boolean isAlreadyFavourite(int userId, int courseId);

	@Query(value = "SELECT * "
			+ "FROM enrollments e WHERE e.user_id = :userId AND e.is_favourite = true", nativeQuery = true)
	List<Enrollment> findAllFavoritesByUserId(int userId);

}
