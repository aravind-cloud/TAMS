package com.cognizant.trms.dto.mapper;
/*
    Author: Aravindan Dandapani
*/

import com.cognizant.trms.dto.model.RoleDTO;
import com.cognizant.trms.dto.model.UserDTO;
import com.cognizant.trms.model.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static UserDTO UserModelToUserDTO(User user) {
        return new UserDTO()
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setMobileNumber(user.getMobileNumber())
                .setRoles(new HashSet<RoleDTO>(user
                        .getRoles()
                        .stream()
                        .map(role -> new ModelMapper().map(role, RoleDTO.class))
                        .collect(Collectors.toSet())));
    }

}
