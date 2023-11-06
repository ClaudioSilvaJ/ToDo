import './assets/App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from "./components/LoginPage";
import "./assets/dist/output.css"
import './assets/index.css';

function App() {
  return (
    <BrowserRouter>
        <Routes>
            <Route path="/login" element={<LoginPage />} />
        </Routes>
    </BrowserRouter>
  );
}

export default App;
