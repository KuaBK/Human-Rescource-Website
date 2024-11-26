import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { getPersonnelByAccountId } from "../../components/services/apiService";

export const fetchPersonnel = createAsyncThunk(
    "personnel/fetch",
    async (accountId, thunkAPI) => {
        const response = await getPersonnelByAccountId(accountId);
        return response?.data?.data; 
    }
)

const personnelSlice = createSlice({
    name: "personnel",
    initialState: {
      data: null,
      loading: false,
      error: null,
    },
    reducers: {},
    extraReducers: (builder) => {
      builder
        .addCase(fetchPersonnel.pending, (state) => {
          state.loading = true;
          state.error = null;
        })
        .addCase(fetchPersonnel.fulfilled, (state, action) => {
          state.loading = false;
          state.data = action.payload;
        })
        .addCase(fetchPersonnel.rejected, (state, action) => {
          state.loading = false;
          state.error = action.error.message;
        });
    },
});
  
export default personnelSlice.reducer;