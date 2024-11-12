import React, { useState } from 'react';
import './DevideTask.css';

const DevideTask = () => {
  const [showForm, setShowForm] = useState(false);
  const [selectedProject, setSelectedProject] = useState(null);
  const [taskDetails, setTaskDetails] = useState({
    description: '',
    due: '',
    status: 'In Progress',
    empId: '',
  });

  const projects = [
    { id: 1, name: 'Project Alpha', description: 'Description for Project Alpha' },
    { id: 2, name: 'Project Beta', description: 'Description for Project Beta' },
    { id: 3, name: 'Project Gamma', description: 'Description for Project Gamma' },
    { id: 4, name: 'Project Delta', description: 'Description for Project Delta' },
  ];

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setTaskDetails((prevDetails) => ({ ...prevDetails, [name]: value }));
  };

  const handleAddTask = () => {
    // Logic to add task
    setShowForm(false);  // Hide form after adding task
    setTaskDetails({
      description: '',
      due: '',
      status: 'In Progress',
      empId: '',
    });
  };

  const openForm = (project) => {
    setSelectedProject(project);
    setShowForm(true);
  };

  return (
    <div className="devide-task-page">
      <h1 className="header">Projects</h1>
      <div className="projects-grid">
        {projects.map((project) => (
          <div key={project.id} className="project-box">
            <h2>{project.name}</h2>
            <p>{project.description}</p>
            <button className="divide-task-button" onClick={() => openForm(project)}>
              Divide Task
            </button>
          </div>
        ))}
      </div>

      {showForm && (
        <div className="task-form-overlay">
          <div className="task-form">
            <h2>New Task for {selectedProject.name}</h2>
            <label>Description</label>
            <textarea
              name="description"
              value={taskDetails.description}
              onChange={handleInputChange}
              className="input description"
            />

            <label>Due Date</label>
            <input
              type="date"
              name="due"
              value={taskDetails.due}
              onChange={handleInputChange}
              className="input"
            />

            <label>Status</label>
            <select
              name="status"
              value={taskDetails.status}
              onChange={handleInputChange}
              className="input"
            >
              <option value="In Progress">In Progress</option>
              <option value="Completed">Completed</option>
              <option value="Overdue">Overdue</option>
            </select>

            <label>Assign to Employee</label>
            <input
              type="text"
              name="empId"
              value={taskDetails.empId}
              onChange={handleInputChange}
              className="input"
              placeholder="Enter Employee ID"
            />

            <button onClick={handleAddTask} className="add-button">
              Add Task
            </button>
            <button onClick={() => setShowForm(false)} className="cancel-button">
              Cancel
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default DevideTask;
