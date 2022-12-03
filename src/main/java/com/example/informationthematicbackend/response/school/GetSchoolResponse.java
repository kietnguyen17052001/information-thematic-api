package com.example.informationthematicbackend.response.school;

import com.example.informationthematicbackend.model.dto.common.SchoolDTO;
import com.example.informationthematicbackend.response.ErrorResponse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class GetSchoolResponse {
    private Boolean success;
    private ErrorResponse errorResponse;
    private SchoolDTO schoolDTO;
}
