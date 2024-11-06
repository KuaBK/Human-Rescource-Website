import React, { useState } from 'react';
import './Project.css';
import EditTask from './EditTask';


const Project = () => {
    const initialProjects = [
        {
            id: 1,
            name: "UI/UX Design",
            company: "Social Geek Made",
            attachments: 5,
            members: 5,
            duration: "4 Month",
            comments: 10,
            daysLeft: 35,
            progress: 70,
            icon: "paint-brush",
        },
        {
            id: 2,
            name: "Website Design",
            company: "Practice to Perfect",
            attachments: 4,
            members: 4,
            duration: "1 Month",
            comments: 3,
            daysLeft: 15,
            progress: 80,
            icon: "pen-tool",
        },
        {
            id: 3,
            name: "App Development",
            company: "Rhinestone",
            attachments: 7,
            members: 6,
            duration: "2 Month",
            comments: 5,
            daysLeft: 25,
            progress: 60,
            icon: "smartphone",
        },
    ];

    const [projects, setProjects] = useState(initialProjects);
    const [isEditOpen, setIsEditOpen] = useState(false);
    const [currentProject, setCurrentProject] = useState(null);

    const handleEditClick = (project) => {
        setCurrentProject(project);
        setIsEditOpen(true);
    };

    const handleSave = (updatedProject) => {
        // Cập nhật dự án trong danh sách với thông tin mới
        setIsEditOpen(false);
    };

    const handleAddEmployee = (newEmployee) => {
        setEmployees((prevEmployees) => [...prevEmployees, newEmployee]);
        setShowAddModal(false);
    };


    const deleteProject = (projectId) => {
        setProjects((prevProjects) =>
            prevProjects.filter((project) => project.id !== projectId)
        );
    };

    return (
        <div className="projects">
            <div className="header d-flex align-items-center justify-content-between">
                <h2>Projects</h2>
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
                                <div className="d-flex align-items-center justify-content-between mt-5">
                                    <h5 className="project-position">{project.name}</h5>
                                    <div className="action-buttons">
                                        <button onClick={() => handleEditClick(project)}>✏️ Edit</button>
                                        <button className="delete" onClick={() => deleteProject(project.id)}>🗑️</button>
                                    </div>
                                </div>

                                <div className="avatars">
                                    {Array(project.members).fill('').map((_, i) => (
                                        <img key={i} src="https://randomuser.me/api/portraits/men/9.jpg" alt="Avatar" className="avatar" />
                                    ))}
                                </div>

                                <div className="row g-2 pt-4">
                                    <div className="col-6 d-flex align-items-center">
                                        <span className="logo">📎</span>
                                        <span className="info">{project.attachments} Attachments</span>
                                    </div>
                                    <div className="col-6 d-flex align-items-center">
                                        <span className="logo">👥</span>
                                        <span className="info">{project.members} Members</span>
                                    </div>
                                    <div className="col-6 d-flex align-items-center">
                                        <span className="logo">⏳</span>
                                        <span className="info">{project.duration}</span>
                                    </div>
                                    <div className="col-6 d-flex align-items-center">
                                        <span className="logo">💬</span>
                                        <span className="info">{project.comments} Comments</span>
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

            <Modal show={showAddModal} onHide={() => setShowAddModal(false)} centered className="custom-modal modal-lg">
                <Modal.Header closeButton>
                    <Modal.Title>Edit Project</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <AddEmployee onAddEmployee={handleAddEmployee} />
                </Modal.Body>
            </Modal>

            {/* {isEditOpen && (
                <EditTask
                    project={currentProject}
                    onClose={() => setIsEditOpen(false)}
                    onSave={handleSave}
                />
            )} */}
        </div>
    );
};

export default Project;
