package com.example.employee.records;

import jakarta.persistence.Id;
//public record EmployeeRec (@Id Integer id, String firstName, String lastName, String metadata) {}
public record EmployeeRec (Integer id, String firstName, String lastName, String metadata) {}

