package com.example.informationthematicbackend.service;

import com.example.informationthematicbackend.request.calendar.ListCalendarRequest;
import com.example.informationthematicbackend.request.user.ChangePasswordRequest;
import com.example.informationthematicbackend.request.user.UpdateUserRequest;
import com.example.informationthematicbackend.response.NoContentResponse;
import com.example.informationthematicbackend.response.OnlyIdResponse;
import com.example.informationthematicbackend.response.UserInfoResponse;
import com.example.informationthematicbackend.response.calendar.ListCalendarResponse;
import com.example.informationthematicbackend.response.clazz.ListClassResponse;

public interface UserService {
    UserInfoResponse getInfoAccount(Long userId);

    OnlyIdResponse updateInfoAccount(Long userId, UpdateUserRequest request);

    NoContentResponse changePassword(Long userId, ChangePasswordRequest request);

    ListCalendarResponse getListCalendar(ListCalendarRequest request);

    ListClassResponse getListMyClass();
}
