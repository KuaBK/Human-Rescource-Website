import React, { useState } from 'react';
import { CheckCircle, X, Clock } from 'phosphor-react'; // Import các icon cần thiết
import EditAttendance from './EditAttendance';



const AdminAttendance = () => {
    const [employees, setEmployees] = useState([
        {
            name: "Joan Dyer",
            attendance: ["full", "half", "absent", "full", "full", "half", "absent", "full", "full", "half", "full", "absent", "full", "half", "absent", "full", "full", "half", "absent", "full", "full", "half", "absent", "full", "full", "half", "absent", "full", "full", "half", "full"]
        },
        {
            name: "Ryan Randall",
            attendance: ["half", "full", "full", "absent", "half", "full", "full", "absent", "half", "full", "absent", "half", "full", "full", "absent", "half", "full", "full", "absent", "half", "full", "full", "absent", "half", "full", "full", "absent", "half", "full", "half", "full"]
        },
        {
            name: "Phil Glover",
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
            <h2 className="text-2xl font-semibold mb-4">Attendance (Admin)</h2>
            <div className="flex justify-between items-center mb-4">
                <div>
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
            <div className="row clearfix g-3">
                <div className="col-sm-12">
                    <table className="table table-hover w-full border border-gray-200 text-xs">
                        <thead>
                            <tr className="bg-gray-100 text-gray-600 uppercase leading-tight">
                                <th className="py-2 px-2 text-left border border-gray-200">Employee</th>
                                {filteredDays.map(day => (
                                    <th key={day} className="py-2 px-1 border border-gray-200 text-center">{day}</th>
                                ))}
                            </tr>
                        </thead>
                        <tbody className="text-gray-700">
                            {employees.map((employee, index) => (
                                <tr key={index} className="hover:bg-gray-100">
                                    <td className="py-2 px-2 border border-gray-200 whitespace-nowrap">{employee.name}</td>
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
                                            className="btn btn-green">Edit</button>


                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
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
