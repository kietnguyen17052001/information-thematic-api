package com.example.informationthematicbackend.converter;

import com.example.informationthematicbackend.model.dto.calendar.CalendarEventDTO;
import com.example.informationthematicbackend.model.dto.calendar.CalendarEventDetailDTO;
import com.example.informationthematicbackend.model.dto.common.ListDTO;
import com.example.informationthematicbackend.response.Response;
import com.example.informationthematicbackend.response.calendar.GetCalendarEventResponse;
import com.example.informationthematicbackend.response.calendar.ListCalendarResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class CalendarConverter extends CommonConverter {
    public Response<ListDTO<CalendarEventDTO>> getResponse(ListCalendarResponse response) {
        return Response.<ListDTO<CalendarEventDTO>>builder()
                .setSuccess(true)
                .setData(ListDTO.<CalendarEventDTO>builder()
                        .setTotalItems((long) response.getItems().size())
                        .setItems(response.getItems())
                        .build())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public Response<CalendarEventDetailDTO> getResponse(GetCalendarEventResponse response) {
        return Response.<CalendarEventDetailDTO>builder()
                .setSuccess(true)
                .setData(response.getCalendarEventDetail())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}