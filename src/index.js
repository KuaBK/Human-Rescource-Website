import React from 'react';
import ReactDOM from 'react-dom/client';

import reportWebVitals from './reportWebVitals';
import '@fortawesome/fontawesome-free/css/all.min.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import Employee from './components/Employee/Employee';
import Attendance from './components/Attendance/EmployeeAttendance';

// import Salary from './components/Salary/Salary';


import Department from './components/Department/Department';
import Training from './components/Training/Training';


import Statistic from './components/Statistic/Statistic';
import ADashboard from './Page/AdminPage/ADashboard';
import EDashboard from './Page/EmployeePage/EDashboard';

import Home from './components/Authentication/Home';
import Login from './components/Authentication/Login';
import Signup from './components/Authentication/Signup';

import EmployeePage from './Page/EmployeePage/EmployeePage';
import AdminPage from './Page/AdminPage/AdminPage';
import EmployeeInfor from './components/Information/EmployeeInfor';
import EmployeeAttendance from './components/Attendance/EmployeeAttendance';
import EmployeeTraining from './components/Training/EmployeeTraining';
import EmployeeChat from './components/Chat/EmployeeChat';
import ManagerPage from './Page/ManagerPage/ManagerPage';

import Participation from './components/Project/Employee/Participation';

import AdminProject from './components/Project/Admin/AdminProject';
import AdminSalary from './components/Salary/AdminSalary';
import AdminTraining from './components/Training/AdminTraining';
import ManagerProject from './components/Project/Manager/ManagerProject';
import ManagerDevideTask from './components/Project/Manager/ManagerDivideTask';

// employee
import SubmitTask from './components/Project/Employee/SubmitTask';
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />

        <Route exact path="/login/admin" element={<AdminPage />}>
          <Route index element={<ADashboard />}></Route>
          <Route path="employee" element={<Employee />} />
          <Route path="department" element={<Department />} />
          <Route path="admin-attendance" element={<Attendance />} />
          <Route path="admin-salary" element={<AdminSalary />} />
  
          <Route path="project" element={<AdminProject/>} />
          <Route path="admin-training" element={<AdminTraining />} />
          <Route path="statistic" element={<Statistic />} />
          <Route path="chat" element={<EmployeeChat />} />

     
        </Route>

        <Route exact path="/login/employee" element={<EmployeePage />}>

          <Route index element={<EDashboard />}></Route>
          <Route path="infor" element={<EmployeeInfor />} />
          <Route path="attendance" element={<EmployeeAttendance />} />
          {/* <Route path="salary" element={<Salary />}/>
                    <Route path="department" element={<Department />}/> */}
          {/* <Route path="project" element={<Project />} /> */}
          <Route path="participation" element={<Participation/>} />
          <Route path="submittask" element={<SubmitTask/>} />
          <Route path="training" element={<EmployeeTraining />} />
          <Route path="chat" element={<EmployeeChat />} />
        </Route>

        <Route exact path="/login/manager" element={<ManagerPage />}>

          {/* <Route index element={<Dashboard />}></Route> */}
          <Route path="infor" element={<EmployeeInfor />} />
          <Route path="attendance" element={<EmployeeAttendance />} />
          {/* <Route path="devidetask" element={<ManagerDevideTask/>} /> */}

          {/* <Route path="salary" element={<Salary />} /> */}

          <Route path="department" element={<Department />} />
          <Route path="project" element={<ManagerProject />} />
          <Route path="training" element={<EmployeeTraining />} />
          <Route path="chat" element={<EmployeeChat />} />
        </Route>
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);

reportWebVitals();
