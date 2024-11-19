import React, { useState } from 'react';
import { CheckCircle, X, Clock, NotePencil } from 'phosphor-react'; // Import các icon cần thiết
import EditAttendance from './EditAttendance';
import './AdminAttendance.scss'

const AdminAttendance = () => {
    const [employees, setEmployees] = useState([
        {
            id: "1",
            selectDate: "1",
            name: "Joan Dyer",
            avatar: 'https://randomuser.me/api/portraits/women/1.jpg',
            attendance: [
                { work_date: "2024-11-01", check_in: "08:00:00", check_out: "17:00:00", attendanceType: "full" },
                { work_date: "2024-11-02", check_in: "09:00:00", check_out: "13:00:00", attendanceType: "full" },
                { work_date: "2024-11-03", check_in: null, check_out: null, attendanceType: "absent" }
            ],
            salaryID: '10',
            employee_code: '01'
        },
        {
            id: "2",
            name: "Ryan Randall",
            selectDate: "2",
            avatar: 'https://randomuser.me/api/portraits/men/1.jpg',
            attendance: [
                { work_date: "2024-11-01", check_in: "08:30:00", check_out: "17:00:00", attendanceType: "absent" },
                { work_date: "2024-11-02", check_in: null, check_out: null, attendanceType: "absent" },
                { work_date: "2024-11-03", check_in: "10:00:00", check_out: "14:00:00", attendanceType: "full" }
            ],
            salaryID: '20',
            employee_code: '01'
        }

    ]);

    const [selectedMonth, setSelectedMonth] = useState(11); // Tháng hiện tại
    const [selectedYear, setSelectedYear] = useState(new Date().getFullYear());
    const [showModal, setShowModal] = useState(false);
    const [selectedEmployee, setSelectedEmployee] = useState(null);

    const calculateAttendance = (checkIn, checkOut) => {
        if (!checkIn || !checkOut) return "absent";

        const [checkInHours, checkInMinutes] = checkIn.split(':').map(Number);
        const [checkOutHours, checkOutMinutes] = checkOut.split(':').map(Number);

        const hoursWorked = (checkOutHours + checkOutMinutes / 60) - (checkInHours + checkInMinutes / 60);
        if (hoursWorked >= 4 && hoursWorked <= 8) return "half";
        if (hoursWorked > 8) return "full";
        return "absent";
    };

    const getDaysInMonth = (month, year) => new Date(year, month, 0).getDate();
    const filteredDays = Array.from({ length: getDaysInMonth(selectedMonth, selectedYear) }, (_, i) => i + 1);

    const handleEditClick = (employee) => {
        if (employee && employee.attendance) {
            setSelectedEmployee(employee);
            setShowModal(true);
        }
    };


    const handleModalClose = () => {
        setShowModal(false);
        setSelectedEmployee(null);
    };

    const handleSave = (updatedEmployee) => {
        setEmployees(prevEmployees =>
            prevEmployees.map(emp =>
                emp.id === updatedEmployee.id
                    ? { ...emp, attendance: updatedEmployee.attendance }  // Cập nhật mảng attendance của nhân viên
                    : emp
            )
        );
        handleModalClose();
    };


    return (
        <div className="container-fluid mt-1">
            <div className='container-xxl'>
                <div className='row align-items-center'>
                    <div className='border-0 mb-4'>
                        <div className='card-header py-3 no-bg-transparent d-flex align-items-center px-0 justify-content-between border-bottom flex-wrap'>
                            <h2 className="fw-bold mb-0">Attendance</h2>
                            <div className="d-flex w-sm-100 col-auto">
                                <label className="mr-2">Select Month:</label>
                                <select value={selectedMonth} onChange={(e) => setSelectedMonth(parseInt(e.target.value))}>
                                    {[...Array(12).keys()].map(i => (
                                        <option key={i + 1} value={i + 1}>{i + 1}</option>
                                    ))}
                                </select>
                                <label className="ml-4 mr-2">Select Year:</label>
                                <input
                                    type="number"
                                    value={selectedYear}
                                    onChange={(e) => setSelectedYear(parseInt(e.target.value))}
                                    className="form-control-inline"
                                    style={{ width: '80px' }}
                                />
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div className="row clearfix g-3">
                <div className='container-xxl'>
                    <div className="col-sm-12">
                        <table className="table table-hover w-full border border-gray-200 text-xs">
                            <thead>
                                <tr className="bg-gray-100 text-gray-600 uppercase leading-tight">
                                    <th className="py-2 px-2 text-left border border-gray-200">Employee Name</th>
                                    {filteredDays.map(day => (
                                        <th key={day} className="py-2 px-1 border border-gray-200 text-center">{day}</th>
                                    ))}
                                </tr>
                            </thead>
                            <tbody className="text-gray-700">
                                {employees.map((employee) => (
                                    <tr key={employee.id} className="hover:bg-gray-100">
                                        <td className="py-2 px-2 border border-gray-200 whitespace-nowrap">
                                            <div className="d-flex align-items-center">
                                                <img className="avatar rounded-circle" src={employee.avatar} alt={employee.name} style={{ width: '30px', height: '30px' }} />
                                                <span className="fw-bold ms-2">{employee.name}</span>
                                            </div>
                                        </td>
                                        {filteredDays.map((day) => {
                                            const attendanceEntry = employee.attendance.find(a => new Date(a.work_date).getDate() === day);
                                            const status = attendanceEntry ? calculateAttendance(attendanceEntry.check_in, attendanceEntry.check_out) : "absent";

                                            return (
                                                <td key={day} className="py-1 px-1 border border-gray-200 text-center">
                                                    {status === "full" && <CheckCircle size={20} style={{ color: 'green' }} />}
                                                    {status === "half" && <Clock size={20} style={{ color: 'yellow' }} />}
                                                    {status === "absent" && <X size={20} style={{ color: 'red' }} />}
                                                </td>
                                            );
                                        })}
                                        <td>
                                            <button
                                                onClick={() => handleEditClick(employee)}
                                                className="btn btn-outline-secondary"
                                                data-bs-toggle="modal"
                                                data-bs-target="#depedit">
                                                <NotePencil size={20} className="text-success" />
                                            </button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <EditAttendance
                show={showModal}
                handleClose={handleModalClose}
                employee={selectedEmployee}
                handleSave={handleSave}
                selectedMonth={selectedMonth}
            />
        </div>
    );
};

export default AdminAttendance;
