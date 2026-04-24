package com.erp.dashboard_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String id;

    private String name;

    @Indexed(unique = true)
    private String email;

    private String password; // BCrypt hashed

    private String role; // STUDENT | FACULTY | ADMIN | PLACEMENT_OFFICER

    private String enrollment;
    private String department;
    private String program;
    private Integer semester;
}
