import React, { useState } from 'react';
import { Modal, Button } from 'react-bootstrap';

const EditAttendance = ({ show, handleClose, employee, handleSave, selectedMonth }) => {
    const [selectedDate, setSelectedDate] = useState(1);
    const [attendanceType, setAttendanceType] = useState('full');
    const [editReason, setEditReason] = useState('');

    const onSave = () => {
        const updatedEmployee = { ...employee };
        const startIndex = (selectedMonth - 1) * 31;
        updatedEmployee.attendance[startIndex + selectedDate - 1] =
            attendanceType === 'full' ? '✔️' : attendanceType === 'half' ? '⚠️' : '❌';
        handleSave(updatedEmployee);
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
                            <option key={i + 1} value={i + 1}>{i + 1}</option>
                        ))}
                    </select>
                </div>
                <div className="mb-3">
                    <label className="block text-sm mb-1">Attendance Type:</label>
                    <select className="form-control" onChange={(e) => setAttendanceType(e.target.value)}>
                        <option value="full">Full Day Attend</option>
                        <option value="half">Half Day Attend</option>
                        <option value="absent">Full Day Absence</option>
                    </select>
                </div>
                <div className="mb-3">
                    <label className="block text-sm mb-1">Edit Reason:</label>
                    <textarea
                        className="form-control"
                        rows="3"
                        value={editReason}
                        onChange={(e) => setEditReason(e.target.value)}
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
