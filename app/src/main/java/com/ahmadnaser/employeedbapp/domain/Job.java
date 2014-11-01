package com.ahmadnaser.employeedbapp.domain;

public class Job {



	public static final String C_ID = "_id";
	public static final String C_NAME = "name";



	private Long id;
	private String Name;


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

}