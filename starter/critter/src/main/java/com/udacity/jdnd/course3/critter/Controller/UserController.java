package com.udacity.jdnd.course3.critter.Controller;

import com.udacity.jdnd.course3.critter.DTOs.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.DTOs.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.DTOs.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final ScheduleService scheduleService;

    /**
     * Instantiates a new User controller.
     *
     * @param customerService the customer service
     * @param employeeService the employee service
     * @param scheduleService the schedule service
     */
    public UserController(CustomerService customerService, EmployeeService employeeService, ScheduleService scheduleService) {
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.scheduleService = scheduleService;
    }

    /**
     * Save customer customer dto.
     *
     * @param customerDTO the customer dto
     * @return the customer dto
     */
    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.convertToDTO(customerService.save(customerDTO));

    }

    /**
     * Get all customers list.
     *
     * @return the list
     */
    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        try {
            return customerService.findAll()
                    .stream().map(customerService::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get owner by pet customer dto.
     *
     * @param petId the pet id
     * @return the customer dto
     */
    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        try {
            return customerService.convertToDTO(customerService.findByPetId(petId));
        } catch (NoSuchElementException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Save employee employee dto.
     *
     * @param employeeDTO the employee dto
     * @return the employee dto
     */
    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            return employeeService.convertToDto(employeeService.save(employeeDTO));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets employee.
     *
     * @param employeeId the employee id
     * @return the employee
     */
    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        try {
            return employeeService.convertToDto(employeeService.findById(employeeId));
        } catch (NoSuchElementException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets availability.
     *
     * @param daysAvailable the days available
     * @param employeeId    the employee id
     */
    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        try {
            employeeService.setAvailability(employeeId, daysAvailable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Find employees for service list.
     *
     * @param employeeDTO the employee dto
     * @return the list
     */
    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        try {
            return employeeService.getAvailableEmployees(employeeDTO.getDate(), employeeDTO.getSkills())
                    .stream().map(employeeService::convertToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
