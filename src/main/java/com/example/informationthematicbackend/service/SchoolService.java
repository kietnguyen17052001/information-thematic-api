package com.example.informationthematicbackend.service;

import com.example.informationthematicbackend.request.school.CreateSchoolRequest;
import com.example.informationthematicbackend.request.school.ListSchoolRequest;
import com.example.informationthematicbackend.request.school.UpdateSchoolRequest;
import com.example.informationthematicbackend.response.NoContentResponse;
import com.example.informationthematicbackend.response.OnlyIdResponse;
import com.example.informationthematicbackend.response.school.GetSchoolResponse;
import com.example.informationthematicbackend.response.school.ListSchoolResponse;
import org.springframework.stereotype.Service;

@Service
public interface SchoolService {
    OnlyIdResponse createSchool(CreateSchoolRequest request);

    OnlyIdResponse updateSchool(Long schoolId, UpdateSchoolRequest request);

    NoContentResponse deleteSchool(Long schoolId);

    GetSchoolResponse getSchool(Long schoolId);

    ListSchoolResponse getListSchool(ListSchoolRequest request);
}