package com.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.employee.domain.Department;
import com.employee.domain.Employee;
import com.employee.rest.RemoteApi;
import com.employee.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private RemoteApi remoteApi;

	@RequestMapping("/getEmployee/{id}")
	@ResponseBody
	public Employee getEmployee(@PathVariable("id") Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		return employee;
	}

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.PUT)
	public @ResponseBody Employee updateEmployee(@RequestBody Employee employee) {
		RestTemplate restTemplate = remoteApi.getRestTemplate();
		List<Employee> employees = employeeService.getEmployeeListByManagerName(employee.getManagerName());
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println(employees.size());
		Department department = restTemplate
				.exchange("http://localhost:8080/DepartmentApp/getDepartment/" + employee.getDepartment().getId(),
						HttpMethod.GET, remoteApi.getHttpEntity(), Department.class)
				.getBody();
		if ((department != null) && (employee.getSalary() > department.getMinSalaryRange())
				&& (employee.getSalary() < department.getMaxSalaryRange()) && (employees.size() > 1)) {
			employeeService.updateEmployee(employee);
		}
		return employee;
	}

	@RequestMapping(value = "/updateSalary", method = RequestMethod.PUT)
	public @ResponseBody Employee updateSalary(@RequestBody Employee employee) {
		employeeService.updateEmployee(employee);
		return employee;
	}

	@RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Employee deleteEmployee(@PathVariable("id") Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		employee.setDepartment(null);
		employeeService.updateEmployee(employee);
		return employeeService.deleteEmployee(employee);
	}

	@RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
	public @ResponseBody Employee createEmployee(@RequestBody Employee employee) {
		RestTemplate restTemplate = remoteApi.getRestTemplate();
		List<Employee> employees = employeeService.getEmployeeListByManagerName(employee.getManagerName());
		Department department = restTemplate
				.exchange("http://localhost:8080/DepartmentApp/getDepartment/" + employee.getDepartment().getId(),
						HttpMethod.GET, remoteApi.getHttpEntity(), Department.class)
				.getBody();
		if ((department != null) && (employee.getSalary() > department.getMinSalaryRange())
				&& (employee.getSalary() < department.getMaxSalaryRange()) && (employees.size() > 1)) {
			employee = employeeService.saveEmployee(employee);
		}
		return employee;
	}
}
