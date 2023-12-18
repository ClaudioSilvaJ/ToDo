import React, { useState } from 'react';

function Home() {
  const redirect = () => {
    window.location.href = '/login';
  }


  return (
    <div
      className="flex-column justify-center items-center h-screen font-oswald">
      <div>
        <label className="text-5xl pr-5">Home page</label>
      </div>
      <div>
        <button
          className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
          onClick={redirect}>
          Login
        </button>
      </div>


    </div>
  );
}

export default Home;
