package com.ahmadnaser.employeedbapp.domain;

import java.math.BigDecimal;

public class Employee {



	public static final String C_ID = "_id";
	public static final String C_ENAME = "ename";
	public static final String C_DESIGNATION = "designation";
	public static final String C_SALARY = "salary";


	private Long id;
	private String Name;
    private String Designation;
    private double Salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }
}