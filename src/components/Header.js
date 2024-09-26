import React from 'react';
import './Header.scss';

import { Nav, Navbar, NavDropdown, Container} from 'react-bootstrap';
import { NavLink } from 'react-router-dom';

import { IoNotificationsOutline } from "react-icons/io5";
import { FaRegMessage } from "react-icons/fa6";

const Header = () => {
    return (
      <Navbar className="custom-navbar" expand="lg">
          <Container>
            <NavLink to="/" className='navbar-brand'>BK-Manarate</NavLink>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="me-auto">
                  <NavLink to="/" className='nav-link'>Trang chủ</NavLink>
                  <NavLink to="/list" className='nav-link'>Danh sách</NavLink>
                  <NavLink to="/attendance" className='nav-link'>Chấm công</NavLink>
                  <NavLink to="/statistic" className='nav-link'>Thống kê</NavLink>
              </Nav>

              <Nav>
                <IoNotificationsOutline />
                <FaRegMessage />
                <NavDropdown title="Dropdown" id="basic-nav-dropdown">
                  <NavDropdown.Item>Hồ sơ</NavDropdown.Item>
                  <NavDropdown.Item>Đăng xuất</NavDropdown.Item>         
                </NavDropdown>
              </Nav>
            </Navbar.Collapse>
          </Container>
      </Navbar>
    );

  // return (
  //   <header className="header">
  //     <div className="logo">BK Manarate</div>
  //     <nav className="nav-menu">
  //       <a href="#dashboard">Dashboard</a>
  //       <a href="#notice">Notice</a>
  //       <a href="#leave">Apply for leave</a>
  //       <a href="#assets">Access/Assets</a>
  //       <a href="#salary">Salary Slip</a>
  //     </nav>
  //     <div className="header-icons">
  //       <span className="icon-notification">🔔</span>
  //       <span className="icon-settings">⚙️</span>
  //       {/* <div className="profile-icon">AR</div> */}
  //       <NavDropdown title="Dropdown" id="basic-nav-dropdown">
  //         <NavDropdown.Item href="#action/3.1">Hồ sơ</NavDropdown.Item>
  //         <NavDropdown.Item href="#action/3.2">Đăng xuất</NavDropdown.Item>         
  //       </NavDropdown>
  //     </div>
  //   </header>
  // );
};

export default Header;
