package com.udacity.jdnd.course3.critter.DTOs.pet;

import com.udacity.jdnd.course3.critter.Entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PetDTO {
    private long id;
    private PetType type;
    private String name;
    private long ownerId;
    private LocalDate birthDate;
    private String notes;

    /**
     * Instantiates a new Pet dto.
     *
     * @param pet the pet
     */
    public PetDTO(Pet pet) {
        id = pet.getId();
        type = pet.getType();
        name = pet.getName();
        ownerId = pet.getId();
        birthDate = pet.getBirthDate();
        notes = pet.getNotes();
    }
}
