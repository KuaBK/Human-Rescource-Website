import React, { useState, useEffect } from 'react';
import { Modal, Button } from 'react-bootstrap';

const AdminSalaryModal = ({ show, handleClose, loan, handleSave }) => {
    const [salaryAmount, setSalaryAmount] = useState(loan ? loan.amount : '');
    const [status, setStatus] = useState(loan ? loan.status : '');

    useEffect(() => {
        if (loan) {
            setSalaryAmount(loan.amount);
            setStatus(loan.status);
        }
    }, [loan]);

    const onSave = () => {
        // Gọi hàm handleSave khi nhấn nút lưu với thông tin đã chỉnh sửa
        handleSave({
            ...loan,
            amount: salaryAmount,
            status: status
        });
        handleClose();
    };

    if (!loan) return null; // Đảm bảo loan tồn tại trước khi hiển thị modal

    return (
        <Modal show={show} onHide={handleClose} centered>
            <Modal.Header closeButton>
                <Modal.Title>Edit Employee Salary</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div className="mb-3">
                    <label className="form-label">Employee ID</label>
                    <input type="text" className="form-control" value={loan.employeeId} disabled />
                </div>
                <div className="mb-3">
                    <label className="form-label">Employee Name</label>
                    <input type="text" className="form-control" value={loan.name} disabled />
                </div>
                <div className="mb-3">
                    <label className="form-label">Department</label>
                    <input type="text" className="form-control" value={loan.department} disabled />
                </div>
                <div className="mb-3">
                    <label className="form-label">Working Days</label>
                    <input type="text" className="form-control" value={loan.workingDays} disabled />
                </div>
                <div className="mb-3">
                    <label className="form-label">Salary Amount</label>
                    <input
                        type="number"
                        className="form-control"
                        value={salaryAmount}
                        onChange={(e) => setSalaryAmount(e.target.value)}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Status</label>
                    <select
                        className="form-select"
                        value={status}
                        onChange={(e) => setStatus(e.target.value)}
                    >
                        <option value="Pending">Pending</option>
                        <option value="Paid">Paid</option>
                    </select>
                </div>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                    Close
                </Button>
                <Button variant="primary" onClick={onSave}>
                    Save changes
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default AdminSalaryModal;
