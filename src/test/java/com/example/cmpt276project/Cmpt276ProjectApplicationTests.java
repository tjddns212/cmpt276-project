package com.example.cmpt276project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cmpt276project.models.Image;
import com.example.cmpt276project.models.ImageRepository;
import com.example.cmpt276project.models.User;
import com.example.cmpt276project.models.UserRepository;

@SpringBootTest
class Cmpt276ProjectApplicationTests {

	@Autowired
	UserRepository userRepo;

	@Autowired
	ImageRepository imageRepo;


	@Test
	void getUserAvatarTest() {

		List<User> users = userRepo.findByUid(8);
		User user = users.get(0);
		List<Image> images = imageRepo.findByUid(user.getAvatar());
		Image image = images.get(0);
		assertEquals("Screenshot 2023-07-05 224015.png", image.getName());
	}

}
