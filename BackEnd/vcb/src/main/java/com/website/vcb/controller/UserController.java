package com.website.vcb.controller;

import com.website.vcb.dto.request.UserCreationRequest;
import com.website.vcb.dto.request.UserUpdateRequest;
import com.website.vcb.dto.response.UserResponse;
import com.website.vcb.entity.User;
import com.website.vcb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    // Biến kết nối với user service
    @Autowired
    private UserService userService;

    // Tạo user mới
    @PostMapping
    UserResponse createUser(@RequestBody @Valid UserCreationRequest request) {
        return userService.createUser(request);
    }

    // Lấy danh sách user
    @GetMapping
    List<UserResponse> getUsers(){
        return userService.getUserList();
    }

    //Lấy thông tin user thông qua id
    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") String userId){
        return userService.getUserById(userId);
    }

    //Update thông tin cho user
    @PatchMapping("/{userId}")
    UserResponse updateUser(@RequestBody UserUpdateRequest request, @PathVariable("userId") String userId){
        return userService.updateUser(request,userId);
    }

    //Xóa thông tin user
    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return "User deleted successfully";
    }
}