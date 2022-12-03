package com.example.informationthematicbackend.response.user;

import com.example.informationthematicbackend.model.dto.common.UserDTO;
import com.example.informationthematicbackend.response.ErrorResponse;
import com.example.informationthematicbackend.response.PageResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class ListUserResponse {
    private Boolean success;
    private ErrorResponse errorResponse;
    private PageResponse pageResponse;
    private List<UserDTO> items;
}
