import React, { useState } from 'react';
import './Page.scss';
import { Outlet } from 'react-router-dom';
import MHeader from './MHeader';
import MSidebar from './MSidebar';

const ManagerPage = () => {
  // Thêm trạng thái để theo dõi trạng thái của sidebar
  const [isSidebarExpanded, setIsSidebarExpanded] = useState(true);

  // Hàm để thay đổi trạng thái sidebar
  const toggleSidebar = () => {
    setIsSidebarExpanded((prev) => !prev);
  };

  return (
    <div className="app-container">
      <MSidebar expanded={isSidebarExpanded} toggleSidebar={toggleSidebar} /> {/* Truyền trạng thái và hàm vào ASidebar */}
      <div
        className={`main-content ${isSidebarExpanded ? 'expanded' : 'collapsed'}`}
      >
        <MHeader />
        <div className="content">
          <Outlet />
        </div>
      </div>

    </div>
  );
};

export default ManagerPage;
