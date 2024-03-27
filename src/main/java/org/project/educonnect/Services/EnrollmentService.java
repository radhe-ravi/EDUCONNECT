package org.project.educonnect.Services;
import org.project.educonnect.DTO.EnrollmentDTO;
import org.project.educonnect.DTO.EnrollmentResponseDTO;
import org.project.educonnect.Mappers.EnrollmentPopulator;
import org.project.educonnect.Models.Course;
import org.project.educonnect.Models.Enrollment;
import org.project.educonnect.Models.Users;
import org.project.educonnect.Repository.CourseRepository;
import org.project.educonnect.Repository.EnrollmentRepository;
import org.project.educonnect.Repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EnrollmentService.class);

	@Autowired
	EnrollmentRepository enrollmentRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CourseRepository courseRespository;

	public EnrollmentResponseDTO addEnrollemnt(EnrollmentDTO enrollmentDTO) {

		Optional<Users> optionalUser = userRepository.findById(enrollmentDTO.getUserId());
		Optional<Course> optionalCourse = courseRespository.findById(enrollmentDTO.getCourseId());

		if (optionalUser.isEmpty() || optionalCourse.isEmpty())
			return null;

		Users user = optionalUser.get();
		Course course = optionalCourse.get();

		if (enrollmentRepository.existsByUserIdAndCourseId(user.getId(), course.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already enrolled");
		}

		Enrollment enrollment = EnrollmentPopulator.INSTANCE.populateEnrollment(enrollmentDTO);

		enrollment.setUsers(user);
		enrollment.setCourse(course);

		enrollmentRepository.save(enrollment);

		EnrollmentResponseDTO enrollmentResponseDTO = EnrollmentPopulator.INSTANCE.enrollmentEntityToDTO(enrollment);
		enrollmentResponseDTO.setCourseId(course.getId());
		enrollmentResponseDTO.setUserId(user.getId());
		enrollmentResponseDTO.setUserName(user.getUserName());
		enrollmentResponseDTO.setCourseTitle(course.getCourseTitle());
		enrollmentResponseDTO.setCourseDescription(course.getCourseDescription());
		enrollmentResponseDTO.setEnrollmentDate(enrollment.getEnrollmentDate());

		return enrollmentResponseDTO;
	}

	public EnrollmentResponseDTO addFavourite(EnrollmentDTO enrollmentDTO) {

		Optional<Users> optionalUser = userRepository.findById(enrollmentDTO.getUserId());
		Optional<Course> optionalCourse = courseRespository.findById(enrollmentDTO.getCourseId());
		Optional<Enrollment> optionalEnrollment = enrollmentRepository.findById(enrollmentDTO.getId());

		if (optionalUser.isEmpty() || optionalCourse.isEmpty() || optionalEnrollment.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User or course not found");

		Users user = optionalUser.get();
		Course course = optionalCourse.get();
		Enrollment enrollment = optionalEnrollment.get();

		if (enrollmentRepository.isAlreadyFavourite(user.getId(), course.getId())) {

			enrollment.setFavourite(false);
		} else
			enrollment.setFavourite(true);

		enrollment.setCourse(course);
		enrollment.setUsers(user);
		enrollmentRepository.save(enrollment);
		EnrollmentResponseDTO enrollmentResponseDTO = EnrollmentPopulator.INSTANCE.enrollmentEntityToDTO(enrollment);
		enrollmentResponseDTO.setCourseId(course.getId());
		enrollmentResponseDTO.setUserId(user.getId());
		enrollmentResponseDTO.setUserName(user.getUserName());
		enrollmentResponseDTO.setCourseTitle(course.getCourseTitle());
		enrollmentResponseDTO.setCourseDescription(course.getCourseDescription());
		return enrollmentResponseDTO;

	}

	public List<EnrollmentResponseDTO> findAllFavoritesByUserId(int userId) {

		Optional<Users> optionalUser = userRepository.findById(userId);

		if (optionalUser.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

		Users user = optionalUser.get();

		List<Enrollment> favouriteByUserIdEntityList = enrollmentRepository.findAllFavoritesByUserId(userId);

		List<EnrollmentResponseDTO> favouriteByUserIdDTOList = EnrollmentPopulator.INSTANCE
				.enrollmentListEntityToDTOList(favouriteByUserIdEntityList);

		return favouriteByUserIdDTOList;
	}
}
