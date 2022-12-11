package com.example.informationthematicbackend.service;

import com.example.informationthematicbackend.request.leaningresult.InputScoreRequest;
import com.example.informationthematicbackend.request.leaningresult.LoadExamResultClassRequest;
import com.example.informationthematicbackend.request.leaningresult.LoadExamResultStudentRequest;
import com.example.informationthematicbackend.request.leaningresult.ModifyScoreRequest;
import com.example.informationthematicbackend.response.NoContentResponse;
import com.example.informationthematicbackend.response.learningresult.LearningResultDetailResponse;
import com.example.informationthematicbackend.response.learningresult.LoadExamResultClassResponse;
import com.example.informationthematicbackend.response.learningresult.LoadExamResultResponse;

public interface LearningResultService {
    LearningResultDetailResponse getLearningResultDetail(Long learningResultId);
    LoadExamResultResponse loadExamResult(LoadExamResultStudentRequest request);

    NoContentResponse inputScore(InputScoreRequest request);

    NoContentResponse modifyScore(ModifyScoreRequest request);

    LoadExamResultClassResponse loadExamResultClass(LoadExamResultClassRequest request);
}