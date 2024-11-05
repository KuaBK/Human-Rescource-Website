import React, { useState } from 'react';
import './EmployeeInfor.scss';
import { FaPhone, FaEnvelope, FaMapMarkerAlt, FaBriefcase, FaBuilding } from 'react-icons/fa';

const EmployeeInfor = () => {
  // State for profile data
  const [profile, setProfile] = useState({
    name: 'quang lee',
    position: 'Senior Software Engineer',
    phone: '+1 (555) 123-4567',
    email: 'quang.ly@hcmut.edu.vn',
    address: 'Huong So, Thua Thien Hue',
    department: 'Technology',
    officeLocation: 'H6- BK',
    employeeID: '12345',
  });

  // State to track if in edit mode
  const [isEditing, setIsEditing] = useState(false);

  // Function to handle form input changes
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setProfile((prevProfile) => ({
      ...prevProfile,
      [name]: value,
    }));
  };

  // Function to toggle edit mode
  const toggleEditMode = () => {
    setIsEditing(!isEditing);
  };

  // Function to save changes
  const handleSave = () => {
    setIsEditing(false);
    // Here, you would typically send the updated profile data to a server.
    console.log('Updated profile:', profile);
  };

  return (
    <div className="employee-profile">
      {/* Profile Header */}
      <div className="profile-header">
        <img
          src=""
          alt="Employee Profile"
          className="profile-image"
        />
        <div className="header-text">
          {isEditing ? (
            <input
              type="text"
              name="name"
              value={profile.name}
              onChange={handleInputChange}
              className="editable-input"
            />
          ) : (
            <h2 className="employee-name">{profile.name}</h2>
          )}
          {isEditing ? (
            <input
              type="text"
              name="position"
              value={profile.position}
              onChange={handleInputChange}
              className="editable-input"
            />
          ) : (
            <p className="employee-position">{profile.position}</p>
          )}
        </div>
      </div>

      {/* Profile Details */}
      <div className="profile-details">
        <div className="info-group">
          <h3>Thông tin liên lạc</h3>
          <p>
            <FaPhone />{' '}
            {isEditing ? (
              <input
                type="text"
                name="phone"
                value={profile.phone}
                onChange={handleInputChange}
                className="editable-input"
              />
            ) : (
              profile.phone
            )}
          </p>
          <p>
            <FaEnvelope />{' '}
            {isEditing ? (
              <input
                type="text"
                name="email"
                value={profile.email}
                onChange={handleInputChange}
                className="editable-input"
              />
            ) : (
              profile.email
            )}
          </p>
          <p>
            <FaMapMarkerAlt />{' '}
            {isEditing ? (
              <input
                type="text"
                name="address"
                value={profile.address}
                onChange={handleInputChange}
                className="editable-input"
              />
            ) : (
              profile.address
            )}
          </p>
        </div>

        <div className="info-group">
          <h3>Thông tin công việc</h3>
          <p><FaBriefcase /> Phòng ban: {profile.department}</p>
          <p><FaBuilding /> Địa chỉ phòng ban: {profile.officeLocation}</p>
          <p>Mã số nhân viên: {profile.employeeID}</p>
        </div>
      </div>

      {/* Edit and Save Buttons */}
      <div className="button-group">
        {isEditing ? (
          <button className="save-button" onClick={handleSave}>Lưu</button>
        ) : (
          <button className="edit-button" onClick={toggleEditMode}>Chỉnh sửa</button>
        )}
      </div>
    </div>
  );
};

export default EmployeeInfor;
