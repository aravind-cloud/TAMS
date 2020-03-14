package com.cognizant.trms.service;

import com.cognizant.trms.dto.model.UserDTO;

public interface UserService {
    /**
     * Register a new user
     *
     * @param userDto
     * @return
     */
    UserDTO signup(UserDTO userDto);

    /**
     * Search an existing user
     *
     * @param email
     * @return
     */
    UserDTO findUserByEmail(String email);

    /**
     * Update profile of the user
     *
     * @param userDto
     * @return
     */
    UserDTO updateProfile(UserDTO userDto);

    /**
     * Update password
     *
     * @param newPassword
     * @return
     */
    UserDTO changePassword(UserDTO userDto, String newPassword);
}
