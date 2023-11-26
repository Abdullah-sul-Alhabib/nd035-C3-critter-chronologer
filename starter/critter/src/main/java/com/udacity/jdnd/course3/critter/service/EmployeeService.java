package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.DTOs.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.DTOs.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Employee service.
 */
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    /**
     * Instantiates a new Employee service.
     *
     * @param employeeRepository the employee repository
     * @param modelMapper        the model mapper
     */
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Save employee.
     *
     * @param employee the employee
     * @return the employee
     */
    public Employee save(EmployeeDTO employee) {
        return employeeRepository.save(modelMapper.map(employee, Employee.class));
    }

    /**
     * Convert to dto employee dto.
     *
     * @param employee the employee
     * @return the employee dto
     */
    public EmployeeDTO convertToDto(Employee employee) {
        //Conversion done through modelMapper, courtesy of Baeldung's 'Entity To DTO Conversion' tutorial.
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    /**
     * Find by id employee.
     *
     * @param id the id
     * @return the employee
     * @throws NoSuchElementException the no such element exception
     */
    public Employee findById(Long id) throws NoSuchElementException {
        return employeeRepository.getOne(id);
    }


    /**
     * Sets days available.
     *
     * @param id            the id
     * @param daysAvailable the days available
     */
    public void setDaysAvailable(Long id, Set<DayOfWeek> daysAvailable) {
        Employee employee = employeeRepository.getOne(id);
        employee.setDaysAvailable(daysAvailable);

        employeeRepository.save(employee);
    }

    /**
     * Find days available set.
     *
     * @param id the id
     * @return the set
     * @throws NoSuchElementException the no such element exception
     */
    public Set<DayOfWeek> findDaysAvailable(Long id) throws NoSuchElementException {
        return employeeRepository.findById(id).get().getDaysAvailable();
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    /**
     * Gets available employees.
     *
     * @param date          the date
     * @param employeeSkill the employee skill
     * @return the available employees
     */
    public List<Employee> getAvailableEmployees(LocalDate date, Set<EmployeeSkill> employeeSkill) {
        List<Employee> allEmployees = findAll();
        return allEmployees.stream().filter(employee ->
                        employee.getDaysAvailable().contains(date.getDayOfWeek())
                                && employee.getSkills().containsAll(employeeSkill))
                .collect(Collectors.toList());
    }
}
