package com.schmogel.isobar.application.dto.response;

import java.util.List;
import java.util.UUID;

public record BandaResponse(
         UUID id,
         String name,
         String image,
         String genre,
         String biography,
         long numPlays,
         List<UUID>albums
) {
}
