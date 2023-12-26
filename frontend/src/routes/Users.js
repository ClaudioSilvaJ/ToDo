import React from 'react';
import { Routes, Route } from "react-router-dom";
import Home from "../views/home";
import ProtectedRoute from "./ProtectedRoute";

const Users = () => {
  return (
      <Routes>
        <Route element={<ProtectedRoute/>}>
          <Route path="/dashboard" element={<Home/>}/>
        </Route>
      </Routes>
  );
}

export default Users;
