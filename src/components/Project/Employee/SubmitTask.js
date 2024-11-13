import React, { useState } from 'react';
import './SubmitTask.css';

const SubmitTask = () => {
    const initialTasks = [
        {
            id: 1,
            name: "UI/UX Design",
            description: "Designing the UI/UX for the new platform.",
            due: "2024-12-15",
            status: "COMPLETED",
            proj_id: 101,
            participants: 5,
            dept_id: 1,
            files: [], // Array to store multiple files
            isUploaded: false // Track upload status
        },
        {
            id: 2,
            name: "Website Design",
            description: "Building the frontend and backend of the new website.",
            due: "2024-11-30",
            status: "OVERDUE",
            proj_id: 102,
            participants: 4,
            dept_id: 2,
            files: [],
            isUploaded: false
        },
        {
            id: 3,
            name: "App Development",
            description: "Developing the mobile application for the company.",
            due: "2024-12-20",
            status: "CANCEL",
            proj_id: 103,
            participants: 6,
            dept_id: 3,
            files: [],
            isUploaded: false
        },
    ];

    const [tasks, setTasks] = useState(initialTasks);

    const handleFileUpload = (event, taskId) => {
        const files = Array.from(event.target.files); // Convert FileList to Array
        setTasks(tasks.map(task =>
            task.id === taskId
                ? { ...task, files: [...task.files, ...files.map(file => file.name)], isUploaded: true }
                : task
        ));
        alert(`Files uploaded successfully for task ID: ${taskId}`);
        // Backend upload logic here
    };

    return (
        <div className="tasks-container">
            <h2 className="tasks-title">Công việc của tôi</h2>
            <div className="tasks-grid">
                {tasks.map((task) => (
                    <div key={task.id} className={`task-card ${task.status === 'COMPLETED' ? 'task-completed' 
                    : task.status === 'OVERDUE' ? 'task-overdue' : 'task-cancelled'}`}>
                        <div className="task-card-header">
                            <p className="task-dept">Phòng ban. {task.dept_id}</p>
                        </div>
                        <div className="task-card-body">
                            <h3 className="task-name">{task.name}</h3>
                            <p className="task-description">{task.description}</p>
                            <p><strong>Ngày hết hạn:</strong> {task.due}</p>
                            <p><strong>Trạng thái:</strong> {task.status}</p>
                            <p><strong>Mã số dự án:</strong> {task.proj_id}</p>
                            <div className="task-info">
                                <div className="info-item">
                                    <span className="icon">👥</span>
                                    <span>{task.participants} Participants</span>
                                </div>
                                <div className="info-item">
                                    <span className="icon">📁</span>
                                    <span>Thuộc phòng ban {task.dept_id}</span>
                                </div>
                            </div>
                            <div className="file-upload">
                                <input
                                    type="file"
                                    accept=".pdf, .doc, .docx, .jpg, .png"
                                    multiple
                                    onChange={(e) => handleFileUpload(e, task.id)}
                                    className="file-input"
                                    id={`upload-${task.id}`}
                                />
                                <label
                                    htmlFor={`upload-${task.id}`}
                                    className={`upload-button ${task.isUploaded ? 'done-button' : ''}`}
                                >
                                    {task.isUploaded ? "✅ Done" : "📂 Upload Files"}
                                </label>
                                {task.files.length > 0 && (
                                    <div className="uploaded-files">
                                        {task.files.map((file, index) => (
                                            <p key={index} className="uploaded-file">📄 {file}</p>
                                        ))}
                                    </div>
                                )}
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default SubmitTask;
