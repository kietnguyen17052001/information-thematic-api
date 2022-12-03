package com.example.informationthematicbackend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classcalendarevent")
public class ClassCalendarEventEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classcalendareventid")
    private Long classCalendarEventId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classid", referencedColumnName = "classid")
    private ClassEntity clazz;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendareventid", referencedColumnName = "calendareventid")
    private CalendarEventEntity calendarEvent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schoolyearid", referencedColumnName = "schoolyearid")
    private SchoolYearEntity schoolYear;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semesterid", referencedColumnName = "semesterid")
    private SemesterEntity semester;
}