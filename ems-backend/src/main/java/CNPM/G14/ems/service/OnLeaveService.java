package CNPM.G14.ems.service;

import CNPM.G14.ems.dto.request.OnLeaveRequest;
import CNPM.G14.ems.dto.response.OnLeaveResponse;

import java.util.List;

public interface OnLeaveService {
    OnLeaveResponse createOnLeaveLetter(OnLeaveRequest request);
    List<OnLeaveResponse> getAllOnLeaveByEmployeeId(int employeeId);
}
