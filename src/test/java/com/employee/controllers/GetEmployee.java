package com.employee.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.employee.repositories.EmployeeRepository;
import com.employee.rest.RemoteApi;
import com.employee.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/config/applicationContext.xml")
public class GetEmployee {

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
	public void testGetEmployee() throws Exception {
		Long id = 1l;
		when(employeeService.getEmployeeById(id)).thenReturn(employeeRepository.findOne(id));
		mockMvc.perform(get("/getEmployee/" + id)).andDo(print());
	}
	
}
