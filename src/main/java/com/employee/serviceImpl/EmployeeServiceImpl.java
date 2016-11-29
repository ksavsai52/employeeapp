package com.employee.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.domain.Employee;
import com.employee.repositories.EmployeeRepository;
import com.employee.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findOne(id);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
		return employee;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getEmployeeListByManagerName(String managerName) {
		return employeeRepository.findAllByManagerName(managerName);
	}

}
