package com.website.vcb.controller;

import com.website.vcb.dto.request.ApiResponese;
import com.website.vcb.dto.request.UserCreationRequest;
import com.website.vcb.dto.request.UserUpdateRequest;
import com.website.vcb.dto.response.UserResponse;
import com.website.vcb.entity.User;
import com.website.vcb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/users")
public class UserController {
    // Biến kết nối với user service
    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    // Tạo user mới
    @PostMapping
    ApiResponese<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        UserResponse userResponse = userService.createUser(request);
        return ApiResponese.<UserResponse>builder()
                .result(userResponse)
                .build();
    }

    // Lấy danh sách user
    @GetMapping
    ApiResponese<List<UserResponse>> getUsers(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}",authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponese.<List<UserResponse>>builder()
                .result(userService.getUserList())
                .build();
    }

    //Lấy thông tin user thông qua id
    @GetMapping("/{userId}")
    ApiResponese<UserResponse> getUser(@PathVariable("userId") String userId){
        UserResponse userResponse = userService.getUserById(userId);
        return ApiResponese.<UserResponse>builder()
                .result(userResponse)
                .build();
    }

    //Update thông tin cho user
   @PatchMapping("/{userId}")
   ApiResponese<UserResponse> updateUser(@RequestBody UserUpdateRequest request, @PathVariable("userId") String userId){
        UserResponse userResponse = userService.updateUser(request,userId);
       return ApiResponese.<UserResponse>builder()
                .result(userResponse)
                .build();
   }

    //Xóa thông tin user
    @DeleteMapping("/{userId}")
    ApiResponese<String> deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return ApiResponese.<String>builder()
                .result("User deleted successfully")
                .build();
    }

    // Lấy thông tin user chính mình
    @GetMapping("/myInfo")
    ApiResponese<UserResponse> getMyInfo(){
        return ApiResponese.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }
}