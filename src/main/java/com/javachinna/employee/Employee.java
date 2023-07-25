package com.javachinna.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    Long employeeId;
    String firstName;
    String secondName;
    Long positionId;
}
