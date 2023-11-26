package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Employee repository.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
