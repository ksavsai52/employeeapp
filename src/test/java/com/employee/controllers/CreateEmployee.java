package com.employee.controllers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.employee.domain.Employee;
import com.employee.repositories.EmployeeRepository;
import com.employee.rest.RemoteApi;
import com.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/config/applicationContext.xml")
public class CreateEmployee {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Mock
	private EmployeeService employeeService;
	
	@Mock
	private RemoteApi remoteApi;

	@InjectMocks
	private EmployeeController employeeController;

	private MockMvc mockMvc;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}

	@Test
	public void testCreateEmployee() throws Exception {
		Employee employee = employeeRepository.findOne(1l);
		Employee employee2 = new Employee();
		employee2.setName("ASASAADDDDsss");
		employee2.setSalary(34000);
		employee2.setManagerName(employee.getManagerName());
		employee2.setDepartment(employee.getDepartment());
		
		ObjectMapper objectMapper = new ObjectMapper();
		String emp = objectMapper.writeValueAsString(employee2);
		
		when(employeeService.getEmployeeListByManagerName(any())).thenReturn(employeeRepository.findAllByManagerName(employee.getManagerName()));
		when(employeeService.saveEmployee(any())).thenReturn(employeeRepository.save(employee2));
		when(remoteApi.getRestTemplate()).thenReturn(new RemoteApi().getRestTemplate());
		when(remoteApi.getHttpEntity()).thenReturn((HttpEntity) new RemoteApi().getHttpEntity());
		mockMvc.perform(post("/createEmployee").contentType(MediaType.APPLICATION_JSON).content(emp)).andDo(print());
	}

}
