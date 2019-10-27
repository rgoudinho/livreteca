package br.com.livreteca.model.mapper;

import br.com.livreteca.dto.UserDTO;
import br.com.livreteca.model.entity.User;

/**
 * Created by ronifabio on 01/05/2019.
 */
public class UserMapper {

    public static User toEntity(UserDTO dto){
        User entity = new User(dto.getLogin(), dto.getPwd(), dto.getEmail());
        return entity;
    }

    public static UserDTO toDTO(User entity){
        UserDTO dto = new UserDTO(entity.getLogin(), entity.getPwd(), entity.getEmail());
        return dto;
    }
}
