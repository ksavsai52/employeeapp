package com.employee.service;

import java.util.List;

import com.employee.domain.Employee;

public interface EmployeeService {

	public Employee getEmployeeById(Long id);

	public Employee updateEmployee(Employee employee);

	public Employee deleteEmployee(Employee employee);

	public Employee saveEmployee(Employee employee);

	public List<Employee> getEmployeeListByManagerName(String managerName);

}
