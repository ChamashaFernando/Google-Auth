package lk.chamasha.googleauth.service.impl;

import lk.chamasha.googleauth.controller.request.CreateEmployeeRequest;
import lk.chamasha.googleauth.controller.request.UpdateEmployeeRequest;
import lk.chamasha.googleauth.controller.response.EmployeeResponse;
import lk.chamasha.googleauth.model.Employee;
import lk.chamasha.googleauth.repository.EmployeeRepository;
import lk.chamasha.googleauth.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {

        Employee emp = Employee.builder()
                .employeeCode(request.getEmployeeCode())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .department(request.getDepartment())
                .salary(request.getSalary())
                .contactNumber(request.getContactNumber())
                .build();

        employeeRepository.save(emp);

        return mapToResponse(emp);
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, UpdateEmployeeRequest request) {

        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        emp.setFirstName(request.getFirstName());
        emp.setLastName(request.getLastName());
        emp.setDepartment(request.getDepartment());
        emp.setSalary(request.getSalary());
        emp.setContactNumber(request.getContactNumber());

        employeeRepository.save(emp);

        return mapToResponse(emp);
    }

    @Override
    public EmployeeResponse getEmployee(Long id) {

        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return mapToResponse(emp);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeResponse mapToResponse(Employee emp) {

        return EmployeeResponse.builder()
                .id(emp.getId())
                .employeeCode(emp.getEmployeeCode())
                .firstName(emp.getFirstName())
                .lastName(emp.getLastName())
                .department(emp.getDepartment())
                .salary(emp.getSalary())
                .contactNumber(emp.getContactNumber())
                .build();
    }
}