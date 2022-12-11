package com.example.informationthematicbackend.converter;

import com.example.informationthematicbackend.model.dto.common.ListDTO;
import com.example.informationthematicbackend.model.dto.common.SchoolDTO;
import com.example.informationthematicbackend.model.entity.SchoolEntity;
import com.example.informationthematicbackend.response.Response;
import com.example.informationthematicbackend.response.school.GetSchoolResponse;
import com.example.informationthematicbackend.response.school.ListSchoolResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.stream.Collectors;

@Component
public class SchoolConverter extends CommonConverter {
    public Response<ListDTO<SchoolDTO>> getResponse(ListSchoolResponse response) {

        return Response.<ListDTO<SchoolDTO>>builder()
                .setSuccess(true)
                .setData(ListDTO.<SchoolDTO>builder()
                        .setTotalItems((long) response.getItems().size())
                        .setItems(response.getItems().stream()
                                .map(s -> SchoolDTO.builder()
                                        .setSchoolId(s.getSchoolId())
                                        .setSchool(s.getSchool())
                                        .setSchoolType(s.getSchoolType())
                                        .setStreet(s.getStreet())
                                        .setDistrict(s.getDistrict())
                                        .setCity(s.getCity())
                                        .setPhone(s.getPhone())
                                        .setWebsite(s.getWebsite())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();

    }

    public Response<SchoolDTO> getResponse(SchoolEntity schoolEntity) {
        return Response.<SchoolDTO>builder()
                .setSuccess(true)
                .setData(SchoolDTO.builder()
                        .setSchoolId(schoolEntity.getSchoolId())
                        .setSchoolType(schoolEntity.getSchoolType())
                        .setStreet(schoolEntity.getStreet())
                        .setDistrict(schoolEntity.getDistrict())
                        .setCity(schoolEntity.getCity())
                        .setPhone(schoolEntity.getPhone())
                        .setWebsite(schoolEntity.getWebsite())
                        .build())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public Response<SchoolDTO> getResponse(GetSchoolResponse response) {
        return Response.<SchoolDTO>builder()
                .setSuccess(true)
                .setData(response.getSchoolDTO())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}