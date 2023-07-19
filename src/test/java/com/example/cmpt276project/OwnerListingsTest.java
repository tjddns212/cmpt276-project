package com.example.cmpt276project;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import com.example.cmpt276project.models.Image;
import com.example.cmpt276project.models.Room;
import com.example.cmpt276project.models.RoomRepository;
import com.example.cmpt276project.models.User;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OwnerListingsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomRepository roomRepo;

    @Test
    public void testGetOwnerListings() throws Exception {
        Integer userId = 1;
        List<Room> expectedRooms = createExpectedRooms(); // Create a method to generate the expected rooms
        User mockedUser = new User("John", "Doe", "Jon", "M", "johndoe@gmail.com", "john123", 1, "Landlord");
        mockedUser.setUid(1);
        // Mock the behavior of the roomService to return the expectedRooms for the
        // specific user ID
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("session_user", mockedUser);
        when(roomRepo.findAll()).thenReturn(expectedRooms);

        // Perform the HTTP GET request to the endpoint with the user ID as a path
        // variable
        mockMvc.perform(MockMvcRequestBuilders.get("/ownerListings/{id}", userId).session(session))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("rooms/ownerListings"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("listings"))
                .andExpect(MockMvcResultMatchers.model().attribute("listings", expectedRooms));
    }

    // Helper method to create a list of expected rooms for testing
    private List<Room> createExpectedRooms() {
        List<Room> expectedRooms = new ArrayList<>();
        Room room1 = new Room("TestingRoom", "TestingAddress", "Vancouver", 50.5, "Testing house", "2023/09/11",
                "2024/09/11", 1);
        byte[] imageData = new byte[] { 0x11, 0x22, 0x33, 0x44, 0x55, 0x66 };

        // Instantiate a MockImage with sample data
        Image mockImage = new Image("example_image", "jpg", imageData);
        room1.setImage(mockImage);
        expectedRooms.add(room1);
        return expectedRooms;
    }
}