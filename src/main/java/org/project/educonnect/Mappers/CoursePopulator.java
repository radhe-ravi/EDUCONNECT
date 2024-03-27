package org.project.educonnect.Mappers;



import org.mapstruct.factory.Mappers;
import org.project.educonnect.DTO.CourseDTO;
import org.project.educonnect.DTO.CourseResponseDTO;
import org.project.educonnect.Models.Course;
import org.mapstruct.Mapper;
import java.util.List;
import java.util.Optional;

@Mapper
public interface CoursePopulator {
	CoursePopulator INSTANCE = Mappers.getMapper(CoursePopulator.class);

	Course populateCourse(CourseDTO courseDTO);

	CourseResponseDTO courseEntityToDTO(Course course);

	List<CourseResponseDTO> courseListEntityToDTO(List<Course> courses);

	CourseResponseDTO optionalCourseEntityToDTO(Optional<Course> course);

}
