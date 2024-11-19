// import React from 'react';
// import './ADashboard.scss';

// const Dashboard = () => {
//   const timesheetProjects = [
//     { name: 'Project Alpha', dueDate: '2024-12-01' },
//     { name: 'Project Beta', dueDate: '2024-12-10' },
//     { name: 'Project Gamma', dueDate: '2024-12-15' },
//   ];

//   const latestMembers = [
//     { name: 'John Doe', lastActive: '2 hours ago' },
//     { name: 'Jane Smith', lastActive: '3 hours ago' },
//     { name: 'Bob Johnson', lastActive: '5 hours ago' },
//   ];

//   return (
//     <div className="container-fluid dashboard">
//       <div className="row align-items-stretch welcome-row">
//         <div className="col-md-4 welcome-message">
//           <h1>Good Morning <span role="img" aria-label="wave">👋</span></h1>
//           <div className="info-grid row">
//             <div className="col-6">
//               <div className="info-box employees">Number of Employees on Leave</div>
//             </div>
//             <div className="col-6">
//               <div className="info-box new-joinee">New Joinee</div>
//             </div>
//             <div className="col-6">
//               <div className="info-box holiday">Upcoming Holiday</div>
//             </div>
//             <div className="col-6">
//               <div className="info-box projects">Current Projects</div>
//             </div>
//           </div>
//         </div>

//         <div className="col-md-3 project-section">
//           <h2>Upcoming Projects</h2>
//           <div className="project-box timesheet">
//             {timesheetProjects.map((project, index) => (
//               <div key={index} className="project-item">
//                 <p><strong>{project.name}</strong></p>
//                 <p>Due: {project.dueDate}</p>
//               </div>
//             ))}
//           </div>
//         </div>

//         <div className="col-md-4 latest-member-section">
//           <h2>Latest Member Activity</h2>
//           <div className="latest-members">
//             {latestMembers.map((member, index) => (
//               <div key={index} className="member-item">
//                 <p><strong>{member.name}</strong></p>
//                 <p>Last Active: {member.lastActive}</p>
//               </div>
//             ))}
//           </div>
//         </div>
//       </div>

//       <div className="row main-content">
//         <div className="col-sm-3 attendance-section">
//           <h2>Attendance</h2>
//           <a href="/attendance" className="attendance-link">View Attendance</a>
//         </div>

//         <div className="col-sm-6 employee-list">
//           <h2>Employee List</h2>
//         </div>

//         <div className="col-sm-3 salary-section">
//           <h2>Salary & Benefits</h2>
//         </div>
//       </div>
//     </div>
//   );
// };

// export default Dashboard;
import React from 'react';
import EmployeesInfo from './EmployeesInfo';
import EmployeesAvailability from './EmployeesAvailability';
import TotalEmployees from './TotalEmployees';
import HiringSources from './HiringSources';
import ApplicationsStats from './ApplicationsStats';
import InterviewAndHiredStats from './InterviewAndHiredStats';
import UpcomingInterviews from './UpcomingInterviews';
import TopPerformers from './TopPerformers'; // Import the TopPerformers component



function App() {
  return (
    <div className="body d-flex py-3">
      <div className="container-xxl">
        <div className="row clearfix g-3">
          {/* Left Column */}
          <div className="col-xl-8 col-lg-12 col-md-12 flex-column">
            <div className="row g-3">
              <EmployeesInfo />
              <EmployeesAvailability />
              <TotalEmployees />
              <HiringSources />
            </div>
          </div>

          {/* Right Column */}
          <div className="col-xl-4 col-lg-12 col-md-12 d-flex flex-column">
            <ApplicationsStats />
            <InterviewAndHiredStats />
            <div className="flex-grow-1 d-flex flex-column">
              <UpcomingInterviews />

            </div>

          </div>

          <TopPerformers /> {/* Add TopPerformers component here */}
        </div>

      </div>
    </div>
  );
}

export default App;
