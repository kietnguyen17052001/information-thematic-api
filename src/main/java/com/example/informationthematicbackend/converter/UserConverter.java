package com.example.informationthematicbackend.converter;

import com.example.informationthematicbackend.common.constaint.Constants;
import com.example.informationthematicbackend.model.dto.calendar.CalendarEventDTO;
import com.example.informationthematicbackend.model.dto.common.ClazzDTO;
import com.example.informationthematicbackend.model.dto.common.ListDTO;
import com.example.informationthematicbackend.model.dto.common.UserDTO;
import com.example.informationthematicbackend.model.dto.student.ProfileStudentDTO;
import com.example.informationthematicbackend.model.dto.student.StudentDTO;
import com.example.informationthematicbackend.model.dto.teacher.TeacherDTO;
import com.example.informationthematicbackend.model.dto.user.SchoolAdminDTO;
import com.example.informationthematicbackend.model.dto.user.UserInfoDTO;
import com.example.informationthematicbackend.response.UserInfoResponse;
import com.example.informationthematicbackend.response.calendar.ListCalendarResponse;
import com.example.informationthematicbackend.response.clazz.ListClassResponse;
import com.example.informationthematicbackend.response.student.GetProfileStudentResponse;
import com.example.informationthematicbackend.response.teacher.GetTeacherResponse;
import com.example.informationthematicbackend.response.user.GetSchoolAdminResponse;
import com.example.informationthematicbackend.response.user.ListUserResponse;
import com.example.informationthematicbackend.response.Response;
import com.example.informationthematicbackend.response.student.GetStudentResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter extends CommonConverter {
    public Response<ListDTO<UserDTO>> getResponse(ListUserResponse response) {
        return Response.<ListDTO<UserDTO>>builder()
                .setSuccess(true)
                .setData(ListDTO.<UserDTO>builder()
                        .setTotalItems((long) response.getItems().size())
                        .setItems(response.getItems())
                        .build())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public Response<StudentDTO> getResponse(GetStudentResponse response) {
        return Response.<StudentDTO>builder()
                .setSuccess(true)
                .setData(response.getStudent())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public Response<ListDTO<SchoolAdminDTO>> getSchoolAdminResponse(ListUserResponse response) {
        return Response.<ListDTO<SchoolAdminDTO>>builder()
                .setSuccess(true)
                .setData(ListDTO.<SchoolAdminDTO>builder()
                        .setTotalItems((long) response.getItems().size())
                        .setItems(response.getItems().stream()
                                .map(r -> SchoolAdminDTO.builder()
                                        .setSchoolAdminId(r.getUserId())
                                        .setUsername(r.getUsername())
                                        .setFirstName(r.getFirstName())
                                        .setLastName(r.getLastName())
                                        .setEmail(r.getEmail())
                                        .setPassword(Constants.PROTECTED)
                                        .setSchoolId(r.getSchoolId())
                                        .setSchoolName(r.getSchoolName())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public Response<SchoolAdminDTO> getResponse(GetSchoolAdminResponse response) {
        return Response.<SchoolAdminDTO>builder()
                .setSuccess(true)
                .setData(response.getSchoolAdminDTO())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public Response<TeacherDTO> getResponse(GetTeacherResponse response) {
        return Response.<TeacherDTO>builder()
                .setSuccess(true)
                .setData(response.getTeacher())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public Response<UserInfoDTO> getResponse(UserInfoResponse response) {
        return Response.<UserInfoDTO>builder()
                .setSuccess(true)
                .setData(UserInfoDTO.builder()
                        .setUser(response.getUser())
                        .setAuthorities(response.getAuthorities())
                        .build())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public Response<ListDTO<CalendarEventDTO>> getResponse(ListCalendarResponse response) {
        return Response.<ListDTO<CalendarEventDTO>>builder()
                .setSuccess(true)
                .setData(ListDTO.<CalendarEventDTO>builder()
                        .setTotalItems((long) response.getItems().size())
                        .setItems(response.getItems())
                        .build())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public Response<ProfileStudentDTO> getResponse(GetProfileStudentResponse response) {
        return Response.<ProfileStudentDTO>builder()
                .setSuccess(true)
                .setData(response.getProfileStudentDTO())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public Response<List<ClazzDTO>> getResponse(ListClassResponse response) {
        return Response.<List<ClazzDTO>>builder()
                .setSuccess(true)
                .setData(response.getItems())
                .build();
    }
}