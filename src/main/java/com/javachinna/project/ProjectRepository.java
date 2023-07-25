package com.javachinna.project;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProjectRepository {

    private final JdbcTemplate jdbcTemplate;

    private RowMapper<Project> RowMapper = (ResultSet resultSet, int n) -> new Project(resultSet.getLong("project_id"), resultSet.getString("name"));

    public void save(Project project) {
        var r = jdbcTemplate.update("insert into projects (name) values (?)", project.name);
    }

    public void update(Project project) {
        jdbcTemplate.update("update projects set name = ? where project_id = ?", project.name, project.projectId);
    }

    public Project findById(Long projectId) {
        Project project = jdbcTemplate.queryForObject("select * from projects where project_id = ?", RowMapper, projectId);
        return project;
    }

    public List<Project> findAll() {
        return jdbcTemplate.query("select * from projects order by project_id", RowMapper);
    }

    public void deleteProject(Long projectId) {
        jdbcTemplate.update("delete from projects where project_id = ?", projectId);
    }

    public List<Project> findProjects(Long employeeId) {
        return jdbcTemplate.query("select p.project_id, name from employees_projects ep inner join projects p on ep.project_id = p.project_id where employee_id = ?", RowMapper, employeeId);
    }

    public void addEmployee(Long projectId, Long employeeId) {
        jdbcTemplate.update("insert into employees_projects (employee_id, project_id) values (?, ?)", employeeId, projectId);
    }

    public Project findByName(String name) {
        return jdbcTemplate.queryForObject("select * from projects where name = ?", RowMapper, name);
    }
}
