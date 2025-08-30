package com.schmogel.isobar.application.service;

import java.util.List;
import java.util.UUID;

import com.schmogel.isobar.application.dto.request.BandasRequestFilter;
import com.schmogel.isobar.application.dto.response.BandaDetalheResponse;
import com.schmogel.isobar.application.dto.response.BandaListaResponse;

public interface BandaApplicationService {

    BandaDetalheResponse obterBanda(UUID bandaId);

    List<BandaListaResponse> listarBandas(BandasRequestFilter filtro);
}
