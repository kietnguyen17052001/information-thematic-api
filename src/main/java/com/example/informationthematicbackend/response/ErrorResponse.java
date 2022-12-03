package com.example.informationthematicbackend.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder(setterPrefix = "set")
public class ErrorResponse {
    private String message;
    private Map<String, String> errors;
}
