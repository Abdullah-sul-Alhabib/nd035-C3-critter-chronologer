package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.DTOs.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * The type Customer service.
 */
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final PetRepository petRepository;


    /**
     * Instantiates a new Customer service.
     *
     * @param customerRepository the customer repository
     * @param modelMapper        the model mapper
     * @param petRepository      the pet repository
     */
    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.petRepository = petRepository;
    }

    /**
     * Save customer.
     *
     * @param customerDTO the customer dto
     * @return the customer
     */
    public Customer save(CustomerDTO customerDTO) {
        Customer owner = modelMapper.map(customerDTO, Customer.class);
        if (customerDTO.getPetIds() != null) {
            owner = setPetIds(customerDTO.getPetIds(), owner);
        }
        return customerRepository.save(owner);
    }

    private Customer setPetIds(List<Long> petIds, Customer customer) {
        List<Pet> pets = petRepository.findAllById(petIds);
        customer.setPets(pets);
        return customer;
    }

    /**
     * Convert to dto customer dto.
     *
     * @param customer the customer
     * @return the customer dto
     */
    public CustomerDTO convertToDTO(Customer customer) {
        //Conversion done through modelMapper, courtesy of Baeldung's 'Entity To DTO Conversion' tutorial.
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        if (customer.getPets() != null && !customer.getPets().isEmpty()) {
            customerDTO.setPetIds(customer.getPets()
                    .stream().map(Pet::getId)
                    .collect(Collectors.toList()));
        }
        return customerDTO;
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    /**
     * Find by id customer.
     *
     * @param id the id
     * @return the customer
     * @throws NoSuchElementException the no such element exception
     */
    public Customer findById(long id) throws NoSuchElementException {
        return customerRepository.findById(id).get();
    }

    /**
     * Find by pet id customer.
     *
     * @param petId the pet id
     * @return the customer
     * @throws NoSuchElementException the no such element exception
     */
    public Customer findByPetId(long petId) throws NoSuchElementException {
        return customerRepository.findCustomerByPetsContains(petRepository.getOne(petId));
    }
}
