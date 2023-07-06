package com.ems.ems;

import com.ems.ems.model.Department;
import com.ems.ems.model.Employee;
import com.ems.ems.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class SampleTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EmployeeService employeeService;

	@BeforeEach
	public void setup() throws Exception {
		Department department = new Department("IT");
		Employee employee = new Employee("jack", "stark", 25000f, department);
		ObjectMapper objectMapper = new ObjectMapper();
		String requestJson = objectMapper.writeValueAsString(employee);
		mockMvc.perform(post("/employee")
						.contentType("application/json")
						.content(requestJson))
				.andDo(print())
				.andExpect(status().isCreated());
	}

	@Test
	public void shouldCreateRecord() throws Exception {
		Department department = new Department("IT");
		Employee employee = new Employee("jack", "stark", 25000f, department);
		ObjectMapper objectMapper = new ObjectMapper();
		String requestJson = objectMapper.writeValueAsString(employee);
		MvcResult result = mockMvc.perform(post("/employee")
						.contentType("application/json")
						.content(requestJson))
				.andDo(print())
				.andExpect(status().isCreated()).andReturn();
		employee = objectMapper.readValue(result.getResponse().getContentAsString(), Employee.class);
		assertNotNull(employeeService.getEmployeeById(employee.getId()));
	}


	@Test
	public void testGetEmployeeById() {

		// Verify the employee is saved and has a valid ID

		// Retrieve the employee by ID and verify the details
		Employee savedEmployee = employeeService.getEmployeeById(1);
		assertEquals("jack", savedEmployee.getFirstName());
		assertEquals("stark", savedEmployee.getLastName());
	}
	@Test
	public void testGetEmployeesBySalary() {
		// Retrieve employees by department name
		List<Employee> itEmployees = employeeService.getEmployeeBySalary(25000f);
		assertEquals(1, itEmployees.size());
		assertEquals("jack", itEmployees.get(0).getFirstName());

	}
	@Test
	public void testGetEmployeesByDepartmentName() {

		// Retrieve employees by department name
		List<Employee> itEmployees = employeeService.getEmployeesByDepartment("IT");
		assertEquals(1, itEmployees.size());
		assertEquals("jack", itEmployees.get(0).getFirstName());

		List<Employee> hrEmployees = employeeService.getEmployeesByDepartment("HR");
		assertEquals(0, hrEmployees.size());
	}


}
