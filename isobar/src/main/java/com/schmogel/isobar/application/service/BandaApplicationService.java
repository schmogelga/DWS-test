package com.schmogel.isobar.application.service;

import com.schmogel.isobar.application.dto.response.BandaResponse;

import java.util.List;
import java.util.UUID;

public interface BandaApplicationService {

    BandaResponse obterBanda(UUID bandaId);
    List<BandaResponse> listarBandas(String nomeFiltro);

}
