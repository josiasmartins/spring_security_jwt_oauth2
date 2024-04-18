package com.josiasmartins.spring_security_jwt_oauth2.controller.dto;

public record LoginResponse(String accessToken, Long expires) { }
