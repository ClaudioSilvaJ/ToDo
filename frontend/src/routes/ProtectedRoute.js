import {Navigate, Outlet} from "react-router-dom";
import React, {useEffect} from "react";
import TokenVerify from "../services/Token/TokenVerify";


const ProtectedRoute = () => {
  const [tokenValied, setTokenValied] = React.useState(null);
  useEffect(() => {
    async function tokenValidated() {
      return await TokenVerify();
    }
    tokenValidated().then(result => setTokenValied(result));
  }, []);
  if(tokenValied === null){
    return null;
  }
  return (
    tokenValied ? <Outlet /> : <Navigate to="/login" replace/>
  );
}
export default ProtectedRoute;

