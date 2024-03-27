package org.project.educonnect.Mappers;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.project.educonnect.DTO.EnrollmentDTO;
import org.project.educonnect.DTO.EnrollmentResponseDTO;
import org.project.educonnect.DTO.FavouriteResponseDTO;
import org.project.educonnect.Models.Enrollment;

import java.util.List;

@Mapper
public interface EnrollmentPopulator {
	EnrollmentPopulator INSTANCE = Mappers.getMapper(EnrollmentPopulator.class);

	Enrollment populateEnrollment(EnrollmentDTO enrollmentDTO);

	EnrollmentResponseDTO enrollmentEntityToDTO(Enrollment enrollment);

	FavouriteResponseDTO favouriteResponseEntityToDTO(Enrollment enrollment);

	List<EnrollmentResponseDTO> enrollmentListEntityToDTOList(List<Enrollment> enrollments);
}
