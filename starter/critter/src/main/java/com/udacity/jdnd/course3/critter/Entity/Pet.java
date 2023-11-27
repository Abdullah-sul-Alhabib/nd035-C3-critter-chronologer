package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.DTOs.pet.PetType;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Pet.
 */
@Entity
@Table
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private PetType type;
    private String name;
    @ManyToOne(optional = false,
            targetEntity = Customer.class)
    private Customer owner;
    private LocalDate birthDate;
    private String notes;
    @ManyToMany(targetEntity = Schedule.class)
    private List<Schedule> schedules;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Pet() {
    }

    public Pet(long id, PetType type, String name, Customer owner, LocalDate birthDate, String notes, List<Schedule> schedules) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.owner = owner;
        this.birthDate = birthDate;
        this.notes = notes;
        this.schedules = schedules;
    }
}

