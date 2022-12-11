package com.example.informationthematicbackend.service;

import com.example.informationthematicbackend.request.subject.GetListSubjectRequest;
import com.example.informationthematicbackend.response.subject.GetListSubjectResponse;

public interface SubjectService {
    GetListSubjectResponse getListSubject(GetListSubjectRequest request);
}