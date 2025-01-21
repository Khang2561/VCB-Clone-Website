package com.website.vcb.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IntrospectResponse { // Xác nhận đúng cho token gửi từ request (kiểm tra xem token này đúng hay không còn hiệu lực hay không)
    boolean valid;
}
