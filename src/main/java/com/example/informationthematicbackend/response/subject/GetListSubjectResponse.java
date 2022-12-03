package com.example.informationthematicbackend.response.subject;

import com.example.informationthematicbackend.model.dto.common.SubjectDTO;
import com.example.informationthematicbackend.response.ErrorResponse;
import com.example.informationthematicbackend.response.PageResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class GetListSubjectResponse {
    private Boolean success;
    private ErrorResponse errorResponse;
    private PageResponse pageResponse;
    private List<SubjectDTO> items;
}
