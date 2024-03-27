package org.project.educonnect.Services;

import org.project.educonnect.DTO.UserDTO;
import org.project.educonnect.DTO.UserResponseDTO;
import org.project.educonnect.Mappers.UserPopulator;
import org.project.educonnect.Models.Users;
import org.project.educonnect.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public UserResponseDTO addUser(UserDTO userDTO) {
		Users user = UserPopulator.INSTANCE.populateUser(userDTO);
		userRepository.save(user);
		UserResponseDTO userResponseDTO = UserPopulator.INSTANCE.userEntityToDTO(user);
		return userResponseDTO;
	}

	public Users getAuthorByIdAndRole(int authorId, String str) {
		Optional<Users> optionalUser = userRepository.findById(authorId);
		if (optionalUser.isPresent()) {
			Users user = optionalUser.get();
			if (user.getRole().equals(str)) {
				return user;
			}
		}

		return null;
	}

	public List<Map<String, Object>> getUsers() {
		return userRepository.findAllUsers();
	}
}
