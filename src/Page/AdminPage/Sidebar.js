import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { PiNotePencilDuotone } from 'react-icons/pi';
import { GrMoney } from 'react-icons/gr';
import { FaProjectDiagram, FaRegUser } from 'react-icons/fa';
import { GiTeamUpgrade } from 'react-icons/gi';
import { TfiStatsUp } from 'react-icons/tfi';
import { BiLogOut } from 'react-icons/bi';
import { ChevronFirst, ChevronLast } from 'lucide-react';
import './Sidebar.scss';

function Sidebar() {
  const [expanded, setExpanded] = useState(true);

  return (
    <div className={`sidebar ${expanded ? 'expanded' : 'collapsed'}`}>
      <div className="sidebar-header">
        <button onClick={() => setExpanded((prev) => !prev)} className="toggle-btn">
          {expanded ? <ChevronFirst className="rotated" /> : <ChevronLast />}
        </button>
      </div>

      <div className="nav-links">
        <Link to="employee" className="nav-link">
          <FaRegUser />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Nhân viên</span>
        </Link>
        <Link to="admin-attendance" className="nav-link">
          <PiNotePencilDuotone />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Chấm công</span>
        </Link>
        <Link to="department" className="nav-link">
          <GrMoney />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Tiền lương và phúc lợi</span>
        </Link>
        <Link to="project" className="nav-link">
          <FaProjectDiagram />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Dự án</span>
        </Link>
        <Link to="admin-training" className="nav-link">
          <GiTeamUpgrade />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Đào tạo và phát triển</span>
        </Link>
        <Link to="statistic" className="nav-link">
          <TfiStatsUp />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Thống kê</span>
        </Link>
        <Link to="logout" className="nav-link">
          <BiLogOut />
          <span className={`link-text ${expanded ? 'show' : ''}`}>Đăng xuất</span>
        </Link>
      </div>
    </div>
  );
}

export default Sidebar;
