import { useParams } from "react-router-dom";
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
    return axios.post(`personel/add`, payload, {
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
    return axios.delete(`/personel/delete?code=${code}`);
}

const putUpdatePersonel = (personelCode, payload) => {
    return axios.put(`/personel/edit`, payload, { 
        params: { code: personelCode },  // Pass the query parameter as 'code'
        headers: {
            "Content-Type": "application/json",  // Optional: Axios automatically sets this for JSON data
        },
    });
}







export {
    postLogin, postCreateNewAccount, postCreateNewPersonel,
    getAllAccount, getAccountById, getAllPersonel, getPersonelByCode,
    putUpdateAccount,putUpdatePersonel,
    deleteAccount, deletePersonel,
}