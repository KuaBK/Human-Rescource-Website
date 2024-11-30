import axios from "../utils/axiosCustomize";

const postLogin = (username, password) => {
    return axios.post(`/auth/login`, { username, password});
}

const postCreateNewAccount = (username, password) => {
    return axios.post(`/account/create`, {username, password});
}

const getAllAccount = () => {
    return axios.get(`/account/all`);
}

const getAccountById = (id) => {
    return axios.get(`/account?accountId=${id}`);
}

const putUpdateAccount = (id, password) => {
    return axios.put(`account/edit`, { id, password });
}

const deleteAccount = (id) => {
    return axios.delete(`accout/delete?accountId=${id}`);
}

const postCreateNewPersonel = (payload) => {
    return axios.post(`personnel/add`, payload, {
        headers: {
            "Content-Type": "application/json", // Optional: axios automatically sets this
        },
    });
}

const getAllPersonel = () => {
    return axios.get(`/personel/all`);
}

const getPersonelByCode = (code) => {
    return axios.get(`/personel?code=${code}`);
}

const deletePersonel = (code) => {
    return axios.delete(`/personnel/delete?code=${code}`);
}

const putUpdatePersonel = (code, payload) => {
    return axios.put(`/personnel/edit`, payload, { 
        params: { code: code },  // Pass the query parameter as 'code'
        headers: {
            "Content-Type": "application/json",  // Optional: Axios automatically sets this for JSON data
        },
    });
}

const postCreateNewDepartment = (payload) => {
    return axios.post(`department/create`, payload, {
        headers: {
            "Content-Type": "application/json",
        },
    });
}

const getAllDepartment = () => {
    return axios.get(`/department/all`);
}

const getPersonnelByAccountId = (accountId) => {
    return axios.get(`/personnel/account`, { 
        params: { accountId: accountId },  
    });
}

const getTaskByEmployeeCode = (code) => {
    return axios.get(`/task/employee`, { 
        params: { employeeId: code },  
    });
}

const postEmployeeCheckin = (code) => {
    return axios.post(`attendance/check-in`, null, { 
        params: { employeeId: code },  
    });
}

const postEmployeeCheckout = (code) => {
    return axios.post(`/attendance/check-out`, null, { 
        params: { employeeId: code },  
    });
}

const getEmployeeAttendance = (code) => {
    return axios.get(`/attendance/employee`, { 
        params: { employeeId: code },  
    });
}


export {
    postLogin, postCreateNewAccount, postCreateNewPersonel,
    postCreateNewDepartment,
    postEmployeeCheckin, postEmployeeCheckout,
    getAllAccount, getAccountById, getAllPersonel, getPersonelByCode,
    getAllDepartment, getPersonnelByAccountId,
    getTaskByEmployeeCode,
    getEmployeeAttendance, 
    putUpdateAccount,putUpdatePersonel,
    deleteAccount, deletePersonel,
}