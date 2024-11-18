//package CNPM.G14.ems.controller;
//
//import CNPM.G14.ems.dto.response.ApiResponse;
//import CNPM.G14.ems.dto.response.AttendanceResponse;
//import CNPM.G14.ems.entity.Attendance;
//import CNPM.G14.ems.service.AttendanceService;
//import lombok.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.Duration;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/attendance")
//@AllArgsConstructor
//public class AttendanceController {
//
//    @Autowired
//    private final AttendanceService attendanceService;
//
//    private AttendanceResponse mapToAttendanceResponse(Attendance attendance) {
//        return new AttendanceResponse(
//                attendance.getAttendanceID(),
//                attendance.getEmployee().getEmployeeCode(),
//                attendance.getDate(),
//                attendance.getCheckIn(),
//                attendance.getCheckOut(),
//                formatDuration(attendance.getTimeWorking())
//        );
//    }
//
//    private String formatDuration(Duration duration) {
//        long hours = duration.toHours();
//        long minutes = duration.toMinutes() % 60;
//        long seconds = duration.getSeconds() % 60;
//        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
//    }
//
//    @GetMapping
//    public ApiResponse<AttendanceResponse> getAttendance(@RequestParam String attendanceId) {
//        try {
//            Attendance attendance = attendanceService.getAttendance(attendanceId);
//            AttendanceResponse response = mapToAttendanceResponse(attendance);
//            return ApiResponse.<AttendanceResponse>builder()
//                    .EC(0)
//                    .EM("Attendance retrieved successfully")
//                    .data(response)
//                    .build();
//        } catch (Exception e) {
//            return ApiResponse.<AttendanceResponse>builder()
//                    .EC(-1)
//                    .EM(e.getMessage())
//                    .data(null)
//                    .build();
//        }
//    }
//
//    @GetMapping("/specific")
//    public ApiResponse<AttendanceResponse> getSpecificAttendance(
//            @RequestParam int employeeId,
//            @RequestParam int year,
//            @RequestParam int month,
//            @RequestParam int day) {
//        try {
//            Attendance attendance = attendanceService.getSpecificAttendance(employeeId, year, month, day);
//            AttendanceResponse response = mapToAttendanceResponse(attendance);
//            return ApiResponse.<AttendanceResponse>builder()
//                    .EC(0)
//                    .EM("Attendance retrieved successfully")
//                    .data(response)
//                    .build();
//        } catch (Exception e) {
//            return ApiResponse.<AttendanceResponse>builder()
//                    .EC(-1)
//                    .EM(e.getMessage())
//                    .data(null)
//                    .build();
//        }
//    }
//
//    @GetMapping("/employee")
//    public ApiResponse<List<AttendanceResponse>> getAttendancesByEmployeeId(@RequestParam int employeeId) {
//        try {
//            List<Attendance> attendances = attendanceService.getAttendancesByEmployeeId(employeeId);
//            List<AttendanceResponse> responses = attendances.stream()
//                                                            .map(this::mapToAttendanceResponse)
//                                                            .collect(Collectors.toList());
//            return ApiResponse.<List<AttendanceResponse>>builder()
//                    .EC(0)
//                    .EM("Attendance records retrieved successfully")
//                    .data(responses)
//                    .build();
//        } catch (Exception e) {
//            return ApiResponse.<List<AttendanceResponse>>builder()
//                    .EC(-1)
//                    .EM(e.getMessage())
//                    .data(null)
//                    .build();
//        }
//    }
//
//    @PostMapping("/check-in")
//    public ApiResponse<AttendanceResponse> checkIn(@RequestParam int employeeId) {
//        try {
//            Attendance attendance = attendanceService.checkIn(employeeId);
//            AttendanceResponse response = mapToAttendanceResponse(attendance);
//            return ApiResponse.<AttendanceResponse>builder()
//                    .EC(0)
//                    .EM("Check-in successful")
//                    .data(response)
//                    .build();
//        } catch (Exception e) {
//            return ApiResponse.<AttendanceResponse>builder()
//                    .EC(-1)
//                    .EM(e.getMessage())
//                    .data(null)
//                    .build();
//        }
//    }
//
//    @PostMapping("/check-out")
//    public ApiResponse<AttendanceResponse> checkOut(@RequestParam int employeeId) {
//        try {
//            Attendance attendance = attendanceService.checkOut(employeeId);
//            AttendanceResponse response = mapToAttendanceResponse(attendance);
//            return ApiResponse.<AttendanceResponse>builder()
//                    .EC(0)
//                    .EM("Check-out successful")
//                    .data(response)
//                    .build();
//        } catch (Exception e) {
//            return ApiResponse.<AttendanceResponse>builder()
//                    .EC(-1)
//                    .EM(e.getMessage())
//                    .data(null)
//                    .build();
//        }
//    }
//
//    @GetMapping("/today-duration")
//    public ApiResponse<String> getWorkingDurationForToday(@RequestParam int employeeId){
//        try {
//            String workingDuration = attendanceService.getWorkingDurationForToday(employeeId);
//            return ApiResponse.<String>builder()
//                    .EC(0)
//                    .EM("Working duration retrieved successfully")
//                    .data(workingDuration)
//                    .build();
//        } catch (Exception e) {
//            return ApiResponse.<String>builder()
//                    .EC(-1)
//                    .EM(e.getMessage())
//                    .data(null)
//                    .build();
//        }
//    }
//
//}
