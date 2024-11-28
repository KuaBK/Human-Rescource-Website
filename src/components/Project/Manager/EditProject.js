// // EditTask.js
// import React, { useState } from 'react';
// import './EditProject.css';

// const EditProject = ({ project, onClose, onSave }) => {
//     const [name, setName] = useState(project.name);
//     const [company, setCompany] = useState(project.company);
//     const [startDate, setStartDate] = useState('');
//     const [endDate, setEndDate] = useState('');
//     const [assignedPerson, setAssignedPerson] = useState('');

//     const handleSave = () => {
//         const updatedProject = {
//             ...project,
//             name,
//             company,
//             startDate,
//             endDate,
//             assignedPerson,
//         };
//         onSave(updatedProject);
//         onClose();
//     };

//     return (
//         <div className="edit-task-modal">
//             <div className="edit-task-content">
//                 <h3></h3>
//                 <label>
//                     Project Name:
//                     <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
//                 </label>
//                 <label>
//                     Company:
//                     <input type="text" value={company} onChange={(e) => setCompany(e.target.value)} />
//                 </label>
//                 <label>
//                     Start Date:
//                     <input type="date" value={startDate} onChange={(e) => setStartDate(e.target.value)} />
//                 </label>
//                 <label>
//                     End Date:
//                     <input type="date" value={endDate} onChange={(e) => setEndDate(e.target.value)} />
//                 </label>
//                 <label>
//                     Assign Person (ID):
//                     <input type="text" value={assignedPerson} onChange={(e) => setAssignedPerson(e.target.value)} />
//                 </label>
//                 <button onClick={handleSave}>Save</button>
//                 <button onClick={onClose}>Cancel</button>
//             </div>
//         </div>
//     );
// };

// export default EditProject;

import React, { useState } from 'react';
import './EditProject.scss';

