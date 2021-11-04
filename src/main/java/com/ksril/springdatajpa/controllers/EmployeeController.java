package com.ksril.springdatajpa.controllers;

import java.util.List;

import com.ksril.springdatajpa.models.Employee;
import com.ksril.springdatajpa.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/employee")
public class EmployeeController implements IEmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Employee> SelectAll() {
        return service.SelectAll();
    }

    @Override
    @GetMapping(path = "{id}")
    public Employee Select(@PathVariable("id") Long id) {
        return service.Select(id);
    }

    @Override
    @PostMapping
    public void Insert(@RequestBody Employee employee) {
        service.Insert(employee);
    }

    @Override
    @PutMapping(path = "{id}")
    public void Update(@PathVariable("id") Long id, @RequestBody Employee employee) {
        service.Update(id, employee);
    }

    @Override
    @DeleteMapping(path = "{id}")
    public void Delete(@PathVariable("id") Long id) {
        service.Delete(id);
    }

}
