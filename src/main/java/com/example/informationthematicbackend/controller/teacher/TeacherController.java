package com.example.informationthematicbackend.controller.teacher;

import com.example.informationthematicbackend.converter.UserConverter;
import com.example.informationthematicbackend.model.dto.common.ListDTO;
import com.example.informationthematicbackend.model.dto.common.MessageDTO;
import com.example.informationthematicbackend.model.dto.common.OnlyIdDTO;
import com.example.informationthematicbackend.model.dto.common.UserDTO;
import com.example.informationthematicbackend.model.dto.teacher.TeacherDTO;
import com.example.informationthematicbackend.request.teacher.CreateTeacherRequest;
import com.example.informationthematicbackend.request.teacher.ListTeacherRequest;
import com.example.informationthematicbackend.request.user.UpdateUserRequest;
import com.example.informationthematicbackend.response.NoContentResponse;
import com.example.informationthematicbackend.response.OnlyIdResponse;
import com.example.informationthematicbackend.response.Response;
import com.example.informationthematicbackend.response.teacher.GetTeacherResponse;
import com.example.informationthematicbackend.response.user.ListUserResponse;
import com.example.informationthematicbackend.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Teacher", description = "Teacher APIs")
@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final UserConverter userConverter;
    private final TeacherService teacherService;

    @Operation(summary = "List teacher")
    @GetMapping
    public Response<ListDTO<UserDTO>> getListTeacher(@ModelAttribute @Valid ListTeacherRequest request) {
        ListUserResponse response = teacherService.getListTeacher(request);
        return userConverter.getResponse(response);
    }

    @Operation(summary = "Get teacher")
    @GetMapping("/{id}")
    public Response<TeacherDTO> getTeacher(@PathVariable("id") Long teacherId) {
        GetTeacherResponse response = teacherService.getTeacher(teacherId);
        return userConverter.getResponse(response);
    }

    @Operation(summary = "Create teacher")
    @PostMapping
    public Response<OnlyIdDTO> createTeacher(@RequestBody CreateTeacherRequest request) {
        OnlyIdResponse response = teacherService.createTeacher(request);
        if (response.getSuccess()) {
            return userConverter.getResponse(response);
        }
        return userConverter.getError(response.getErrorResponse());
    }

    @Operation(summary = "Update teacher")
    @PutMapping("/{id}")
    public Response<OnlyIdDTO> updateTeacher(@PathVariable("id") Long teacherId, @RequestBody UpdateUserRequest request) {
        OnlyIdResponse response = teacherService.updateTeacher(teacherId, request);
        if (response.getSuccess()) {
            return userConverter.getResponse(response);
        }
        return userConverter.getError(response.getErrorResponse());
    }

    @Operation(summary = "Delete teacher")
    @DeleteMapping("/{id}")
    public Response<MessageDTO> deleteTeacher(@PathVariable("id") Long teacherId) {
        NoContentResponse response = teacherService.deleteTeacher(teacherId);
        if (response.getSuccess()) {
            return userConverter.getResponse(response);
        } else {
            return userConverter.getError(response.getErrorResponse());
        }
    }

}