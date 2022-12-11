package com.example.informationthematicbackend.controller.student;

import com.example.informationthematicbackend.converter.UserConverter;
import com.example.informationthematicbackend.model.dto.common.ListDTO;
import com.example.informationthematicbackend.model.dto.common.MessageDTO;
import com.example.informationthematicbackend.model.dto.common.OnlyIdDTO;
import com.example.informationthematicbackend.model.dto.common.UserDTO;
import com.example.informationthematicbackend.model.dto.student.ProfileStudentDTO;
import com.example.informationthematicbackend.model.dto.student.StudentDTO;
import com.example.informationthematicbackend.request.leaningresult.CreateUpdateLearningResultRequest;
import com.example.informationthematicbackend.request.student.CreateStudentRequest;
import com.example.informationthematicbackend.request.student.GetProfileStudentRequest;
import com.example.informationthematicbackend.request.student.ListStudentRequest;
import com.example.informationthematicbackend.response.NoContentResponse;
import com.example.informationthematicbackend.response.OnlyIdResponse;
import com.example.informationthematicbackend.response.Response;
import com.example.informationthematicbackend.response.student.GetProfileStudentResponse;
import com.example.informationthematicbackend.response.student.GetStudentResponse;
import com.example.informationthematicbackend.response.user.ListUserResponse;
import com.example.informationthematicbackend.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Student", description = "Student APIs")
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final UserConverter userConverter;

    @Operation(summary = "List student")
    @GetMapping
    public Response<ListDTO<UserDTO>> getListStudent(@ModelAttribute ListStudentRequest request) {
        ListUserResponse response = studentService.getListStudent(request);
        return userConverter.getResponse(response);
    }

    @Operation(summary = "Get student")
    @GetMapping("/{id}")
    public Response<StudentDTO> getStudent(@PathVariable("id") Long studentId) {
        GetStudentResponse response = studentService.getStudent(studentId);
        return userConverter.getResponse(response);
    }

    @Operation(summary = "Profile student")
    @GetMapping("/profile")
    public Response<ProfileStudentDTO> getProfileStudent(@ModelAttribute @Valid GetProfileStudentRequest request) {
        GetProfileStudentResponse response = studentService.getProfileStudent(request);
        if (response.getSuccess()) {
            return userConverter.getResponse(response);
        }
        return userConverter.getError(response.getErrorResponse());
    }

    @Operation(summary = "Update learning result in profile student")
    @PutMapping("profile/learningResult/{id}")
    public Response<OnlyIdDTO> updateLearningResultForProfileStudent(@PathVariable("id") Long learningResultId, @RequestBody CreateUpdateLearningResultRequest request) {
        OnlyIdResponse response = studentService.updateLearningResultForProfileStudent(learningResultId, request);
        if (response.getSuccess()) {
            return userConverter.getResponse(response);
        }
        return userConverter.getError(response.getErrorResponse());
    }

    @Operation(summary = "Create student")
    @PostMapping
    public Response<OnlyIdDTO> createStudent(@RequestBody CreateStudentRequest request) {
        OnlyIdResponse response = studentService.createStudent(request);
        if (response.getSuccess()) {
            return userConverter.getResponse(response);
        } else {
            return userConverter.getError(response.getErrorResponse());
        }
    }

    @Operation(summary = "Update student")
    @PutMapping("/{id}")
    public Response<OnlyIdDTO> updateStudent(@PathVariable("id") Long studentId, @RequestBody StudentDTO request) {
        OnlyIdResponse response = studentService.updateStudent(studentId, request);
        if (response.getSuccess()) {
            return userConverter.getResponse(response);
        } else {
            return userConverter.getError(response.getErrorResponse());
        }
    }

    @Operation(summary = "Delete student")
    @DeleteMapping("/{id}")
    public Response<MessageDTO> deleteStudent(@PathVariable("id") Long studentId) {
        NoContentResponse response = studentService.deleteStudent(studentId);
        if (response.getSuccess()) {
            return userConverter.getResponse(response);
        } else {
            return userConverter.getError(response.getErrorResponse());
        }
    }
}