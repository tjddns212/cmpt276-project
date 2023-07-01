import logo from "./logo.svg";
import React, { useEffect, useState } from "react";
import LoginAndSignUp from "./pages/LoginAndSignUp/LoginAndSignUp";
import styled from "styled-components";

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
    fetchTestValues(); // just testing that the calls to the server works properly
  }, []);
  return (
    <AppWrapper>
      <LoginAndSignUp />
    </AppWrapper>
  );
}
const AppWrapper = styled.div`
  width: 100%;
`;
export default App;
