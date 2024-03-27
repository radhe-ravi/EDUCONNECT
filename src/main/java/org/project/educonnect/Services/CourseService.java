package org.project.educonnect.Services;


import jakarta.transaction.Transactional;
import lombok.Data;
import org.project.educonnect.DTO.CourseDTO;
import org.project.educonnect.DTO.CourseResponseDTO;
import org.project.educonnect.Mappers.CoursePopulator;
import org.project.educonnect.Models.Course;
import org.project.educonnect.Models.Users;
import org.project.educonnect.Repository.CourseRepository;
import org.project.educonnect.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class CourseService {


	private final CourseRepository courseRepository;


	private final UserRepository userRepository;

	public CourseResponseDTO addCourse(CourseDTO courseDTO) {
		Optional<Users> optionalUser = userRepository.findById(courseDTO.getAuthorId());
		Course course = CoursePopulator.INSTANCE.populateCourse(courseDTO);
		if (optionalUser.isPresent()) {
			Users author = optionalUser.get();
			course.setAuthor(author);
		}
		courseRepository.save(course);
		CourseResponseDTO courseResponseDTO = CoursePopulator.INSTANCE.courseEntityToDTO(course);
		courseResponseDTO.setAuthorId(course.getAuthor().getId());
		return courseResponseDTO;

	}

	@Transactional
	public void updateCourse(int courseId, CourseDTO courseDTO) {
		Optional<Course> optionalCourse = courseRepository.findById(courseId);

		if (optionalCourse.isEmpty())
			return;

		Course course = optionalCourse.get();
		course.setCourseDescription(courseDTO.getCourseDescription());
		course.setCourseTitle(courseDTO.getCourseTitle());
		courseRepository.save(course);

	}

	public List<CourseDTO> getCourses() {
		return courseRepository.getCourses();
	}

	public Optional<CourseResponseDTO> getSearchedCourses(CourseDTO courseDTO) {
		return courseRepository.findByTitle(courseDTO.getCourseTitle());
	}
}
