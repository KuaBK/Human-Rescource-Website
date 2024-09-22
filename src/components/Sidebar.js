import React from 'react';
import './Sidebar.css';

function Sidebar() {
  return (
    <div className="sidebar">
      <ul>
        <li><a href="#">Dashboard</a></li>
        <li><a href="#">Notice</a></li>
        <li><a href="#">Apply for Leave</a></li>
        <li><a href="#">Access/Asset</a></li>
        <li><a href="#">Salary Slip</a></li>
        <li><a href="#">Attendance</a></li>
        <li><a href="#">Holiday</a></li>
        <li><a href="#">Employee Detail</a></li>
        <li><a href="#">Calendar</a></li>
        <li><a href="#">Resign</a></li>
        <li><a href="#">Logout</a></li>
      </ul>
    </div>
  );
}

export default Sidebar;