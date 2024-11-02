
import React, { useState } from 'react'
import './Employee.css'
import { Star, Files, Plus, User } from 'phosphor-react';
import { Link } from 'react-router-dom';
import EmployeeProfileModal from './EmployeeProfileModal';
import AddEmployee from './AddEmployee';
import { useNavigate } from 'react-router-dom';
import { Modal } from 'react-bootstrap'; // Nhập Modal từ react-bootstrap


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />


function getPositionColor(position) {
    switch (position) {
        case "Mobile Developer":
            return "#FFD700"; // Vàng
        case "QA/QC Engineer":
            return "#ADD8E6"; // Xanh nhạt
        case "UI/UX Designer":
            return "#DDA0DD"; // Tím nhạt
        case "Quality Assurance":
            return "#98FB98"; // Xanh lá nhạt
        case "Employee":
            return "#87CEFA"; // Xanh dương nhạt
        case "Software Engineer":
            return "#fc88dd"
        default:
            return "#d3d3d3"; // Xám nhạt cho các chức vụ không xác định
    }
}


const EmployeeCard = ({ employee, onProfileClick, index }) => {
    return (
        <div className="col" style={{ animationDelay: `${index * 0.2}s` }}>
            <div className="card">
                <div className='row no-gutters'>
                    <div className='col-3 d-flex flex-column align-items-center' style={{ height: '100%' }}>
                        {/* Phần Avatar chiếm 2/3 */}
                        <div className="d-flex flex-column align-items-center flex-grow-2">
                            <img
                                src={employee.avatar}
                                className='card-img rounded-circle mt-3'
                                alt='Avatar'
                                style={{
                                    objectFit: 'cover',
                                    height: '100px',
                                    width: '100px',
                                    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.2)',
                                }}
                            />
                        </div>

                        {/* Phần Icons chiếm 1/3 */}
                        <div className='d-flex justify-content-around align-items-center mt-2 w-100 flex-grow-1'>
                            <div className='text-center'>
                                <Files size={30} weight="bold" />
                                <p className='mb-0'>{employee.tasks} Tasks</p>
                            </div>
                            <div className='text-center'>
                                <Star size={30} weight="bold" />
                                <p className='mb-0'>{employee.stars} Stars</p>
                            </div>
                        </div>
                    </div>



                    <div className='col-9'>
                        <div className='card-body d-flex flex-column' style={{ height: '100%' }}>

                            {/* Phần tên chiếm 1/5 */}
                            <div className='d-flex align-items-center' style={{ flex: '1' }}>
                                <h5 className='card-title mb-0'>
                                    {employee.firstName} {employee.lastName}
                                </h5>
                            </div>

                            {/* Phần chức vụ chiếm 1/5, chỉ tô màu nền trong phạm vi chữ */}
                            <div className='d-flex align-items-center mt-2' style={{ flex: '1' }}>
                                <h6 className='card-subtitle mb-0'>
                                    <span
                                        style={{
                                            backgroundColor: getPositionColor(employee.position),
                                            color: 'white',
                                            borderRadius: '4px',
                                            padding: '2px 8px',
                                        }}
                                    >
                                        {employee.position}
                                    </span>
                                </h6>
                            </div>

                            {/* Đường kẻ ngăn cách */}
                            <hr className='my-2' style={{ flex: '0 0 1px', width: '100%' }} />

                            {/* Phần mô tả công việc chiếm 2/5 */}
                            <div className='d-flex' style={{ flex: '2' }}>
                                <p className='card-text'>{employee.job}</p>
                            </div>

                            {/* Phần nút chiếm 1/5 */}
                            <div className='d-flex justify-content-start mt-auto' style={{ flex: '1' }}>
                                <button className='btn btn-primary me-1'>
                                    <Plus size={16} className="me-1" /> {/* Biểu tượng Plus */}
                                    Thêm Task
                                </button>
                                <button className='btn btn-secondary' onClick={onProfileClick}>
                                    <User size={16} className="me-1" /> {/* Biểu tượng User */}
                                    Profile
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};



