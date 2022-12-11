package com.example.informationthematicbackend.service;

import com.example.informationthematicbackend.request.school.CreateSchoolAdminRequest;
import com.example.informationthematicbackend.request.user.ListSchoolAdminRequest;
import com.example.informationthematicbackend.response.NoContentResponse;
import com.example.informationthematicbackend.response.OnlyIdResponse;
import com.example.informationthematicbackend.response.user.GetSchoolAdminResponse;
import com.example.informationthematicbackend.response.user.ListUserResponse;

public interface SchoolAdminService {
    OnlyIdResponse createSchoolAdmin(CreateSchoolAdminRequest request);

    OnlyIdResponse updateSchoolAdmin(Long schoolAdminId, CreateSchoolAdminRequest request);

    ListUserResponse getListSchoolAdmin(ListSchoolAdminRequest request);

    GetSchoolAdminResponse getSchoolAdmin(Long schoolAdminId);

    NoContentResponse deleteSchoolAdmin(Long schoolAdminId);
}