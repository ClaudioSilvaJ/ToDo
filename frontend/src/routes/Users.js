import React from 'react';
import { Routes, Route } from "react-router-dom";
import Home from "../views/home";
import ProtectedRoute from "./ProtectedRoute";
import DashboardUser from "../views/commonUser/dashboardUser/DashboardUser"

const Users = () => {
  return (
      <Routes>
        <Route element={<ProtectedRoute/>}>
          <Route path="/dashboard" element={<DashboardUser/>}/>
        </Route>
      </Routes>
  );
}

export default Users;
