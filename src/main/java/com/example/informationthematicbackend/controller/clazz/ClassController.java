package com.example.informationthematicbackend.controller.clazz;

import com.example.informationthematicbackend.converter.ClazzConverter;
import com.example.informationthematicbackend.model.dto.common.ClazzDTO;
import com.example.informationthematicbackend.model.dto.common.ListDTO;
import com.example.informationthematicbackend.model.dto.common.MessageDTO;
import com.example.informationthematicbackend.model.dto.common.OnlyIdDTO;
import com.example.informationthematicbackend.request.clazz.CreateUpdateClassRequest;
import com.example.informationthematicbackend.request.clazz.ListClassRequest;
import com.example.informationthematicbackend.response.NoContentResponse;
import com.example.informationthematicbackend.response.OnlyIdResponse;
import com.example.informationthematicbackend.response.Response;
import com.example.informationthematicbackend.response.clazz.GetClassResponse;
import com.example.informationthematicbackend.response.clazz.ListClassResponse;
import com.example.informationthematicbackend.service.ClazzService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Class", description = "Class APIs")
@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClassController {
    private final ClazzService clazzService;
    private final ClazzConverter clazzConverter;

    @Operation(summary = "List class")
    @GetMapping
    public Response<ListDTO<ClazzDTO>> getListClass(@ModelAttribute @Valid ListClassRequest request) {
        ListClassResponse response = clazzService.getListClass(request);
        if (response.getSuccess()) {
            return clazzConverter.getResponse(response);
        }
        return clazzConverter.getError(response.getErrorResponse());
    }

    @Operation(summary = "Get class")
    @GetMapping("/{id}")
    public Response<ClazzDTO> getClass(@PathVariable("id") Long clazzId) {
        GetClassResponse response = clazzService.getClass(clazzId);
        return clazzConverter.getResponse(response);
    }

    @Operation(summary = "Create class")
    @PostMapping
    public Response<OnlyIdDTO> createClass(@RequestBody CreateUpdateClassRequest request) {
        OnlyIdResponse response = clazzService.createClass(request);
        if (response.getSuccess()) {
            return clazzConverter.getResponse(response);
        }
        return clazzConverter.getError(response.getErrorResponse());
    }

    @Operation(summary = "Update class")
    @PutMapping("/{id}")
    public Response<OnlyIdDTO> updateClass(@PathVariable("id") Long clazzId, @RequestBody CreateUpdateClassRequest request) {
        OnlyIdResponse response = clazzService.updateClass(clazzId, request);
        if (response.getSuccess()) {
            return clazzConverter.getResponse(response);
        }
        return clazzConverter.getError(response.getErrorResponse());
    }

    @Operation(summary = "Delete class")
    @DeleteMapping("/{id}")
    public Response<MessageDTO> deleteClass(@PathVariable("id") Long clazzId) {
        NoContentResponse response = clazzService.deleteClass(clazzId);
        return clazzConverter.getResponse(response);
    }

}