package com.javachinna.project;


import com.javachinna.employee.Employee;
import com.javachinna.employee.EmployeeDto;
import com.javachinna.employee.EmployeeRepository;
import com.javachinna.position.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;


    @PostMapping("")
    public void addProject(@RequestBody Project project) {
        projectRepository.save(project);
    }

    @GetMapping("/{projectId}")
    public Project getById(@PathVariable Long projectId) {
        return projectRepository.findById(projectId);
    }

    @GetMapping()
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @PutMapping("/{projectId}")
    public void updateProject(@PathVariable Long projectId, @RequestBody Project project) {
        project.setProjectId(projectId);
        projectRepository.update(project);
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectRepository.deleteProject(projectId);
    }

    @PutMapping("/{projectId}/addEmployee/{employeeId}")
    public void addEmployee(@PathVariable Long projectId, @PathVariable Long employeeId) {
        projectRepository.addEmployee(projectId, employeeId);
    }

    @GetMapping("/projectName/{name}")
    public ProjectDto getByProjectName(@PathVariable String name) {
        var project = projectRepository.findByName(name);
        var employees = employeeRepository.getProjectEmployees(project.projectId);
        var projectDto = new ProjectDto();
        projectDto.projectName = project.name;
        for (Employee employee : employees) {
            var position = positionRepository.findById(employee.getPositionId());
            var employeeDto = new EmployeeDto(employee, position.getName());
            projectDto.employees.add(employeeDto);
        }
        return projectDto;
    }
}
