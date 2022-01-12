package com.higor.qualificacao.validador;

import com.higor.qualificacao.model.entity.Endereco;

public class ValidadorEndereco {

    public static void validarDadosObrigatorios(Endereco endereco) {
        final GenericValidador validador = new GenericValidador();
        validador.add(endereco.getStreetName(), "Street Name");
        validador.add(endereco.getNumber(), "Number");
        validador.add(endereco.getNeighbourhood(), "Neighborhood");
        validador.add(endereco.getCity(), "City");
        validador.add(endereco.getState(), "State");
        validador.add(endereco.getCountry(), "Country");
        validador.add(endereco.getZipCode(), "Zip Code");

        validador.validate();
    }

}
