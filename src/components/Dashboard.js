import React from 'react';
import './Dashboard.css';

const Dashboard = () => {
  return (
    <div className="dashboard">
      <div className="welcome-section">
        <h2>Hello Arpita 👋</h2>
        <p>You can manage your things from here</p>
      </div>
      <div className="widget-grid">
        <div className="widget">Notice</div>
        <div className="widget">Apply for Leave</div>
        <div className="widget">Access/Assets</div>
        <div className="widget">Salary Slip</div>
        <div className="widget">Attendance</div>
        <div className="widget">Holiday</div>
        <div className="widget">Employee Detail</div>
        <div className="widget">Resign</div>
        <div className="widget">Calendar</div>
      </div>
    </div>
  );
};

export default Dashboard;
