import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './TaskModal.scss';

const TaskModal = ({ projectId, employees, onClose }) => {
    const [tasks, setTasks] = useState([]);
    const [loading, setLoading] = useState(true);
    const [editingIndex, setEditingIndex] = useState(null);

    useEffect(() => {
        const fetchTasks = async () => {
            try {
                setLoading(true);
                const response = await fetch(`http://localhost:8080/api/tasks/project?id=${projectId}`);
                const data = await response.json();
                if (data.code === 1000) {
                    setTasks([data.result]);
                } else {
                    console.error(data.message);
                }
            } catch (error) {
                console.error('Error fetching tasks:', error);
            } finally {
                setLoading(false);
            }
        };

        if (projectId) {
            fetchTasks();
        }
    }, [projectId]);

    const toggleEditMode = (index) => {
        setEditingIndex(editingIndex === index ? null : index);
    };

    const handleInputChange = (index, field, value) => {
        const updatedTasks = [...tasks];
        updatedTasks[index][field] = value;
        setTasks(updatedTasks);
    };

    const getAvatarUrl = (index) => `https://randomuser.me/api/portraits/men/${index % 100}.jpg`;

    return (
        <div className="modal fade show" style={{ display: 'block' }} tabIndex="-1">
            <div className="modal-dialog modal-lg task-modal">
                <div className="modal-content">
                    {/* Header */}
                    <div className="modal-header">
                        <h5 className="modal-title">Nhiệm vụ cho dự án</h5>
                        <button type="button" className="btn-close" onClick={onClose}></button>
                    </div>

                    {/* Body */}
                    <div className="modal-body" style={{ maxHeight: '400px', overflowY: 'auto' }}>
                        {loading ? (
                            <p>Đang tải dữ liệu...</p>
                        ) : tasks.length > 0 ? (
                            <div className="task-list">
                                {tasks.map((task, index) => (
                                    <div key={index} className="task-item border rounded p-3 mb-3">
                                        {/* Header của mỗi task */}
                                        <div className="d-flex justify-content-between align-items-center">
                                            <h6>Tên nhiệm vụ: {task.title}</h6>
                                            <div>
                                                <button
                                                    className="btn btn-sm btn-primary me-2"
                                                    onClick={() => toggleEditMode(index)}
                                                >
                                                    {editingIndex === index ? 'Lưu' : 'Chỉnh sửa'}
                                                </button>
                                            </div>
                                        </div>

                                        {/* Phần chính */}
                                        <div className="d-flex align-items-start mb-2">
                                            {/* Avatar nhân viên */}
                                            <img
                                                src={getAvatarUrl(index)}
                                                alt="avatar"
                                                className="rounded-circle me-3"
                                                width="50"
                                                height="50"
                                            />
                                            <div>
                                                <p>
                                                    <strong>Nhân viên thực hiện:</strong>{' '}
                                                    {editingIndex === index ? (
                                                        <select
                                                            className="form-select"
                                                            value={task.employeeName}
                                                            onChange={(e) =>
                                                                handleInputChange(index, 'employeeName', e.target.value)
                                                            }
                                                        >
                                                            {employees.map((employee) => (
                                                                <option
                                                                    key={employee.id}
                                                                    value={employee.name}
                                                                >
                                                                    {employee.name}
                                                                </option>
                                                            ))}
                                                        </select>
                                                    ) : (
                                                        task.employeeName
                                                    )}
                                                </p>
                                                <p>
                                                    <strong>Mô tả:</strong>{' '}
                                                    {editingIndex === index ? (
                                                        <input
                                                            type="text"
                                                            className="form-control"
                                                            value={task.description}
                                                            onChange={(e) =>
                                                                handleInputChange(index, 'description', e.target.value)
                                                            }
                                                        />
                                                    ) : (
                                                        task.description
                                                    )}
                                                </p>
                                            </div>
                                        </div>

                                        {/* Phần bổ sung */}
                                        <div className="row">
                                            <div className="col-md-6">
                                                <strong>Hạn nộp:</strong>{' '}
                                                {editingIndex === index ? (
                                                    <input
                                                        type="date"
                                                        className="form-control"
                                                        value={task.due}
                                                        onChange={(e) =>
                                                            handleInputChange(index, 'due', e.target.value)
                                                        }
                                                    />
                                                ) : (
                                                    task.due
                                                )}
                                            </div>
                                            <div className="col-md-6">
                                                <strong>Trạng thái:</strong>{' '}
                                                {editingIndex === index ? (
                                                    <select
                                                        className="form-select"
                                                        value={task.status}
                                                        onChange={(e) =>
                                                            handleInputChange(index, 'status', e.target.value)
                                                        }
                                                    >
                                                        <option value="COMPLETED">Hoàn thành</option>
                                                        <option value="IN_PROGRESS">Đang làm</option>
                                                        <option value="NOT_STARTED">Chưa bắt đầu</option>
                                                    </select>
                                                ) : (
                                                    task.status
                                                )}
                                            </div>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        ) : (
                            <p>Không có nhiệm vụ nào.</p>
                        )}
                    </div>

                    {/* Footer */}
                    <div className="modal-footer">
                        <button type="button" className="btn btn-primary" onClick={onClose}>
                            Đóng
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default TaskModal;
