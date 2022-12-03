package com.example.informationthematicbackend.response.schoolyear;

import com.example.informationthematicbackend.model.dto.common.SchoolYearDTO;
import com.example.informationthematicbackend.response.ErrorResponse;
import com.example.informationthematicbackend.response.PageResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class ListSchoolYearResponse {
    private Boolean success;
    private ErrorResponse errorResponse;
    private PageResponse pageResponse;
    private List<SchoolYearDTO> items;
}
