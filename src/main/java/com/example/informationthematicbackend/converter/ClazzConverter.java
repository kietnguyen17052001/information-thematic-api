package com.example.informationthematicbackend.converter;

import com.example.informationthematicbackend.model.dto.common.ClazzDTO;
import com.example.informationthematicbackend.model.dto.common.ListDTO;
import com.example.informationthematicbackend.response.Response;
import com.example.informationthematicbackend.response.clazz.GetClassResponse;
import com.example.informationthematicbackend.response.clazz.ListClassResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ClazzConverter extends CommonConverter {
    public Response<ListDTO<ClazzDTO>> getResponse(ListClassResponse response) {
        return Response.<ListDTO<ClazzDTO>>builder()
                .setSuccess(true)
                .setData(ListDTO.<ClazzDTO>builder()
                        .setTotalItems((long) response.getItems().size())
                        .setItems(response.getItems())
                        .build())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public Response<ClazzDTO> getResponse(GetClassResponse response) {
        return Response.<ClazzDTO>builder()
                .setSuccess(true)
                .setData(response.getClazzDTO())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}