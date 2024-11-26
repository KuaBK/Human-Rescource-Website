import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    accountId: null,
    token: null,
    expired: null,
    authenticated: false,
    role: null
};

const userSlice = createSlice({
    name: "user",
    initialState,
    reducers: {
        login(state, action) {
            const { accountId, token, expired, authenticated, role } = action.payload;
                state.accountId = accountId;
                state.token = token;
                state.expired = expired;
                state.authenticated = authenticated;
                state.role = role;
        },
        logout(state) {
            state.accountId = null;
            state.token = null;
            state.expired = null;
            state.authenticated = false;
            state.role = null;
        },
    },
});

export const { login, logout } = userSlice.actions;

export default userSlice.reducer;
