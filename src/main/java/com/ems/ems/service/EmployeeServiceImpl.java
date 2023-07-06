package com.ems.ems.service;

import com.ems.ems.model.Employee;
import com.ems.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        return null;
    }

    @Override
    public List<Employee> getEmployees() {
        return null;
    }

    @Override
    public Employee getEmployeeById(long id) {
        return null;
    }

    @Override
    public List<Employee> getEmployeesByDepartment(String departmentName) {
        return null;
    }

    @Override
    public List<Employee> getEmployeeBySalary(Float salary) {
        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public void deleteEmployee(long id) {

    }
}
