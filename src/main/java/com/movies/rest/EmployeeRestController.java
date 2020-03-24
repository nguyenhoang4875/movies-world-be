package com.movies.rest;

import com.movies.dao.EmployeeDao;
import com.movies.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeDao employeeDao;

    // quick and dirty: inject employee dao
    @Autowired
    public EmployeeRestController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    // expose "/employee" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        employeeDao.findAll().forEach(System.out::println);
        return employeeDao.findAll();
    }


}
