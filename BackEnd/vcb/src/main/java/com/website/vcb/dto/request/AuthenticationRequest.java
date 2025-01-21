package com.website.vcb.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest { // Định nghĩa cho giá trị đăng nhập trả lên server
    String username;
    String password;
}
