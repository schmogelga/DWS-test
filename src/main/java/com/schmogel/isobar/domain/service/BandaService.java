package com.schmogel.isobar.domain.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.github.benmanes.caffeine.cache.Cache;
import com.schmogel.isobar.application.dto.request.BandasRequestFilter;
import com.schmogel.isobar.application.dto.response.BandaDetalheResponse;
import com.schmogel.isobar.application.dto.response.BandaListaResponse;
import com.schmogel.isobar.application.service.BandaApplicationService;
import com.schmogel.isobar.domain.exception.NotFoundException;
import com.schmogel.isobar.domain.integration.BandaApiPort;
import com.schmogel.isobar.domain.mapper.BandaMapper;
import com.schmogel.isobar.infrastructure.api.dto.Banda;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

@Service
@RequiredArgsConstructor
public class BandaService implements BandaApplicationService {

    private final BandaApiPort bandaApi;
    private final BandaMapper bandaMapper = Mappers.getMapper(BandaMapper.class);
    private final Cache<String, Banda> bandasNameCache;
    private final Cache<UUID, Banda> bandasIdCache;

    @Override
    public BandaDetalheResponse obterBanda(UUID bandaId) {
        Banda banda =
                Optional.ofNullable(bandasIdCache.get(bandaId, bandaApi::obterBanda))
                        .orElseThrow(
                                () ->
                                        new NotFoundException(
                                                String.format("Banda não encontrada para o id: %s", bandaId)));
        return bandaMapper.toBandaCompletaResponse(banda);
    }

    public List<BandaListaResponse> listarBandas(BandasRequestFilter filtro) {
        carregarCache();

        return filtrarBandas(filtro.nome()).stream()
                .sorted(getComparator(filtro.ordenacao()))
                .map(bandaMapper::toBandaListaResponse)
                .toList();
    }

    private void carregarCache() {
        if (bandasNameCache.asMap().isEmpty()) {
            bandaApi
                    .listarBandas()
                    .forEach(
                            b -> {
                                bandasNameCache.put(b.getName(), b);
                                bandasIdCache.put(b.getId(), b);
                            });
        }
    }

    private List<Banda> filtrarBandas(String filtroNome) {
        return bandasNameCache.asMap().values().stream()
                .filter(
                        b ->
                                filtroNome == null
                                        || filtroNome.isBlank()
                                        || b.getName().toLowerCase().contains(filtroNome.toLowerCase()))
                .toList();
    }

    private Comparator<Banda> getComparator(BandasRequestFilter.Ordenacao ordenacao) {
        if (ordenacao == null) {
            return Comparator.comparingLong(Banda::getNumPlays)
                    .reversed()
                    .thenComparing(Banda::getName, String.CASE_INSENSITIVE_ORDER);
        }

        return switch (ordenacao) {
            case NAME -> Comparator.comparing(Banda::getName, String.CASE_INSENSITIVE_ORDER);
            case NUM_PLAYS ->
                    Comparator.comparingLong(Banda::getNumPlays)
                            .reversed()
                            .thenComparing(Banda::getName, String.CASE_INSENSITIVE_ORDER);
        };
    }
}
