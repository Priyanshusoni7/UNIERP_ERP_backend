package com.erp.dashboard_service.controller;

import com.erp.dashboard_service.dto.CourseDTO;
import com.erp.dashboard_service.dto.ProfileResponse;
import com.erp.dashboard_service.dto.QuickLinksResponse;
import com.erp.dashboard_service.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/profile/{userId}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable String userId) {
        return ResponseEntity.ok(dashboardService.getProfile(userId));
    }

    @GetMapping("/quick-links/{userId}")
    public ResponseEntity<QuickLinksResponse> getQuickLinks(@PathVariable String userId) {
        return ResponseEntity.ok(dashboardService.getQuickLinks(userId));
    }

    @GetMapping("/courses/{userId}")
    public ResponseEntity<List<CourseDTO>> getCourses(@PathVariable String userId) {
        return ResponseEntity.ok(dashboardService.getCourses(userId));
    }
}
