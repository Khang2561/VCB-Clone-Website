package com.website.vcb.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse { // Trả về quyền đăng nhập và token
    String token;
    boolean authenticated;
}
