package com.example.informationthematicbackend.response.student;

import com.example.informationthematicbackend.model.dto.student.StudentDTO;
import com.example.informationthematicbackend.response.ErrorResponse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class GetStudentResponse {
    private Boolean success;
    private ErrorResponse errorResponse;
    private StudentDTO student;
}
