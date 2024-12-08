import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { PiNotePencilDuotone } from 'react-icons/pi';
import { FaProjectDiagram, FaRegUser } from 'react-icons/fa';
import { GiTeamUpgrade } from 'react-icons/gi';
import { BiLogOut, BiChat, BiX, BiChevronRight } from 'react-icons/bi'; 
import './MSidebar.scss';

function MSidebar() {
  const [expanded, setExpanded] = useState(true);
  const [projectDropdown, setProjectDropdown] = useState(false);
  const [personnel, setPersonnel] = useState(null); // State to store personnel data

  const [isLogoHidden, setIsLogoHidden] = useState(false); // Trạng thái checkbox để ẩn logo

  useEffect(() => {
    const fetchPersonnel = async () => {
      try {
        const token = localStorage.getItem("token");
        const accountId = localStorage.getItem("accountId");
  
        console.log("accountID, token", accountId, token);
        if (!accountId || !token) return; // Tránh gọi API nếu không có accountId hoặc token
  
        const response = await fetch(
          `http://localhost:8080/api/managers/account?id=${accountId}`,
          {
            method: "GET",
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );
  
        if (response.ok) {
          const data = await response.json();
          setPersonnel(data);
          console.log("personnel >>>", data);
        } else {
          console.error("Error fetching personnel data:", response.statusText);
        }
      } catch (error) {
        console.error("Error fetching personnel data:", error);
      }
    };
  
    fetchPersonnel();
  }, []); // Không cần thêm accountId hoặc token vào dependencies
  
    

  return (
    <div className={`sidebar ${expanded ? 'expanded' : 'collapsed'}`}>

     <div className="sidebar-header">
        <div className="header-row">
          <span className={`logo ${isLogoHidden ? 'hide-text' : ''}`}>BK-MANARATE</span>
          <button onClick={() => setExpanded((prev) => !prev)} className="toggle-btn" aria-label="Toggle Sidebar">
            {expanded ? 'X' : '>'}
          </button>
        </div>
        <div className="user-info">
          <img
            className="avatar rounded-circle"
            src={personnel?.avatar || 'https://via.placeholder.com/50'}
            alt="User Avatar"
          />
          {expanded && (
            <span className="user-name">
              {personnel?.lastName && personnel?.firstName
                ? `${personnel.lastName} ${personnel.firstName}`
                : 'Loading...'}
            </span>
          )}
        </div>
      </div>


      <div className="nav-links">
        <Link to="infor" className="nav-link-side">
          <FaRegUser />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Thông tin</span>
        </Link>
        <Link to="attendance" className="nav-link-side">
          <PiNotePencilDuotone />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Chấm công</span>
        </Link>

        <Link to="project" className="nav-link-side">
          <FaProjectDiagram />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Dự án</span>
        </Link>
    
        <Link to="notification" className="nav-link-side">
          <BiChat />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Thông báo</span>
        </Link>
        
        <Link to="logout" className="nav-link-side">
          <BiLogOut />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Đăng xuất</span>
        </Link>
      </div>
    </div>
  );
}

export default MSidebar;
