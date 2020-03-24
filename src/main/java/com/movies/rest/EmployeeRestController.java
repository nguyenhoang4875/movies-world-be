package com.movies.rest;

import com.movies.entity.Employee;
import com.movies.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {


    private EmployeeService employeeService;

    // quick and dirty: inject employee dao
    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // expose "/employee" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        employeeService.findAll().forEach(System.out::println);
        return employeeService.findAll();
    }

}
