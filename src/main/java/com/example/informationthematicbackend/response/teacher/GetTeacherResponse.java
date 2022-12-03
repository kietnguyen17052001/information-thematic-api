package com.example.informationthematicbackend.response.teacher;

import com.example.informationthematicbackend.model.dto.teacher.TeacherDTO;
import com.example.informationthematicbackend.response.ErrorResponse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class GetTeacherResponse {
    private Boolean success;
    private ErrorResponse errorResponse;
    private TeacherDTO teacher;
}
