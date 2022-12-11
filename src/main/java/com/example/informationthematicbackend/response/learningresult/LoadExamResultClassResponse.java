package com.example.informationthematicbackend.response.learningresult;

import com.example.informationthematicbackend.model.dto.clazz.ExamResultClassDTO;
import com.example.informationthematicbackend.response.ErrorResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder(setterPrefix = "set")
public class LoadExamResultClassResponse implements Serializable {
    private Boolean success;
    private ErrorResponse errorResponse;
    private ExamResultClassDTO examResultClass;
}