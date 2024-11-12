import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';
import { PiNotePencilDuotone } from 'react-icons/pi';
import { FaProjectDiagram, FaRegUser } from 'react-icons/fa';
import { GiTeamUpgrade } from 'react-icons/gi';
import { BiLogOut, BiChat, BiX, BiChevronRight, BiChevronDown } from 'react-icons/bi';
import './ESidebar.scss';

function ESidebar() {
  const [expanded, setExpanded] = useState(true);
  const [projectDropdown, setProjectDropdown] = useState(false);

  const toggleProjectDropdown = () => {
    setProjectDropdown((prev) => !prev);
  };

  return (
    <div className={`sidebar ${expanded ? 'expanded' : 'collapsed'}`}>
      <div className="sidebar-header">
        <button
          onClick={() => setExpanded((prev) => !prev)}
          className="toggle-btn"
          aria-label="Toggle Sidebar"
        > 
          {expanded ? <BiX /> : <BiChevronRight />}
        </button>
      </div>

      <div className="nav-links">
        <NavLink to="infor" className="nav-link-side" activeClassName="active-link">
          <FaRegUser />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Thông tin</span>
        </NavLink>
        <NavLink to="attendance" className="nav-link-side" activeClassName="active-link">
          <PiNotePencilDuotone />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Chấm công</span>
        </NavLink>

        {/* Project Dropdown */}
        <div className="nav-link-side dropdown" onClick={toggleProjectDropdown}>
          <FaProjectDiagram />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Dự án</span>
          {expanded && <BiChevronDown className={`dropdown-icon ${projectDropdown ? 'open' : ''}`} />}
        </div>
        {expanded && projectDropdown && (
          <div className="dropdown-content">
            <NavLink to="participation" className="dropdown-item" activeClassName="active-link">
              Các dự án tham gia
            </NavLink>
            <NavLink to="submittask" className="dropdown-item" activeClassName="active-link">
              Nộp task
            </NavLink>
          </div>
        )}

        <NavLink to="training" className="nav-link-side" activeClassName="active-link">
          <GiTeamUpgrade />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Đào tạo</span>
        </NavLink>
        <NavLink to="chat" className="nav-link-side" activeClassName="active-link">
          <BiChat />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Tin nhắn</span>
        </NavLink>
        <NavLink to="logout" className="nav-link-side" activeClassName="active-link">
          <BiLogOut />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Đăng xuất</span>
        </NavLink>
      </div>
    </div>
  );
}

export default ESidebar;
