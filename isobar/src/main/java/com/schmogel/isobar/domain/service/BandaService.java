package com.schmogel.isobar.domain.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.schmogel.isobar.application.dto.response.BandaResponse;
import com.schmogel.isobar.application.service.BandaApplicationService;
import com.schmogel.isobar.domain.integration.BandaApiPort;
import com.schmogel.isobar.domain.mapper.BandaMapper;
import com.schmogel.isobar.infrastructure.api.dto.Banda;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BandaService implements BandaApplicationService {

    private final BandaApiPort bandaApi;
    private final BandaMapper bandaMapper = Mappers.getMapper(BandaMapper.class);
    private final Cache<String, Banda> bandasCache;

    @Override
    public BandaResponse obterBanda(UUID bandaId) {
        //@TODO: cachear retorno por ids
        return bandaMapper.toBandaResponse(bandaApi.obterBanda(bandaId));
    }

    public List<BandaResponse> listarBandas(String filtroNome) {
        carregarCacheSeVazio();

        return filtrarBandas(filtroNome)
                .stream()
                .map(bandaMapper::toBandaResponse)
                .toList();
    }

    private void carregarCacheSeVazio() {
        if (bandasCache.asMap().isEmpty()) {
            bandaApi.listarBandas()
                    .forEach(b -> bandasCache.put(b.getName(), b));
        }
    }

    private List<Banda> filtrarBandas(String filtroNome) {
        return bandasCache.asMap().values()
                .stream()
                .filter(b -> filtroNome == null || filtroNome.isBlank()
                        || b.getName().toLowerCase().contains(filtroNome.toLowerCase()))
                .toList();
    }
}
