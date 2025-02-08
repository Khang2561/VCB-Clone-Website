package com.website.vcb.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.website.vcb.dto.request.AuthenticationRequest;
import com.website.vcb.dto.request.IntrospectRequest;
import com.website.vcb.dto.response.AuthenticationResponse;
import com.website.vcb.dto.response.IntrospectResponse;
import com.website.vcb.exception.AppException;
import com.website.vcb.exception.ErrorCode;
import com.website.vcb.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    UserRepository userRepository;

    @NonFinal
    @Value("${app.security.signer-key}")
    protected String signerKey;

    // Xác nhận đã đăng nhập thành công hay chưa
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Kiểm tra tài khoảng có tồn tại hay không
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        // Mã hóa tài khoảng
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        // Kiểm tra mật khẩu
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        // Trả về lỗi nếu như tài khoảng không đăng nhập được
        if (!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        // Tạo token
        var token = generateToken(request.getUsername());
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    // Function tạo token
    private String generateToken(String username) {
        // Hàm hash tạo token
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        // Setting cho token
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username) // username
                .issuer("vcb.com") // nguồn
                .issueTime(new Date()) // Thời gian phát hành
                .expirationTime(Date.from(Instant.now().plus(1, ChronoUnit.HOURS))) // Thời gian hết hạn
                .claim("customClaim", "Custom").build();
        // Tạo token
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    // Kiểm tra tính hợp lệ của Token
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        // Tạo đối tượng JWSVerifier để xác minh token
        JWSVerifier verifier = new MACVerifier(signerKey.getBytes());
        // Phân tích token
        SignedJWT signedJWT = SignedJWT.parse(token);
        // Lấy thời gian hết hạn của token
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        // Xác minh token và kiểm tra expiration time
        boolean valid = expiryTime != null && expiryTime.after(new Date()) && signedJWT.verify(verifier);
        // Trả về kết quả
        return IntrospectResponse.builder()
                .valid(valid)
                .build();
    }
}
