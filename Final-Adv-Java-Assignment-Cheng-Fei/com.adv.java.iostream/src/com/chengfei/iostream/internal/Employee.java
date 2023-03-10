package com.chengfei.iostream.internal;

import java.time.*;
import java.io.*;

public class Employee implements Serializable{
    private String name;
    private double salary;
    private LocalDate hireDate;

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.hireDate = LocalDate.of(year, month, day);
    }

    public String getName() {return this.name;}
    public double getSalary() {return this.salary;}
    public LocalDate getHireDate() {return this.hireDate;}
}
