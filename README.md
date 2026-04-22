# JWT Authentication with Refresh Token

This project is a Spring Boot application that demonstrates JWT-based authentication with Refresh Token architecture.

## Purpose
The main goal of this project is to build a secure and sustainable authentication system by using:
- Access Token
- Refresh Token
- Token renewal mechanism

## Features
- User login with username and password
- JWT-based authentication
- Access Token generation
- Refresh Token generation
- Refresh endpoint for renewing access tokens
- Protected API endpoint example

## Technologies
- Java
- Spring Boot
- Spring Security
- JWT
- Maven

## Endpoints
- `POST /auth/login` → login and get access token + refresh token
- `POST /auth/refresh` → generate a new access token using refresh token
- `GET /message` → protected endpoint

## Example Flow
1. User logs in with username and password
2. System returns access token and refresh token
3. Access token is used to access protected endpoints
4. When access token expires, refresh token is used to get a new one

## Notes
This project was created to understand not only basic JWT usage, but also how Refresh Token architecture works in real-world applications.
