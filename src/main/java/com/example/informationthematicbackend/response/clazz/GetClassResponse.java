package com.example.informationthematicbackend.response.clazz;

import com.example.informationthematicbackend.model.dto.common.ClazzDTO;
import com.example.informationthematicbackend.response.ErrorResponse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class GetClassResponse {
    private Boolean success;
    private ErrorResponse errorResponse;
    private ClazzDTO clazzDTO;
}
