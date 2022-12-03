package com.example.informationthematicbackend.response.school;

import com.example.informationthematicbackend.model.dto.common.SchoolDTO;
import com.example.informationthematicbackend.response.ErrorResponse;
import com.example.informationthematicbackend.response.PageResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class ListSchoolResponse {
    private Boolean success;
    private ErrorResponse errorResponse;
    private PageResponse pageResponse;
    private List<SchoolDTO> items;
}
