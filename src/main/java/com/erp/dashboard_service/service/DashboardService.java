package com.erp.dashboard_service.service;

import com.erp.dashboard_service.dto.CourseDTO;
import com.erp.dashboard_service.dto.ProfileResponse;
import com.erp.dashboard_service.dto.QuickLinksResponse;
import com.erp.dashboard_service.exception.ResourceNotFoundException;
import com.erp.dashboard_service.model.CourseClass;
import com.erp.dashboard_service.model.Enrollment;
import com.erp.dashboard_service.model.User;
import com.erp.dashboard_service.repository.CourseClassRepository;
import com.erp.dashboard_service.repository.EnrollmentRepository;
import com.erp.dashboard_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UserRepository userRepository;
    private final CourseClassRepository courseClassRepository;
    private final EnrollmentRepository enrollmentRepository;

    public ProfileResponse getProfile(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return ProfileResponse.builder()
                .name(user.getName())
                .enrollment(user.getEnrollment())
                .department(user.getDepartment())
                .program(user.getProgram())
                .semester(user.getSemester())
                .build();
    }

    public QuickLinksResponse getQuickLinks(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        String role = user.getRole();
        String classroomUrl = "";
        String hireSphereUrl = "";
        String codeStageUrl = "";

        if ("STUDENT".equalsIgnoreCase(role)) {
            classroomUrl = "https://unierp-classroom.vercel.app/dashboard/" + userId;
            hireSphereUrl = "https://unierp-hire-sphere-frontend.vercel.app/" + userId;
            codeStageUrl = "https://campusone-codestage.vercel.app/home/" + userId;
        } else if ("FACULTY".equalsIgnoreCase(role)) {
            classroomUrl = "https://unierp-classroom.vercel.app/dashboard/" + userId;
        } else if ("PLACEMENT_OFFICER".equalsIgnoreCase(role)) {
            hireSphereUrl = "https://unierp-hire-sphere-frontend.vercel.app/" + userId;
        } else if ("ADMIN".equalsIgnoreCase(role)) {
            classroomUrl = "https://unierp-classroom.vercel.app/dashboard/" + userId;
            hireSphereUrl = "https://unierp-hire-sphere-frontend.vercel.app/" + userId;
            codeStageUrl = "https://campusone-codestage.vercel.app/home/" + userId;
        }

        List<String> features = Arrays.asList(
                "My Courses",
                "Attendance",
                "Timetable",
                "Fees",
                "Exam Results",
                "My Profile");

        return QuickLinksResponse.builder()
                .classroomUrl(classroomUrl)
                .hireSphereUrl(hireSphereUrl)
                .codeStageUrl(codeStageUrl)
                .features(features)
                .build();
    }

    public List<CourseDTO> getCourses(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<CourseDTO> courses = new ArrayList<>();

        if ("STUDENT".equalsIgnoreCase(user.getRole())) {
            List<Enrollment> enrollments = enrollmentRepository.findByStudentId(userId);
            List<String> classIds = enrollments.stream().map(Enrollment::getClassId).collect(Collectors.toList());
            List<CourseClass> classes = (List<CourseClass>) courseClassRepository.findAllById(classIds);
            
            courses = classes.stream().map(c -> CourseDTO.builder()
                    .id(c.getId())
                    .name(c.getName())
                    .subject(c.getSubject())
                    .classCode(c.getClassCode())
                    .role("STUDENT")
                    .build()).collect(Collectors.toList());
                    
        } else if ("FACULTY".equalsIgnoreCase(user.getRole())) {
            List<CourseClass> classes = courseClassRepository.findByFacultyId(userId);
            courses = classes.stream().map(c -> CourseDTO.builder()
                    .id(c.getId())
                    .name(c.getName())
                    .subject(c.getSubject())
                    .classCode(c.getClassCode())
                    .role("FACULTY")
                    .build()).collect(Collectors.toList());
        }

        return courses;
    }
}
