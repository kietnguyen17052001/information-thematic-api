package com.example.informationthematicbackend.service;

import com.example.informationthematicbackend.model.dto.teacher.TeacherDTO;
import com.example.informationthematicbackend.request.teacher.CreateTeacherRequest;
import com.example.informationthematicbackend.request.teacher.ListTeacherRequest;
import com.example.informationthematicbackend.request.user.UpdateUserRequest;
import com.example.informationthematicbackend.response.NoContentResponse;
import com.example.informationthematicbackend.response.OnlyIdResponse;
import com.example.informationthematicbackend.response.teacher.GetTeacherResponse;
import com.example.informationthematicbackend.response.user.ListUserResponse;

public interface TeacherService {
    ListUserResponse getListTeacher(ListTeacherRequest request);

    GetTeacherResponse getTeacher(Long teacherId);

    OnlyIdResponse createTeacher(CreateTeacherRequest request);

    OnlyIdResponse updateTeacher(Long teacherId, UpdateUserRequest request);

    NoContentResponse deleteTeacher(Long teacherId);
}