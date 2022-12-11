package com.example.informationthematicbackend.converter;

import com.example.informationthematicbackend.model.dto.common.ListDTO;
import com.example.informationthematicbackend.model.dto.common.SubjectDTO;
import com.example.informationthematicbackend.response.Response;
import com.example.informationthematicbackend.response.subject.GetListSubjectResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class SubjectConverter extends CommonConverter {

    public Response<ListDTO<SubjectDTO>> getResponse(GetListSubjectResponse response) {
        return Response.<ListDTO<SubjectDTO>>builder()
                .setSuccess(true)
                .setData(ListDTO.<SubjectDTO>builder()
                        .setTotalItems((long) response.getItems().size())
                        .setItems(response.getItems())
                        .build())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}