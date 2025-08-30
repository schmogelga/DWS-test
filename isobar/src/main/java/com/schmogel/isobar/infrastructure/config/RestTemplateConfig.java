package com.schmogel.isobar.infrastructure.config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.schmogel.isobar.infrastructure.api.dto.Banda;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        var restTemplate = new RestTemplate(factory);

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        return new SimpleClientHttpRequestFactory();
    }

    @Bean
    public Cache<String, Banda> bandasNameCache() {
        return Caffeine.newBuilder().maximumSize(500).expireAfterWrite(10, TimeUnit.MINUTES).build();
    }

    @Bean
    public Cache<UUID, Banda> bandasIdCache() {
        return Caffeine.newBuilder().maximumSize(500).expireAfterWrite(60, TimeUnit.MINUTES).build();
    }
}
