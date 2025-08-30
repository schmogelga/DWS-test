package com.schmogel.isobar.application.dto.response;

import java.util.UUID;

public record BandaListaResponse(UUID id, String name, String image, long numPlays) {}
