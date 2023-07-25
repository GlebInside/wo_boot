package com.javachinna.position;

import com.javachinna.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionRepository positionRepository;
    private final EmployeeRepository employeeRepository;

    @PostMapping("")
    public void addPosition(@RequestBody Position position) {
        positionRepository.save(position);
    }

    @GetMapping("/{positionId}")
    public Position getById(@PathVariable Long positionId) {
        return positionRepository.findById(positionId);
    }

    @GetMapping()
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @PutMapping("/{positionId}")
    public void updatePosition(@PathVariable Long positionId, @RequestBody Position position) {
        position.setPositionId(positionId);
        positionRepository.update(position);
    }

    @DeleteMapping("/{positionId}")
    public void deletePosition(@PathVariable Long positionId) {
        positionRepository.deletePosition(positionId);
    }

//    @GetMapping("/for-employee")
//    public List<Position> getPositionsForEmployee(@RequestParam String firstName, String secondName) {
//        var employee = employeeRepository.find(firstName, secondName);
//        return positionRepository.findByEmployee(employee);
//    }
}