const EditProject = ({ onClose, onSave }) => {
    const [project, setProject] = useState({
        name: '',
        description: '',
        members: [],
        tasks: [],
    });

    const [task, setTask] = useState({ taskName: '', deadline: '', description: '' });
    const [employeeCode, setEmployeeCode] = useState('');
    const [errors, setErrors] = useState({
        projectName: '',
        taskName: '',
        employeeCode: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setProject((prev) => ({ ...prev, [name]: value }));
    };

    const handleTaskChange = (e) => {
        const { name, value } = e.target;
        setTask((prev) => ({ ...prev, [name]: value }));
    };

    const handleAddTask = () => {
        // Validate task inputs
        if (!task.taskName || !task.deadline || !task.description) {
            if (!task.taskName) {
                alert('Yêu cầu nhập tên nhiệm vụ');
            }
            if (!task.deadline) {
                alert('Yêu cầu nhập thời hạn');
            }
            if (!task.description) {
                alert('Yêu cầu nhập mô tả');
            }
            return;
        }

        // Reset errors and add the task
        setErrors((prev) => ({
            ...prev,
            taskName: '',
            taskDeadline: '',
            taskDescription: '',
        }));
        setProject((prev) => ({
            ...prev,
            tasks: [...prev.tasks, task],
        }));
        setTask({ taskName: '', deadline: '', description: '' });
    };

    const handleRemoveTask = (index) => {
        setProject((prev) => ({
            ...prev,
            tasks: prev.tasks.filter((_, i) => i !== index),
        }));
    };

    const handleAddMember = () => {
        if (!employeeCode) {
            setErrors((prev) => ({
                ...prev,
                employeeCode: 'Employee code is required',
            }));
            return;
        }
        setErrors((prev) => ({ ...prev, employeeCode: '' }));
        setProject((prev) => ({
            ...prev,
            members: [...prev.members, employeeCode],
        }));
        setEmployeeCode('');
    };

    const handleSave = () => {
        // Validate project name
        if (!project.name) {
            setErrors((prev) => ({
                ...prev,
                projectName: 'Project name is required',
            }));
            return;
        }
        setErrors((prev) => ({ ...prev, projectName: '' }));
        onSave(project);
    };

    return (
        <div className="modal-overlay" onClick={onClose}>
            <div className="edit-modal" onClick={(e) => e.stopPropagation()}>
                <div className="modal-content">
                    <div className='modal-header'>
                        <h2>Tạo dự án</h2>
                        <button type="button" className="btn-close btn-primary" aria-label="Close" onClick={onClose}></button>

                    </div>
                    <form className="form-container">
                        <label>
                            Tên dự án:
                            <input
                                type="text"
                                name="name"
                                placeholder="Nhập tên dự án"
                                value={project.name}
                                onChange={handleChange}
                            />
                            {errors.projectName && <p className="error">{errors.projectName}</p>}
                        </label>
                        <label>
                            Mô tả dự án:
                            <textarea
                                name="description"
                                placeholder="Nhập mô tả dự án"
                                value={project.description}
                                onChange={handleChange}
                            />
                        </label>

                        <label>
                            Thêm nhân viên (mã nhân viên):
                            <input
                                type="text"
                                placeholder="Nhập mã nhân viên"
                                value={employeeCode}
                                onChange={(e) => setEmployeeCode(e.target.value)}
                                className='employee-code-input'
                            />
                            <button
                                type="button"
                                onClick={handleAddMember}
                                className="btn btn-primary add-member-btn"
                            >
                                Thêm nhân viên
                            </button>
                            {errors.employeeCode && <p className="error">{errors.employeeCode}</p>}
                        </label>
                        <div className="member-list">
                            {project.members.map((member, index) => (
                                <p key={index}>Employee Code: {member}</p>
                            ))}
                        </div>

                        <label>
                            <div className="task-inputs">
                                <div className="input-group">
                                    <label htmlFor="taskName">Tên nhiệm vụ</label>
                                    <input
                                        type="text"
                                        id="taskName"
                                        name="taskName"
                                        placeholder="Tên nhiệm vụ"
                                        value={task.taskName}
                                        onChange={handleTaskChange}
                                    />
                                </div>
                                <div className="input-group">
                                    <label htmlFor="deadline">Hạn chót</label>
                                    <input
                                        type="date"
                                        id="deadline"
                                        name="deadline"
                                        placeholder="Hạn chót"
                                        value={task.deadline}
                                        onChange={handleTaskChange}
                                    />
                                </div>
                                <div className="input-group">
                                    <label htmlFor="description">Mô tả nhiệm vụ</label>
                                    <input
                                        name="description"
                                        id="description"
                                        placeholder="Mô tả nhiệm vụ"
                                        value={task.description}
                                        onChange={handleTaskChange}
                                    />
                                </div>
                                <button
                                    type="button"
                                    onClick={handleAddTask}
                                    className="btn btn-primary"
                                >
                                    Thêm nhiệm vụ
                                </button>
                            </div>
                            {errors.taskName && <p className="error">{errors.taskName}</p>}
                            {errors.taskDeadline && <p className="error">{errors.taskDeadline}</p>}
                            {errors.taskDescription && <p className="error">{errors.taskDescription}</p>}
                        </label>

                        <div className="task-list">
                            <h2>Danh sách Task</h2>
                            {project.tasks.map((task, index) => (
                                <div key={index} className="task-item">
                                    <div className="task-detail">
                                        <span className="task-label">Task Name:</span>
                                        <span className="task-value">{task.taskName}</span>
                                    </div>
                                    <div className="task-detail">
                                        <span className="task-label">Deadline:</span>
                                        <span className="task-value">{task.deadline}</span>
                                    </div>
                                    <div className="task-detail">
                                        <span className="task-label">Description:</span>
                                        <span className="task-value">{task.description || "No description provided"}</span>
                                    </div>
                                    <button
                                        type="button"
                                        onClick={() => handleRemoveTask(index)}
                                        className="btn btn-danger"
                                    >
                                        Remove
                                    </button>
                                </div>
                            ))}
                        </div>

                    </form>
                    <div className="action-buttons">
                        <button onClick={handleSave} className="btn btn-primary btn-success">
                            Save
                        </button>
                        {/* <button onClick={onClose} className="btn btn-secondary">
                            Cancel
                        </button> */}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default EditProject;
