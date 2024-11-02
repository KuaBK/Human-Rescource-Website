import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import reportWebVitals from './reportWebVitals';

import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import Employee from './components/Employee/Employee';
import Attendance from './components/Attendance/Attendance';
import Salary from './components/Salary/Salary';
import Department from './components/Department/Department';
import Project from './components/Project/Project';
import Training from './components/Training/Training';
import Statistic from './components/Statistic/Statistic';
import Dashboard from './components/Dashboard';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
          <Route path="/" element={<App />}> 
            <Route index element={<Dashboard />}></Route>
            <Route path="employee" element={<Employee />}/>
            <Route path="attendance" element={<Attendance />}/>
            <Route path="salary" element={<Salary />}/>
            <Route path="department" element={<Department />}/>
            <Route path="project" element={<Project />}/>
            <Route path="training" element={<Training />}/>
            <Route path="statistic" element={<Statistic />}/>
          </Route>
      </Routes>
    </BrowserRouter>
    
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
