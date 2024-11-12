
// import React, { useState } from 'react'
// import { Star, Files, Plus, User, Money } from 'phosphor-react';
// import { Link } from 'react-router-dom';
// import { useNavigate } from 'react-router-dom';
// import { Modal } from 'react-bootstrap'; // Nhập Modal từ react-bootstrap
// import AdminSalaryModal from './AdminSalaryModal.js'
// import './AdminSalary.scss'


// <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />


// function getPositionColor(position) {
//     switch (position) {
//         case "Mobile Developer":
//             return "#FFD700"; // Vàng
//         case "QA/QC Engineer":
//             return "#ADD8E6"; // Xanh nhạt
//         case "UI/UX Designer":
//             return "#DDA0DD"; // Tím nhạt
//         case "Quality Assurance":
//             return "#98FB98"; // Xanh lá nhạt
//         case "Employee":
//             return "#87CEFA"; // Xanh dương nhạt
//         case "Software Engineer":
//             return "#fc88dd"
//         default:
//             return "#d3d3d3"; // Xám nhạt cho các chức vụ không xác định
//     }
// }

// const EmployeeCard = ({ employee, onProfileClick, index }) => {
//     return (
//         <div className="col" style={{ animationDelay: `${index * 0.2}s` }}>
//             <div className="card">
//                 <div className='row no-gutters employee-list-row'>
//                     <div className='col-3 d-flex flex-column align-items-center' style={{ height: '100%' }}>
//                         {/* Phần Avatar chiếm 2/3 */}
//                         <div className="d-flex flex-column align-items-center flex-grow-2">
//                             <img
//                                 src={employee.avatar}
//                                 className='card-img rounded-circle mt-3'
//                                 alt='Avatar'
//                                 style={{
//                                     objectFit: 'cover',
//                                     height: '100px',
//                                     width: '100px',
//                                     boxShadow: '0 4px 8px rgba(0, 0, 0, 0.2)',
//                                 }}
//                             />
//                         </div>

//                         {/* Phần Icons chiếm 1/3 */}
//                         <div className='d-flex justify-content-around align-items-center mt-1 w-100'>
//                             <div className='text-center'>
//                                 <p
//                                     className='bg-success text-white rounded'
//                                     style={{
//                                         padding: '10px', // Điều chỉnh padding (top-bottom, left-right)

//                                         width: '100px', // Cài đặt chiều rộng cố định
//                                         height: '39px', // Cài đặt chiều cao cố định
//                                     }}
//                                 >
//                                     {employee.salary.toLocaleString()} VND
//                                 </p>
//                             </div>
//                         </div>

//                     </div>



//                     <div className='col-9'>
//                         <div className='card-body d-flex flex-column' style={{ height: '100%' }}>

//                             {/* Phần tên chiếm 1/5 */}
//                             <div className='d-flex align-items-center' style={{ flex: '1' }}>
//                                 <h5 className='card-title mb-0'>
//                                     {employee.firstName} {employee.lastName}
//                                 </h5>
//                             </div>

//                             {/* Phần chức vụ chiếm 1/5, chỉ tô màu nền trong phạm vi chữ */}
//                             <div className='d-flex align-items-center mt-2' style={{ flex: '1' }}>
//                                 <h6 className='card-subtitle mb-0'>
//                                     <span
//                                         style={{
//                                             backgroundColor: getPositionColor(employee.position),
//                                             color: 'white',
//                                             borderRadius: '4px',
//                                             padding: '2px 8px',
//                                         }}
//                                     >
//                                         {employee.position}
//                                     </span>
//                                 </h6>
//                             </div>

//                             {/* Đường kẻ ngăn cách */}
//                             <hr className='my-2' style={{ flex: '0 0 1px', width: '100%' }} />

//                             {/* Phần mô tả công việc chiếm 2/5 */}
//                             <div className='d-flex' style={{ flex: '2' }}>
//                                 <p className='card-text'>{employee.job}</p>
//                             </div>

//                             {/* Phần nút chiếm 1/5 */}
//                             <div className='d-flex justify-content-start mt-auto' style={{ flex: '1' }}>
//                                 {/* <button className='btn btn-primary me-1'>
//                                     <Plus size={16} className="me-1" />
//                                     Thêm Task
//                                 </button> */}

//                                 <button className='btn btn-warning' onClick={onProfileClick}>
//                                     <Money size={16} className="me-1" /> {/* Biểu tượng Money */}
//                                     Lương
//                                 </button>

//                             </div>
//                         </div>
//                     </div>
//                 </div>
//             </div>
//         </div>
//     );
// };






