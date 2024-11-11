// ESidebar.js

import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';
import { PiNotePencilDuotone } from 'react-icons/pi';
import { FaProjectDiagram, FaRegUser } from 'react-icons/fa';
import { GiTeamUpgrade } from 'react-icons/gi';
import { BiLogOut, BiChat, BiX, BiChevronRight } from 'react-icons/bi';
import './ESidebar.scss';

function ESidebar() {
  const [expanded, setExpanded] = useState(true);

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
        <NavLink to="project" className="nav-link-side" activeClassName="active-link">
          <FaProjectDiagram />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Dự án</span>
        </NavLink>
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
