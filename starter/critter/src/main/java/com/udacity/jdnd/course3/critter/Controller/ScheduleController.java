package com.udacity.jdnd.course3.critter.Controller;

import com.udacity.jdnd.course3.critter.DTOs.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * Instantiates a new Schedule controller.
     *
     * @param scheduleService the schedule service
     */
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * Create schedule schedule dto.
     *
     * @param scheduleDTO the schedule dto
     * @return the schedule dto
     */
    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        try {
            return scheduleService.converToDto(scheduleService.save(scheduleDTO));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets all schedules.
     *
     * @return the all schedules
     */
    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        try {
            return scheduleService.findAll().stream()
                    .map(scheduleService::converToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets schedule for pet.
     *
     * @param petId the pet id
     * @return the schedule for pet
     */
    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        try {
            return scheduleService.findByPetId(petId).stream()
                    .map(scheduleService::converToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets schedule for employee.
     *
     * @param employeeId the employee id
     * @return the schedule for employee
     */
    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        try {
            return scheduleService.findByEmployeeId(employeeId)
                    .stream().map(scheduleService::converToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets schedule for customer.
     *
     * @param customerId the customer id
     * @return the schedule for customer
     */
    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        try {
            return scheduleService.findByCustomerId(customerId)
                    .stream().map(scheduleService::converToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
