package com.website.vcb.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse { // Trả về thông tin user
    String id;
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
}
