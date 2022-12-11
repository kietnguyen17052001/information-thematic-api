package com.example.informationthematicbackend.service;

import com.example.informationthematicbackend.request.clazz.CreateUpdateClassRequest;
import com.example.informationthematicbackend.request.clazz.ListClassRequest;
import com.example.informationthematicbackend.response.NoContentResponse;
import com.example.informationthematicbackend.response.OnlyIdResponse;
import com.example.informationthematicbackend.response.clazz.GetClassResponse;
import com.example.informationthematicbackend.response.clazz.ListClassResponse;

public interface ClazzService {
    ListClassResponse getListClass(ListClassRequest request);

    GetClassResponse getClass(Long clazzId);

    OnlyIdResponse createClass(CreateUpdateClassRequest request);

    OnlyIdResponse updateClass(Long clazzId, CreateUpdateClassRequest request);

    NoContentResponse deleteClass(Long clazzId);
}