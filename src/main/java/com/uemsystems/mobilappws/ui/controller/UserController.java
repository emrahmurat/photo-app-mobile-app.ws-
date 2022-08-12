package com.uemsystems.mobilappws.ui.controller;

import com.uemsystems.mobilappws.service.UserService;
import com.uemsystems.mobilappws.ui.model.request.UserDetailsRequestModel;
import com.uemsystems.mobilappws.ui.model.response.UserRest;
import com.uemsystems.mobilappws.shared.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String getUser(){
        return "get user was called";
    }
    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel){
        UserRest returnValue = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetailsRequestModel,userDto);

        UserDto createdUser = this.userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser,returnValue);
        return returnValue;
    }

    @PutMapping
    public String updateUser(){
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser(){
        return "delete user was called";
    }
}
