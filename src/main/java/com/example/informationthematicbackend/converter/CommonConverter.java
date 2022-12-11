package com.example.informationthematicbackend.converter;

import com.example.informationthematicbackend.common.exception.ErrorResponseRuntimeException;
import com.example.informationthematicbackend.model.dto.common.ErrorDTO;
import com.example.informationthematicbackend.model.dto.common.MessageDTO;
import com.example.informationthematicbackend.model.dto.common.OnlyIdDTO;
import com.example.informationthematicbackend.response.ErrorResponse;
import com.example.informationthematicbackend.response.NoContentResponse;
import com.example.informationthematicbackend.response.OnlyIdResponse;
import com.example.informationthematicbackend.response.Response;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommonConverter {

    public Response<OnlyIdDTO> getResponse(OnlyIdResponse response) {
        return Response.<OnlyIdDTO>builder()
                .setSuccess(true)
                .setData(OnlyIdDTO.builder()
                        .setId(response.getId())
                        .setName(response.getName())
                        .build())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public Response<MessageDTO> getResponse(NoContentResponse response) {
        return Response.<MessageDTO>builder()
                .setSuccess(true)
                .setMessage("Successful")
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public <T> Response<T> getError(ErrorResponse errorResponse) {
        throw new ErrorResponseRuntimeException(errorResponse, getErrorDTOs(errorResponse.getErrors()));
    }

    public List<ErrorDTO> getErrorDTOs(Map<String, String> errors) {
        List<ErrorDTO> errorDTOS = new ArrayList<>();
        for (var v : errors.entrySet()) {
            errorDTOS.add(ErrorDTO.builder()
                    .setKey(v.getKey())
                    .setValue(v.getValue())
                    .build());
        }
        return errorDTOS;
    }
}
