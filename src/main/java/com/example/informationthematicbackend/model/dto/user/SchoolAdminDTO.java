package com.example.informationthematicbackend.model.dto.user;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class SchoolAdminDTO implements Serializable {
    private Long schoolAdminId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Long schoolId;
    private String schoolName;
}
