package com.josiasmartins.spring_security_jwt_oauth2.controller.dto;

import java.util.List;

public record FeedDTO(
        List<FeedItemDTO> feedItens,
        int page,
        int pageSize,
        int totalPages,
        long totalElements
) {}
