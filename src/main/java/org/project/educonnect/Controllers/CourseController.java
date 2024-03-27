package org.project.educonnect.Controllers;

import org.project.educonnect.DTO.CourseDTO;
import org.project.educonnect.DTO.CourseResponseDTO;
import org.project.educonnect.Mappers.CoursePopulator;
import org.project.educonnect.Models.Course;
import org.project.educonnect.Models.Users;
import org.project.educonnect.Repository.CourseRepository;
import org.project.educonnect.Services.CourseService;
import org.project.educonnect.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CourseController {

	@Autowired
	CourseService courseService;

	@Autowired
	UserService userService;

	@Autowired
	CourseRepository courseRespository;

	@PostMapping("/courses")
	public ResponseEntity<CourseResponseDTO> addCourse(@RequestBody CourseDTO courseDTO) {
		Users author = userService.getAuthorByIdAndRole(courseDTO.getAuthorId(), "AUTHOR");
		if (author == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Author not found with the given ID or not of role 'author'");
		}

		return new ResponseEntity<>(courseService.addCourse(courseDTO), HttpStatus.CREATED);
	}

	@PutMapping("/courses/{courseId}")
	public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable int courseId,
			@RequestBody CourseDTO courseDTO) {
		System.out.println(courseId);

		Optional<Course> course = courseRespository.findById(courseId);
		if (course == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
		courseService.updateCourse(courseId, courseDTO);
		CourseResponseDTO courseResponseDTO = CoursePopulator.INSTANCE.courseEntityToDTO(course.get());
		courseResponseDTO.setAuthorId(course.get().getAuthor().getId());
		return new ResponseEntity<>(courseResponseDTO, HttpStatus.OK);

	}

	@GetMapping("/courses")
	public ResponseEntity<List<CourseDTO>> getCourses() {

		List<CourseDTO> courses = courseService.getCourses();

		return new ResponseEntity<>(courses, HttpStatus.OK);
	}

	@GetMapping("/course")
	public ResponseEntity<Optional<CourseResponseDTO>> getSearchedCourse(@RequestBody CourseDTO courseDTO) {
		Optional<CourseResponseDTO> courses = courseService.getSearchedCourses(courseDTO);

		if (courses.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
}
