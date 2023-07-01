import React, {useState} from 'react';
import LoginForm from './LoginForm';
import SignUpForm from './SignUpForm';
import { styled } from 'styled-components';

const LoginAndSignUp = () => {
    const [userOnSignUpPage, setUserOnSignUpPage] = useState(true);
    return (
        <LoginAndSignUpWrapper>
          {userOnSignUpPage ? <SignUpForm/> : <LoginForm/> }
        </LoginAndSignUpWrapper>
    )
}
const LoginAndSignUpWrapper = styled.div`
    background-color: #FF4040;
    min-height:100vh;
`;
export default LoginAndSignUp;