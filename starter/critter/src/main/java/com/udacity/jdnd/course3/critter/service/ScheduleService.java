package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.DTOs.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Schedule service.
 */
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final PetRepository petRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    /**
     * Instantiates a new Schedule service.
     *
     * @param scheduleRepository the schedule repository
     * @param petService         the pet service
     * @param petRepository      the pet repository
     * @param employeeService    the employee service
     * @param employeeRepository the employee repository
     * @param customerService    the customer service
     * @param customerRepository the customer repository
     * @param modelMapper        the model mapper
     */
    public ScheduleService(ScheduleRepository scheduleRepository, PetService petService, PetRepository petRepository, EmployeeService employeeService, EmployeeRepository employeeRepository, CustomerService customerService, CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.scheduleRepository = scheduleRepository;
        this.petRepository = petRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Conver to dto schedule dto.
     *
     * @param schedule the schedule
     * @return the schedule dto
     */
    public ScheduleDTO converToDto(Schedule schedule) {
        ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);
        if (schedule.getEmployees() != null && !schedule.getEmployees().isEmpty()) {
            scheduleDTO.setEmployeeIds(schedule.getEmployees()
                    .stream().map(Employee::getId)
                    .collect(Collectors.toList()));
        }
        if (schedule.getPets() != null && !schedule.getPets().isEmpty()) {
            scheduleDTO.setPetIds(schedule.getPets()
                    .stream().map(Pet::getId)
                    .collect(Collectors.toList()));
        }

        return scheduleDTO;
    }

    /**
     * Save schedule.
     *
     * @param scheduleDTO the schedule dto
     * @return the schedule
     */
    public Schedule save(ScheduleDTO scheduleDTO) {
        Schedule schedule = modelMapper.map(scheduleDTO, Schedule.class);

        if (scheduleDTO.getEmployeeIds() != null && !scheduleDTO.getEmployeeIds().isEmpty()) {
            schedule.setEmployees(employeeRepository.findAllById(scheduleDTO.getEmployeeIds()));
        }
        if (scheduleDTO.getPetIds() != null && !scheduleDTO.getPetIds().isEmpty()) {
            schedule.setPets(petRepository.findAllById(scheduleDTO.getPetIds()));
        }

        return scheduleRepository.save(schedule);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    /**
     * Find by employee id list.
     *
     * @param employeeId the employee id
     * @return the list
     */
    public List<Schedule> findByEmployeeId(long employeeId) {
        return scheduleRepository.findScheduleByEmployeesContains(employeeRepository.getOne(employeeId));
    }

    /**
     * Find by pet id list.
     *
     * @param petId the pet id
     * @return the list
     */
    public List<Schedule> findByPetId(long petId) {
        return scheduleRepository.findScheduleByPetsContains(petRepository.getOne(petId));
    }

    /**
     * Find by customer id list.
     *
     * @param customerId the customer id
     * @return the list
     */
    public List<Schedule> findByCustomerId(long customerId) {
        return scheduleRepository.findAllByPetsIn(customerRepository.getOne(customerId).getPets());
    }


}
