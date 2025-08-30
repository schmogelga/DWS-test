package com.schmogel.isobar.application.web;

import com.schmogel.isobar.application.dto.response.BandaResponse;
import com.schmogel.isobar.application.service.BandaApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BandaController implements BandaApi {

    private final BandaApplicationService bandaService;

    @Override
    public BandaResponse obterBanda(UUID bandaId) {
        return bandaService.obterBanda(bandaId);
    }
}
