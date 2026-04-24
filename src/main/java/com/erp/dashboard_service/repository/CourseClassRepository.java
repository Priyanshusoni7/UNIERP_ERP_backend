package com.erp.dashboard_service.repository;

import com.erp.dashboard_service.model.CourseClass;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseClassRepository extends MongoRepository<CourseClass, String> {
    List<CourseClass> findByFacultyId(String facultyId);
}
