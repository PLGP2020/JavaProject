package com.carrental.carrental.web;

import com.carrental.carrental.domain.Employee;
import com.carrental.carrental.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/api/employees")
    public Iterable<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/api/employees/{id}")
    public Employee getEmployeeById(@PathVariable("id") long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping(path = "/api/employees/new")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping(path = "/api/employees/{id}")
    public Employee updateEmployee(@PathVariable("id") long employeeId, @RequestBody Employee employee) {
        return employeeService.updateEmployee(employeeId, employee);
    }

    @DeleteMapping(path = "/api/employees/{id}")
    public void deleteEmployee(@PathVariable("id") long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
    
}
