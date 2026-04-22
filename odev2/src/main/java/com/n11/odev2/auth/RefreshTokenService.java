package com.n11.odev2.auth;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RefreshTokenService {

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenDurationMs;

    private final Map<String, RefreshToken> refreshTokenStore = new ConcurrentHashMap<>();

    public RefreshToken createRefreshToken(String username) {
        String tokenValue = UUID.randomUUID().toString();
        Date expiryDate = new Date(System.currentTimeMillis() + refreshTokenDurationMs);

        RefreshToken refreshToken = new RefreshToken(tokenValue, username, expiryDate);
        refreshTokenStore.put(tokenValue, refreshToken);

        return refreshToken;
    }

    public RefreshToken verifyRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenStore.get(token);

        if (refreshToken == null) {
            throw new RuntimeException("Refresh token not found");
        }

        if (refreshToken.isExpired()) {
            refreshTokenStore.remove(token);
            throw new RuntimeException("Refresh token expired");
        }

        return refreshToken;
    }

    public void deleteByToken(String token) {
        refreshTokenStore.remove(token);
    }
}