package com.schmogel.isobar.domain.mapper;

import com.schmogel.isobar.application.dto.response.BandaResponse;
import com.schmogel.isobar.infrastructure.api.dto.Banda;
import org.mapstruct.Mapper;

@Mapper
public interface BandaMapper {

    BandaResponse toBandaResponse(Banda banda);
}
