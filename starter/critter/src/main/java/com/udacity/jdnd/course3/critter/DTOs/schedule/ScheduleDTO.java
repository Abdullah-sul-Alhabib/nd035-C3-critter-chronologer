package com.udacity.jdnd.course3.critter.DTOs.schedule;

import com.udacity.jdnd.course3.critter.DTOs.user.EmployeeSkill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleDTO {
    private long id;
    private List<Long> employeeIds;
    private List<Long> petIds;
    private LocalDate date;
    private Set<EmployeeSkill> activities;
}