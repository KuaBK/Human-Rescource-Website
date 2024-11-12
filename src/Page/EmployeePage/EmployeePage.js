import React from 'react';

import './Page.scss';
import { Outlet} from 'react-router-dom';
import EHeader from './EHeader';
import ESidebar from './ESidebar';

const EmployeePage = () => {
  return (
    <div className="app-container">
      <div className='header-container'>
        <EHeader/>
      </div>
      <div className="main-container">
        <div className='sidenav-container'>
          <ESidebar/>
        </div>
        <div className='app-container'>
          <Outlet/>
        </div>  
      </div>
    </div>
  );
}

export default EmployeePage;