// const AdminSalary = ({ x }) => {
//     console.log('x ở trang employee, x = ', x)
//     x = x + 1;
//     console.log('x ở trang employee lần 2, x = ', x)
//     const [employees, setEmployees] = useState([
//         {
//             id: 1,
//             lastName: 'Doe',
//             firstName: 'John',
//             department: 'Development',
//             position: 'Software Engineer',
//             job: 'Phát triển ứng dụng và hệ thống phần mềm.',
//             dateOfHire: '2020-01-15',
//             baseSalary: '1000 USD',
//             projectsCount: 4,
//             role: 'Employee',
//             currentProject: 'Project A',
//             phoneNumber: '0123456789',
//             gender: 'Male',
//             email: 'john.doe@example.com',
//             address: '123 Main St',
//             avatar: 'https://randomuser.me/api/portraits/men/1.jpg',
//             tasks: 10,
//             stars: 5,
//             salary: 1000,
//         },
//         {
//             id: 2,
//             lastName: 'Doe',
//             firstName: 'John',
//             department: 'Development',
//             position: 'Mobile Developer',
//             job: 'Phát triển ứng dụng và hệ thống phần mềm.',
//             dateOfHire: '2020-01-15',
//             baseSalary: '1000 USD',
//             projectsCount: 5,
//             role: 'Employee',
//             currentProject: 'Project A',
//             phoneNumber: '0123456789',
//             gender: 'Male',
//             email: 'john.doe@example.com',
//             address: '123 Main St',
//             avatar: 'https://randomuser.me/api/portraits/men/2.jpg',
//             tasks: 10,
//             stars: 5,
//             salary: 1000,
//         },
//         {
//             id: 3,
//             lastName: 'Doe',
//             firstName: 'John',
//             department: 'Development',
//             position: 'UI/UX Designer',
//             job: 'Phát triển ứng dụng và hệ thống phần mềm.',
//             dateOfHire: '2020-01-15',
//             baseSalary: '1000 USD',
//             projectsCount: 5,
//             role: 'Employee',
//             currentProject: 'Project A',
//             phoneNumber: '0123456789',
//             gender: 'Male',
//             email: 'john.doe@example.com',
//             address: '123 Main St',
//             avatar: 'https://randomuser.me/api/portraits/men/3.jpg',
//             tasks: 10,
//             stars: 5,
//             salary: 1000,
//         }, {
//             id: 4,
//             lastName: 'Doe',
//             firstName: 'John',
//             department: 'Development',
//             position: 'Quality Assurance',
//             job: 'Phát triển ứng dụng và hệ thống phần mềm.',
//             dateOfHire: '2020-01-15',
//             baseSalary: '1000 USD',
//             projectsCount: 5,
//             role: 'Employee',
//             currentProject: 'Project A',
//             phoneNumber: '0123456789',
//             gender: 'Male',
//             email: 'john.doe@example.com',
//             address: '123 Main St',
//             avatar: 'https://randomuser.me/api/portraits/men/4.jpg',
//             tasks: 10,
//             stars: 5,
//             salary: 1000,
//         },
//         {
//             id: 5,
//             lastName: 'Doe',
//             firstName: 'John',
//             department: 'Development',
//             position: 'Employee',
//             job: 'Phát triển ứng dụng và hệ thống phần mềm.',
//             dateOfHire: '2020-01-15',
//             baseSalary: '1000 USD',
//             projectsCount: 5,
//             role: 'Employee',
//             currentProject: 'Project A',
//             phoneNumber: '0123456789',
//             gender: 'Male',
//             email: 'john.doe@example.com',
//             address: '123 Main St',
//             avatar: 'https://randomuser.me/api/portraits/men/5.jpg',
//             tasks: 10,
//             stars: 5,
//             salary: 1000,
//         }, {
//             id: 6,
//             lastName: 'Doe',
//             firstName: 'John',
//             department: 'Development',
//             position: 'Employee',
//             job: 'Phát triển ứng dụng và hệ thống phần mềm.',
//             dateOfHire: '2020-01-15',
//             baseSalary: '1000 USD',
//             projectsCount: 5,
//             role: 'Employee',
//             currentProject: 'Project A',
//             phoneNumber: '0123456789',
//             gender: 'Male',
//             email: 'john.doe@example.com',
//             address: '123 Main St',
//             avatar: 'https://randomuser.me/api/portraits/men/6.jpg',
//             tasks: 10,
//             stars: 5,
//             salary: 1000,
//         }, {
//             id: 7,
//             lastName: 'Doe',
//             firstName: 'John',
//             department: 'Development',
//             position: 'Employee',
//             job: 'Phát triển ứng dụng và hệ thống phần mềm.',
//             dateOfHire: '2020-01-15',
//             baseSalary: '1000 USD',
//             projectsCount: 5,
//             role: 'Employee',
//             currentProject: 'Project A',
//             phoneNumber: '0123456789',
//             gender: 'Male',
//             email: 'john.doe@example.com',
//             address: '123 Main St',
//             avatar: 'https://randomuser.me/api/portraits/men/7.jpg',
//             tasks: 10,
//             stars: 5,
//             salary: 1000,
//         },
//         {
//             id: 8,
//             lastName: 'Doe',
//             firstName: 'John',
//             department: 'Development',
//             position: 'Employee',
//             job: 'Phát triển ứng dụng và hệ thống phần mềm.',
//             dateOfHire: '2020-01-15',
//             baseSalary: '1000 USD',
//             projectsCount: 5,
//             role: 'Employee',
//             currentProject: 'Project A',
//             phoneNumber: '0123456789',
//             gender: 'Male',
//             email: 'john.doe@example.com',
//             address: '123 Main St',
//             avatar: 'https://randomuser.me/api/portraits/men/8.jpg',
//             tasks: 10,
//             stars: 5,
//             salary: 1000,
//         },
//         {
//             id: 9,
//             lastName: 'Doe',
//             firstName: 'John',
//             department: 'Development',
//             position: 'Employee',
//             job: 'Phát triển ứng dụng và hệ thống phần mềm.',
//             dateOfHire: '2020-01-15',
//             baseSalary: '1000 USD',
//             projectsCount: 5,
//             role: 'Employee',
//             currentProject: 'Project A',
//             phoneNumber: '0123456789',
//             gender: 'Male',
//             email: 'john.doe@example.com',
//             address: '123 Main St',
//             avatar: 'https://randomuser.me/api/portraits/men/9.jpg',
//             tasks: 10,
//             stars: 5,
//             salary: 1000,
//         },
//         {
//             id: 10,
//             lastName: 'Doe',
//             firstName: 'John',
//             department: 'Development',
//             position: 'Employee',
//             job: 'Phát triển ứng dụng và hệ thống phần mềm.',
//             dateOfHire: '2020-01-15',
//             baseSalary: '1000 USD',
//             projectsCount: 5,
//             role: 'Employee',
//             currentProject: 'Project A',
//             phoneNumber: '0123456789',
//             gender: 'Male',
//             email: 'john.doe@example.com',
//             address: '123 Main St',
//             avatar: 'https://randomuser.me/api/portraits/men/10.jpg',
//             tasks: 10,
//             stars: 5,
//             salary: 1000,
//         },




