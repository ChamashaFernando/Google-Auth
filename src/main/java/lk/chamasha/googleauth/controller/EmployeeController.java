//package lk.chamasha.googleauth.controller;
//
//import lk.chamasha.googleauth.controller.request.CreateEmployeeRequest;
//import lk.chamasha.googleauth.controller.request.UpdateEmployeeRequest;
//import lk.chamasha.googleauth.controller.response.EmployeeResponse;
//import lk.chamasha.googleauth.service.EmployeeService;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/employees")
//@RequiredArgsConstructor
//@CrossOrigin
//public class EmployeeController {
//
//    private final EmployeeService employeeService;
//
//    @PostMapping
//    public ResponseEntity<EmployeeResponse> createEmployee(
//            @RequestBody CreateEmployeeRequest request) {
//
//        return ResponseEntity.ok(employeeService.createEmployee(request));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<EmployeeResponse> updateEmployee(
//            @PathVariable Long id,
//            @RequestBody UpdateEmployeeRequest request) {
//
//        return ResponseEntity.ok(employeeService.updateEmployee(id, request));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable Long id) {
//
//        return ResponseEntity.ok(employeeService.getEmployee(id));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
//
//        return ResponseEntity.ok(employeeService.getAllEmployees());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
//
//        employeeService.deleteEmployee(id);
//
//        return ResponseEntity.ok("Employee deleted successfully");
//    }
//}

package lk.chamasha.googleauth.controller;

import jakarta.validation.Valid;
import lk.chamasha.googleauth.controller.request.CreateEmployeeRequest;
import lk.chamasha.googleauth.controller.request.UpdateEmployeeRequest;
import lk.chamasha.googleauth.controller.response.ApiResponse;
import lk.chamasha.googleauth.controller.response.EmployeeResponse;
import lk.chamasha.googleauth.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@CrossOrigin
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(
            @Valid @RequestBody CreateEmployeeRequest request) {

        EmployeeResponse response =
                employeeService.createEmployee(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<EmployeeResponse>builder()
                                .success(true)
                                .message("Employee created successfully")
                                .data(response)
                                .build()
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody UpdateEmployeeRequest request) {

        EmployeeResponse response =
                employeeService.updateEmployee(id, request);

        return ResponseEntity.ok(
                ApiResponse.<EmployeeResponse>builder()
                        .success(true)
                        .message("Employee updated successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> getEmployee(
            @PathVariable Long id) {

        EmployeeResponse response =
                employeeService.getEmployee(id);

        return ResponseEntity.ok(
                ApiResponse.<EmployeeResponse>builder()
                        .success(true)
                        .message("Employee retrieved successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getAllEmployees() {

        List<EmployeeResponse> response =
                employeeService.getAllEmployees();

        return ResponseEntity.ok(
                ApiResponse.<List<EmployeeResponse>>builder()
                        .success(true)
                        .message("Employees retrieved successfully")
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Employee deleted successfully")
                        .build()
        );
    }
}