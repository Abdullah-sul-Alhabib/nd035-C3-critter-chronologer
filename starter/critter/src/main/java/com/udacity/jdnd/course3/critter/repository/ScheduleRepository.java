package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Schedule repository.
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    /**
     * Find schedule by pets contains list.
     *
     * @param pets the pets
     * @return the list
     */
    List<Schedule> findScheduleByPetsContains(Pet pets);

    /**
     * Find schedule by employees contains list.
     *
     * @param employees the employees
     * @return the list
     */
    List<Schedule> findScheduleByEmployeesContains(Employee employees);

    /**
     * Find all by pets in list.
     *
     * @param pets the pets
     * @return the list
     */
    List<Schedule> findAllByPetsIn(List<Pet> pets);
}
