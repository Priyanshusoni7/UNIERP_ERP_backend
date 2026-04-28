package com.erp.dashboard_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuickLinksResponse {
    private String classroomUrl;
    private String hireSphereUrl;
    private String codeStageUrl;
    private List<String> features;
}
