package com.higor.qualificacao.model.dto.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalidadeGeometryLocation {

    @JsonProperty(value = "lng")
    private Double longitude;

    @JsonProperty(value = "lat")
    private Double latitude;

}
