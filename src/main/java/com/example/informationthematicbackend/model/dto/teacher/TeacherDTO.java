package com.example.informationthematicbackend.model.dto.teacher;

import com.example.informationthematicbackend.model.dto.common.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set")
public class TeacherDTO implements Serializable {
    private UserDTO teacher;
    private Integer numOfPeriodInWeek;
    private List<Clazz> classes;

    @Getter
    @Setter
    @Builder(setterPrefix = "set")
    public static class Clazz {
        private Long classId;
        private String clazz;
        private String schoolYear;
        private String semester;
    }
}
