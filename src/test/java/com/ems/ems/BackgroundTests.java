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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BackgroundTests {

    @Autowired
    private EmployeeService employeeService;


    @Test
    public void testCreateEmployee() {
        // Create a new department
        Department department = new Department();
        department.setName("IT");

        // Create a new employee
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setDepartment(department);
        employee = employeeService.addEmployee(employee);

        // Verify the employee is saved and has a valid ID

        // Retrieve the employee by ID and verify the details
        Employee savedEmployee = employeeService.getEmployeeById(employee.getId());
        assertEquals("John", savedEmployee.getFirstName());
        assertEquals("Doe", savedEmployee.getLastName());
        assertEquals(department.getDepartmentId(), savedEmployee.getDepartment().getDepartmentId());
    }
    @Test
    public void testGetEmployeesBySalary() {
        // Create departments
        Department itDepartment = new Department();
        itDepartment.setName("IT");

        Department hrDepartment = new Department();
        hrDepartment.setName("HR");

        // Create employees
        Employee employee1 = new Employee();
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setDepartment(itDepartment);
        employee1.setSalary(25000f);
        employeeService.addEmployee(employee1);

        Employee employee2 = new Employee();
        employee2.setFirstName("Sam");
        employee2.setLastName("Doe");
        employee2.setDepartment(hrDepartment);
        employee2.setSalary(30000f);
        employeeService.addEmployee(employee2);

        // Retrieve employees by department name
        List<Employee> itEmployees = employeeService.getEmployeeBySalary(25000f);
        assertEquals(1, itEmployees.size());
        assertEquals("John", itEmployees.get(0).getFirstName());

        List<Employee> hrEmployees = employeeService.getEmployeeBySalary(30000f);
        assertEquals(1, hrEmployees.size());
        assertEquals("Sam", hrEmployees.get(0).getFirstName());
    }
    @Test
    public void testGetEmployeesByDepartmentName() {
        // Create departments
        Department itDepartment = new Department();
        itDepartment.setName("IT");

        Department hrDepartment = new Department();
        hrDepartment.setName("HR");

        // Create employees
        Employee employee1 = new Employee();
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setDepartment(itDepartment);
        employeeService.addEmployee(employee1);

        Employee employee2 = new Employee();
        employee2.setFirstName("Sam");
        employee2.setLastName("Doe");
        employee2.setDepartment(hrDepartment);
        employeeService.addEmployee(employee2);

        // Retrieve employees by department name
        List<Employee> itEmployees = employeeService.getEmployeesByDepartment("IT");
        assertEquals(1, itEmployees.size());
        assertEquals("John", itEmployees.get(0).getFirstName());

        List<Employee> hrEmployees = employeeService.getEmployeesByDepartment("HR");
        assertEquals(1, hrEmployees.size());
        assertEquals("Sam", hrEmployees.get(0).getFirstName());
    }

    @Test
    public void testGetAllEmployeesWhenEmpty() {
        // Perform the test
        List<Employee> retrievedEmployees = employeeService.getEmployees();

        // Assertions
        assertTrue(!retrievedEmployees.isEmpty());
    }

    @Test
    public void testUpdateEmployee() {
        Department department = new Department();
        department.setName("IT");

        // Create a new employee
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setDepartment(department);
        employee = employeeService.addEmployee(employee);

        employee = employeeService.getEmployeeById(1);
        // perform the test
        employee.setSalary(40000f);
        Employee updatedEmployee = employeeService.updateEmployee(employee);

        // Assertions
        assertNotNull(updatedEmployee);
        assertEquals(40000f, updatedEmployee.getSalary());
    }

    @Test
    public void testGetEmployeeByIdWhenNotExists() {
        // Perform the test
        Employee retrievedEmployee = employeeService.getEmployeeById(999L);

        // Assertions
        assertNull(retrievedEmployee);
    }

    @Test
    public void testSaveEmployeeWithNullName() {
        // Create a new employee with null name
        Employee employee = new Employee(null, null, 1000f, null);

        // Perform the test
        assertThrows(DataIntegrityViolationException.class, () -> employeeService.addEmployee(employee));
    }

    @Test
    public void testDeleteEmployeeWhenNotExists() {
        // Perform the test
        assertThrows(NoSuchElementException.class, () -> employeeService.deleteEmployee(999L));
    }

    @Test
    public void testGetEmployeesByDepartmentNameWhenEmpty() {
        // Perform the test
        List<Employee> retrievedEmployees = employeeService.getEmployeesByDepartment("Engineering");

        // Assertions
        assertTrue(retrievedEmployees.isEmpty());
    }

    @Test
    public void testGetEmployeesByDepartmentNameWhenInvalid() {
        // Add test data to the repository
        Department department = new Department();
        department.setName("IT");

        // Create a new employee
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setDepartment(department);
        employee = employeeService.addEmployee(employee);

        // Perform the test
        List<Employee> retrievedEmployees = employeeService.getEmployeesByDepartment("Sales");

        // Assertions
        assertTrue(retrievedEmployees.isEmpty());
    }



}
