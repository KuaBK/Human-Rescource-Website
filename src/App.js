import React from 'react';
import Sidebar from './components/Sidebar/Sidebar';
import Dashboard from './components/Dashboard';
import Header from './components/Header';
import './App.scss';

import { Outlet} from 'react-router-dom';

const App = () => {
  return (
    <div className="app-container">
      <div className='header-container'>
        <Header />
      </div>
      <div className="main-container">
        <div className='sidenav-container'>
          <Sidebar />
        </div>
        <div className='app-container'>
          <Outlet />
        </div>  
      </div>
    </div>
  );
}

export default App;
