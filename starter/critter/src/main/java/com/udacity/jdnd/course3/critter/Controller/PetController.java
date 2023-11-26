package com.udacity.jdnd.course3.critter.Controller;

import com.udacity.jdnd.course3.critter.DTOs.pet.PetDTO;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;

    /**
     * Instantiates a new Pet controller.
     *
     * @param petService the pet service
     */
    public PetController(PetService petService) {
        this.petService = petService;
    }

    /**
     * Save pet pet dto.
     *
     * @param pet the pet
     * @return the pet dto
     */
    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO pet) {
        try {
            return petService.convertToDTO(petService.save(pet));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Gets pet.
     *
     * @param petId the pet id
     * @return the pet
     */
    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        try {
            Pet pet = petService.FindById(petId);
            return petService.convertToDTO(pet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get pets list.
     *
     * @return the list
     */
    @GetMapping
    public List<PetDTO> getPets() {
        try {
            return petService.findAll()
                    .stream().map(petService::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets pets by owner.
     *
     * @param ownerId the owner id
     * @return the pets by owner
     */
    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        try {
            return petService.findByOwnerId(ownerId)
                    .stream().map(petService::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
