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
    
};

export default Header;
