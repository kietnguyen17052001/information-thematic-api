package com.example.informationthematicbackend.response.calendar;

import com.example.informationthematicbackend.model.dto.calendar.CalendarEventDetailDTO;
import com.example.informationthematicbackend.response.ErrorResponse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class GetCalendarEventResponse {
    private Boolean success;
    private ErrorResponse errorResponse;
    private CalendarEventDetailDTO calendarEventDetail;
}
