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
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToMany(targetEntity = Employee.class)
    private List<Employee> employees;
    @ManyToMany(targetEntity = Pet.class)
    private List<Pet> pets;
    private LocalDate date;
    @ElementCollection
    private Set<EmployeeSkill> activities;
}
