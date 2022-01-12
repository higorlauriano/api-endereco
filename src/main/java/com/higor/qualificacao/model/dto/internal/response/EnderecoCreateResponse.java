package com.higor.qualificacao.model.dto.internal.response;

import lombok.Data;

@Data
public class EnderecoCreateResponse {

    private Long id;

    private String streetName;

    private Integer number;

    private String complement;

    private String neighbourhood;

    private String city;

    private String state;

    private String country;

    private String zipCode;

    private Double latitude;

    private Double longitude;

}
