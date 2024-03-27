package org.project.educonnect.Mappers;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.project.educonnect.DTO.UserDTO;
import org.project.educonnect.DTO.UserResponseDTO;
import org.project.educonnect.Models.Users;

@Mapper
public interface UserPopulator {
	UserPopulator INSTANCE = Mappers.getMapper(UserPopulator.class);

	Users populateUser(UserDTO userDTO);

	UserResponseDTO userEntityToDTO(Users user);
}
