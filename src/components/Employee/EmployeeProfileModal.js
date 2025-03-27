import React, { useState, useEffect } from 'react';
import { Modal, Form, Button, Alert } from 'react-bootstrap';
import axios from 'axios';
import './EmployeeProfileModal.css';

const EmployeeProfileModal = ({ show, handleClose, employee, onEmployeeUpdate }) => {
    const [editEmployee, setEditEmployee] = useState(employee);
    const [loading, setLoading] = useState(false);
    const [successMessage, setSuccessMessage] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    useEffect(() => {
        setEditEmployee(employee);
    }, [employee]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setEditEmployee((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const onSave = async () => {
        setLoading(true);
        setSuccessMessage('');
        setErrorMessage('');
        try {
            const response = await axios.patch(
                `http://localhost:8080/api/employee/update`,
                { ...editEmployee },
                {
                    params: { personnel_code: editEmployee.personnelCode }, // Use the correct parameter name
                    headers: {
                        'Content-Type': 'application/json',
                    },
                }
            );

            if (response.data.code === 1000) {
                setSuccessMessage(response.data.message);
                onEmployeeUpdate(response.data.result); // Notify parent about the updated data
                handleClose();
            } else {
                setErrorMessage(response.data.message || 'Update failed');
            }
        } catch (error) {
            setErrorMessage(
                error.response?.data?.message || 'An error occurred while updating the employee.'
            );
        } finally {
            setLoading(false);
        }
    };

    return (
        <Modal show={show} onHide={handleClose} centered className="employee-profile-modal">
            <Modal.Header closeButton>
                <Modal.Title>Thông tin nhân viên</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {successMessage && <Alert variant="success">{successMessage}</Alert>}
                {errorMessage && <Alert variant="danger">{errorMessage}</Alert>}
                <Form className="employee-profile-form">
                    <Form.Group controlId="formPosition" className="mt-2">
                        <Form.Label>Chức vụ</Form.Label>
                        <Form.Control
                            as="select"
                            name="role"
                            value={editEmployee.position}
                            onChange={handleChange}
                            disabled
                        >
                            <option value="Manager">Manager</option>
                            <option value="Employee">Employee</option>
                        </Form.Control>
                    </Form.Group>
                    <div className="row g-3 mb-0">
                        <div className="col-sm-6">
                            <Form.Group controlId="formFirstName">
                                <Form.Label>Tên</Form.Label>
                                <Form.Control
                                    type="text"
                                    name="firstName"
                                    value={editEmployee.firstName}
                                    onChange={handleChange}
                                />
                            </Form.Group>
                        </div>
                        <div className="col-sm-6">
                            <Form.Group controlId="formLastName">
                                <Form.Label>Họ</Form.Label>
                                <Form.Control
                                    type="text"
                                    name="lastName"
                                    value={editEmployee.lastName}
                                    onChange={handleChange}
                                />
                            </Form.Group>
                        </div>
                    </div>
                    <Form.Group controlId="formGender" className="mt-2">
                        <Form.Label>Giới tính</Form.Label>
                        <Form.Control
                            as="select"
                            name="sex"
                            value={editEmployee.sex}
                            onChange={handleChange}
                        >
                            <option value="MALE">Male</option>
                            <option value="FEMALE">Female</option>
                        </Form.Control>
                    </Form.Group>
                    <Form.Group controlId="formEmail" className="mt-2">
                        <Form.Label>Email</Form.Label>
                        <Form.Control
                            type="email"
                            name="email"
                            value={editEmployee.email}
                            onChange={handleChange}
                        />
                    </Form.Group>
                    <Form.Group controlId="formUsername" className="mt-2">
                        <Form.Label>Tên tài khoản</Form.Label>
                        <Form.Control
                            type="text"
                            name="username"
                            value={editEmployee.username}
                            onChange={handleChange}
                        />
                    </Form.Group>
                    <Form.Group controlId="formPassword" className="mt-2">
                        <Form.Label>Mật khẩu</Form.Label>
                        <Form.Control
                            type="text"
                            name="password"
                            value={editEmployee.password}
                            onChange={handleChange}
                        />
                    </Form.Group>
                    <Form.Group controlId="formCity" className="mt-2">
                        <Form.Label>Thành phố</Form.Label>
                        <Form.Control
                            type="text"
                            name="city"
                            value={editEmployee.city}
                            onChange={handleChange}
                        />
                    </Form.Group>
                    <Form.Group controlId="formStreet" className="mt-2">
                        <Form.Label>Đường</Form.Label>
                        <Form.Control
                            type="text"
                            name="street"
                            value={editEmployee.street}
                            onChange={handleChange}
                        />
                    </Form.Group>
                    <Form.Group controlId="formPhoneNumber" className="mt-2">
                        <Form.Label>Số điện thoại</Form.Label>
                        <Form.Control
                            type="text"
                            name="phone"
                            value={editEmployee.phone}
                            onChange={handleChange}
                        />
                    </Form.Group>
                    {editEmployee.role === 'Manager' && (
                        <Form.Group controlId="formManagerDate" className="mt-2">
                            <Form.Label>Ngày bắt đầu quản lý</Form.Label>
                            <Form.Control
                                type="date"
                                name="manageDate"
                                value={editEmployee.manageDate || ''}
                                onChange={handleChange}
                            />
                        </Form.Group>
                    )}
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleClose} disabled={loading}>
                    Đóng
                </Button>
                <Button variant="primary" onClick={onSave} disabled={loading}>
                    {loading ? 'Đang lưu...' : 'Lưu'}
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default EmployeeProfileModal;
