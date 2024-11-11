import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { PiNotePencilDuotone } from 'react-icons/pi';
import { FaProjectDiagram, FaRegUser } from 'react-icons/fa';
import { GiTeamUpgrade } from 'react-icons/gi';
import { BiLogOut, BiChat, BiX, BiChevronRight } from 'react-icons/bi'; 
import './MSidebar.scss';

function MSidebar() {
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
        <Link to="training" className="nav-link-side">
          <GiTeamUpgrade />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Đào tạo</span>
        </Link>
        <Link to="chat" className="nav-link-side">
          <BiChat />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Tin nhắn</span>
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
