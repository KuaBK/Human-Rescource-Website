import React, { useEffect, useState } from 'react';
import './EmployeeInfor.scss';
import { FaPhone, FaEnvelope, FaMapMarkerAlt, FaBriefcase, FaBuilding } from 'react-icons/fa';
import { useDispatch, useSelector } from 'react-redux';

const EmployeeInfor = () => {

  const {personnel} = useSelector((state) => state);

  // State for profile data
  const [profile, setProfile] = useState({
    name: '',
    position: '',
    phone: '',
    email: '',
    address: '',
    department: '',
    profileImage: '', // New state for the profile image
  });

  useEffect(() => {
    if(personnel?.data){
      console.log("personnel info >>>", personnel.data);
      setProfile((prevProfile) => ({
        ...prevProfile,
        name: `${personnel.data.lastName} ${personnel.data.firstName}`,
        phone: `${personnel.data.phone}`,
        email: `${personnel.data.email}`,
        address: `${personnel.data.street} ${personnel.data.city}`,
        department: `${personnel.data.departmentName}`,
        position: `${personnel.data.personelCode}`,
        profileImage: `${personnel.data.avatar}`
      }))
    }
  }, [personnel])

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

  // Function to handle profile image upload
  const handleImageChange = async (e) => {
    const file = e.target.files[0];
    console.log("file >>>", file);

    if (file) {
        const formData = new FormData();
        formData.append('image', file);
        console.log("formData >>>>", formData);

        const imageUrl = URL.createObjectURL(file);
        setProfile((prevProfile) => ({
            ...prevProfile,
            profileImage: imageUrl,
        }));

        // try {
        //   const response = await fetch('/api/upload', {
        //     method: 'POST',
        //     body: formData,
        //   });

        //   if (response.ok) {
        //     const data = await response.json();
        //     console.log("Uploaded image URL >> ", data.imageUrl);

        //     // Save the URL to the database or state
        //     setProfile((prevProfile) => ({
        //         ...prevProfile,
        //         profileImage: data.imageUrl, // Save the image URL returned from the server
        //     }));
        //   } else {
        //       console.error("Failed to upload the image.");
        //   }
        // } catch (error) {
        //   console.error("Error uploading image: ", error);
        // }

    } 


    // console.log("file >> ", file);
    // if (file) {
    //   const imageUrl = URL.createObjectURL(file); // Create a local URL for the image
    //   console.log("image >> ", imageUrl);
    //   setProfile((prevProfile) => ({
    //     ...prevProfile,
    //     profileImage: imageUrl,
    //   }));
    // }
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
          src={profile.profileImage || "default_profile_picture.png"} // Use a default image if none is uploaded
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

      {/* Profile Image Upload */}
      {isEditing && (
        <div className="image-upload">
          <input
            type="file"
            accept="image/*"
            onChange={handleImageChange}
            className="file-upload"
            id="file-upload"
          />
          <label htmlFor="file-upload" className="upload-button">
            <i className="fas fa-upload"></i> Upload Profile Picture
          </label>
        </div>
      )}

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
          {/* <p><FaBuilding /> Địa chỉ phòng ban: {profile.officeLocation}</p>
          <p>Mã số nhân viên: {profile.employeeID}</p> */}
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
