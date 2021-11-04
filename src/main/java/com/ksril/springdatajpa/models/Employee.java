package com.ksril.springdatajpa.models;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "employee", uniqueConstraints = {
        @UniqueConstraint(name = "employee_email_unique", columnNames = "email") })
public class Employee {
    @Id
    @SequenceGenerator(name = "employee_sequence", sequenceName = "employee_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")

    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "firstName", nullable = false, columnDefinition = "Text")
    private String firstName;

    @Column(name = "lastName", nullable = false, columnDefinition = "Text")
    private String lastName;

    @Column(name = "email", nullable = false, columnDefinition = "Text")
    private String email;

    @Column(name = "dateOfBirth", nullable = false, columnDefinition = "Date")
    private LocalDate dob;

    @Transient
    private Integer age;

    public Employee() {
    }

    public Employee(Long id, String firstName, String lastName, String email, LocalDate dob, Integer age) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setDob(dob);
    }

    public Employee(String firstName, String lastName, String email, LocalDate dob, Integer age) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setDob(dob);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
