package com.schmogel.isobar.application.dto.response;

import java.util.List;

//TODO: paginação
public record BandasResponse(List<BandaListaResponse> bandas, int count) {
    public BandasResponse(List<BandaListaResponse> bandas) {
        this(bandas, bandas != null ? bandas.size() : 0);
    }
}
