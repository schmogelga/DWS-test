package com.schmogel.isobar.infrastructure.api;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.schmogel.isobar.domain.exception.IntegracaoException;
import com.schmogel.isobar.domain.exception.NotFoundException;
import com.schmogel.isobar.domain.integration.BandaApiPort;
import com.schmogel.isobar.infrastructure.api.dto.Banda;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BandaApiAdapter implements BandaApiPort {

    private final RestTemplate restTemplate;

    private static final String MENSAGEM_ERRO = "Erro ao buscar banda: ";
    private static final String MENSAGEM_ERRO_2 = "Banda não encontrada";

    @Value("${api-integration.bandas.obter}")
    private String urlBandasObter;

    @Value("${api-integration.bandas.listar}")
    private String urlBandasListar;

    @Override
    public Banda obterBanda(UUID bandaId) {
        log.info("Consultando banda de id {}", bandaId);

        try {
            URI uri = UriComponentsBuilder.fromUriString(urlBandasObter).buildAndExpand(bandaId).toUri();

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

    @Override
    public List<Banda> listarBandas() {
        log.info("Listando todas as bandas");

        try {
            URI uri = UriComponentsBuilder.fromUriString(urlBandasListar).build().toUri();

            ResponseEntity<Banda[]> response =
                    restTemplate.exchange(uri, HttpMethod.GET, null, Banda[].class);

            log.info("Bandas listadas com sucesso");
            return Arrays.asList(response.getBody());

        } catch (HttpClientErrorException clientError) {
            if (clientError.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException("Nenhuma banda encontrada");
            }
            log.error(MENSAGEM_ERRO + "listar todas - {}", clientError.getMessage());
            throw new IntegracaoException(MENSAGEM_ERRO + "listar todas - {}", clientError.getMessage());

        } catch (HttpServerErrorException serverError) {
            log.error(MENSAGEM_ERRO + "listar todas - {}", serverError.getMessage());
            throw new IntegracaoException("Serviço de bandas indisponível no momento");

        } catch (RestClientException e) {
            log.error(MENSAGEM_ERRO + "listar todas - {}", e.getMessage());
            throw new IntegracaoException(MENSAGEM_ERRO + "listar todas - {}", e.getMessage());
        }
    }
}
