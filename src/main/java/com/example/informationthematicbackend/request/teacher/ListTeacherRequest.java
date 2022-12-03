package com.example.informationthematicbackend.request.teacher;

import com.example.informationthematicbackend.request.PageRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListTeacherRequest extends PageRequest {
    private String firstName;
    private String lastName;
    private String search;
    private String district;
    private Long classId;
    private Long subjectId;
}
