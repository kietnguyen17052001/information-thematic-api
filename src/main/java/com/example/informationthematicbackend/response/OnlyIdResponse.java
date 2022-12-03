package com.example.informationthematicbackend.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(setterPrefix = "set")
public class OnlyIdResponse {
    private Long id;
    private String name;
    private Boolean success;
    private ErrorResponse errorResponse;
}
