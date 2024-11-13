import React from 'react';
import './APage.scss';

import { Outlet } from 'react-router-dom';
import AHeader from './AHeader';
import ASidebar from './ASidebar';

const AdminPage = () => {
  return (
    <div className="app-container">
      <div className='header-container'>
        <AHeader />
      </div>
      <div className="main-container">
        <div className='sidenav-container'>
          <ASidebar />
        </div>
        <div className='app-container'>
          <Outlet />
        </div>
      </div>
    </div>
  );
}

export default AdminPage;
