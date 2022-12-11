package com.example.informationthematicbackend.controller.calendar;

import com.example.informationthematicbackend.converter.CalendarConverter;
import com.example.informationthematicbackend.model.dto.calendar.CalendarEventDTO;
import com.example.informationthematicbackend.model.dto.calendar.CalendarEventDetailDTO;
import com.example.informationthematicbackend.model.dto.common.ListDTO;
import com.example.informationthematicbackend.model.dto.common.MessageDTO;
import com.example.informationthematicbackend.model.dto.common.OnlyIdDTO;
import com.example.informationthematicbackend.request.calendar.CreateUpdateCalendarRequest;
import com.example.informationthematicbackend.request.calendar.ListCalendarRequest;
import com.example.informationthematicbackend.response.NoContentResponse;
import com.example.informationthematicbackend.response.OnlyIdResponse;
import com.example.informationthematicbackend.response.Response;
import com.example.informationthematicbackend.response.calendar.GetCalendarEventResponse;
import com.example.informationthematicbackend.response.calendar.ListCalendarResponse;
import com.example.informationthematicbackend.service.CalendarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Calendar", description = "Calendar APIs")
@RestController
@RequestMapping("/api/calendars")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarConverter calendarConverter;
    private final CalendarService calendarService;

    @Operation(summary = "List calendar event")
    @GetMapping
    public Response<ListDTO<CalendarEventDTO>> getListCalendarEvent(@Valid @ModelAttribute ListCalendarRequest request) {
        ListCalendarResponse response = calendarService.getListCalendar(request);
        if (response.getSuccess()) {
            return calendarConverter.getResponse(response);
        }
        return calendarConverter.getError(response.getErrorResponse());
    }

    @Operation(summary = "Get calendar event")
    @GetMapping("/{id}")
    public Response<CalendarEventDetailDTO> getCalendarEvent(@PathVariable("id") Long calendarEventId) {
        GetCalendarEventResponse response = calendarService.getCalendar(calendarEventId);
        if (response.getSuccess()) {
            return calendarConverter.getResponse(response);
        }
        return calendarConverter.getError(response.getErrorResponse());
    }

    @Operation(summary = "Create calendar event")
    @PostMapping
    public Response<OnlyIdDTO> createCalendarEvent(@Valid @RequestBody CreateUpdateCalendarRequest request) {
        OnlyIdResponse response = calendarService.createCalendar(request);
        if (response.getSuccess()) {
            return calendarConverter.getResponse(response);
        }
        return calendarConverter.getError(response.getErrorResponse());
    }

    @Operation(summary = "Update calendar event")
    @PutMapping("/{id}")
    public Response<OnlyIdDTO> updateCalendarEvent(@PathVariable("id") Long calendarEventId, @Valid @RequestBody CreateUpdateCalendarRequest request) {
        OnlyIdResponse response = calendarService.updateCalendar(calendarEventId, request);
        if (response.getSuccess()) {
            return calendarConverter.getResponse(response);
        }
        return calendarConverter.getError(response.getErrorResponse());
    }

    @Operation(summary = "Delete calendar event")
    @DeleteMapping("/{id}")
    public Response<MessageDTO> deleteCalendarEvent(@PathVariable("id") Long calendarEventId) {
        NoContentResponse response = calendarService.deleteCalendar(calendarEventId);
        return calendarConverter.getResponse(response);
    }
}