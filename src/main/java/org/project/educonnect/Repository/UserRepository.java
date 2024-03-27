package org.project.educonnect.Repository;
import org.project.educonnect.Models.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepositoryImplementation<Users, Integer> {

	@Query("SELECT u.id AS userID, u.userName AS userName, u.role AS userRole FROM Users u")
	List<Map<String, Object>> findAllUsers();

}
