import React from 'react';
import './Sidebar.scss';
import { Nav } from 'react-bootstrap';
import { Link } from 'react-router-dom';

import { PiNotePencilDuotone } from "react-icons/pi";
import { GrMoney } from "react-icons/gr";
import { FaProjectDiagram, FaRegUser } from "react-icons/fa";
import { GiTeamUpgrade } from "react-icons/gi";
import { TfiStatsUp } from "react-icons/tfi";
import { BiLogOut } from "react-icons/bi";

function Sidebar() {
  return (
    <div className="sidebar">
      <Link to="infor"   className='nav-link'> <FaRegUser /> Thông tin cá nhân</Link>
      <Link to="attendance" className='nav-link'> <PiNotePencilDuotone /> Chấm công</Link>
      {/* <Link to="department" className='nav-link'> <GrMoney /> Tiền lương và phúc lợi</Link> */}
      <Link to="project"    className='nav-link'> <FaProjectDiagram /> Dự án</Link>
      <Link to="training"   className='nav-link'> <GiTeamUpgrade /> Đào tạo và phát triển</Link>
      <Link to="chat"   className='nav-link'> <GiTeamUpgrade /> Tin nhắn</Link>
      <Link to="chat"   className='nav-link'> <GiTeamUpgrade /> Đăng ký tăng ca</Link>


      {/* <Link to="statistic"  className='nav-link'> <TfiStatsUp /> Thống kê</Link> */}
      <Link to="logout"     className='nav-link'> <BiLogOut /> Đăng xuất</Link>
    </div>
  );
}

export default Sidebar;