package com.higor.qualificacao.service;

import com.higor.qualificacao.exception.CustomRuntimeException;
import com.higor.qualificacao.model.dto.external.response.Localidade;
import com.higor.qualificacao.model.dto.external.response.LocalidadeGeometryLocation;
import com.higor.qualificacao.model.entity.Endereco;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocalidadeService {

    private static final String URL = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String KEY = "AIzaSyCj0cY2yEvVfYhAaTz3-P2MW-YRKmhz5Uw";

    private String getRequestUrl(final String enderecoBusca) {
        return URL + "?address=" + enderecoBusca + "&key="+ KEY;
    }

    private Localidade doRequest(final String enderecoBusca) {
        try {
            final ResponseEntity<Localidade> response = new RestTemplate().getForEntity(
                    getRequestUrl(enderecoBusca),
                    Localidade.class
            );

            return response.getBody();
        } catch (Exception e) {
            throw new CustomRuntimeException("Erro ao consultar Coordenadas Geograficas do endereco");
        }
    }

    public LocalidadeGeometryLocation getCoordenadasGeograficas(final Endereco endereco) {

        final String enderecoFormatted = getEnderecoFormatted(endereco);

        final Localidade localidade = doRequest(enderecoFormatted);

        if (localidade.getResults() != null
                && !localidade.getResults().isEmpty()) {
            return localidade.getResults()
                    .iterator()
                    .next()
                    .getGeometry()
                    .getLocation();
        }

        throw new CustomRuntimeException("Nao foi possivel obter as Coordenadas Geograficas do Endereco");
    }

    private String getEnderecoFormatted(final Endereco endereco) {

        final StringBuilder sb = new StringBuilder();

        if (endereco.getCountry().equalsIgnoreCase("BR")
                || endereco.getCountry().equalsIgnoreCase("BRASIL")
                || endereco.getCountry().equalsIgnoreCase("BRAZIL")) {
            sb.append(endereco.getStreetName()).append(", ");
            sb.append(endereco.getNumber()).append(" - ");
            sb.append(endereco.getNeighbourhood()).append(", ");
            sb.append(endereco.getCity()).append(" - ");
            sb.append(endereco.getState()).append(", ");
            sb.append(endereco.getZipCode()).append(", ");
            sb.append(endereco.getCountry());

        } else {
            sb.append(endereco.getNumber()).append(" ");
            sb.append(endereco.getStreetName()).append(", ");
            sb.append(endereco.getCity()).append(", ");
            sb.append(endereco.getState()).append(" ");
            sb.append(endereco.getZipCode()).append(", ");
            sb.append(endereco.getCountry());
        }

        return sb.toString();
    }

}
