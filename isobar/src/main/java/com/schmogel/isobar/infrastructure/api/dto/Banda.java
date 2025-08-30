package com.schmogel.isobar.infrastructure.api.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Banda {
    private UUID id;
    private String name;
    private String image;
    private String genre;
    private String biography;
    private long numPlays;
    private List<UUID> albums;
}
