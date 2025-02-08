package com.website.vcb.dto.response;

import com.website.vcb.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse { // Trả về thông tin user
    String id;
    String username;
    String firstName;
    String lastName;
    LocalDate dob;
    String roles;
}
