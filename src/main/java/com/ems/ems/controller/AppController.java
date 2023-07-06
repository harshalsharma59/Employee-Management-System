package com.ems.ems.controller;

import com.ems.ems.model.Employee;
import com.ems.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AppController {
    @Autowired
    private EmployeeService employeeService;

//    Add a new Employees
    @PostMapping("/employee")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee){
        return new ResponseEntity<String>("Not implemented", HttpStatus.NOT_IMPLEMENTED);
    }

//    Get All Employees
    @GetMapping("/employee")
    public ResponseEntity<?> getEmployees(){
        return new ResponseEntity<String>("Not implemented", HttpStatus.NOT_IMPLEMENTED);
    }

//  Get Employee By Employee ID
    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployeeById(@RequestParam long id){
        return new ResponseEntity<String>("Not implemented", HttpStatus.NOT_IMPLEMENTED);
    }

//    Get Employees By Salary
    @GetMapping(value = "/employee", params = {"salary"})
    public ResponseEntity<?> getEmployeeBySalary(@RequestParam float salary) {
        return new ResponseEntity<String>("Not implemented", HttpStatus.NOT_IMPLEMENTED);
    }

//    Get Employees By Department
    @GetMapping(value = "/employee", params = {"department"})
    public ResponseEntity<?> getEmployeeByDepartment(@RequestParam String department) {
        return new ResponseEntity<String>("Not implemented", HttpStatus.NOT_IMPLEMENTED);
    }

//    Update an employee record
    @PutMapping("/employee")
    public ResponseEntity<?> updateEmployee(@RequestParam Employee employee) {
        return new ResponseEntity<String>("Not implemented", HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@RequestParam long id) {
        return new ResponseEntity<String>("Not implemented", HttpStatus.NOT_IMPLEMENTED);
    }
}
