package com.schmogel.isobar.infrastructure.api;

import com.schmogel.isobar.domain.exception.IntegracaoException;
import com.schmogel.isobar.domain.exception.NotFoundException;
import com.schmogel.isobar.domain.integration.BandaApiPort;

import java.net.URI;
import java.util.UUID;

import com.schmogel.isobar.infrastructure.api.dto.Banda;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BandaApiAdapter implements BandaApiPort {

    private final RestTemplate restTemplate;

    private static final String MENSAGEM_ERRO = "Erro ao buscar banda: ";
    private static final String MENSAGEM_ERRO_2 = "Banda não encontrada";

    @Value("${api-integration.bandas.url}")
    private String urlBandas;

    @Override
    public Banda obterBanda(UUID bandaId) {
        log.info("Consultando banda de id {}", bandaId);

        try {
            URI uri = getUri(urlBandas, bandaId.toString());
            ResponseEntity<Banda> response =
                    restTemplate.exchange(uri, HttpMethod.GET, null, Banda.class);

            log.info("Banda {} obtida com sucesso", bandaId);
            return response.getBody();

        } catch (HttpClientErrorException clientError) {
            if (clientError.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException(MENSAGEM_ERRO_2);
            }
            log.error(MENSAGEM_ERRO + "{} - {}", bandaId, clientError.getMessage());
            throw new IntegracaoException(MENSAGEM_ERRO + "{} - {}", bandaId, clientError.getMessage());

        } catch (HttpServerErrorException serverError) {
            log.error(MENSAGEM_ERRO + "{} - {}", bandaId, serverError.getMessage());
            throw new IntegracaoException("Serviço de bandas indisponível no momento");
        } catch (RestClientException e) {
            log.error(MENSAGEM_ERRO + "{} - {}", bandaId, e.getMessage());
            throw new IntegracaoException(MENSAGEM_ERRO + "{} - {}", bandaId, e.getMessage());
        }
    }

    private URI getUri(String url, String bandId) {
        return UriComponentsBuilder
                .fromUriString(url)
                .buildAndExpand(bandId)
                .toUri();
    }
}

