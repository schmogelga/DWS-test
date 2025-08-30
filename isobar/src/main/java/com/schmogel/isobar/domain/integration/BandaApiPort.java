package com.schmogel.isobar.domain.integration;

import com.schmogel.isobar.infrastructure.api.dto.Banda;

import java.util.List;
import java.util.UUID;

public interface BandaApiPort {

    Banda obterBanda(UUID bandaId);

    List<Banda> listarBandas();
}
