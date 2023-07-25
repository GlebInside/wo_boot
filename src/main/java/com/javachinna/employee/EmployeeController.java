package com.javachinna.employee;

import com.javachinna.project.Project;
import com.javachinna.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    @PostMapping("")
    public void addEmployee(@RequestBody Employee employee) {
        employeeRepository.save(employee);
    }

    @PutMapping("/{employeeId}")
    public void updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employee) {
        employee.setEmployeeId(employeeId);
        employeeRepository.update(employee);
    }

    @GetMapping("/{employeeId}")
    public Employee getById(@PathVariable Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @GetMapping()
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @DeleteMapping("{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId) {
        employeeRepository.deleteEmployee(employeeId);
    }

    @GetMapping("/projects")
    public List<Project> getProjects(@RequestParam String firstName, @RequestParam String secondName) {
        var employee = employeeRepository.find(firstName, secondName);
        return projectRepository.findProjects(employee.employeeId);
    }
}
