package com.erp.dashboard_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "enrollments")
public class Enrollment {
    @Id
    private String id;
    private String classId;
    private String studentId;
    private Date createdAt;
    private Date updatedAt;
}
