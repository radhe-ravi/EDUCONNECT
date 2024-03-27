package org.project.educonnect.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponseDTO {
	private int id;
	private int userId;
	private int courseId;
	private String userName;
	private String courseTitle;
	private String courseDescription;

	private Timestamp enrollmentDate;

	private boolean isFavourite;
}
