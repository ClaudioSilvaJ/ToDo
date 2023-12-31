import React, {useEffect, useState} from 'react';
import PersonOutlineIcon from '@mui/icons-material/PersonOutline';
import HttpsOutlinedIcon from '@mui/icons-material/HttpsOutlined';
import {FcGoogle} from "react-icons/fc";
import {
  AiFillGithub,
  AiOutlineEyeInvisible, AiOutlineEye
} from "react-icons/ai";
import {Login, Register}
  from "../../services/Login/LoginService";
import TokenVerify from "../../services/Token/TokenVerify";

function LoginPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);

  const handleLogin = async () => {
    if(username === '' || password === ''){
      alert("Por favor preencha todos os campos!");
    } else {
      await Login(username, password);
    }
  };

  const handleRegister = async () => {
    if (username === '' || password === '') {
      alert("Por favor preencha todos os campos!");
    } else {
      await Register(username, password);
    }
  }

  useEffect(()=> {
    async function tokenValidated() {
      return await TokenVerify();
    }
    tokenValidated().then(result => {
      if(result){
        window.location.href = "/user/dashboard";
      }
    });
  })

  return (
    <div className="flex flex-row justify-center items-center h-screen font-oswald">
      <div className="w-11/12 flex-1 flex-col justify-center px-6 py-12 lg:px-8">
        <div className="sm:mx-auto sm:w-full sm:max-w-sm">
          <h1 className="mt-10 text-center text-8xl font-oswald font-light leading-9 tracking-tight text-gray-900">
            Welcome
          </h1>
          <h2 className="mt-7 text-center text-lg font-extralight">
            It's good to see you here again
          </h2>
        </div>

        <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
          <form method="POST">
            <div className="mt-2 relative">
              <input
                  placeholder="Username"
                  id="email"
                  name="email"
                  type="email"
                  autoComplete="email"
                  onChange={(e) => setUsername(e.target.value)}
                  required
                  className="block w-full bg-gray-200 rounded-2xl border-0 py-2 pl-9 pr-4 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-gray-700 sm:text-md sm:leading-9"
              />
              <div className="absolute inset-y-0 left-0 pl-2 flex items-center pointer-events-none">
                <PersonOutlineIcon className="h-5 w-5 text-gray-800"/>
              </div>
            </div>

            <div className="mt-2 relative">
              <input
                  placeholder="Password"
                  id="password"
                  name="password"
                  type={showPassword ? "text" : "password"}
                  autoComplete="current-password"
                  onChange={(e) => setPassword(e.target.value)}
                  required
                  className="block w-full bg-gray-200 rounded-2xl border-0 py-2 pl-9 pr-4 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-gray-700 sm:text-md sm:leading-9"
              />
              <div className="absolute inset-y-0 left-0 pl-2 flex items-center pointer-events-none">
                <HttpsOutlinedIcon className="h-5 w-5 text-gray-800"/>
              </div>
              <button
                  type="button"
                  onClick={() => setShowPassword(!showPassword)}
                  className="absolute inset-y-0 right-0 pr-4 flex items-center"
                  style={{fontSize: "1.2rem"}}
              >
                {showPassword ? <AiOutlineEyeInvisible/> : <AiOutlineEye/>}

              </button>
            </div>

            <div className="mt-2">
              <button
                  onClick={handleLogin}
                  className="mb-2 w-full bg-emerald-600 text-white rounded-2xl py-4 text-sm font-bold shadow-sm hover:bg-emerald-800 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black"
              >
                LOGIN
              </button>

              <button
                  onClick={handleRegister}
                  className="w-full bg-emerald-600 text-white rounded-2xl py-4 text-sm font-bold shadow-sm hover:bg-emerald-800 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black"
              >
                SIGN-IN
              </button>

            </div>
          </form>

          <div className="flex justify-center items-center">
            <h2 className="pr-1 font-bold">
              Login
            </h2>
            <h2 className="font-oswald pt-5 pb-5 text-center">
              with Others
            </h2>
          </div>

          <div className="flex flex-row justify-center items-center">
            <button
                className="w-full flex text-black rounded-2xl py-2 mr-3 text-sm font-semibold shadow-sm border-2 border-gray-800 hover:bg-gray-200 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black">
              <FcGoogle className="icon"/>
              <label className="pt-1 pr-1">Login with</label>
              <label className="pt-1 pr-3 font-bold">Google</label>
            </button>
            <button
                className="w-full flex text-black rounded-2xl py-2 text-sm font-semibold shadow-sm border-2 border-gray-800 hover:bg-gray-200 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black">
              <AiFillGithub className="icon"/>
              <label className="pt-1 pr-1">Login with</label>
              <label className="pt-1 pr-3 font-bold">GitHub</label>
            </button>
          </div>
          <div className="flex flex-row justify-center items-center pt-4">
            <button
                className="w-full flex text-black rounded-2xl py-2 mr-3 font-semibold text-sm shadow-sm border-2 border-gray-800 hover:bg-gray-200 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black">
              <img
                  className="icon"
                  alt="..."
                  src={require("../../assets/imgs/facebook-icon.png")}
              />
              <label className="pt-1 pr-1 temp-font">Login with</label>
              <label className="pt-1 pr-3 font-bold temp-font">Facebook</label>
            </button>
            <button
                className="w-full flex text-black rounded-2xl py-2 text-sm font-semibold shadow-sm border-2 border-gray-800 hover:bg-gray-200 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black">
              <img
                  className="icon"
                  alt="..."
                  src={require("../../assets/imgs/linkedin-icon.png")}
              />
              <label className="pt-1 pr-1">Login with</label>
              <label className="pt-1 pr-3 font-bold">Linkedin</label>
            </button>
          </div>
        </div>
      </div>
      <div className="flex justify-center w-1/2">
        <img
            className="w-11/12 pr-10"
            alt="..."
            src={require("../../assets/imgs/login-page-img.png")}
        />
      </div>
    </div>
  );
}

export default LoginPage;
