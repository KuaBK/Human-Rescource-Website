import React, { useEffect, useState } from "react";
import "./Department.scss";
import { FaEdit, FaTrashAlt } from "react-icons/fa";
import { getAllDepartment, postCreateNewDepartment } from "../services/apiService";

const Department = () => {

  const [departments, setDepartments] = useState([]);
  const [error, setError] = useState(null);
  const [showForm, setShowForm] = useState(false);
  const [formData, setFormData] = useState({
      name: "",
      manager_code: "",
  });

  const fetchDepartments = async() => {
    try {
        const response = await getAllDepartment();
        console.log(response);
        if (response && response.data) {
          setDepartments(response.data.data); 
        }
    } catch (err) {
        setError(err.message); // Log error
        console.error("Error fetching departments:", err);
    }
  };

  useEffect(() => {
    fetchDepartments();
  }, [])


  // const initialDepartments = [
  //   {
  //     id: 1,
  //     name: "Marketing",
  //     establish_date: "2020-05-15",
  //     number_employees: 25,
  //     manager_code: 101001,
  //   },
  //   {
  //     id: 2,
  //     name: "Engineering",
  //     establish_date: "2019-03-10",
  //     number_employees: 50,
  //     manager_code: 102002,
  //   },
  //   {
  //     id: 3,
  //     name: "Human Resources",
  //     establish_date: "2021-08-20",
  //     number_employees: 15,
  //     manager_code: null,
  //   },
  // ];

  // const [departments, setDepartments] = useState(initialDepartments);
  

  const handleFormChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const openForm = (department = null) => {
    setShowForm(true);
    setFormData({
      name: "",
      manager_code: "",
    });
  };

  const closeForm = () => {
    setShowForm(false);
    setFormData({
      name: "",
      manager_code: "",
    });
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();
    try {
      const payload = {
        departmentName: formData.name,
        managerCode: formData.manager_code
      };
      const response = await postCreateNewDepartment(payload);
      if (response && response.data){
        console.log(response.data);
      }
      fetchDepartments();
    } catch (err) {
      setError(err.message);
        console.log(err);
    }
    closeForm();    
  };

  const deleteDepartment = (id) => {
    if (window.confirm("Are you sure you want to delete this department?")) {
      setDepartments(departments.filter((dept) => dept.id !== id));
    }
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
              <p><strong>Mã phòng ban:</strong> {department.departmentId}</p>
              {/* <p><strong>Ngày thành lập:</strong> {department.establish_date || "Chưa có"}</p> */}
              <p><strong>Số lượng nhân viên:</strong> {department.numberOfEmployees}</p>
              <p><strong>Mã quản lý:</strong> {department.manageCode || "Chưa có"}</p>
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
                <label style={{ textAlign: "left", display: "block" }}>Tên phòng ban:</label>
                <input
                  type="text"
                  name="name"
                  value={formData.name}
                  onChange={handleFormChange}
                  required
                />
              </div>

              {/* <div className="form-group">
                <label>Ngày thành lập:</label>
                <input
                  type="date"
                  name="establish_date"
                  value={formData.establish_date}
                  onChange={handleFormChange}
                />
              </div> */}
              
              <div className="form-group">
                <label style={{ textAlign: "left", display: "block" }}>Mã quản lý:</label>
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
