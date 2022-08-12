package com.uemsystems.mobilappws.service;

import com.uemsystems.mobilappws.io.entity.UserEntity;
import com.uemsystems.mobilappws.repository.UserRepository;
import com.uemsystems.mobilappws.shared.UserDto;
import com.uemsystems.mobilappws.shared.Utils;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    Utils utils;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDto createUser(UserDto user) {

        if(userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Record already exist");
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user,userEntity);

        String publicUserId = utils.generateUserId(30);

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(publicUserId);

        UserEntity storedByUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedByUserDetails,returnValue);

        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = new UserEntity();
        
        
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
