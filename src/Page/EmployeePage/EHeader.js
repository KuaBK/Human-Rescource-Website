import React from 'react';
import './EHeader.scss';
import { NavLink } from 'react-router-dom';
import { IoNotificationsOutline } from "react-icons/io5";
import { FaRegMessage } from "react-icons/fa6";
import { Nav, Navbar, NavDropdown, Container } from 'react-bootstrap';

const EHeader = () => {
  return (
    <Navbar className="custom-navbar" expand="lg">
      <Container>
        <NavLink to="/" className="navbar-brand">BK-Manarate</NavLink>
        <Navbar.Toggle aria-controls="navbar-nav" />
        <Navbar.Collapse id="navbar-nav">
          <Nav className="me-auto">
            <NavLink to="/" className="nav-link">Trang chủ</NavLink>
          </Nav>
          <Nav className="header-icons">
            <IoNotificationsOutline className="icon" />
            <FaRegMessage className="icon" />
            <NavDropdown title="Profile" id="profile-dropdown">
              {/* Change href to 'to' and use 'NavLink' for navigation */}
              <NavDropdown.Item as={NavLink} to="/infor">Hồ sơ</NavDropdown.Item>
              <NavDropdown.Item as={NavLink} to="/logout">Đăng xuất</NavDropdown.Item>
            </NavDropdown>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default EHeader;
