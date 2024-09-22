import React from 'react';
import './Header.css';  // Separate file for styling

const Header = () => {
  return (
    <header className="header">
      <div className="logo">OMS Employee</div>
      <nav className="nav-menu">
        <a href="#dashboard">Dashboard</a>
        <a href="#notice">Notice</a>
        <a href="#leave">Apply for leave</a>
        <a href="#assets">Access/Assets</a>
        <a href="#salary">Salary Slip</a>
      </nav>
      <div className="header-icons">
        <span className="icon-notification">🔔</span>
        <span className="icon-settings">⚙️</span>
        <div className="profile-icon">AR</div>
      </div>
    </header>
  );
};

export default Header;
