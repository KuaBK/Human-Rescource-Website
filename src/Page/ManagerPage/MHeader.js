
import React, { useEffect, useState } from "react";
import "./MHeader.scss";
import { Navbar, NavDropdown, Container } from "react-bootstrap";
import { NavLink, useNavigate } from "react-router-dom";

const MHeader = () => {
  const [name, setName] = useState("Loading...");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleLogout = () => {
    navigate("/");
  };



  useEffect(() => {
    const fetchManagerName = async () => {
      try {
        const token = localStorage.getItem("token");
        const accountId = localStorage.getItem("accountId");


        if (!token || !accountId) {
          setError("Authentication token or account ID not found");
          return;
        }

        const response = await fetch(
          `http://localhost:8080/api/managers/account?id=${accountId}`,
          {
            method: "GET",
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );

        if (!response.ok) {
          throw new Error("Failed to fetch manager data");
        }

        const data = await response.json();
        setName(`${data.lastName} ${data.firstName}`);

      } catch (err) {
        console.error("Error fetching manager name:", err);
        setError(err.message);
      }
    };


  }, []);

  return (
    <Navbar className="custom-navbar" expand="lg">
      <Container>
        <NavLink to="/" className="navbar-brand">
          My App
        </NavLink>

        {/* User Dropdown */}
        <NavDropdown
          title={
            <>
              <span className="profile-name">
                {name}
              </span>
            </>
          }
          id="profile-dropdown"
        >
          <NavDropdown.Item>Hồ sơ</NavDropdown.Item>
          <NavDropdown.Item onClick={handleLogout}>Đăng xuất</NavDropdown.Item>
        </NavDropdown>

      </Container >
    </Navbar >
  );
};

export default MHeader;