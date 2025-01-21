package com.website.vcb.controller;

import com.nimbusds.jose.JOSEException;
import com.website.vcb.dto.request.ApiResponese;
import com.website.vcb.dto.request.AuthenticationRequest;
import com.website.vcb.dto.request.IntrospectRequest;
import com.website.vcb.dto.response.AuthenticationResponse;
import com.website.vcb.dto.response.IntrospectResponse;
import com.website.vcb.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    // Biến kết nối với Authentication Service
    AuthenticationService authenticationService;

    // Post cho login để lấy token
    @PostMapping("/token")
    ApiResponese<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiResponese.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    // Post để xác thực token
    @PostMapping("/introspect")
    ApiResponese<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponese.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
}
