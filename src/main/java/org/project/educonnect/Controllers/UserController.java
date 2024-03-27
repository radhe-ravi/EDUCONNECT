package org.project.educonnect.Controllers;

import com.example.learningportal.learningportal.DTO.UserDTO;
import com.example.learningportal.learningportal.DTO.UserResponseDTO;
import com.example.learningportal.learningportal.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@PostMapping("/users")
	public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserDTO userDTO) {
		log.info("request body", userDTO);
		return new ResponseEntity<>(userService.addUser(userDTO), HttpStatus.CREATED);
	}

	@GetMapping("/users")
	public ResponseEntity<List<Map<String, Object>>> getUsers() {
		List<Map<String, Object>> users = userService.getUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);

	}
}
