package com.ksril.springdatajpa.services;

import java.util.List;

import com.ksril.springdatajpa.models.Employee;

public interface IEmployeeService {

    public List<Employee> SelectAll();

    public Employee Select(Long id);

    public void Insert(Employee employee);

    public void Update(Long id, Employee employee);

    public void Delete(Long id);

}
