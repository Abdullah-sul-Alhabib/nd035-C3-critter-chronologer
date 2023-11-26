package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.DTOs.user.EmployeeSkill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * The type Schedule.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue
    private long id;
    @ManyToMany
    private List<Employee> employees;
    @ManyToMany
    private List<Pet> pets;
    private LocalDate date;
    @ElementCollection
    private Set<EmployeeSkill> activities;
}
