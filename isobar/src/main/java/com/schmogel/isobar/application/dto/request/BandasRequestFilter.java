package com.schmogel.isobar.application.dto.request;

public record BandasRequestFilter(String nome, Ordenacao ordenacao) {
    public enum Ordenacao {
        NUM_PLAYS,
        NAME
    }
}
