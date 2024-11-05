import React from 'react';
import './Page.scss';

import { Outlet} from 'react-router-dom';
import Header from './Header';
import Sidebar from './Sidebar';

const AdminPage = () => {
  return (
    <div className="app-container">
      <div className='header-container'>
        <Header/>
      </div>
      <div className="main-container">
        <div className='sidenav-container'>
          <Sidebar/>
        </div>
        <div className='app-container'>
          <Outlet/>
        </div>  
      </div>
    </div>
  );
}

export default AdminPage;
