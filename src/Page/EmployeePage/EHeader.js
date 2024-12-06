import React, { useEffect, useState } from "react";
import "./EHeader.scss";
import { Navbar, NavDropdown, Container } from "react-bootstrap";
import { NavLink } from "react-router-dom";

const EHeader = ({ personnel }) => {
  const [name, setName] = useState("Loading...");
  const [error, setError] = useState("");
  const [showNotifications, setShowNotifications] = useState(false);

  const notifications = [
    {
      id: 1,
      avatar: "assets/images/xs/avatar1.jpg",
      name: "Dylan Hunter",
      time: "2MIN",
      content: "Added 2021-02-19 my-Task ui/ux Design",
      badge: "Review",
    },
    // Add other notifications here...
  ];

  const toggleNotifications = () => {
    setShowNotifications(!showNotifications);
  };

  useEffect(() => {
    const fetchEmployeeName = async () => {
      try {
        const token = localStorage.getItem("token");
        const accountId = localStorage.getItem("accountId");

        if (!token || !accountId) {
          setError("Authentication token or account ID not found");
          return;
        }

        const response = await fetch(
          `http://localhost:8080/api/employee/account?id=${accountId}`,
          {
            method: "GET",
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );

        if (!response.ok) {
          throw new Error("Failed to fetch employee data");
        }

        const data = await response.json();
        setName(`${data.lastName} ${data.firstName}`);
      } catch (err) {
        console.error("Error fetching employee name:", err);
        setError(err.message);
      }
    };

    fetchEmployeeName();
  }, []);

  return (
    <Navbar className="custom-navbar" expand="lg">
      <Container>
        <NavLink to="/" className="navbar-brand">
          My App
        </NavLink>

        <div className="header-icons position-relative">
          {/* Notification Icon */}
          <div className="bell position-relative" onClick={toggleNotifications}>
            <span className="material-icons layer-1">notifications_active</span>
            <span className="material-icons layer-2">notifications</span>
            <span className="material-icons layer-3">notifications</span>
            <span className="badge bg-danger position-absolute top-0 start-100 translate-middle">
              {notifications.length}
            </span>
          </div>

          {/* Notification Dropdown */}
          {showNotifications && (
            <div
              id="NotificationsDiv"
              className="dropdown-menu rounded-lg shadow border-0 dropdown-animation dropdown-menu-sm-end p-0 m-0"
              style={{
                display: "block",
                position: "absolute",
                top: "40px",
                right: "0",
                zIndex: 10,
                maxHeight: "300px",
                overflowY: "auto",
              }}
            >
              <div className="card border-0 w380">
                <div className="card-header border-0 p-3">
                  <h5 className="mb-0 font-weight-light d-flex justify-content-between">
                    <span>Notifications</span>
                    <span className="badge text-white">{notifications.length}</span>
                  </h5>
                </div>
                <div className="tab-content card-body">
                  <div className="tab-pane fade show active">
                    <ul className="list-unstyled list mb-0">
                      {notifications.map((notification) => (
                        <li key={notification.id} className="py-2 mb-1 border-bottom">
                          <div className="d-flex notification-item">
                            {notification.avatar ? (
                              <img
                                className="avatar rounded-circle"
                                src={notification.avatar}
                                alt=""
                              />
                            ) : (
                              <div className="avatar rounded-circle no-thumbnail">
                                {notification.name.charAt(0)}
                              </div>
                            )}
                            <div className="flex-fill ms-2">
                              <p className="d-flex justify-content-between mb-1">
                                <span className="notification-name">{notification.name}</span>
                                <small className="notification-time">{notification.time}</small>
                              </p>
                              <span className="notification-content">
                                {notification.content}{" "}
                                {notification.badge && (
                                  <span className="badge bg-success">{notification.badge}</span>
                                )}
                              </span>
                            </div>
                          </div>
                        </li>
                      ))}
                    </ul>
                  </div>
                </div>
                <a
                  className="card-footer text-center border-top-0 text-primary"
                  href="#"
                  style={{ textDecoration: "none" }}
                >
                  View all notifications
                </a>
              </div>
            </div>
          )}

          {/* User Dropdown */}
          <NavDropdown
            title={
              <>
                <span className="profile-name">
                  {personnel?.firstName ? personnel.firstName : "Loading..."}
                </span>
              </>
            }
            id="profile-dropdown"
          >
            <NavDropdown.Item>Hồ sơ</NavDropdown.Item>
            <NavDropdown.Item>Đăng xuất</NavDropdown.Item>
          </NavDropdown>
        </div>
      </Container>
    </Navbar>
  );
};

export default EHeader;
