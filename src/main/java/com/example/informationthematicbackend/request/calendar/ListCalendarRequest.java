package com.example.informationthematicbackend.request.calendar;

import com.example.informationthematicbackend.request.PageRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListCalendarRequest extends PageRequest {
    private Long userId;
    private Long classId;
    private String calendarEvent;
    private String calendarEventType;
    private Long semesterId;
    private Long schoolYearId;
}
