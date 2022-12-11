package com.example.informationthematicbackend.controller.schoolyear;

import com.example.informationthematicbackend.converter.SchoolYearConverter;
import com.example.informationthematicbackend.model.dto.common.ListDTO;
import com.example.informationthematicbackend.model.dto.common.MessageDTO;
import com.example.informationthematicbackend.model.dto.common.OnlyIdDTO;
import com.example.informationthematicbackend.model.dto.common.SchoolYearDTO;
import com.example.informationthematicbackend.request.schoolyear.CreateUpdateSchoolYearRequest;
import com.example.informationthematicbackend.request.schoolyear.NewSchoolYearRequest;
import com.example.informationthematicbackend.response.NoContentResponse;
import com.example.informationthematicbackend.response.OnlyIdResponse;
import com.example.informationthematicbackend.response.Response;
import com.example.informationthematicbackend.response.schoolyear.ListSchoolYearResponse;
import com.example.informationthematicbackend.service.NewSchoolYearService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "SchoolYear", description = "SchoolYear APIs")
@RestController
@RequestMapping("/api/schoolyear")
@RequiredArgsConstructor

public class SchoolYearController {
    private final NewSchoolYearService schoolYearService;
    private final SchoolYearConverter schoolYearConverter;

    @Operation(summary = "List school year")
    @GetMapping
    public Response<ListDTO<SchoolYearDTO>> getListSchoolYear() {
        ListSchoolYearResponse response = schoolYearService.getListSchoolYear();
        return schoolYearConverter.getResponse(response);
    }

    @Operation(summary = "Create school year")
    @PostMapping
    public Response<OnlyIdDTO> createSchoolYear(@RequestBody CreateUpdateSchoolYearRequest request) {
        OnlyIdResponse response = schoolYearService.createSchoolYear(request);
        if (response.getSuccess()) {
            return schoolYearConverter.getResponse(response);
        }
        return schoolYearConverter.getError(response.getErrorResponse());
    }

    @Operation(summary = "Update school year")
    @PutMapping("/{id}")
    public Response<OnlyIdDTO> updateSchoolYear(@PathVariable("id") Long schoolYearId, @RequestBody CreateUpdateSchoolYearRequest request) {
        OnlyIdResponse response = schoolYearService.updateSchoolYear(schoolYearId, request);
        if (response.getSuccess()) {
            return schoolYearConverter.getResponse(response);
        }
        return schoolYearConverter.getError(response.getErrorResponse());
    }

    @Operation(summary = "Start new school year")
    @PostMapping("/startNewSchoolYear")
    public Response<MessageDTO> startNewSchoolYear(@RequestBody NewSchoolYearRequest request) {
        NoContentResponse response = schoolYearService.startNewSchoolYear(request);
        return schoolYearConverter.getResponse(response);
    }
}