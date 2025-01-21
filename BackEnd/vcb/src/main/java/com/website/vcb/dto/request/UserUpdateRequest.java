package com.website.vcb.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest { // Định nghĩa cho request update tài khoản
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
}