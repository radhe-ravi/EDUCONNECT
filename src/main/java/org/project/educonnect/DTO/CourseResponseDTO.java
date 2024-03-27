package org.project.educonnect.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDTO {

    private int id;
    private String courseTitle;
    private String courseDescription;
    private int authorId;
}

