package com.example.customer.dto;

import java.util.List;

public record ErrorDetail(
        String code,
        String message,
        List<String> details
) {}