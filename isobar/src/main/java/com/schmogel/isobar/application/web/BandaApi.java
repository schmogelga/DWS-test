package com.schmogel.isobar.application.web;

import com.schmogel.isobar.application.dto.response.BandaResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Tag(name = "Bandas", description = "Operações de busca de bandas")
@RequestMapping(value = "/api/v1/bandas", produces = MediaType.APPLICATION_JSON_VALUE)
public interface BandaApi {

    @GetMapping("/{bandaId}")
    BandaResponse obterBanda(@PathVariable UUID bandaId);
}
