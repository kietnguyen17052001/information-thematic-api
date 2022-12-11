package com.example.informationthematicbackend.converter;

import com.example.informationthematicbackend.model.dto.clazz.ExamResultClassDTO;
import com.example.informationthematicbackend.model.dto.student.ExamResultDTO;
import com.example.informationthematicbackend.model.dto.student.LearningResultDetailDTO;
import com.example.informationthematicbackend.response.Response;
import com.example.informationthematicbackend.response.learningresult.LearningResultDetailResponse;
import com.example.informationthematicbackend.response.learningresult.LoadExamResultClassResponse;
import com.example.informationthematicbackend.response.learningresult.LoadExamResultResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class LearningResultConverter extends CommonConverter {
    public Response<LearningResultDetailDTO> getResponse(LearningResultDetailResponse response) {
        return Response.<LearningResultDetailDTO>builder()
                .setSuccess(true)
                .setData(response.getLearningResultDetail())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public Response<List<ExamResultDTO>> getResponse(LoadExamResultResponse response) {
        return Response.<List<ExamResultDTO>>builder()
                .setSuccess(true)
                .setData(response.getExamResult())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public Response<ExamResultClassDTO> getResponse(LoadExamResultClassResponse response) {
        return Response.<ExamResultClassDTO>builder()
                .setSuccess(true)
                .setData(response.getExamResultClass())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}