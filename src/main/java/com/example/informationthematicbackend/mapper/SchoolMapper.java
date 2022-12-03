package com.example.informationthematicbackend.mapper;

import com.example.informationthematicbackend.model.dto.common.SchoolDTO;
import com.example.informationthematicbackend.model.entity.SchoolEntity;

public class SchoolMapper {
    public static SchoolDTO entity2dto(SchoolEntity entity) {
        SchoolDTO dto = new SchoolDTO();
        dto.setSchoolId(entity.getSchoolId());
        dto.setSchool(entity.getSchool());
        dto.setStreet(entity.getStreet());
        dto.setDistrict(entity.getDistrict());
        dto.setCity(entity.getCity());
        dto.setSchoolType(entity.getSchoolType());
        dto.setPhone(entity.getPhone());
        dto.setWebsite(entity.getWebsite());
        return dto;
    }

}
