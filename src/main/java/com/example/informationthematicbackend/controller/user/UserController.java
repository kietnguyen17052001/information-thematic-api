package com.example.informationthematicbackend.controller.user;

import com.example.informationthematicbackend.converter.UserConverter;
import com.example.informationthematicbackend.model.dto.calendar.CalendarEventDTO;
import com.example.informationthematicbackend.model.dto.common.ClazzDTO;
import com.example.informationthematicbackend.model.dto.common.ListDTO;
import com.example.informationthematicbackend.model.dto.common.MessageDTO;
import com.example.informationthematicbackend.model.dto.common.OnlyIdDTO;
import com.example.informationthematicbackend.model.dto.user.UserInfoDTO;
import com.example.informationthematicbackend.request.calendar.ListCalendarRequest;
import com.example.informationthematicbackend.request.user.ChangePasswordRequest;
import com.example.informationthematicbackend.request.user.UpdateUserRequest;
import com.example.informationthematicbackend.response.NoContentResponse;
import com.example.informationthematicbackend.response.OnlyIdResponse;
import com.example.informationthematicbackend.response.Response;
import com.example.informationthematicbackend.response.UserInfoResponse;
import com.example.informationthematicbackend.response.calendar.ListCalendarResponse;
import com.example.informationthematicbackend.response.clazz.ListClassResponse;
import com.example.informationthematicbackend.security.UserPrincipal;
import com.example.informationthematicbackend.service.UserService;
import com.example.informationthematicbackend.util.RequestUtil;
import com.example.informationthematicbackend.util.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "My account", description = "My Account APIs")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @Operation(summary = "Get my information")
    @GetMapping
    public Response<UserInfoDTO> getMyInfo() {
        UserPrincipal principal = SecurityUtils.getPrincipal();
        UserInfoResponse response = userService.getInfoAccount(principal.getUserId());
        return userConverter.getResponse(response);
    }

    @Operation(summary = "Get my classes")
    @GetMapping("/classes")
    public Response<List<ClazzDTO>> getMyClasses() {
        ListClassResponse response = userService.getListMyClass();
        return userConverter.getResponse(response);
    }

    @Operation(summary = "List calendar event")
    @GetMapping("/calendar")
    public Response<ListDTO<CalendarEventDTO>> listCalendarEvent(@ModelAttribute @Valid ListCalendarRequest request) {
        request.setClassId(RequestUtil.defaultIfNull(request.getClassId(), -1L));
        request.setSemesterId(RequestUtil.defaultIfNull(request.getSemesterId(), -1L));
        ListCalendarResponse response = userService.getListCalendar(request);
        if (response.getSuccess()) {
            return userConverter.getResponse(response);
        }
        return userConverter.getError(response.getErrorResponse());
    }

    @Operation(summary = "Update my information")
    @PutMapping
    public Response<OnlyIdDTO> updateMyInfo(@RequestBody UpdateUserRequest request) {
        UserPrincipal principal = SecurityUtils.getPrincipal();
        OnlyIdResponse response = userService.updateInfoAccount(principal.getUserId(), request);
        if (response.getSuccess()) {
            return userConverter.getResponse(response);
        }
        return userConverter.getError(response.getErrorResponse());
    }

    @Operation(summary = "Change password")
    @PutMapping("/password")
    public Response<MessageDTO> changeMyPassword(@RequestBody ChangePasswordRequest request) {
        UserPrincipal principal = SecurityUtils.getPrincipal();
        NoContentResponse response = userService.changePassword(principal.getUserId(), request);
        if (response.getSuccess()) {
            return userConverter.getResponse(response);
        }
        return userConverter.getError(response.getErrorResponse());
    }
}