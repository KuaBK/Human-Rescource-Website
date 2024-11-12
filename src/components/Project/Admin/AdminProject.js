import React, { useState } from 'react';
import './AdminProject.css';
import EditProject from './EditProject';

const AdminProject = () => {
    const initialProjects = [
        {
            id: 1,
            name: "UI/UX Design",
            description: "Designing the UI/UX for the new platform.",
            participants: 5,
            dept_id: 1,
        },
        {
            id: 2,
            name: "Website Design",
            description: "Building the frontend and backend of the new website.",
            participants: 4,
            dept_id: 2,
        },
        {
            id: 3,
            name: "App Development",
            description: "Developing the mobile application for the company.",
            participants: 6,
            dept_id: 3,
        },
    ];

    const [projects, setProjects] = useState(initialProjects);
    const [isEditOpen, setIsEditOpen] = useState(false);
    const [currentProject, setCurrentProject] = useState(null);
    const [isCreateOpen, setIsCreateOpen] = useState(false);
    const [newProject, setNewProject] = useState({
        name: '',
        description: '',
        participants: 0,
        dept_id: 0, // department ID to be selected
    });

    const handleEditClick = (project) => {
        setCurrentProject(project);
        setIsEditOpen(true);
    };

    const handleSave = (updatedProject) => {
        setProjects(projects.map(project =>
            project.id === updatedProject.id ? updatedProject : project
        ));
        setIsEditOpen(false);
    };

    const deleteProject = (projectId) => {
        setProjects((prevProjects) =>
            prevProjects.filter((project) => project.id !== projectId)
        );
    };

    const handleCreateProject = () => {
        if (!newProject.name || !newProject.description || newProject.dept_id === 0) {
            alert("Project name, description, and department are required.");
            return;
        }
        setProjects([...projects, { ...newProject, id: projects.length + 1 }]);
        setIsCreateOpen(false);
        setNewProject({
            name: '',
            description: '',
            participants: 0,
            dept_id: 0,
        });
    };

    return (
        <div className="projects">
            <div className="header d-flex align-items-center justify-content-between">
                <h2>Projects</h2>
                <button className="create-button" onClick={() => setIsCreateOpen(true)}>Create Project</button>
                <div className="filter-buttons">
                    <button>All</button>
                    <button>Started</button>
                    <button>Approval</button>
                    <button>Completed</button>
                </div>
            </div>
            <hr />
            <div className="row g-3 gy-5 py-3 row-deck">
                {projects.map((project) => (
                    <div key={project.id} className="col-xxl-4 col-xl-4 col-lg-4 col-md-6 col-sm-6">
                        <div className="card">
                            <div className="card-header">
                                <p className="company-name">Dept. {project.dept_id}</p>
                            </div>
                            <div className="card-body">
                                <div className="d-flex align-items-center justify-content-between mt-5">
                                    <h5 className="project-position">{project.name}</h5>
                                    <div className="action-buttons">
                                        <button onClick={() => handleEditClick(project)}>✏️ Edit</button>
                                        <button className="delete" onClick={() => deleteProject(project.id)}>🗑️</button>
                                    </div>
                                </div>

                                <p>{project.description}</p>

                                <div className="row g-2 pt-4">
                                    <div className="col-6 d-flex align-items-center">
                                        <span className="logo">👥</span>
                                        <span className="info">{project.participants} Participants</span>
                                    </div>
                                    <div className="col-6 d-flex align-items-center">
                                        <span className="logo">📁</span>
                                        <span className="info">Dept {project.dept_id}</span>
                                    </div>
                                </div>

                                <hr />
                            </div>
                        </div>
                    </div>
                ))}
            </div>

            {isEditOpen && (
                <EditProject
                    project={currentProject}
                    onClose={() => setIsEditOpen(false)}
                    onSave={handleSave}
                />
            )}

            {isCreateOpen && (
                <div className="create-project-modal">
                    <div className="create-project-content">
                        <h3>Tạo dự án mới</h3>
                        <label>
                            Tên dự án:
                            <input type="text" value={newProject.name} onChange={(e) => setNewProject({...newProject, name: e.target.value})} />
                        </label>
                        <label>
                            Mô tả dự án:
                            <textarea value={newProject.description} onChange={(e) => setNewProject({...newProject, description: e.target.value})} />
                        </label>
                        <label>
                            Người tham gia:
                            <input type="number" value={newProject.participants} onChange={(e) => setNewProject({...newProject, participants: parseInt(e.target.value)})} />
                        </label>
                        <label>
                            Mã số phòng ban đảm nhận:
                            <input type="number" value={newProject.dept_id} onChange={(e) => setNewProject({...newProject, dept_id: parseInt(e.target.value)})} />
                        </label>
                        <button onClick={handleCreateProject}>Save</button>
                        <button onClick={() => setIsCreateOpen(false)}>Cancel</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default AdminProject;
