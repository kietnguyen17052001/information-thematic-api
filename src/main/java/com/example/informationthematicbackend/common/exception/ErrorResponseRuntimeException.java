package com.example.informationthematicbackend.common.exception;

import com.example.informationthematicbackend.model.dto.common.ErrorDTO;
import com.example.informationthematicbackend.response.ErrorResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class ErrorResponseRuntimeException extends RuntimeException {
    private ErrorResponse errorResponse;
    private List<ErrorDTO> errorDTOs;

    public ErrorResponseRuntimeException(ErrorResponse errorResponse, List<ErrorDTO> errorDTOs) {
        this.errorResponse = errorResponse;
        this.errorDTOs = errorDTOs;
    }
}
