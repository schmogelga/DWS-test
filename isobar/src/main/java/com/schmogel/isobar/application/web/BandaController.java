package com.schmogel.isobar.application.web;

import com.schmogel.isobar.application.dto.response.BandaResponse;
import com.schmogel.isobar.domain.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BandaController implements BandaApi{


    @Override
    public BandaResponse obterBanda(UUID bandaId) {
        throw new NotFoundException("não implementado");
    }
}
