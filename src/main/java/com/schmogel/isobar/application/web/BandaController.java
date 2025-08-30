package com.schmogel.isobar.application.web;

import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import com.schmogel.isobar.application.dto.request.BandasRequestFilter;
import com.schmogel.isobar.application.dto.response.BandaDetalheResponse;
import com.schmogel.isobar.application.dto.response.BandasResponse;
import com.schmogel.isobar.application.service.BandaApplicationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BandaController implements BandaApi {

    private final BandaApplicationService bandaService;

    @Override
    public BandaDetalheResponse obterBanda(UUID bandaId) {
        return bandaService.obterBanda(bandaId);
    }

    @Override
    public BandasResponse listarBandas(BandasRequestFilter filtro) {
        return bandaService.listarBandas(filtro);
    }
}
