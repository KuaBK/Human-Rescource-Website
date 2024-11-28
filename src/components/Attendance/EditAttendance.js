import React, { useState, useEffect } from 'react';
import { Modal, Button } from 'react-bootstrap';

const EditAttendance = ({ show, handleClose, employee, handleSave, selectedMonth, selectedYear }) => {
    const [selectedDate, setSelectedDate] = useState(1);


    const [attendanceDetails, setAttendanceDetails] = useState({
        work_date: '',
        check_in: '',
        check_out: '',
        attendanceType: 'absent',
        // salaryID: '',
        // employeeCode: '',
    });
    const [salaryID, setSalaryID] = useState('');
    const [employeeCode, setEmployeeCode] = useState('');


    useEffect(() => {
        if (employee && Array.isArray(employee.attendance)) {
            // Gán giá trị cho salaryID và employeeCode từ employee
            setSalaryID(employee.salaryID);
            setEmployeeCode(employee.employee_code);

            const selectedAttendance = employee.attendance.find(a => {
                const date = new Date(a.work_date);
                return date.getDate() === selectedDate && date.getMonth() + 1 === selectedMonth;
            }) || {
                work_date: `${selectedYear}-${selectedMonth < 10 ? '0' + selectedMonth : selectedMonth}-${selectedDate < 10 ? '0' + selectedDate : selectedDate}`,
                check_in: '',
                check_out: '',
                attendanceType: 'absent',
            };

            setAttendanceDetails(selectedAttendance);
        }
    }, [employee, selectedDate, selectedMonth, selectedYear]);

    // const handleChange = (e) => {
    //     const { name, value } = e.target;
    //     setAttendanceDetails((prev) => ({
    //         ...prev,
    //         [name]: value,
    //     }));
    // };


    const calculateAttendanceType = (checkIn, checkOut) => {
        if (!checkIn || !checkOut) return 'absent';
        const [checkInHours, checkInMinutes] = checkIn.split(':').map(Number);
        const [checkOutHours, checkOutMinutes] = checkOut.split(':').map(Number);
        const hoursWorked = (checkOutHours + checkOutMinutes / 60) - (checkInHours + checkInMinutes / 60);
        if (hoursWorked >= 4 && hoursWorked <= 8) return 'half';
        if (hoursWorked > 8) return 'full';
        return 'absent';
    };

    const handleFieldChange = (field, value) => {
        setAttendanceDetails(prevDetails => {
            const updatedDetails = { ...prevDetails, [field]: value };

            // Cập nhật lại attendanceType khi thay đổi check-in hoặc check-out
            if (field === 'check_in' || field === 'check_out') {
                const newAttendanceType = calculateAttendanceType(updatedDetails.check_in, updatedDetails.check_out);
                updatedDetails.attendanceType = newAttendanceType;
            }

            return updatedDetails;
        });
    };


    const onSave = () => {
        const updatedEmployee = { ...employee };
        const updatedAttendance = [...updatedEmployee.attendance];

        const startIndex = (selectedMonth - 1) * 31 + selectedDate - 1;
        updatedAttendance[startIndex] = {
            ...attendanceDetails,
            work_date: `${selectedYear}-${selectedMonth < 10 ? '0' + selectedMonth : selectedMonth}-${selectedDate < 10 ? '0' + selectedDate : selectedDate}`,
            salaryID,
            employeeCode,
        };

        updatedEmployee.attendance = updatedAttendance;

        handleSave(updatedEmployee);

        handleClose();
    };

    return (
        <Modal show={show} onHide={handleClose} centered>
            <Modal.Header closeButton>
                <Modal.Title>Edit Attendance for {employee ? employee.name : 'Employee'}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div className="mb-3">
                    <label className="block text-sm mb-1">Select Date:</label>
                    <select
                        className="form-control"
                        value={selectedDate}
                        onChange={(e) => setSelectedDate(parseInt(e.target.value))}
                    >
                        {Array.from({ length: 31 }, (_, i) => (
                            <option key={i + 1} value={i + 1}>
                                {i + 1}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="mb-3">
                    <label className="block text-sm mb-1">Work date:</label>
                    <input
                        type="date"
                        className="form-control"
                        value={attendanceDetails.work_date}
                        readOnly
                    />
                </div>

                <div className="mb-3">
                    <label className="block text-sm mb-1">Check-in Time:</label>
                    <input
                        type="time"
                        className="form-control"
                        value={attendanceDetails.check_in}
                        onChange={(e) => handleFieldChange('check_in', e.target.value)}
                    />
                </div>

                <div className="mb-3">
                    <label className="block text-sm mb-1">Check-out Time:</label>
                    <input
                        type="time"
                        className="form-control"
                        value={attendanceDetails.check_out}
                        onChange={(e) => handleFieldChange('check_out', e.target.value)}
                    />
                </div>
                <div className="mb-3">
                    <label className="block text-sm mb-1">Attendance Type:</label>
                    <input
                        type="text"
                        className="form-control"
                        value={attendanceDetails.attendanceType}
                        readOnly
                    />
                </div>
                <div className="mb-3">
                    <label className="block text-sm mb-1">Salary ID:</label>
                    <input
                        type="text"
                        className="form-control"
                        value={salaryID}
                        onChange={(e) => setSalaryID(e.target.value)}

                    // type="text"
                    // name="form-control"
                    // value={attendanceDetails.salaryID}
                    // onChange={handleChange}

                    />
                </div>
                <div className="mb-3">
                    <label className="block text-sm mb-1">Employee Code:</label>
                    <input
                        type="text"
                        className="form-control"
                        value={employeeCode}

                        onChange={(e) => setEmployeeCode(e.target.value)}

                    // type="text"
                    // name='form-control'
                    // value={attendanceDetails.employeeCode}
                    // onChange={handleChange}

                    />
                </div>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                    Cancel
                </Button>
                <Button variant="primary" onClick={onSave}>
                    Save
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default EditAttendance;
