package com.cognizant.trms.service;

import com.cognizant.trms.dto.mapper.UserMapper;
import com.cognizant.trms.dto.model.UserDTO;
import com.cognizant.trms.exception.EntityType;
import com.cognizant.trms.exception.ExceptionType;
import com.cognizant.trms.exception.TRMSException;
import com.cognizant.trms.model.user.Role;
import com.cognizant.trms.model.user.User;
import com.cognizant.trms.model.user.UserRoles;
import com.cognizant.trms.repository.RoleRepository;
import com.cognizant.trms.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

/*
    Author: Aravindan Dandapani
*/
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDTO signup(UserDTO userDto) {
        Role user_role;
       User user = userRepository.findByEmail(userDto.getEmail());
       if (user == null){
           if (userDto.isAdmin()) {
               user_role = roleRepository.findByRole(UserRoles.ADMIN.name());
           } else {
               user_role = roleRepository.findByRole(UserRoles.HIRINGMANAGER.name());
           }
           user = new User()
                   .setEmail(userDto.getEmail())
                   .setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()))
                   .setRoles(new HashSet<>(Arrays.asList(user_role)))
                   .setFirstName(userDto.getFirstName())
                   .setLastName(userDto.getLastName())
                   .setMobileNumber(userDto.getMobileNumber());
           return UserMapper.UserModelToUserDTO(userRepository.save(user));
       }
        throw exception(EntityType.USER, ExceptionType.DUPLICATE_ENTITY, userDto.getEmail());



    }

    /**
     * Search an existing user
     *
     * @param email
     * @return
     */
    @Override
    public UserDTO findUserByEmail(String email) {
        return null;
    }

    /**
     * Update profile of the user
     *
     * @param userDto
     * @return
     */
    @Override
    public UserDTO updateProfile(UserDTO userDto) {
        return null;
    }

    /**
     * Update password
     *
     * @param userDto
     * @param newPassword
     * @return
     */
    @Override
    public UserDTO changePassword(UserDTO userDto, String newPassword) {
        return null;
    }

    /**
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return TRMSException.throwException(entityType, exceptionType, args);
    }
}
