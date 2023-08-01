package com.example.cmpt276project;

import com.example.cmpt276project.controllers.AccountManagementController;
import com.example.cmpt276project.models.User;
import com.example.cmpt276project.models.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountManagementControllerTests {

    @Mock
    private UserRepository userRepository;
    @Mock
    private Model model;
    @Mock
    private HttpSession httpSession;
    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private AccountManagementController accountManagementController;

    @Test
    public void getAccountManagementWithoutUserAccount(){
        given(httpSession.getAttribute("session_user")).willReturn(null);

        String returnValue = accountManagementController.getAccountManagement(model, httpSession);

        assert(returnValue.equals("redirect:/error/404.html"));
    }

    @Test
    public void getAccountManagementWithStudentAccount(){
        User user = new User();
        user.setAccountType("Student");

        given(httpSession.getAttribute("session_user")).willReturn(user);

        String returnValue = accountManagementController.getAccountManagement(model, httpSession);

        assert(returnValue.equals("redirect:/error/404.html"));
    }

    @Test
    public void getAccountManagementWithLandlordAccount(){
        User user = new User();
        user.setAccountType("Landlord");

        given(httpSession.getAttribute("session_user")).willReturn(user);

        String returnValue = accountManagementController.getAccountManagement(model, httpSession);

        assert(returnValue.equals("redirect:/error/404.html"));
    }

    @Test
    public void getAccountManagementWithAdminAccount(){
        User user = new User();
        user.setAccountType("Admin");
        List<User> users = new ArrayList<>();

        given(httpSession.getAttribute("session_user")).willReturn(user);
        given(userRepository.findAll()).willReturn(users);

        String returnValue = accountManagementController.getAccountManagement(model, httpSession);

        verify(model).addAttribute("users", users);

        assert(returnValue.equals("account-management.html"));
    }

    @Test
    public void postDeleteAccountWithoutUserAccount(){
        given(httpSession.getAttribute("session_user")).willReturn(null);

        String returnValue = accountManagementController.postDeleteAccount(1, httpSession, redirectAttributes);

        assert(returnValue.equals("redirect:/error/404.html"));
    }

    @Test
    public void postDeleteAccountWithStudentAccount(){
        User user = new User();
        user.setAccountType("Student");

        given(httpSession.getAttribute("session_user")).willReturn(user);

        String returnValue = accountManagementController.postDeleteAccount(1, httpSession, redirectAttributes);

        assert(returnValue.equals("redirect:/error/404.html"));
    }

    @Test
    public void postDeleteAccountWithLandlordAccount(){
        User user = new User();
        user.setAccountType("Landlord");

        given(httpSession.getAttribute("session_user")).willReturn(user);

        String returnValue = accountManagementController.postDeleteAccount(1, httpSession, redirectAttributes);

        assert(returnValue.equals("redirect:/error/404.html"));
    }

    @Test
    public void postDeleteAccountWithAdminAccountDeleteSameAccount(){
        User user = new User();
        user.setAccountType("Admin");
        user.setUid(1);

        given(httpSession.getAttribute("session_user")).willReturn(user);

        String returnValue = accountManagementController.postDeleteAccount(1, httpSession, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute("message", "Unable to delete itself account");

        assert(returnValue.equals("redirect:/account-management"));
    }

    @Test
    public void postDeleteAccountWithAdminAccountDeleteAnotherAccount(){
        User user = new User();
        user.setAccountType("Admin");
        user.setUid(1);

        given(httpSession.getAttribute("session_user")).willReturn(user);

        String returnValue = accountManagementController.postDeleteAccount(2, httpSession, redirectAttributes);

        verify(userRepository).deleteById(2);

        assert(returnValue.equals("redirect:/account-management"));
    }
}
