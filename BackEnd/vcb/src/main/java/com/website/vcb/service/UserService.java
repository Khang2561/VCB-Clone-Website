package com.website.vcb.service;

import com.website.vcb.dto.request.UserCreationRequest;
import com.website.vcb.dto.request.UserUpdateRequest;
import com.website.vcb.dto.response.UserResponse;
import com.website.vcb.entity.User;
import com.website.vcb.enums.Role;
import com.website.vcb.exception.ErrorCode;
import com.website.vcb.mapper.UserMapper;
import com.website.vcb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Tạo user mới
    public UserResponse createUser(UserCreationRequest request){
        //Check tài khoảng đã tồn tại hay chưa
        if(userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException(ErrorCode.USER_EXISTED.getMessage());
        }
        //Mã hóa mật khẩu
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        // Set role cho user
        String roles = Role.USER.name();
        user.setRoles(roles);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    // Lấy toàn bộ danh sách user
    public List<UserResponse> getUserList(){
        return userMapper.toUserResponse(userRepository.findAll());
    }

    // Lấy thông tin user theo Id
    public UserResponse getUserById(String id){
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found")));
    }

    //Update thông tin user
    public  UserResponse updateUser(UserUpdateRequest request, String userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found"));
        userMapper.updateUser(user,request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    // Xóa tài khoảng
    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}