//         // Các nhân viên khác
//     ]);

//     const [selectedEmployee, setSelectedEmployee] = useState(null);
//     const [showModal, setShowModal] = useState(false);
//     const [showAddModal, setShowAddModal] = useState(false);

//     const handleModalClose = () => {
//         setShowModal(false);
//         setSelectedEmployee(null);
//     };

//     const handleSave = (updatedEmployee) => {
//         setEmployees(employees.map(emp => emp.id === updatedEmployee.id ? updatedEmployee : emp));
//         handleModalClose();
//     };

//     const handleAddEmployee = (newEmployee) => {
//         setEmployees((prevEmployees) => [...prevEmployees, newEmployee]);
//         setShowAddModal(false);
//     };

//     const handleAddModalShow = () => {
//         setShowAddModal(true);
//     };

//     const handleProfileClick = (employee) => {
//         setSelectedEmployee(employee);
//         setShowModal(true);
//     };

//     return (
//         <div>
//             <div className='d-flex justify-content-between align-items-center mb-0'>
//                 <h2 className='mb-0'>Đào tạo và phát triển</h2>


//                 {/* <button className='btn btn-success' onClick={handleAddModalShow}>
//                     <Plus size={16} className="me-1" /> 

//                     Thêm nhân viên
//                 </button>
//              */}
//             </div>


//             <hr style={{ width: '100%', border: '1px solid #ddd', margin: '10px 0' }} />

//             <div className="row g-3 row-cols-1 row-cols-sm-1 row-cols-md-1 row-cols-lg-2 row-cols-xl-2 row-cols-xxl-2 row-deck pt-4">
//                 {employees.map((employee, index) => (
//                     <EmployeeCard
//                         key={employee.id}
//                         employee={employee}
//                         onProfileClick={() => handleProfileClick(employee)}
//                         index={index} // Thêm index tại đây

//                     />
//                 ))}

