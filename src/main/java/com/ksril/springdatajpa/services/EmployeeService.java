package com.ksril.springdatajpa.services;

import java.io.Console;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import com.ksril.springdatajpa.models.Employee;
import com.ksril.springdatajpa.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> SelectAll() {
        return repository.findAll();
    }

    @Override
    public Employee Select(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void Insert(Employee employee) {
        repository.save(employee);
    }

    @Override
    @Transactional
    public void Update(Long id, Employee employee) {

        Employee currentEmployee = repository.getById(id);

        if (currentEmployee == null) {
            throw new IllegalStateException("Not found employee record with ID " + id);
        } else {
            if (employee.getFirstName() != null && employee.getFirstName().length() > 0
                    && !Objects.equals(currentEmployee.getFirstName(), employee.getFirstName()))
                currentEmployee.setFirstName(employee.getFirstName());

            if (employee.getLastName() != null && employee.getLastName().length() > 0
                    && !Objects.equals(currentEmployee.getLastName(), employee.getLastName()))
                currentEmployee.setLastName(employee.getLastName());

            if (employee.getEmail() != null && employee.getEmail().length() > 0
                    && !Objects.equals(currentEmployee.getEmail(), employee.getEmail())) {
                Optional<Employee> employeeByEmail = repository.findEmployeeByEmail(employee.getEmail());
                if (employeeByEmail.isPresent())
                    throw new IllegalStateException("Email is already taken.");

                currentEmployee.setEmail(employee.getEmail());
            }
        }
    }

    @Override
    public void Delete(Long id) {
        repository.deleteById(id);
    }

}
