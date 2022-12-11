package com.example.informationthematicbackend.service;

import com.example.informationthematicbackend.request.schoolyear.CreateUpdateSchoolYearRequest;
import com.example.informationthematicbackend.request.schoolyear.NewSchoolYearRequest;
import com.example.informationthematicbackend.response.NoContentResponse;
import com.example.informationthematicbackend.response.OnlyIdResponse;
import com.example.informationthematicbackend.response.schoolyear.ListSchoolYearResponse;

public interface NewSchoolYearService {
    ListSchoolYearResponse getListSchoolYear();

    OnlyIdResponse createSchoolYear(CreateUpdateSchoolYearRequest request);

    OnlyIdResponse updateSchoolYear(Long schoolYearId, CreateUpdateSchoolYearRequest request);

    NoContentResponse startNewSchoolYear(NewSchoolYearRequest request);
}