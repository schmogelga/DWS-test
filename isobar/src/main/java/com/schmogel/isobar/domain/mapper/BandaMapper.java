package com.schmogel.isobar.domain.mapper;

import com.schmogel.isobar.application.dto.response.BandaDetalheResponse;
import com.schmogel.isobar.application.dto.response.BandaListaResponse;
import com.schmogel.isobar.infrastructure.api.dto.Banda;
import org.mapstruct.Mapper;

@Mapper
public interface BandaMapper {

    BandaDetalheResponse toBandaCompletaResponse(Banda banda);

    BandaListaResponse toBandaListaResponse(Banda banda);
}
