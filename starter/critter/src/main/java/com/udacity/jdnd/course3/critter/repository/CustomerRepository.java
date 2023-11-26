package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Customer repository.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * Find customer by pets contains customer.
     *
     * @param pet the pet
     * @return the customer
     */
    Customer findCustomerByPetsContains(Pet pet);
}
