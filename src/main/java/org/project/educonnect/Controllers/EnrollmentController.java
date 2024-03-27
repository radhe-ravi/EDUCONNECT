package org.project.educonnect.Controllers;

import org.project.educonnect.DTO.EnrollmentDTO;
import org.project.educonnect.DTO.EnrollmentResponseDTO;
import org.project.educonnect.Services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EnrollmentController {

	@Autowired
	EnrollmentService enrollmentService;

	@PostMapping("/enrollments")
	public ResponseEntity<EnrollmentResponseDTO> addEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) {

		EnrollmentResponseDTO enrollment = enrollmentService.addEnrollemnt(enrollmentDTO);


		if (enrollment == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course or user with given ids not found");

		return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
	}

	@PostMapping("/favourites")
	public ResponseEntity<EnrollmentResponseDTO> addFavourite(@RequestBody EnrollmentDTO enrollmentDTO) {

		EnrollmentResponseDTO enrollmentResponseDTO = enrollmentService.addFavourite(enrollmentDTO);

		if (enrollmentResponseDTO == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");

		return new ResponseEntity<>(enrollmentResponseDTO, HttpStatus.CREATED);
	}

	@GetMapping("/favourites/{userId}")
	public ResponseEntity<List<EnrollmentResponseDTO>> findFavouritedByUserId(@PathVariable int userId) {

		List<EnrollmentResponseDTO> enrollmentResponseDTOs = enrollmentService.findAllFavoritesByUserId(userId);

		if (enrollmentResponseDTOs == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
		return new ResponseEntity<>(enrollmentResponseDTOs, HttpStatus.OK);
	}

}
