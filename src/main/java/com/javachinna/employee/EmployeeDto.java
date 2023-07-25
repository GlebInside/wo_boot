package com.javachinna.employee;

public class EmployeeDto {
    public final String secondName;
    public final String firstName;
    public final String positionName;

    public EmployeeDto(Employee employee, String positionName) {
        firstName = employee.firstName;
        secondName = employee.secondName;
        this.positionName = positionName;
    }
}
