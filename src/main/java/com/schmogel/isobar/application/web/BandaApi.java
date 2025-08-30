package com.schmogel.isobar.application.web;

import java.util.UUID;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schmogel.isobar.application.dto.request.BandasRequestFilter;
import com.schmogel.isobar.application.dto.response.BandaDetalheResponse;
import com.schmogel.isobar.application.dto.response.BandasResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Bandas", description = "Operações de busca de bandas")
@RequestMapping(value = "/api/v1/bandas", produces = MediaType.APPLICATION_JSON_VALUE)
public interface BandaApi {

    @GetMapping("/{bandaId}")
    BandaDetalheResponse obterBanda(@PathVariable UUID bandaId);

    @GetMapping
    BandasResponse listarBandas(@ParameterObject BandasRequestFilter filtro);
}
