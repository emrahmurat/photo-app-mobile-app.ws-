package com.uemsystems.mobilappws.service;

import com.uemsystems.mobilappws.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);
}
