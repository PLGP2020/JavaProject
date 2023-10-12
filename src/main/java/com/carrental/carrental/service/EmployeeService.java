package com.carrental.carrental.service;

import com.carrental.carrental.domain.Employee;

public interface EmployeeService {

    Iterable<Employee> getAllEmployees();

    Employee getEmployeeById(Long employeeId);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee updatedEmployee);

    void deleteEmployee(Long id);
}
