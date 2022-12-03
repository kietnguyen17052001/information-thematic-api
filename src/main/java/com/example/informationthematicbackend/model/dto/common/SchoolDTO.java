package com.example.informationthematicbackend.model.dto.common;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set")
public class SchoolDTO implements Serializable {
    private Long schoolId;
    private String school;
    private String phone;
    private String schoolType;
    private String street;
    private String district;
    private String city;
    private String website;
}
