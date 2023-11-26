package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.DTOs.user.EmployeeSkill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * The type Employee.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ElementCollection
    private Set<EmployeeSkill> skills;
    @ElementCollection
    private Set<DayOfWeek> daysAvailable;
    @ManyToMany
    private List<Schedule> schedules;
}
