import { useEffect, useState } from 'react'
import './Employee.css'
import { Star, Files, Plus, User, Trash } from 'phosphor-react';
import EmployeeProfileModal from './EmployeeProfileModal';
import AddEmployee from './AddEmployee';
import { Modal } from 'react-bootstrap';
import axios from "axios"
import { deletePersonel, postCreateNewAccount, postCreateNewPersonel, putUpdateAccount, putUpdatePersonel,  } from '../services/apiService';

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />


const getPositionColor = (position) => {
    const colors = {
        "Mobile Developer": "#FFD700",
        "QA/QC Engineer": "#ADD8E6",
        "UI/UX Designer": "#DDA0DD",
        "Quality Assurance": "#98FB98",
        "EMPLOYEE": "#87CEFA",
        "Software Engineer": "#fc88dd",
    };
    return colors[position] || "#d3d3d3";
};

const getRoleColor = (role) => (role === "EMPLOYEE" ? "#0004fc" : "#fc0000");


const EmployeeCard = ({ employee, onProfileClick, index, onDeleteClick }) => {
    return (
        <div className="col" style={{ animationDelay: `${index * 0.2}s` }}>
            <div className="card">
                <div className='row no-gutters employee-list-row'>
                    {/* HEADER */}
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
                                <p className='mb-0'>{employee.task} Tasks</p>  {/*taskcomplete*/}
                            </div>
                            <div className='text-center'>
                                <Star size={30} weight="bold" />
                                <p className='mb-0'>{employee.stars} Stars</p>
                            </div>
                        </div>
                    </div>

                    {/* BODY */}
                    <div className='col-9'>
                        <div className='card-body d-flex flex-column' style={{ height: '100%' }}>

                            {/* Phần tên chiếm 1/5 */}
                            <div className='d-flex align-items-center justify-content-between' style={{ flex: '1' }}>
                                <h5 className='card-title mb-0'>
                                    {employee.lastName} {employee.firstName}
                                </h5>
                                <button className='btn btn-danger' onClick={onDeleteClick}>
                                    <Trash size={16} className="me-1" />
                                </button>
                            </div>

                            {/* Phần chức vụ chiếm 1/5, chỉ tô màu nền trong phạm vi chữ */}
                            <div className='d-flex align-items-center mt-2' style={{ flex: '1' }}>
                                <h6 className='card-subtitle mb-0'>
                                    {/* <span
                                        style={{
                                            backgroundColor: getPositionColor(employee.position),
                                            color: 'white',
                                            borderRadius: '4px',
                                            padding: '2px 8px',
                                        }}
                                    >
                                        {employee.position}
                                    </span> */}
                                </h6>
                                
                                {/* Position tag */}
                                <h6 className='card-subtitle mb-0 custom-ml'>
                                    <span
                                        style={{
                                            backgroundColor: getRoleColor(employee.position),
                                            color: 'white',
                                            borderRadius: '4px',
                                            padding: '2px 8px',
                                        }}
                                    >
                                        {employee.position}
                                    </span>
                                </h6>
                                {/* Code tag */}
                                <h6 className='card-subtitle mb-0 custom-ml'>
                                    <span
                                        style={{
                                            marginLeft:"15px",
                                            backgroundColor: "#3c88da",
                                            color: 'white',
                                            borderRadius: '4px',
                                            padding: '2px 8px',
                                        }}
                                    >
                                        {employee.personelCode}
                                    </span>
                                </h6>

                                <h6 className='card-subtitle mb-0 custom-ml'>
                                    <span
                                        style={{
                                            marginLeft:"15px",
                                            backgroundColor: "green",
                                            color: 'white',
                                            borderRadius: '4px',
                                            padding: '2px 8px',
                                        }}
                                    >
                                        {employee.department}
                                    </span>
                                </h6>

                                {/* Department tag */}
                                <h6 className='card-subtitle mb-0 custom-ml'>
                                    <span
                                        style={{
                                            marginLeft:"15px",
                                            backgroundColor: "green",
                                            color: 'white',
                                            borderRadius: '4px',
                                            padding: '2px 8px',
                                        }}
                                    >
                                        {employee.deptName || "Not found"}
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
                            <div className='d-flex mt-auto' style={{ flex: '1', gap: "30px"}}>
                                {/* <button className='btn btn-primary me-1'>
                                    <Plus size={16} className="me-1" /> 
                                    Thêm Task
                                </button> */}

                                <button className='btn btn-info text-white me-1' onClick={onProfileClick}>
                                    <User size={16} className="me-1" /> Hồ sơ
                                </button>

                                <button className='btn btn-danger text-white me-1' onClick={onProfileClick}>
                                    Chuyển phòng ban
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
    // console.log('x ở trang employee, x = ', x)
    // x = x + 1;
    // console.log('x ở trang employee lần 2, x = ', x)

    // avatar, manageDate

    const [employees, setEmployees] = useState([]);
    const [error, setError] = useState(null);

    const [selectedEmployee, setSelectedEmployee] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [showAddModal, setShowAddModal] = useState(false);

    const fetchEmployees = async () => {
        try {
            const response = await axios.get("http://localhost:8080/personnel/all");
            if (response && response.data) {
                setEmployees(response.data.data); // Update employees state
            }
            console.log(response);
        } catch (err) {
            setError(err.message); // Log error
            console.error("Error fetching employees:", err);
        }
    };

    useEffect(() => {
        fetchEmployees();
    }, [])


    const handleModalClose = () => {
        setShowModal(false);
        setSelectedEmployee(null);
    };

    const handleSave = async (updatedEmployee) => {
        // setEmployees(employees.map(emp => emp.id === updatedEmployee.id ? updatedEmployee : emp));
        console.log("Employee updated >>>", updatedEmployee);
        try{
            const payload = {
                firstName: updatedEmployee.firstName,
                lastName: updatedEmployee.lastName,
                gender: updatedEmployee.sex,
                email: updatedEmployee.email,
                city: updatedEmployee.city,
                street: updatedEmployee.street,
                phoneNumber: updatedEmployee.phone
            }
            const response = await putUpdatePersonel(updatedEmployee.code, payload);
            if (response && response.data){
                console.log("Updated personel", response.data);
                fetchEmployees();
            }
        } catch (err) {
            setError(err.message);
            console.log(err);
        }

        handleModalClose();
    };

    const handleAddEmployee = async (newEmployee) => {
        console.log(newEmployee);
        try {
            const response = await postCreateNewAccount(newEmployee.username, newEmployee.password)
            if (response && response.data){
                const accountId = response.data.data.id;
                const payload = {
                    accountId, 
                    firstName: newEmployee.firstName,
                    lastName: newEmployee.lastName,
                    position: 'EMPLOYEE',
                    city: newEmployee.city,
                    street: newEmployee.street,
                    sex: newEmployee.gender,
                    email: newEmployee.email,
                    city: newEmployee.city,
                    street: newEmployee.street,
                    phoneNumber: newEmployee.phoneNumber
                };

                console.log(payload);
            
                const result = await postCreateNewPersonel(payload);
                if (result && result.data){
                    console.log("New personel created:", result.data);
                    fetchEmployees();
                }
            }
        } catch (err) {
            setError(err.message);
            console.log(err);
        }

        // setEmployees((prevEmployees) => [...prevEmployees, newEmployee]);
        setShowAddModal(false);
    };

    const handleAddModalShow = () => {
        setShowAddModal(true);
    };

    const handleProfileClick = (employee) => {
        setSelectedEmployee(employee);
        setShowModal(true);
    };

    const handleDeleteClick = async (employeeId) => {
        // setEmployees(prevEmployees => prevEmployees.filter(emp => emp.id !== employeeId));
        console.log(employeeId);
        try{
            const response = await deletePersonel(employeeId);
        } catch (err) {
            setError(err.message);
            console.log(err);
        }
        fetchEmployees();
    };

    return (
        <div>
            <div className='d-flex justify-content-between align-items-center mb-0'>
                <h2 className='mb-0'>Danh sách nhân viên</h2>
                <button className='btn btn-success text-white' onClick={handleAddModalShow}>
                    <Plus size={16} className="me-1" /> {/* Biểu tượng Plus */}
                    Thêm nhân viên
                </button>
            </div>

            <hr style={{ width: '100%', border: '1px solid #ddd', margin: '10px 0' }} />

            <div className="row g-3 row-cols-1 row-cols-sm-1 row-cols-md-1 row-cols-lg-2 row-cols-xl-2 row-cols-xxl-2 row-deck pt-4">
                {employees.map((employee, index) => (
                    <EmployeeCard
                        key={employee.personelCode}
                        employee={employee}
                        onProfileClick={() => handleProfileClick(employee)}
                        onDeleteClick={() => handleDeleteClick(employee.personelCode)}
                        index={index} // Thêm index tại đây

                    />
                ))}
                {selectedEmployee && (
                    <EmployeeProfileModal
                        show={showModal}
                        handleClose={handleModalClose}
                        employee={selectedEmployee}
                        //employee={JSON.parse(JSON.stringify(employee))}  // Truyền bản sao của đối tượng
                        handleSave={handleSave}
                    />
                )}
            </div>
            <Modal show={showAddModal} onHide={() => setShowAddModal(false)} centered className="custom-modal modal-lg">
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
