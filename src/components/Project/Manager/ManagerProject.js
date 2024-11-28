import React, { useState } from 'react';
import './ManagerProject.scss';
import TaskModal from './TaskModal';

// import ManagerDevideTask from './ManagerDivideTask';

import MemberModal from './EditMemberModal';


const ManagerProject = () => {
    const initialProjects = [
        {
            id: 1,
            name: "UI/UX Design",
            company: "Social Geek Made",
            attachments: 5,

            // members: [
            //     { id: "E001", name: "Nguyen Van A" },
            //     { id: "E002", name: "Tran Thi B" },
            //     { id: "E003", name: "Le Van C" },
            //     { id: "E004", name: "Pham Thi D" },
            //     { id: "E005", name: "Hoang Van E" },
            // ],
            members: 5,
            duration: "4 Month",
            comments: 10,
            daysLeft: 35,
            progress: 70,
            icon: "paint-brush",
            tasks: [
                {
                    taskname: "thiet ke UI",
                    description: "Design homepage",
                    deadline: "2024-12-15",
                    status: "Not Started",
                    employee_codes: ["E001"],
                },
                {
                    taskname: "ve usecase diagram",
                    description: "Improve user flow",
                    deadline: "2024-12-20",
                    status: "In Progress",
                    employee_codes: ["E003"],
                },
                {
                    taskname: "ve usecase diagram",
                    description: "Improve user flow",
                    deadline: "2024-12-20",
                    status: "In Progress",
                    employee_codes: ["E002"],
                },


            ],
        },
        // Thêm các project khác ở đây...
    ];

    const [isMemberModalOpen, setIsMemberModalOpen] = useState(false);
    const [currentMembers, setCurrentMembers] = useState([]);

    // const [projects] = useState(initialProjects);

    const [selectedProject, setSelectedProject] = useState(null);
    const [isTaskFormOpen, setIsTaskFormOpen] = useState(false);
    const [currentProject, setCurrentProject] = useState(null);
    const [currentProjectId, setCurrentProjectId] = useState(null);
    const [projects, setProjects] = useState(initialProjects);

    const employees = [
        { code: 'E001', name: 'Nguyen Van A' },
        { code: 'E002', name: 'Nguyen Van T' },

        { code: 'E003', name: 'Le Van C' },
        { code: 'E004', name: 'Vo Le' }
    ];
    const handleAttachmentClick = (project) => {
        setSelectedProject(project);
    };

    const closeTaskModal = () => {
        setSelectedProject(null);
    };




    const closeMemberModal = () => {
        setIsMemberModalOpen(false);
    };

    const handleSaveMembers = (updatedMembers) => {
        setProjects((prevProjects) =>
            prevProjects.map((project) => {
                if (project.id === currentProjectId) {
                    const newEmployeeCodes = updatedMembers.map((member) => member.code);
                    const updatedTasks = project.tasks.map((task) => ({
                        ...task,
                        employee_codes: [...new Set([...task.employee_codes, ...newEmployeeCodes])],
                    }));
                    return { ...project, tasks: updatedTasks };
                }
                return project;
            })
        );
        closeMemberModal();
    };


    const handleMemberClick = (project) => {
        if (!project || !Array.isArray(project.tasks)) {
            // console.error("Project tasks are undefined or not an array:", project);
            setCurrentMembers([]);
            setIsMemberModalOpen(true);
            return;
        }

        // Lấy danh sách nhân viên từ tasks
        const members = project.tasks.flatMap((task) =>
            task.employee_codes.map((code) => employees.find((emp) => emp.code === code))
        );
        const uniqueMembers = Array.from(new Set(members.map((member) => member.code)))
            .map((code) => members.find((member) => member.code === code));

        setCurrentMembers(uniqueMembers);
        setCurrentProjectId(project.id);
        setIsMemberModalOpen(true);


        // setCurrentMembers(members.filter((member) => member !== undefined));
        // setCurrentProjectId(project.id);
        // setIsMemberModalOpen(true);

    };




    // const handleDivideTaskClick = (project) => {
    //     setCurrentProject(project);
    //     setIsTaskFormOpen(true);
    // };

    const handleTaskSave = (newTask) => {
        // Logic to save the new task
        setIsTaskFormOpen(false);
    };

    return (
        <div className="manager-projects">
            <div className="header d-flex align-items-center justify-content-between">
                <h2>Manager Projects</h2>
                <div className="filter-buttons">
                    <button>All</button>
                    <button>Started</button>
                    <button>Approval</button>
                    <button>Completed</button>
                </div>
            </div>
            <hr />
            <div className="row g-3 gy-5 py-3 row-deck">
                {projects.map((project, index) => (
                    <div key={index} className="col-xxl-4 col-xl-4 col-lg-4 col-md-6 col-sm-6">
                        <div className="card">
                            <div className="card-header">
                                <img src={`/path/to/icons/${project.icon}.png`} alt={`${project.name} icon`} className="project-icon" />
                                <p className="company-name">{project.company}</p>
                            </div>
                            <div className="card-body">
                                {/* <div className="d-flex align-items-center justify-content-between mt-5">
                                        <h5 className="project-position">{project.name}</h5>
                                        <div className="action-buttons">
                                            <button onClick={() => handleDivideTaskClick(project)}>Divide Task</button>
                                        </div>
                                    </div> */}

                                <div className="avatars">
                                    {Array(project.members).fill('').map((_, i) => (
                                        <img key={i} src="https://randomuser.me/api/portraits/men/9.jpg" alt="Avatar" className="avatar" />
                                    ))}
                                </div>

                                <div className="row g-2 pt-4">
                                    <div
                                        className="col-6 d-flex align-items-center attachment-link"
                                        onClick={() => handleAttachmentClick(project)}
                                    >
                                        <span className="logo">📎</span>
                                        <span className="info">{project.attachments} Attachments</span>
                                    </div>

                                    <div
                                        className="col-6 d-flex align-items-center"
                                        onClick={() => handleMemberClick(project)} // Sửa từ project.members thành project
                                    >
                                        <span className="logo">👥</span>
                                        <span className="info">{project.members} Members</span>
                                    </div>


                                </div>

                                <hr />

                                <div className="progress-section">
                                    <span className="progress-text">Progress</span>
                                    <div className="progress-bars">
                                        <div className="progress-bar bg-secondary" role="progressbar" style={{ width: `${project.progress}%` }} aria-valuenow={project.progress} aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                    <span className="days-left">{project.daysLeft} Days Left</span>
                                </div>
                            </div>
                        </div>
                    </div>
                ))}
            </div>

            {selectedProject && (
                <TaskModal
                    project={selectedProject}
                    employees={employees}
                    onClose={closeTaskModal}
                />
            )}

            {isMemberModalOpen && (
                <MemberModal
                    members={currentMembers}
                    employees={employees}
                    onClose={closeMemberModal}
                    onSave={handleSaveMembers}
                />
            )}


            {/* 

            {isTaskFormOpen && (
                <ManagerDevideTask
                    project={currentProject}

                    onClose={() => setIsTaskFormOpen(false)}
                    onSave={handleTaskSave}
                />
            )} */}
        </div>
    );
};

export default ManagerProject;
