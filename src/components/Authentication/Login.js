import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { postLogin } from "../services/apiService";
import { useDispatch } from "react-redux";
import { login } from "../../store/slices/user-slices";

import './Login.css'; 

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const submit = async (e) => {
      e.preventDefault(); setErrorMessage("");
      try {
        const response = await postLogin(username, password);
        if (response && response.data){
          const { accountId, token, expired, authenticated, role } = response.data?.result;
          console.log(">>>>> ", response.data.result);
          dispatch(
            login({
              accountId,
              token,
              expired,
              authenticated,
              role
            })
          );
          console.log("role >>>", role);
          if (role === "EMPLOYEE") {
            navigate("/login/employee");
          } else if (role === "MANAGER") {
            navigate("/login/manager");
          } else {
            navigate("/login/admin")
          }
        }        
      } catch(e) {
        setErrorMessage("Login failed. Please try again.");
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
                            required
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
                            required
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
                </tbody> 
              </table>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}

export default Login;
