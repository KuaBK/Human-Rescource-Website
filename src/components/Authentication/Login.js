import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import './Login.css'; // Import the external CSS
import { postLogin } from "../services/apiService";

function Login() {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const navigate = useNavigate();

  const submit = async (e) => {
      e.preventDefault();
      try{
        const response = await postLogin(username, password);
        if(response && response.data?.ec){
          navigate("/login/admin");
        }        
      } catch(e) {
        console.log(e);
      }
  }
  

  return (
    <div className="login-container">
      <table className="login-table">
        <thead>
          <tr>
            <td id="subTitle" className="login-title">
              BK Manager
            </td>
          </tr>
        </thead>
        <tbody className="login-body">
          <tr>
            <td>
              <table className="login-content">
                <tbody> {/* Add this wrapper */}
                  <tr>
                    <td id="pageContent" className="login-box">
                      <h1 className="login-heading">Đăng Nhập</h1>
                      {errorMessage && <p className="error-message">{errorMessage}</p>}
                      <form onSubmit={submit} className="login-form">
                        <div className="form-group">
                          <label htmlFor="email" className="form-label">
                            Tên đăng nhập
                          </label>
                          <input
                            type="text"
                            id="username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            placeholder="Enter your username"
                            className="form-input"
                          />
                        </div>
                        <div className="form-group">
                          <label htmlFor="password" className="form-label">
                            Mật khẩu
                          </label>
                          <input
                            type="password"
                            id="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            placeholder="Enter your password"
                            className="form-input"
                          />
                        </div>
                        <div>
                          <button type ="submit" className="submit-button">
                            Đăng nhập
                          </button>
                        </div>
                      </form>
                    </td>
                  </tr>
                </tbody> {/* End wrapper */}
              </table>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}

export default Login;
