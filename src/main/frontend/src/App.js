import logo from "./logo.svg";
import "./App.css";
import React, { useEffect, useState } from "react";

function App() {
  const [testValues, setTestValues] = useState([]);
  const fetchTestValues = async () => {
    const response = await fetch("/testing", {
      headers: {
        accepts: "application/json",
      },
    });
    console.log(response);
    if (!response.ok) {
      throw new Error("data couldn't be fetched");
    } else {
      const values = await response.json();
      console.log(values);
      setTestValues(values);
    }
  };
  useEffect(() => {
    fetchTestValues();
  }, []);
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
        {testValues.map((testVal) => {
          return <div>{testVal}</div>;
        })}
      </header>
    </div>
  );
}

export default App;
