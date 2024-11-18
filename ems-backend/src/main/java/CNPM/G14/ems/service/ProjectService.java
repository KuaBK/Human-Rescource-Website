package CNPM.G14.ems.service;

import CNPM.G14.ems.dto.request.EmployeeInProjectRequest;
import CNPM.G14.ems.dto.request.ProjectCreationRequest;
import CNPM.G14.ems.dto.response.EIPResponse;
import CNPM.G14.ems.dto.response.ProjectResponse;

import java.util.List;

public interface ProjectService {
    ProjectResponse getProject(int projectId);
    List<ProjectResponse> getProjectsByDeptId(int deptId);
    ProjectResponse createProject(ProjectCreationRequest request);
    List<ProjectResponse> getAllProjects();
    List<EIPResponse> findEmployeesInProject(int projectID);
    void assignEmployeeToProject(EmployeeInProjectRequest request);
    void removeEmployeeFromProject(EmployeeInProjectRequest request);
}
