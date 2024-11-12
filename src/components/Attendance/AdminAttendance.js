import React, { useState } from 'react';
import { CheckCircle, X, Clock, NotePencil } from 'phosphor-react'; // Import các icon cần thiết
import EditAttendance from './EditAttendance';

const AdminAttendance = () => {
    const [employees, setEmployees] = useState([
        {
            name: "Joan Dyer",
            avatar: 'https://randomuser.me/api/portraits/women/1.jpg', // Thêm avatar
            attendance: ["full", "half", "absent", "full", "full", "half", "absent", "full", "full", "half", "full", "absent", "full", "half", "absent", "full", "full", "half", "absent", "full", "full", "half", "absent", "full", "full", "half", "absent", "full", "full", "half", "full"]
        },
        {
            name: "Ryan Randall",
            avatar: 'https://randomuser.me/api/portraits/men/1.jpg', // Thêm avatar
            attendance: ["half", "full", "full", "absent", "half", "full", "full", "absent", "half", "full", "absent", "half", "full", "full", "absent", "half", "full", "full", "absent", "half", "full", "full", "absent", "half", "full", "full", "absent", "half", "full", "half", "full"]
        },
        {
            name: "Phil Glover",
            avatar: 'https://randomuser.me/api/portraits/men/2.jpg', // Thêm avatar
            attendance: ["absent", "full", "half", "absent", "full", "half", "full", "absent", "half", "full", "full", "absent", "half", "full", "absent", "half", "full", "absent", "half", "full", "absent", "full", "half", "full", "absent", "half", "full", "full", "absent", "half", "full"]
        },
    ]);

    const [selectedMonth, setSelectedMonth] = useState(1); // Tháng 1 mặc định
    const [selectedYear, setSelectedYear] = useState(new Date().getFullYear()); // Năm hiện tại mặc định
    const [showModal, setShowModal] = useState(false);
    const [selectedEmployee, setSelectedEmployee] = useState(null);

    const handleEditClick = (employee) => {
        setSelectedEmployee(employee);
        setShowModal(true);
    };

    const handleModalClose = () => {
        setShowModal(false);
        setSelectedEmployee(null);
    };

    const handleSave = (updatedEmployee) => {
        setEmployees(employees.map(emp => emp.name === updatedEmployee.name ? updatedEmployee : emp));
        handleModalClose();
    };

    const getDaysInMonth = (month, year) => new Date(year, month, 0).getDate();
    const filteredDays = Array.from({ length: getDaysInMonth(selectedMonth, selectedYear) }, (_, i) => i + 1);

    return (
        <div className="container-fluid mt-1">
            <div className='container-xxl'>
                <div className='row align-items-center'>
                    <div className='border-0 mb-4'>
                        <div className='card-header py-3 no-bg-transparent d-flex align-items-center px-0 justify-content-between border-bottom flex-wrap'>

                            <h2 className="fw-bold mb-0">
                                Attendance
                            </h2>


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
                                {employees.map((employee, index) => (
                                    <tr key={index} className="hover:bg-gray-100">
                                        <td className="py-2 px-2 border border-gray-200 whitespace-nowrap">
                                            <div className="d-flex align-items-center">
                                                <img className="avatar rounded-circle" src={employee.avatar} alt={employee.name} style={{ width: '30px', height: '30px' }} />
                                                <span className="fw-bold ms-2">{employee.name}</span>
                                            </div>
                                        </td>
                                        {filteredDays.map((dayIndex) => (
                                            <td key={dayIndex} className="py-1 px-1 border border-gray-200 text-center">
                                                {employee.attendance[dayIndex - 1] === "full" && (
                                                    <div className="flex justify-center items-center">
                                                        <CheckCircle size={20} style={{ color: 'green' }} />
                                                    </div>
                                                )}
                                                {employee.attendance[dayIndex - 1] === "half" && (
                                                    <div className="flex justify-center items-center">
                                                        <Clock size={20} style={{ color: 'yellow' }} />
                                                    </div>
                                                )}
                                                {employee.attendance[dayIndex - 1] === "absent" && (
                                                    <div className="flex justify-center items-center">
                                                        <X size={20} style={{ color: 'red' }} />
                                                    </div>
                                                )}
                                            </td>
                                        ))}
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
            />
        </div>
    );
};

export default AdminAttendance;
