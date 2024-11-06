import React from 'react';
import ReactDOM from 'react-dom/client';

import reportWebVitals from './reportWebVitals';
import '@fortawesome/fontawesome-free/css/all.min.css';


import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import Employee from './components/Employee/Employee';
import Attendance from './components/Attendance/EmployeeAttendance';
import Salary from './components/Salary/Salary';
import Department from './components/Department/Department';
import Project from './components/Project/Project';
import Training from './components/Training/Training';
import Statistic from './components/Statistic/Statistic';
import Dashboard from './Page/AdminPage/Dashboard';
import Home from './components/Authentication/Home';
import Login from './components/Authentication/Login';
import Signup from './components/Authentication/Signup';

import EmployeePage from './Page/EmployeePage/EmployeePage';
import AdminPage from './Page/AdminPage/AdminPage';
import EmployeeInfor from './components/Information/EmployeeInfor';
import EmployeeAttendance from './components/Attendance/EmployeeAttendance';
import EmployeeTraining from './components/Training/EmployeeTraining';
import AdminAttendance from './components/Attendance/AdminAttendance'

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>

        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />

        <Route exact path="/login/admin" element={<AdminPage />}>

          <Route index element={<Dashboard />}></Route>
          <Route path="employee" element={<Employee />} />
          <Route path="attendance" element={<Attendance />} />
          <Route path="salary" element={<Salary />} />
          <Route path="department" element={<Department />} />
          <Route path="project" element={<Project />} />
          <Route path="training" element={<Training />} />
          <Route path="statistic" element={<Statistic />} />
          <Route path="admin-attendance" element={<AdminAttendance />} />


        </Route>

        <Route exact path="/login/employee" element={<EmployeePage />}>

          <Route index element={<Dashboard />}></Route>
          <Route path="infor" element={<EmployeeInfor />} />
          <Route path="attendance" element={<EmployeeAttendance />} />
          <Route path="salary" element={<Salary />} />
          <Route path="department" element={<Department />} />
          <Route path="project" element={<Project />} />
          <Route path="training" element={<EmployeeTraining />} />
          <Route path="statistic" element={<Statistic />} />
        </Route>



      </Routes>
    </BrowserRouter>

  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();