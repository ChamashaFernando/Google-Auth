package lk.chamasha.googleauth.service;

import lk.chamasha.googleauth.controller.request.CreateEmployeeRequest;
import lk.chamasha.googleauth.controller.request.UpdateEmployeeRequest;
import lk.chamasha.googleauth.controller.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse createEmployee(CreateEmployeeRequest request);

    EmployeeResponse updateEmployee(Long id, UpdateEmployeeRequest request);

    EmployeeResponse getEmployee(Long id);

    List<EmployeeResponse> getAllEmployees();

    void deleteEmployee(Long id);
}