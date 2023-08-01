package com.example.cmpt276project;

import com.example.cmpt276project.controllers.PostManagementController;
import com.example.cmpt276project.models.Room;
import com.example.cmpt276project.models.RoomRepository;
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
public class PostManagementControllerTests {

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private Model model;
    @Mock
    private HttpSession httpSession;
    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private PostManagementController postManagementController;

    @Test
    public void getPostsWithoutUserAccount(){
        given(httpSession.getAttribute("session_user")).willReturn(null);

        String returnValue = postManagementController.getPosts(model, httpSession);

        assert(returnValue.equals("redirect:/error/404.html"));
    }

    @Test
    public void getPostsWithStudentAccount(){
        User user = new User();
        user.setAccountType("Student");

        given(httpSession.getAttribute("session_user")).willReturn(user);

        String returnValue = postManagementController.getPosts(model, httpSession);

        assert(returnValue.equals("redirect:/error/404.html"));
    }

    @Test
    public void getPostsWithLandlordAccount(){
        User user = new User();
        user.setAccountType("Landlord");

        given(httpSession.getAttribute("session_user")).willReturn(user);

        String returnValue = postManagementController.getPosts(model, httpSession);

        assert(returnValue.equals("redirect:/error/404.html"));
    }

    @Test
    public void getPostsWithAdminAccount(){
        User user = new User();
        user.setAccountType("Admin");
        List<Room> rooms = new ArrayList<>();

        given(httpSession.getAttribute("session_user")).willReturn(user);
        given(roomRepository.findAll()).willReturn(rooms);

        String returnValue = postManagementController.getPosts(model, httpSession);

        verify(model).addAttribute("rooms", rooms);

        assert(returnValue.equals("post-management.html"));
    }

    @Test
    public void deletePostWithoutUserAccount(){
        given(httpSession.getAttribute("session_user")).willReturn(null);

        String returnValue = postManagementController.deletePost(1, httpSession, redirectAttributes);

        assert(returnValue.equals("redirect:/error/404.html"));
    }

    @Test
    public void deletePostWithStudentAccount(){
        User user = new User();
        user.setAccountType("Student");

        given(httpSession.getAttribute("session_user")).willReturn(user);

        String returnValue = postManagementController.deletePost(1, httpSession, redirectAttributes);

        assert(returnValue.equals("redirect:/error/404.html"));
    }

    @Test
    public void deletePostWithLandlordAccount(){
        User user = new User();
        user.setAccountType("Landlord");

        given(httpSession.getAttribute("session_user")).willReturn(user);

        String returnValue = postManagementController.deletePost(1, httpSession, redirectAttributes);

        assert(returnValue.equals("redirect:/error/404.html"));
    }

    @Test
    public void deletePostWithAdminAccount(){
        User user = new User();
        user.setAccountType("Admin");

        given(httpSession.getAttribute("session_user")).willReturn(user);

        String returnValue = postManagementController.deletePost(1, httpSession, redirectAttributes);

        verify(roomRepository).deleteById(1);

        assert(returnValue.equals("redirect:/post-management"));
    }
}
