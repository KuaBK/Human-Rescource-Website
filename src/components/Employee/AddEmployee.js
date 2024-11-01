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

        {/* Input file for avatar upload */}
        <input type="file" accept="image/*" onChange={handleAvatarChange} /><br />

        <button type="button" onClick={handleAddClick}>Add Employee</button>
      </form>
    </div>
  );
};

export default AddEmployee;

