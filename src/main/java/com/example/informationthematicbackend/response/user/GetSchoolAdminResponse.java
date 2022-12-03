package com.example.informationthematicbackend.response.user;

import com.example.informationthematicbackend.model.dto.user.SchoolAdminDTO;
import com.example.informationthematicbackend.response.ErrorResponse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class GetSchoolAdminResponse {
    private Boolean success;
    private ErrorResponse errorResponse;
    private SchoolAdminDTO schoolAdminDTO;
}
