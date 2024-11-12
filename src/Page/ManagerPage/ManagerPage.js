import React from 'react';

import './Page.scss';

import { Outlet} from 'react-router-dom';
import MHeader from './MHeader';
import MSidebar from './MSidebar';

const ManagerPage = () => {
  return (
    <div className="app-container">
      <div className='header-container'>
        <MHeader/>
      </div>
      <div className="main-container">
        <div className='sidenav-container'>
          <MSidebar/>
        </div>
        <div className='app-container'>
          <Outlet/>
        </div>  
      </div>
    </div>
  );
}

export default ManagerPage;
