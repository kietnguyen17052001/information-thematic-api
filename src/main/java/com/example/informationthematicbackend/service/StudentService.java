package com.example.informationthematicbackend.service;

import com.example.informationthematicbackend.model.dto.student.StudentDTO;
import com.example.informationthematicbackend.request.leaningresult.CreateUpdateLearningResultRequest;
import com.example.informationthematicbackend.request.student.CreateStudentRequest;
import com.example.informationthematicbackend.request.student.GetProfileStudentRequest;
import com.example.informationthematicbackend.request.student.ListStudentRequest;
import com.example.informationthematicbackend.response.NoContentResponse;
import com.example.informationthematicbackend.response.OnlyIdResponse;
import com.example.informationthematicbackend.response.student.GetProfileStudentResponse;
import com.example.informationthematicbackend.response.student.GetStudentResponse;
import com.example.informationthematicbackend.response.user.ListUserResponse;

public interface StudentService {
    OnlyIdResponse createStudent(CreateStudentRequest request);

    ListUserResponse getListStudent(ListStudentRequest request);

    GetStudentResponse getStudent(Long studentId);

    OnlyIdResponse updateStudent(Long studentId, StudentDTO request);

    NoContentResponse deleteStudent(Long studentId);

    GetProfileStudentResponse getProfileStudent(GetProfileStudentRequest request);

    OnlyIdResponse updateLearningResultForProfileStudent(Long learningResultId, CreateUpdateLearningResultRequest request);
}