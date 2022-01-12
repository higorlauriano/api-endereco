package com.higor.qualificacao.model.dto.internal.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoCreateRequest {

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
