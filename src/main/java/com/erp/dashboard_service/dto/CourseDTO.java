package com.erp.dashboard_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {
    private String id;
    private String name;
    private String subject;
    private String classCode;
    private String role; // "STUDENT" or "FACULTY" context
}
