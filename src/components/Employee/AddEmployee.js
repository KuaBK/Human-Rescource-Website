import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './AddEmployee.css';

const AddEmployee = ({ onAddEmployee, x }) => {
  console.log('x ở trang add employee, x = ', x);
  x = x + 100;
  console.log('x ở trang add employee lần 2, x = ', x);

  const [newEmployee, setNewEmployee] = useState({
    id: '',
    lastName: '',
    firstName: '',
    department: '',
    position: '',
    job: '',
    dateOfHire: '',
    baseSalary: '',
    projectsCount: '',
    role: 'Employee',
    currentProject: '',
    phoneNumber: '',
    gender: 'Male',
    email: '',
<<<<<<< HEAD
    status: 'Active', // Default to "Active"
=======
>>>>>>> 95d15daef6d009b1683e94aaaa22fde705893ac8
    address: '',
    avatar: '',
    tasks: 0,
    stars: 0,
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewEmployee({ ...newEmployee, [name]: value });
  };

  const handleAvatarChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setNewEmployee({ ...newEmployee, avatar: reader.result });
      };
      reader.readAsDataURL(file);
    }
  };

  const handleAddClick = () => {
    const newEmpWithId = { ...newEmployee, id: Date.now() };
    onAddEmployee(newEmpWithId);
    navigate('/employee');
  };

  return (
    <div className="add-employee-container">
      <form className="add-employee-form">
<<<<<<< HEAD
        <input type="text" name="lastName" placeholder="Họ" value={newEmployee.lastName} onChange={handleChange} /><br />
        <input type="text" name="firstName" placeholder="Tên" value={newEmployee.firstName} onChange={handleChange} /><br />
        <input type="text" name="department" placeholder="Phòng ban" value={newEmployee.department} onChange={handleChange} /><br />
        <input type="text" name="position" placeholder="Vị trí" value={newEmployee.position} onChange={handleChange} /><br />
        <input type="text" name="job" placeholder="Công việc" value={newEmployee.job} onChange={handleChange} /><br />
        <input type="date" name="dateOfHire" value={newEmployee.dateOfHire} onChange={handleChange} /><br />
        <input type="number" name="baseSalary" placeholder="Lương cơ bản" value={newEmployee.baseSalary} onChange={handleChange} /><br />
        <input type="number" name="projectsCount" placeholder="Số lượng dự án đang làm" value={newEmployee.projectsCount} onChange={handleChange} /><br />
        <select name="role" value={newEmployee.role} onChange={handleChange}>
          <option value="Employee">Nhân viên</option>
          <option value="Manager">Quản lý</option>
        </select><br />
        <input type="text" name="currentProject" placeholder="Dự án hiện tại" value={newEmployee.currentProject} onChange={handleChange} /><br />
        <input type="text" name="phoneNumber" placeholder="Phone Number" value={newEmployee.phoneNumber} onChange={handleChange} /><br />
        <select name="gender" value={newEmployee.gender} onChange={handleChange}>
          <option value="Male">Nam</option>
          <option value="Female">Nữ</option>
          <option value="Other">Khác</option>
        </select><br />
        <input type="email" name="email" placeholder="Email" value={newEmployee.email} onChange={handleChange} /><br />

        {/* Status dropdown */}
        <select name="status" value={newEmployee.status} onChange={handleChange}>
          <option value="Active">Hoạt động</option>
          <option value="Inactive">Không hoạt động</option>
        </select><br />

        <input type="text" name="address" placeholder="Địa chỉ" value={newEmployee.address} onChange={handleChange} /><br />
=======
        <input type="text" name="lastName" placeholder="Last Name" value={newEmployee.lastName} onChange={handleChange} /><br />
        <input type="text" name="firstName" placeholder="First Name" value={newEmployee.firstName} onChange={handleChange} /><br />
        <input type="text" name="department" placeholder="Department" value={newEmployee.department} onChange={handleChange} /><br />
        <input type="text" name="position" placeholder="Position" value={newEmployee.position} onChange={handleChange} /><br />
        <input type="text" name="job" placeholder="Job" value={newEmployee.job} onChange={handleChange} /><br />
        <input type="date" name="dateOfHire" value={newEmployee.dateOfHire} onChange={handleChange} /><br />
        <input type="number" name="baseSalary" placeholder="Base Salary" value={newEmployee.baseSalary} onChange={handleChange} /><br />
        <input type="number" name="projectsCount" placeholder="Projects Count" value={newEmployee.projectsCount} onChange={handleChange} /><br />
        <select name="role" value={newEmployee.role} onChange={handleChange}>
          <option value="Employee">Employee</option>
          <option value="Manager">Manager</option>
        </select><br />
        <input type="text" name="currentProject" placeholder="Current Project" value={newEmployee.currentProject} onChange={handleChange} /><br />
        <input type="text" name="phoneNumber" placeholder="Phone Number" value={newEmployee.phoneNumber} onChange={handleChange} /><br />
        <select name="gender" value={newEmployee.gender} onChange={handleChange}>
          <option value="Male">Male</option>
          <option value="Female">Female</option>
          <option value="Other">Other</option>
        </select><br />
        <input type="email" name="email" placeholder="Email" value={newEmployee.email} onChange={handleChange} /><br />
        <input type="text" name="address" placeholder="Address" value={newEmployee.address} onChange={handleChange} /><br />
>>>>>>> 95d15daef6d009b1683e94aaaa22fde705893ac8

        {/* Input file for avatar upload */}
        <input type="file" accept="image/*" onChange={handleAvatarChange} /><br />

<<<<<<< HEAD
        <button type="button" onClick={handleAddClick}>Thêm nhân viên</button>
=======
        <button type="button" onClick={handleAddClick}>Add Employee</button>
>>>>>>> 95d15daef6d009b1683e94aaaa22fde705893ac8
      </form>
    </div>
  );
};

export default AddEmployee;
<<<<<<< HEAD
=======

>>>>>>> 95d15daef6d009b1683e94aaaa22fde705893ac8
