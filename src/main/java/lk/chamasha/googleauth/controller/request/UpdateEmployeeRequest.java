package lk.chamasha.googleauth.controller.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequest {

    private String firstName;
    private String lastName;
    private String department;
    private BigDecimal salary;
    private String contactNumber;
}