package com.example.employee.controller;

import com.example.employee.entity.Employee;
import com.example.employee.records.EmployeeRec;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    EmployeeController (EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(value = "/employees/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
        return ResponseEntity.ok().body(employee);
    }

    @GetMapping(value="/employees", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @PostMapping(value="/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee createEmployee(@RequestBody EmployeeRec emp) {
        var employee = new Employee(emp.id(), emp.firstName(), emp.lastName(), emp.metadata());
        return employeeRepository.save(employee);
    }

    @PutMapping(value="/employeesEmployee", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee updateEmployee(@RequestBody EmployeeRec emp) {
        var employee = new Employee(emp.id(), emp.firstName(), emp.lastName(), emp.metadata());
        return employeeRepository.save(employee);
    }

    @DeleteMapping(value = "/employees/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
        employeeRepository.delete(employee);
        return ResponseEntity.ok().build();
    }
}