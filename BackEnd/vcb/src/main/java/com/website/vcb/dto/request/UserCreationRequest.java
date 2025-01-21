package com.website.vcb.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest { // Định nghĩa cho request tạo user mới
    @Size(min = 3, max = 50, message = "USERNAME_INVALID")
    String username;
    @Size(min = 8, max = 50, message = "PASSWORD_INVALID")
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
}