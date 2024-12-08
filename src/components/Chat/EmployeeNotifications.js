import React, { useState } from "react";
import "./EmployeeNotifications.scss";

const notifications = [
  {
    id: 1,
    title: "Task Update",
    content: "Your task has been updated by the manager.",
    sender: "Quang",
    createdAt: "10:10 AM",
    read: false,
  },
  {
    id: 2,
    title: "Meeting Reminder",
    content: "Don’t forget about the team meeting at 3 PM today.",
    sender: "Quynh",
    createdAt: "11:00 AM",
    read: true,
  },
  {
    id: 3,
    title: "Project Deadline",
    content: "The project deadline has been extended to next week.",
    sender: "Minh",
    createdAt: "1:30 PM",
    read: false,
  },
];

const EmployeeNotifications = () => {
  const [notificationList, setNotificationList] = useState(notifications);

  // Mark a notification as read
  const markAsRead = (id) => {
    setNotificationList((prev) =>
      prev.map((notification) =>
        notification.id === id ? { ...notification, read: true } : notification
      )
    );
  };

  return (
    <div className="notifications-container">
      <h2>Thông báo</h2>
      <div className="notifications-grid">
        {notificationList.map((notification) => (
          <div
            key={notification.id}
            className={`notification-card ${
              notification.read ? "read" : "unread"
            }`}
          >
            <div className="notification-header">
              <h3 className="notification-title">{notification.title}</h3>
              {!notification.read && (
                <span className="new-badge">Mới</span>
              )}
            </div>
            <p className="notification-content">{notification.content}</p>
            <div className="notification-footer">
              <span className="sender-name">Người gửi: {notification.sender}</span>
              <span className="created-at">{notification.createdAt}</span>
              {!notification.read && (
                <button
                  className="mark-read-button"
                  onClick={() => markAsRead(notification.id)}
                >
                  Đánh dấu đã đọc
                </button>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default EmployeeNotifications;
