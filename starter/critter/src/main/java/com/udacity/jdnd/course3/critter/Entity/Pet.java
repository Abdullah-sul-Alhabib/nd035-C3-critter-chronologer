package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.DTOs.pet.PetType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Pet.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue
    private long id;
    private PetType type;
    private String name;
    @ManyToOne(optional = false,
            targetEntity = Customer.class)
    private Customer owner;
    private LocalDate birthDate;
    private String notes;
    @ManyToMany
    private List<Schedule> schedules;
}
