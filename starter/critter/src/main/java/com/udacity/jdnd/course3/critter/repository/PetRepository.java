package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.Entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Pet repository.
 */
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    /**
     * Find pets by owner id list.
     *
     * @param customerId the customer id
     * @return the list
     */
    List<Pet> findPetsByOwner_Id(Long customerId);
}
