package com.erp.dashboard_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Role is required (STUDENT | FACULTY | ADMIN | PLACEMENT_OFFICER)")
    private String role;

    // Optional fields
    private String enrollment;
    private String department;
    private String program;
    private Integer semester;
}
