package CNPM.G14.ems.service;

import CNPM.G14.ems.dto.request.SalaryBoardRequest;
import CNPM.G14.ems.dto.response.SalaryBoardResponse;
import CNPM.G14.ems.entity.Attendance;
import CNPM.G14.ems.entity.SalaryBoard;

import java.util.List;

public interface SalaryBoardService {
    SalaryBoardResponse createSalaryBoard(SalaryBoardRequest salaryBoardRequestDTO);
    SalaryBoardResponse getSalaryBoard(Long id);
    SalaryBoardResponse updateSalaryBoard(Long id, SalaryBoard updatedFields);
    void updatePayRate(double newFullWorkPay, double newHalfWorkPay);
    SalaryBoardResponse updateBonusAndPenalty(Long id, double bonus, double penalty);
    List<SalaryBoardResponse> getAllSalaryBoardsByEmployee(int employeeCode);
    SalaryBoardResponse getSalaryBoardByEmployeeAndDate(int employeeCode, int month, int year);
}
