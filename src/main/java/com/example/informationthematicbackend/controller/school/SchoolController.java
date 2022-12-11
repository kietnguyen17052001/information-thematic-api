package com.example.informationthematicbackend.controller.school;

import com.example.informationthematicbackend.converter.SchoolConverter;
import com.example.informationthematicbackend.model.dto.common.ListDTO;
import com.example.informationthematicbackend.model.dto.common.MessageDTO;
import com.example.informationthematicbackend.model.dto.common.OnlyIdDTO;
import com.example.informationthematicbackend.model.dto.common.SchoolDTO;
import com.example.informationthematicbackend.request.school.CreateSchoolRequest;
import com.example.informationthematicbackend.request.school.ListSchoolRequest;
import com.example.informationthematicbackend.request.school.UpdateSchoolRequest;
import com.example.informationthematicbackend.response.NoContentResponse;
import com.example.informationthematicbackend.response.OnlyIdResponse;
import com.example.informationthematicbackend.response.Response;
import com.example.informationthematicbackend.response.school.GetSchoolResponse;
import com.example.informationthematicbackend.response.school.ListSchoolResponse;
import com.example.informationthematicbackend.service.SchoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "School", description = "School APIs")
@RestController
@RequestMapping("/api/schools")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;
    private final SchoolConverter schoolConverter;

    @Operation(summary = "List school")
    @GetMapping
    public Response<ListDTO<SchoolDTO>> getListSchool(@ModelAttribute ListSchoolRequest request) {
        ListSchoolResponse response = schoolService.getListSchool(request);
        return schoolConverter.getResponse(response);
    }

    @Operation(summary = "Get school")
    @GetMapping("/{id}")
    public Response<SchoolDTO> getSchool(@PathVariable("id") Long schoolId) {
        GetSchoolResponse response = schoolService.getSchool(schoolId);
        return schoolConverter.getResponse(response);
    }

    @Operation(summary = "Create school")
    @PostMapping
    public Response<OnlyIdDTO> createSchool(@RequestBody CreateSchoolRequest request) {
        OnlyIdResponse response = schoolService.createSchool(request);
        if (response.getSuccess()) {
            return schoolConverter.getResponse(response);
        } else {
            return schoolConverter.getError(response.getErrorResponse());
        }
    }

    @Operation(summary = "Update school")
    @PutMapping("/{id}")
    public Response<OnlyIdDTO> updateSchool(@PathVariable("id") Long schoolId, @RequestBody UpdateSchoolRequest request) {
        OnlyIdResponse response = schoolService.updateSchool(schoolId, request);
        if (response.getSuccess()) {
            return schoolConverter.getResponse(response);
        } else {
            return schoolConverter.getError(response.getErrorResponse());
        }
    }

    @Operation(summary = "Delete school")
    @DeleteMapping("/{id}")
    public Response<MessageDTO> deleteSchool(@PathVariable("id") Long schoolId) {
        NoContentResponse response = schoolService.deleteSchool(schoolId);
        if (response.getSuccess()) {
            return schoolConverter.getResponse(response);
        } else {
            return schoolConverter.getError(response.getErrorResponse());
        }
    }
}