const Employee = ({ x }) => {
    console.log('x ở trang employee, x = ', x)
    x = x + 1;
    console.log('x ở trang employee lần 2, x = ', x)
    const [employees, setEmployees] = useState([
        {
            id: 1,
            lastName: 'Doe',
            firstName: 'John',
            department: 'Development',
            position: 'Software Engineer',
            job: 'Phát triển ứng dụng và hệ thống phần mềm.',
            dateOfHire: '2020-01-15',
            baseSalary: '1000 USD',
            projectsCount: 5,
            role: 'Employee',
            currentProject: 'Project A',
            phoneNumber: '0123456789',
            gender: 'Male',
            email: 'john.doe@example.com',
            address: '123 Main St',
            avatar: 'https://randomuser.me/api/portraits/men/1.jpg',
            tasks: 10,
            stars: 5,
        },
        {
            id: 2,
            lastName: 'Doe',
            firstName: 'John',
            department: 'Development',
            position: 'Mobile Developer',
            job: 'Phát triển ứng dụng và hệ thống phần mềm.',
            dateOfHire: '2020-01-15',
            baseSalary: '1000 USD',
            projectsCount: 5,
            role: 'Employee',
            currentProject: 'Project A',
            phoneNumber: '0123456789',
            gender: 'Male',
            email: 'john.doe@example.com',
            address: '123 Main St',
            avatar: 'https://randomuser.me/api/portraits/men/2.jpg',
            tasks: 10,
            stars: 5,
        },
        {
            id: 3,
            lastName: 'Doe',
            firstName: 'John',
            department: 'Development',
            position: 'UI/UX Designer',
            job: 'Phát triển ứng dụng và hệ thống phần mềm.',
            dateOfHire: '2020-01-15',
            baseSalary: '1000 USD',
            projectsCount: 5,
            role: 'Employee',
            currentProject: 'Project A',
            phoneNumber: '0123456789',
            gender: 'Male',
            email: 'john.doe@example.com',
            address: '123 Main St',
            avatar: 'https://randomuser.me/api/portraits/men/3.jpg',
            tasks: 10,
            stars: 5,
        }, {
            id: 4,
            lastName: 'Doe',
            firstName: 'John',
            department: 'Development',
            position: 'Quality Assurance',
            job: 'Phát triển ứng dụng và hệ thống phần mềm.',
            dateOfHire: '2020-01-15',
            baseSalary: '1000 USD',
            projectsCount: 5,
            role: 'Employee',
            currentProject: 'Project A',
            phoneNumber: '0123456789',
            gender: 'Male',
            email: 'john.doe@example.com',
            address: '123 Main St',
            avatar: 'https://randomuser.me/api/portraits/men/4.jpg',
            tasks: 10,
            stars: 5,
        },
        {
            id: 5,
            lastName: 'Doe',
            firstName: 'John',
            department: 'Development',
            position: 'Employee',
            job: 'Phát triển ứng dụng và hệ thống phần mềm.',
            dateOfHire: '2020-01-15',
            baseSalary: '1000 USD',
            projectsCount: 5,
            role: 'Employee',
            currentProject: 'Project A',
            phoneNumber: '0123456789',
            gender: 'Male',
            email: 'john.doe@example.com',
            address: '123 Main St',
            avatar: 'https://randomuser.me/api/portraits/men/5.jpg',
            tasks: 10,
            stars: 5,
        }, {
            id: 6,
            lastName: 'Doe',
            firstName: 'John',
            department: 'Development',
            position: 'Employee',
            job: 'Phát triển ứng dụng và hệ thống phần mềm.',
            dateOfHire: '2020-01-15',
            baseSalary: '1000 USD',
            projectsCount: 5,
            role: 'Employee',
            currentProject: 'Project A',
            phoneNumber: '0123456789',
            gender: 'Male',
            email: 'john.doe@example.com',
            address: '123 Main St',
            avatar: 'https://randomuser.me/api/portraits/men/6.jpg',
            tasks: 10,
            stars: 5,
        }, {
            id: 7,
            lastName: 'Doe',
            firstName: 'John',
            department: 'Development',
            position: 'Employee',
            job: 'Phát triển ứng dụng và hệ thống phần mềm.',
            dateOfHire: '2020-01-15',
            baseSalary: '1000 USD',
            projectsCount: 5,
            role: 'Employee',
            currentProject: 'Project A',
            phoneNumber: '0123456789',
            gender: 'Male',
            email: 'john.doe@example.com',
            address: '123 Main St',
            avatar: 'https://randomuser.me/api/portraits/men/7.jpg',
            tasks: 10,
            stars: 5,
        },
        {
            id: 8,
            lastName: 'Doe',
            firstName: 'John',
            department: 'Development',
            position: 'Employee',
            job: 'Phát triển ứng dụng và hệ thống phần mềm.',
            dateOfHire: '2020-01-15',
            baseSalary: '1000 USD',
            projectsCount: 5,
            role: 'Employee',
            currentProject: 'Project A',
            phoneNumber: '0123456789',
            gender: 'Male',
            email: 'john.doe@example.com',
            address: '123 Main St',
            avatar: 'https://randomuser.me/api/portraits/men/8.jpg',
            tasks: 10,
            stars: 5,
        },
        {
            id: 9,
            lastName: 'Doe',
            firstName: 'John',
            department: 'Development',
            position: 'Employee',
            job: 'Phát triển ứng dụng và hệ thống phần mềm.',
            dateOfHire: '2020-01-15',
            baseSalary: '1000 USD',
            projectsCount: 5,
            role: 'Employee',
            currentProject: 'Project A',
            phoneNumber: '0123456789',
            gender: 'Male',
            email: 'john.doe@example.com',
            address: '123 Main St',
            avatar: 'https://randomuser.me/api/portraits/men/9.jpg',
            tasks: 10,
            stars: 5,
        },
        {
            id: 10,
            lastName: 'Doe',
            firstName: 'John',
            department: 'Development',
            position: 'Employee',
            job: 'Phát triển ứng dụng và hệ thống phần mềm.',
            dateOfHire: '2020-01-15',
            baseSalary: '1000 USD',
            projectsCount: 5,
            role: 'Employee',
            currentProject: 'Project A',
            phoneNumber: '0123456789',
            gender: 'Male',
            email: 'john.doe@example.com',
            address: '123 Main St',
            avatar: 'https://randomuser.me/api/portraits/men/10.jpg',
            tasks: 10,
            stars: 5,
        },




        // Các nhân viên khác
    ]);

    const [selectedEmployee, setSelectedEmployee] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [showAddModal, setShowAddModal] = useState(false);

    const handleModalClose = () => {
        setShowModal(false);
        setSelectedEmployee(null);
    };

    const handleSave = (updatedEmployee) => {
        setEmployees(employees.map(emp => emp.id === updatedEmployee.id ? updatedEmployee : emp));
        handleModalClose();
    };

    const handleAddEmployee = (newEmployee) => {
        setEmployees((prevEmployees) => [...prevEmployees, newEmployee]);
        setShowAddModal(false);
    };

    const handleAddModalShow = () => {
        setShowAddModal(true);
    };

    const handleProfileClick = (employee) => {
        setSelectedEmployee(employee);
        setShowModal(true);
    };

    return (
        <div>
            <div className='d-flex justify-content-between align-items-center mb-0'>
                <h2 className='mb-0'>Employee List</h2>
                <button className='btn btn-success' onClick={handleAddModalShow}>
                    <Plus size={16} className="me-1" /> {/* Biểu tượng Plus */}

                    Thêm nhân viên
                </button>
            </div>


            <hr style={{ width: '100%', border: '1px solid #ddd', margin: '10px 0' }} />

            <div className="row g-3 row-cols-1 row-cols-sm-1 row-cols-md-1 row-cols-lg-2 row-cols-xl-2 row-cols-xxl-2 row-deck pt-4">
                {employees.map((employee, index) => (
                    <EmployeeCard
                        key={employee.id}
                        employee={employee}
                        onProfileClick={() => handleProfileClick(employee)}
                        index={index} // Thêm index tại đây

                    />
                ))}
                {selectedEmployee && (
                    <EmployeeProfileModal
                        show={showModal}
                        handleClose={handleModalClose}
                        employee={selectedEmployee}
                        handleSave={handleSave}
                    />
                )}
            </div>
            <Modal show={showAddModal} onHide={() => setShowAddModal(false)} centered className="custom-modal">
                <Modal.Header closeButton>
                    <Modal.Title>Thêm Nhân Viên Mới</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <AddEmployee onAddEmployee={handleAddEmployee} />
                </Modal.Body>
            </Modal>
        </div>
    );
};

export default Employee;
