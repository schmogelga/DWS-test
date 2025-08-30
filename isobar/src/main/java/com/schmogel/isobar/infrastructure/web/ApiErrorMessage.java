package com.schmogel.isobar.infrastructure.web;

import java.time.Instant;
import java.util.List;

public record ApiErrorMessage(
        int status,
        List<String> errors,
        String path,
        Instant timestamp
) {

    public ApiErrorMessage(int status, List<String> errors, String path) {
        this(status, errors, path, Instant.now());
    }

}
