package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.DTOs.pet.PetDTO;
import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Pet service.
 */
@Service
public class PetService {
    private final PetRepository petRepository;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    /**
     * Instantiates a new Pet service.
     *
     * @param petRepository      the pet repository
     * @param modelMapper        the model mapper
     * @param customerRepository the customer repository
     */
    public PetService(PetRepository petRepository, ModelMapper modelMapper, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
    }

    /**
     * Convert to dto pet dto.
     *
     * @param pet the pet
     * @return the pet dto
     */
    public PetDTO convertToDTO(Pet pet) {
        return modelMapper.map(pet, PetDTO.class);
    }

    /**
     * Find by id pet.
     *
     * @param petId the pet id
     * @return the pet
     */
    public Pet findById(long petId) {
        return petRepository.getOne(petId);
    }

    /**
     * Save pet.
     *
     * @param petDTO the pet dto
     * @return the pet
     */
    public Pet save(PetDTO petDTO) {
        Pet pet = modelMapper.map(petDTO, Pet.class);
        Customer owner = customerRepository.getOne(petDTO.getOwnerId());
        List<Pet> petList = new ArrayList<>();
        if (owner.getPets() == null) {
            owner.setPets(petList);
        } else {
            petList = owner.getPets();
        }
        pet.setOwner(owner);
        pet = petRepository.save(pet);
        petList.add(pet);
        owner.setPets(petList);
        customerRepository.save(owner);

        return pet;
    }

    /**
     * Find by id pet.
     *
     * @param Id the id
     * @return the pet
     */
    public Pet FindById(long Id) {
        return petRepository.getOne(Id);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    /**
     * Find by owner id list.
     *
     * @param customerId the customer id
     * @return the list
     */
    public List<Pet> findByOwnerId(Long customerId) {
        return petRepository.findPetsByOwner_Id(customerId);
    }
}
