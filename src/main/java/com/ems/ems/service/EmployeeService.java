package com.ems.ems.service;

import com.ems.ems.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    public Employee addEmployee(Employee employee);
    public List<Employee> getEmployees();
    public Employee getEmployeeById(long id);
    public List<Employee> getEmployeesByDepartment(String departmentName);
    public List<Employee> getEmployeeBySalary(Float salary);
    public Employee updateEmployee(Employee employee);

    public void deleteEmployee(long id);

}
