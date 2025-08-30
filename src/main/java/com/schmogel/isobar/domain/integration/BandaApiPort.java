package com.schmogel.isobar.domain.integration;

import java.util.List;
import java.util.UUID;

import com.schmogel.isobar.infrastructure.api.dto.Banda;

public interface BandaApiPort {

    Banda obterBanda(UUID bandaId);

    List<Banda> listarBandas();
}
