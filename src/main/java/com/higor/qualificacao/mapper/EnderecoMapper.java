package com.higor.qualificacao.mapper;

import com.higor.qualificacao.model.dto.internal.request.EnderecoCreateRequest;
import com.higor.qualificacao.model.dto.internal.response.EnderecoCreateResponse;
import com.higor.qualificacao.model.entity.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco enderecoCreateRequestToEndereco(EnderecoCreateRequest enderecoCreateRequest);

    EnderecoCreateResponse enderecoToEnderecoCreateResponse(Endereco endereco);

}
