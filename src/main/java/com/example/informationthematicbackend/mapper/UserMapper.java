package com.example.informationthematicbackend.mapper;

import com.example.informationthematicbackend.common.constant.Constants;
import com.example.informationthematicbackend.model.dto.common.UserDTO;
import com.example.informationthematicbackend.model.entity.UserEntity;
import com.example.informationthematicbackend.util.RequestUtil;

public class UserMapper {
    public static UserDTO entity2dto(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
        dto.setUsername(RequestUtil.blankIfNull(entity.getUsername()));
        dto.setPassword(Constants.PROTECTED);
        dto.setLastName(entity.getLastName());
        dto.setFirstName(entity.getFirstName());
        dto.setDisplayName(entity.getLastName() + " " + entity.getFirstName());
        dto.setEmail(RequestUtil.blankIfNull(entity.getEmail()));
        dto.setPhone(RequestUtil.blankIfNull(entity.getPhone()));
        if (entity.getRole() != null) {
            dto.setRole(entity.getRole().getRole());
        }
        dto.setGender(Boolean.TRUE.equals(entity.getGender()) ? Boolean.TRUE : Boolean.FALSE);
        dto.setJob(RequestUtil.blankIfNull(entity.getJob()));
        if (entity.getSchool() != null) {
            dto.setSchoolId(entity.getSchool().getSchoolId());
            dto.setSchoolName(entity.getSchool().getSchool());
        }
        dto.setStreet(RequestUtil.blankIfNull(entity.getStreet()));
        dto.setDistrict(RequestUtil.blankIfNull(entity.getDistrict()));
        dto.setCity(RequestUtil.blankIfNull(entity.getCity()));
        dto.setPlaceOfBirth(RequestUtil.blankIfNull(entity.getPlaceOfBirth()));
        dto.setNationality(RequestUtil.blankIfNull(entity.getNationality()));
        if (entity.getDateOfBirth() != null) {
            dto.setDateOfBirth(entity.getDateOfBirth().toString());
        }
        dto.setStudentId(RequestUtil.blankIfNull(entity.getStudentId()));
        dto.setTeacherId(RequestUtil.blankIfNull(entity.getTeacherId()));
        dto.setWorkingPosition(RequestUtil.blankIfNull(entity.getWorkingPosition()));
        if (entity.getSubject() != null) {
            dto.setTeachSubject(entity.getSubject().getSubject());
        }
        return dto;
    }

}
