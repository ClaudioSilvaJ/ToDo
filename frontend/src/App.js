import './assets/App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from "./views/commonUser/LoginPage";
import "./assets/dist/output.css"
import './assets/index.css';
import Home from "./views/home";

function App() {
  return (
    <BrowserRouter>
        <Routes>
            <Route path={"/"} element={<Home />} />
            <Route path="/login" element={<LoginPage />} />
        </Routes>
    </BrowserRouter>
  );
}

export default App;
