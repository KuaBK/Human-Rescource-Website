import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import './Login.css'; // Import the external CSS

function Login() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  async function submit(e) {
    e.preventDefault();
    try {
      const res = await axios.post("http://localhost:3001/", { email, password });
      if (res.data === "exist") {
        navigate("/home", { state: { id: email } });
      } else if (res.data === "notexist") {
        setErrorMessage("User has not signed up");
      }
    } catch (e) {
      setErrorMessage("Wrong details, please try again.");
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
                          type="email"
                          id="email"
                          value={email}
                          onChange={(e) => setEmail(e.target.value)}
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
                        <button type="submit" className="submit-button">
                          Đăng nhập
                        </button>
                      </div>
                    </form>
                    <div className="alternative">
                      <p className="alt-text">OR</p>
                      <Link to="/signup" className="signup-link">
                        Đăng ký
                      </Link>
                    </div>
                    <hr className="separator" />
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}

export default Login;
