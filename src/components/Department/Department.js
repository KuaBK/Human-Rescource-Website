import React, { useState } from "react";
import "./Department.scss";
import { FaEdit, FaTrashAlt } from "react-icons/fa";

const Department = () => {
  const initialDepartments = [
    {
      id: 1,
      name: "Marketing",
      establish_date: "2020-05-15",
      number_employees: 25,
      manager_code: 101001,
    },
    {
      id: 2,
      name: "Engineering",
      establish_date: "2019-03-10",
      number_employees: 50,
      manager_code: 102002,
    },
    {
      id: 3,
      name: "Human Resources",
      establish_date: "2021-08-20",
      number_employees: 15,
      manager_code: null,
    },
  ];

  const [departments, setDepartments] = useState(initialDepartments);
  const [showForm, setShowForm] = useState(false);
  const [formData, setFormData] = useState({
    id: "",
    name: "",
    establish_date: "",
    number_employees: 0,
    manager_code: "",
  });

  const handleFormChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const openForm = (department = null) => {
    setShowForm(true);
    if (department) {
      // Prefill form data for editing
      setFormData(department);
    } else {
      // Reset form data for creating
      setFormData({
        id: "", // No ID for new departments
        name: "",
        establish_date: "",
        number_employees: 0,
        manager_code: "",
      });
    }

    console.log("pass open edit");
  };


  const closeForm = () => {
    setShowForm(false);
    setFormData({
      id: "",
      name: "",
      establish_date: "",
      number_employees: 0,
      manager_code: "",
    });

    console.log("pass open close");
  };



  const handleFormSubmit = (e) => {
    e.preventDefault();
    if (formData.id) {
      // Update existing department
      setDepartments((prevDepartments) =>
        prevDepartments.map((dept) =>
          dept.id === formData.id ? { ...formData } : dept
        )
      );
      alert("Department updated successfully!");
    } else {
      // Add a new department with incremented ID
      const newDepartment = {
        ...formData,
        id: departments.length ? Math.max(...departments.map((dept) => dept.id)) + 1 : 1, // Increment ID
      };
      setDepartments((prevDepartments) => [...prevDepartments, newDepartment]);
      alert("New department added successfully!");
    }
    closeForm();

    console.log("pass submit");
  };


  const deleteDepartment = (id) => {
    if (window.confirm("Are you sure you want to delete this department?")) {
      setDepartments(departments.filter((dept) => dept.id !== id));
    }

    console.log("pass delete");
  };

  return (
    <div className="departments-container">
      <h2 className="departments-title">Danh sách phòng ban</h2>
      <div className="departments-grid">
        {departments.map((department) => (
          <div
            key={department.id}
            className={`department-card department-${department.id}`}
          >
            <div className="department-card-header">
              <h3 className="department-name">{department.name}</h3>
              <div className="department-actions">
                <FaEdit
                  className="action-icon edit-icon"
                  onClick={() => openForm(department)}
                />
                <FaTrashAlt
                  className="action-icon delete-icon"
                  onClick={() => deleteDepartment(department.id)}
                />
              </div>
            </div>
            <div className="department-card-body">
              <p><strong>Mã phòng ban:</strong> {department.id}</p>
              <p><strong>Ngày thành lập:</strong> {department.establish_date || "Chưa có"}</p>
              <p><strong>Số lượng nhân viên:</strong> {department.number_employees}</p>
              <p><strong>Mã quản lý:</strong> {department.manager_code || "Chưa có"}</p>
            </div>
          </div>
        ))}
      </div>
      <button className="create-department-button" onClick={() => openForm()}>
        + Tạo phòng ban mới
      </button>

      {/* Modal for form */}
      {showForm && (
        <div className="modal">
          <div className="modal-content">
            <h3>{formData.id ? "Chỉnh sửa phòng ban" : "Tạo phòng ban mới"}</h3>
            <form onSubmit={handleFormSubmit}>
              <div className="form-group">
                <label>Tên phòng ban:</label>
                <input
                  type="text"
                  name="name"
                  value={formData.name}
                  onChange={handleFormChange}
                  required
                />
              </div>

              <div className="form-group">
                <label>Mã phòng ban:</label>
                <input
                  type="number"
                  name="id" // Changed to match the state property
                  value={formData.id}
                  onChange={handleFormChange}
                  disabled // Disable ID input for new departments
                />
              </div>

              <div className="form-group">
                <label>Ngày thành lập:</label>
                <input
                  type="date"
                  name="establish_date"
                  value={formData.establish_date}
                  onChange={handleFormChange}
                />
              </div>
              <div className="form-group">
                <label>Số lượng nhân viên:</label>
                <input
                  type="number"
                  name="number_employees"
                  value={formData.number_employees}
                  onChange={handleFormChange}
                />
              </div>
              <div className="form-group">
                <label>Mã quản lý:</label>
                <input
                  type="text"
                  name="manager_code"
                  value={formData.manager_code}
                  onChange={handleFormChange}
                />
              </div>
              <div className="form-buttons">
                <button type="submit" className="submit-button">
                  {formData.id ? "Cập nhật" : "Tạo mới"}
                </button>
                <button
                  type="button"
                  className="cancel-button"
                  onClick={closeForm}
                >
                  Hủy
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default Department;