//                 {selectedEmployee && (
//                     <AdminSalaryModal
//                         show={showModal}
//                         handleClose={handleModalClose}
//                         employee={selectedEmployee}
//                         handleSave={handleSave}
//                     />
//                 )}
//             </div>

//             {/*
//             <Modal show={showAddModal} onHide={() => setShowAddModal(false)} centered className="custom-modal modal-lg">
//                 <Modal.Header closeButton>
//                     <Modal.Title>Thêm Nhân Viên Mới</Modal.Title>
//                 </Modal.Header>
//                 <Modal.Body>
//                     <AddEmployee onAddEmployee={handleAddEmployee} />
//                 </Modal.Body>
//             </Modal> */}




//         </div>
//     );
// };

// export default AdminSalary;
import React, { useState } from 'react';
import { Trash, NotePencil } from 'phosphor-react';
import AdminSalaryModal from './AdminSalaryModal';

const AdminSalary = () => {
    const [showModal, setShowModal] = useState(false);
    const [selectedLoan, setSelectedLoan] = useState(null);

    const handleModalOpen = (loan) => {
        setSelectedLoan(loan);
        setShowModal(true);
    };

    const handleModalClose = () => {
        setShowModal(false);
        setSelectedLoan(null);
    };

    const handleSave = (updatedLoan) => {
        // Xử lý lưu thông tin cập nhật
        console.log(updatedLoan);
    };

    const handleDelete = (loanId) => {
        setLoans(prevLoans => prevLoans.filter(loan => loan.employeeId !== loanId));
    };

    const [loans, setLoans] = useState([
        {
            employeeId: 'LP-0101',
            name: 'Joan Dyer',
            image: 'https://randomuser.me/api/portraits/men/1.jpg',
            purpose: 'for weddings and family functions',
            date: '14 Jan, 2022',
            amount: 4000,
            department: 'Sales',
            workingDays: 22,
            status: 'Pending'
        },
    ]);

    const EmployeeLoanList = ({ loans }) => {
        return (
            <tbody>
                {loans.map((loan, index) => (
                    <tr key={index}>
                        <td><span className="fw-bold">{loan.employeeId}</span></td>
                        <td className="d-flex align-items-center">
                            <img className="avatar rounded-circle" src={loan.image} alt="" />
                            <div>
                                <span className="fw-bold ms-1">{loan.name}</span>
                                <span className="text-muted ms-1 d-block">Purpose : {loan.purpose}</span>
                            </div>
                        </td>
                        <td>{loan.department}</td>
                        <td>{loan.workingDays}</td>
                        <td>{loan.date}</td>
                        <td><span className="text-danger fw-bold">${loan.amount}</span></td>
                        <td><span className={`fw-bold ${loan.status === 'Paid' ? 'text-success' : 'text-warning'}`}>{loan.status}</span></td>
                        <td>
                            <div className="btn-group" role="group" aria-label="Basic outlined example">
                                <button type="button" className="btn btn-outline-secondary" onClick={() => handleModalOpen(loan)}>
                                    <NotePencil size={20} className="text-success" />
                                </button>
                                <button type="button" className="btn btn-outline-secondary deleterow" onClick={() => handleDelete(loan.employeeId)}>
                                    <Trash size={20} className="text-danger" />
                                </button>
                            </div>
                        </td>
                    </tr>
                ))}
            </tbody>
        );
    };

    return (
        <div className='body d-flex py-lg-3 py-md-2'>
            <div className='container-xxl'>
                <div className='row align-items-center'>
                    <div className='border-0 mb-4'>
                        <div className='card-header py-3 no-bg-transparent d-flex align-items-center px-0 justify-content-between border-bottom flex-wrap'>
                            <h3 className='fw-bold mb-0'>Employee Salary</h3>
                        </div>
                    </div>
                </div>
                <div className="row clearfix g-3" style={{ width: '80vw' }}>
                    <div className="col-sm-12" style={{ width: '100%' }}>
                        <div className="card mb-3" style={{ width: '100%' }}>
                            <div className="card-body" style={{ overflowY: 'auto', height: '100%' }}>
                                <table id="myProjectTable" className="table table-hover align-middle mb-0" style={{ width: '100%' }}>
                                    <thead>
                                        <tr>
                                            <th>Employee ID</th>
                                            <th>Employee Name</th>
                                            <th>Department</th>
                                            <th>Working Days</th>
                                            <th>Date</th>
                                            <th>Salary Amount</th>
                                            <th>Status</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <EmployeeLoanList loans={loans} />
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <AdminSalaryModal show={showModal} handleClose={handleModalClose} loan={selectedLoan} handleSave={handleSave} />
        </div>
    );
};

export default AdminSalary;

