package com.schmogel.isobar.domain.service;

import com.schmogel.isobar.application.dto.response.BandaResponse;
import com.schmogel.isobar.application.service.BandaApplicationService;
import com.schmogel.isobar.domain.integration.BandaApiPort;
import com.schmogel.isobar.domain.mapper.BandaMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BandaService implements BandaApplicationService {

    private final BandaApiPort bandaApi;
    private final BandaMapper bandaMapper = Mappers.getMapper(BandaMapper.class);

    @Override
    public BandaResponse obterBanda(UUID bandaId) {
        return bandaMapper.toBandaResponse(bandaApi.obterBanda(bandaId));
    }
}
