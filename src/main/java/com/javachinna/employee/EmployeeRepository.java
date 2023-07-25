package com.javachinna.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;

    private org.springframework.jdbc.core.RowMapper<Employee> RowMapper = (ResultSet resultSet, int n) -> new Employee(resultSet.getLong("employee_id"), resultSet.getString("first_name"), resultSet.getString("second_name"), resultSet.getLong("position_id"));

    public void save(Employee employee) {
        jdbcTemplate.update("insert into employees (first_name, second_name, position_id) values (?, ?, ?)", employee.firstName, employee.secondName, employee.positionId);
    }

    public void update(Employee employee) {
        jdbcTemplate.update("update employees set first_name = ?, second_name = ?, position_id = ? where employee_id = ?", employee.firstName, employee.secondName, employee.positionId, employee.employeeId);
    }

    public Employee findById(Long employeeId) {
        Employee employee = jdbcTemplate.queryForObject("select * from employees where employee_id = ?", RowMapper, employeeId);
        return employee;
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query("select * from employees order by employee_id", RowMapper);
    }

    public void deleteEmployee(Long employeeId) {
        jdbcTemplate.update("delete from employees where employee_id = ?", employeeId);
    }

    public Employee find(String firstName, String secondName) {
        return jdbcTemplate.queryForObject("select * from employees where first_name = ? and second_name = ?", RowMapper, firstName, secondName);
    }

    public List<Employee> getProjectEmployees(Long projectId) {
        return jdbcTemplate.query("select ep.employee_id, first_name, second_name, position_id from employees_projects ep inner join employees e on ep.employee_id = e.employee_id where project_id = ?", RowMapper, projectId);
    }
}

