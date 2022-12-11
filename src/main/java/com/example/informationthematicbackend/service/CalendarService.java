package com.example.informationthematicbackend.service;

import com.example.informationthematicbackend.request.calendar.CreateUpdateCalendarRequest;
import com.example.informationthematicbackend.request.calendar.ListCalendarRequest;
import com.example.informationthematicbackend.response.NoContentResponse;
import com.example.informationthematicbackend.response.OnlyIdResponse;
import com.example.informationthematicbackend.response.calendar.GetCalendarEventResponse;
import com.example.informationthematicbackend.response.calendar.ListCalendarResponse;

public interface CalendarService {
    ListCalendarResponse getListCalendar(ListCalendarRequest request);
    GetCalendarEventResponse getCalendar(Long calendarEventId);
    OnlyIdResponse createCalendar(CreateUpdateCalendarRequest request);

    OnlyIdResponse updateCalendar(Long calendarEventId, CreateUpdateCalendarRequest request);

    NoContentResponse deleteCalendar(Long calendarEventId);
}