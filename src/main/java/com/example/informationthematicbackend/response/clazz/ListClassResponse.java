package com.example.informationthematicbackend.response.clazz;

import com.example.informationthematicbackend.model.dto.common.ClazzDTO;
import com.example.informationthematicbackend.response.ErrorResponse;
import com.example.informationthematicbackend.response.PageResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class ListClassResponse {
    private Boolean success;
    private ErrorResponse errorResponse;
    private PageResponse pageResponse;
    private List<ClazzDTO> items;
}
