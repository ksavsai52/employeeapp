package com.employee.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public List<Employee> findAllByManagerName(String managerName);
	
}
