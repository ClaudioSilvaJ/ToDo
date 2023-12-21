import './assets/App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from "./views/commonUser/LoginPage";
import "./assets/dist/output.css"
import './assets/index.css';
import Home from "./views/home";
import ContactUs from "./views/commonUser/contactUs";
import {Navigation} from "@mui/icons-material";
import DashboardUser from "./views/commonUser/dashboardUser/DashboardUser";
import isLogin from "./isLogin";

function App() {
  return (
    <BrowserRouter>
        <Routes>
            <Route path={"/"} element={<Home />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/contactUs" element={<ContactUs/>}/>
            <Route path="/user/dashboard" element={isLogin() ? <DashboardUser/> : <Navigation to="/"/> }/>
        </Routes>
    </BrowserRouter>
  );
}

export default App;
