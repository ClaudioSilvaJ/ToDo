import React from 'react';
import ReactDOM from 'react-dom/client';
import './assets/index.css';
import "./assets/dist/output.css"
import './assets/App.css';
import {
  BrowserRouter, Navigate,
  Route,
  Routes
} from "react-router-dom";
import Home from "./views/home";
import LoginPage from "./views/commonUser/LoginPage";
import ContactUs from "./views/commonUser/contactUs";
import Users from "./Routes/Users";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path={"/"} element={<Home />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/contactUs" element={<ContactUs/>}/>
        <Route path="/user/*" element={<Users/>}/>
        <Route path="*" element={
          <Navigate to="/" replace/>
        } />
      </Routes>
    </BrowserRouter>
  </React.StrictMode>);

