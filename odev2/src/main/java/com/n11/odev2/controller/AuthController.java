package com.n11.odev2.controller;

import com.n11.odev2.auth.RefreshTokenService;
import com.n11.odev2.auth.TokenManager;
import com.n11.odev2.auth.RefreshToken;
import com.n11.odev2.request.LoginRequest;
import com.n11.odev2.request.RefreshTokenRequest;
import com.n11.odev2.response.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenManager tokenManager;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public AuthController(TokenManager tokenManager,
                          AuthenticationManager authenticationManager,
                          RefreshTokenService refreshTokenService) {
        this.tokenManager = tokenManager;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        String accessToken = tokenManager.generateAccessToken(loginRequest.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(loginRequest.getUsername());

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken.getToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(request.getRefreshToken());

        String newAccessToken = tokenManager.generateAccessToken(refreshToken.getUsername());

        return ResponseEntity.ok(new AuthResponse(newAccessToken, refreshToken.getToken()));
    }
}