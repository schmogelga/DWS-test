package com.schmogel.isobar.application.service;

import java.util.UUID;

import com.schmogel.isobar.application.dto.request.BandasRequestFilter;
import com.schmogel.isobar.application.dto.response.BandaDetalheResponse;
import com.schmogel.isobar.application.dto.response.BandasResponse;

public interface BandaApplicationService {

    BandaDetalheResponse obterBanda(UUID bandaId);

    BandasResponse listarBandas(BandasRequestFilter filtro);
}
