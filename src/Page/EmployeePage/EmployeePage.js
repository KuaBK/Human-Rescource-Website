
import React, { useState } from 'react';
import { Outlet } from 'react-router-dom';
import EHeader from './EHeader';
import ESidebar from './ESidebar';
import './EPage.scss';

const EmployeePage = () => {
  // Thêm trạng thái để theo dõi trạng thái của sidebar
  const [isSidebarExpanded, setIsSidebarExpanded] = useState(true);

  // Hàm để thay đổi trạng thái sidebar
  const toggleSidebar = () => {
    setIsSidebarExpanded((prev) => !prev);
  };

  return (
    <div className="app-container">
      <ESidebar expanded={isSidebarExpanded} toggleSidebar={toggleSidebar} /> {/* Truyền trạng thái và hàm vào ASidebar */}
      <div
        className="main-content"
        style={{
          marginLeft: isSidebarExpanded ? '250px' : '80px',
          width: `calc(100% - ${isSidebarExpanded ? '250px' : '80px'})`, // Đảm bảo .main-content chiếm hết phần không gian còn lại sau sidebar
        }}// Thay đổi margin-left dựa vào trạng thái sidebar
      >
        <EHeader /> {/* Header positioned at the top, next to the sidebar */}
        <div className="content">
          <Outlet />
        </div>
      </div>
    </div>
  );
};

export default EmployeePage;

