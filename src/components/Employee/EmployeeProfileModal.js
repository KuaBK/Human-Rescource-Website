import React from 'react';
import { Modal, Form, Button } from 'react-bootstrap';
import './EmployeeProfileModal.css';

const EmployeeProfileModal = ({ show, handleClose, employee, handleSave }) => {
    const [editEmployee, setEditEmployee] = React.useState(employee);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setEditEmployee((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const onSave = () => {
        handleSave(editEmployee);
        handleClose();
    };

    return (
        <Modal show={show} onHide={handleClose} centered className="employee-profile-modal">
            <Modal.Header closeButton>
                <Modal.Title>Thông tin nhân viên</Modal.Title>
            </Modal.Header>
            <Modal.Body>

                <Form className="employee-profile-form">

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



                            <Form.Group controlId="formLastName" >
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


                    <Form.Group controlId="formDepartment" className="mt-2">
                        <Form.Label>Phòng ban</Form.Label>
                        <Form.Control
                            type="text"
                            name="department"
                            value={editEmployee.department}
                            readOnly
                        />
                    </Form.Group>

                    <Form.Group controlId="formPosition" className="mt-2">
                        <Form.Label>Chức vụ</Form.Label>
                        <Form.Control
                            type="text"
                            name="position"
                            value={editEmployee.position}
                            onChange={handleChange}
                        />
                    </Form.Group>
                    <Form.Group controlId="formJob" className="mt-2">
                        <Form.Label>Mô tả công việc</Form.Label>
                        <Form.Control
                            type="text"
                            name="job"
                            value={editEmployee.job}
                            onChange={handleChange}
                        />
                    </Form.Group>
                    <Form.Group controlId="formDateOfHire" className="mt-2">
                        <Form.Label>Ngày vào làm</Form.Label>
                        <Form.Control
                            type="text"
                            name="dateOfHire"
                            value={editEmployee.dateOfHire}
                            readOnly
                        />
                    </Form.Group>

                    {editEmployee.role == 'Manager' && (<>
                        <Form.Group controlId="formManagerDate" className="mt-2">
                            <Form.Label> Ngày bắt đầu quản lý </Form.Label>
                            <Form.Control
                                type="date"
                                name="manageDate"
                                value={editEmployee.manageDate || ''}
                                onChange={handleChange}
                            />
                        </Form.Group>
                    </>)}

                    <Form.Group controlId="formBaseSalary" className="mt-2">
                        <Form.Label>Lương cơ bản</Form.Label>
                        <Form.Control
                            type="text"
                            name="baseSalary"
                            value={editEmployee.baseSalary}
                            onChange={handleChange}
                        />
                    </Form.Group>

                    <div className="row g-3 mb-0">
                        <div className="col-sm-6">
                            <Form.Group controlId="formNumberOfProject">
                                <Form.Label>Số lượng task hoàn thành</Form.Label>
                                <Form.Control
                                    type="text"
                                    name="numberProject"
                                    value={editEmployee.tasksCount}
                                    onChange={handleChange}
                                />
                            </Form.Group>
                        </div>

                        <div className="col-sm-6">




                            <Form.Group controlId="formCurrentProject">
                                <Form.Label>Dự án hiện tại</Form.Label>
                                <Form.Control
                                    type="text"
                                    name="currentProject"
                                    value={editEmployee.currentProject}
                                    onChange={handleChange}
                                />


                            </Form.Group>


                        </div>
                    </div>

                    <Form.Group controlId="formPhoneNumber" className="mt-2">
                        <Form.Label>Số điện thoại</Form.Label>
                        <Form.Control
                            type="text"
                            name="phoneNumber"
                            value={editEmployee.phoneNumber}
                            onChange={handleChange}
                        />
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
                    <Form.Group controlId="formAddress" className="mt-2">
                        <Form.Label>Địa chỉ</Form.Label>
                        <Form.Control
                            type="text"
                            name="address"
                            value={editEmployee.address}
                            onChange={handleChange}
                        />
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                    Đóng
                </Button>
                <Button variant="primary" onClick={onSave}>
                    Lưu
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default EmployeeProfileModal;
