package com.example.informationthematicbackend.request.leaningresult;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUpdateLearningResultRequest {
    private Long schoolYearId;
    private Long classId;
    private Double averageScore;
    private String conduct;
}
