package com.higor.qualificacao.model.dto.external.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Localidade {

    private List<LocalidadeResult> results;

}
