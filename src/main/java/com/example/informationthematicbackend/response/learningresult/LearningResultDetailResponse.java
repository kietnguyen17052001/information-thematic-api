package com.example.informationthematicbackend.response.learningresult;

import com.example.informationthematicbackend.model.dto.student.LearningResultDetailDTO;
import com.example.informationthematicbackend.response.ErrorResponse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class LearningResultDetailResponse {
    private Boolean success;
    private ErrorResponse errorResponse;
    private LearningResultDetailDTO learningResultDetail;
}
