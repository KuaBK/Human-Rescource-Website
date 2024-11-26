import React, { useCallback, useEffect, useState } from 'react';
import './SubmitTask.css';
import { useSelector } from 'react-redux';
import { getTaskByEmployeeCode } from '../../services/apiService';

const SubmitTask = () => {

    const {personnel} = useSelector((state) => state);
    const [tasks, setTasks] = useState([]);

    const fetchTask = useCallback(async () => {
        if (personnel?.data){
            try{
                const response = await getTaskByEmployeeCode(personnel.data.code);

                if(response?.data?.data && Array.isArray(response.data.data) && response.data.data.length > 0){
                    const transformedTasks = response.data.data.map(task => ({
                        id: task.id,
                        // name: task.name || "N/A", // Provide default values if necessary
                        description: task.description || "No description",
                        due: task.due || "No due date",
                        status: task.status || "PENDING",             
                        projectId: task.projectId || 0,
                        files: [],
                        isUploaded: false, // Default upload status
                    }));
                    setTasks(transformedTasks);
                }
            } catch (error) {
                console.error("Error fetching tasks:", error);
            }
        }
    }, [personnel?.data]) 

    useEffect(() => {
        fetchTask();
    }, [fetchTask]);

    // id
    // name
    // description
    // due
    // status
    // projectId
    // departmentId
    // files: []
    // isUploaded: false

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
                {tasks && tasks.length > 0 ? (
                    tasks.map((task) => (
                        <div
                            key={task.id} 
                            className={`task-card ${task.status === 'COMPLETED' ? 'task-completed'
                                : task.status === 'OVERDUE' ? 'task-overdue' 
                                : 'task-cancelled'}`}
                        >
                            <div className="task-card-header">
                                <p className="task-dept">Phòng ban. 1</p>
                            </div>

                            <div className="task-card-body">
                                <h3 className="task-name">{task.description}</h3>
                                {/* <p className="task-description">{task.description}</p> */}
                                <p><strong>Ngày hết hạn:</strong> {task.due}</p>
                                <p><strong>Trạng thái:</strong> {task.status}</p>
                                <p><strong>Mã số dự án:</strong> {task.projectId}</p>

                                {/* <div className="task-info">
                                    <div className="info-item">
                                        <span className="icon">👥</span>
                                        <span>{task.participants} Participants</span>
                                    </div>

                                    <div className="info-item">
                                        <span className="icon">📁</span>
                                        <span>Thuộc phòng ban {task.dept_id}</span>
                                    </div>
                                </div> */}

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
                                {task && task.files && task.files.length > 0 && (
                                    <div className="uploaded-files">
                                        {task.files.map((file, index) => (
                                            <p key={index} className="uploaded-file">📄 {file}</p>
                                        ))}
                                    </div>
                                )}
                            </div>
                        </div>
                            
                        </div>
                    ))) 
                : 
                (<p>Không có công việc nào để hiển thị.</p>)
                }
            </div>
        </div>
    );
};

export default SubmitTask;
