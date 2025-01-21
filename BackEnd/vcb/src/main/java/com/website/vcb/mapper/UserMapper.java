package com.website.vcb.mapper;

import com.website.vcb.dto.request.UserCreationRequest;
import com.website.vcb.dto.request.UserUpdateRequest;
import com.website.vcb.dto.response.UserResponse;
import com.website.vcb.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // Phương thức chuyển từ UserCreationRequest sang User
    User toUser(UserCreationRequest request);

    // Phương thức chuyển từ UserUpdateRequest sang User
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    //Chuyển user thành UserResponse
    UserResponse toUserResponse(User user);

    // Chuyển list user  sang list user response
    List<UserResponse> toUserResponse(List<User> users);
}
